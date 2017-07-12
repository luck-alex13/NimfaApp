package com.nimfa.nimfaapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.dataModels.Currency;
import com.nimfa.nimfaapp.databinding.CurrencyBagItemBinding;
import com.nimfa.nimfaapp.databinding.CurrencyItemBinding;
import com.nimfa.nimfaapp.fragments.CryptoBagFragment;
import com.nimfa.nimfaapp.io_package.DialogManager;

import java.util.ArrayList;

/**
 * NimfaApp
 * Created by Alex on 09.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public class CurrencyBagRVAdapter extends RecyclerView.Adapter<CurrencyBagRVAdapter.CurrencyViewHolder> {

    private final ArrayList<Currency> currencyArrayList;

    public CurrencyBagRVAdapter(ArrayList<Currency> arrayList) {
        this.currencyArrayList = arrayList;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CurrencyBagItemBinding itemBinding = CurrencyBagItemBinding.inflate(inflater, parent, false);
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
        CurrencyBagItemBinding itemBinding;

        public CurrencyViewHolder(CurrencyBagItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(Currency currency) {
            itemBinding.setCurr(currency);
            itemBinding.currImgView.setImageResource(currency.getIcon());

        }
    }
}
