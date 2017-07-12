package com.nimfa.nimfaapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nimfa.nimfaapp.databinding.NavHeaderMainBinding;
import com.nimfa.nimfaapp.fragments.CryptoBagFragment;
import com.nimfa.nimfaapp.fragments.ExchangeFragment;
import com.nimfa.nimfaapp.fragments.GetLoanFragment;
import com.nimfa.nimfaapp.fragments.ICOFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private boolean backArrowShowed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        ((TextView) headerLayout.findViewById(R.id.nav_user_mail)).setText(MyApp.Instance.getUserEmail());
        displayFragment(new GetLoanFragment(), true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void setSubTitle(String title){
        toolbar.setSubtitle(title);
    }
    public void setSubTitle(int title){
        toolbar.setSubtitle(title);
    }

    /**
     * Отобразить фрагмент в контейнер. Если {@code if(first == true)},
     * не кладем в BackStack для корректного пехода назад, тк если это сделать при первой транзации
     * при вызове {@code  super.OnBackPressed()} будет пустой экран
     *
     * @param fragment {@link Fragment} новый фрагмент
     * @param first    флаг, показывающий первая транзакция или нет
     */
    public void displayFragment(Fragment fragment, boolean first) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_view, fragment);
            if (!first) {
                fragmentTransaction.addToBackStack(null);
            }
            fragmentTransaction.commit();
        }
    }

    public void updateToolbarTitle(int title) {
        getSupportActionBar().setTitle(title);
    }

    public void updateToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_get_loan) {
            displayFragment(new GetLoanFragment(), false);
        } else if (id == R.id.nav_portfolio) {
            displayFragment(new CryptoBagFragment(), false);
        } else if (id == R.id.nav_exchange) {
            displayFragment(new ExchangeFragment(), false);
        } else if (id == R.id.nav_ico) {
            displayFragment(new ICOFragment(), false);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int getFragmentCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount() - 1);
    }

    public void replaceFragmentAndInimHamburger(Fragment fragment) {
        showBackArrow(true);
        displayFragment(fragment, false);
    }

    private void showBackArrow(boolean show) {
        backArrowShowed = show;

        if (show) {
            //drawerToggle.setDrawerIndicatorEnabled(!show);
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            drawerToggle.syncState();
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer.openDrawer(GravityCompat.START);
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);

        }
    }


}
