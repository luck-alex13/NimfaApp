package com.nimfa.nimfaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.dataModels.Currency;
import com.nimfa.nimfaapp.dataModels.Details;
import com.nimfa.nimfaapp.databinding.CurrencyItemBinding;
import com.nimfa.nimfaapp.fragments.CryptoBagFragment;
import com.nimfa.nimfaapp.io_package.DialogManager;
import com.nimfa.nimfaapp.io_package.Utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * NimfaApp
 * Created by Alex on 09.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.CurrencyViewHolder> {

    private final ArrayList<Currency> currencyArrayList;
    private MainActivity mainActivity;

    public CurrencyRVAdapter(ArrayList<Currency> arrayList, MainActivity activity) {
        this.currencyArrayList = arrayList;
        this.mainActivity = activity;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CurrencyItemBinding itemBinding = CurrencyItemBinding.inflate(inflater, parent, false);
        return new CurrencyViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        holder.bind(currencyArrayList.get(position));
    }


    @Override
    public int getItemCount() {
        return currencyArrayList.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        CurrencyItemBinding itemBinding;

        public CurrencyViewHolder(CurrencyItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(final Currency currency) {
            itemBinding.setCurr(currency);
            itemBinding.currImgView.setImageResource(currency.getIcon());
            itemBinding.cardPopup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(v.getContext(), itemBinding.cardPopup);
                    popup.inflate(R.menu.popup_menu);
                    //adding click listener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.item_details:
                                    //handle menu1 click
                                    inflateDetails(currency, v.getContext());
                                    break;
                                case R.id.item_help:
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currency.getHelp()));
                                    v.getContext().startActivity(browserIntent);
                                    break;
                            }
                            return false;
                        }
                    });
                    //displaying the popup
                    popup.show();
                }
            });
            itemBinding.byeCurrency.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogManager.Instance.showInputDialog(v.getContext(), v.getContext().getString(R.string.input_amount), new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                            mainActivity.displayFragment(new CryptoBagFragment(), false);
                        }
                    });
                }
            });
        }

        private void inflateDetails(Currency currency, Context context) {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                    .title(currency.getName())
                    .customView(R.layout.details_view, true)
                    .positiveText(R.string.ok);
            MaterialDialog dialog = builder.build();
            LinearLayout contentView = (LinearLayout) dialog.getView().findViewById(R.id.details_content_view);
            LayoutInflater inflater = LayoutInflater.from(context);
            for (Details entry : currency.getDetails()) {
                View view = inflater.inflate(R.layout.decription_item, null);
                ((TextView) view.findViewById(R.id.curr_label_tv)).setText(entry.getTitle());
                ((TextView) view.findViewById(R.id.curr_title_tv)).setText(entry.getValue());
                contentView.addView(view);
            }


            dialog.show();
        }
    }
}
