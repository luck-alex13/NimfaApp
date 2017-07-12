package com.nimfa.nimfaapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nimfa.nimfaapp.databinding.ActivityLoginBinding;
import com.nimfa.nimfaapp.io_package.DialogManager;
import com.nimfa.nimfaapp.io_package.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        ArrayList<String> emails = new ArrayList<>();
        emails.add("test_email@gmail.com");
        emails.add("hello_user@mail.ru");

        addEmailsToAutoComplete(emails);

        binding.emailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void attemptLogin() {

        // Reset errors.
        binding.emailAutoComplete.setError(null);
        binding.passwordEditText.setError(null);

        // Store values at the time of the login attempt.
        String email = binding.emailAutoComplete.getText().toString();
        String password = binding.passwordEditText.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(email)) {// Check for a valid email address.
            binding.emailAutoComplete.setError(getString(R.string.error_field_required));
            focusView = binding.emailAutoComplete;
            cancel = true;
        } else if (!isEmailValid(email)) {
            binding.emailAutoComplete.setError(getString(R.string.error_invalid_email));
            focusView = binding.emailAutoComplete;
            cancel = true;
        } else if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            binding.passwordEditText.setError(getString(R.string.error_invalid_password));
            focusView = binding.passwordEditText;
            cancel = true;
        } else if (!binding.loginChbox1.isChecked() || !binding.loginChbox2.isChecked()) {
            Utils.showToast(LoginActivity.this, "Вы должны быть согласны с условиями!");
            return;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            MyApp.Instance.setUserEmail(email);
            DialogManager.Instance.showProgressDialog(LoginActivity.this);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    DialogManager.Instance.hide();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    finish();
                }
            }, 3 * 1000);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        binding.emailAutoComplete.setAdapter(adapter);
    }


}

