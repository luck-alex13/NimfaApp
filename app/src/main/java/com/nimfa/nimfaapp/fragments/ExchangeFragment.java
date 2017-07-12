package com.nimfa.nimfaapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.MyApp;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.adapter.CurrencyRVAdapter;
import com.nimfa.nimfaapp.databinding.FragmentExchangeBinding;
import com.nimfa.nimfaapp.io_package.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExchangeFragment extends Fragment {


    public ExchangeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.exchange);
        ((MainActivity)getActivity()).setSubTitle(R.string.bye_currency_from_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentExchangeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exchange, container, false);
        if(MyApp.Instance.getLoanAmoumt() > 0) {
            binding.cryptoCurrencyList.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.cryptoCurrencyList.setAdapter(new CurrencyRVAdapter(Utils.generateItems(), (MainActivity) getActivity()));
            binding.cryptoCurrencyError.setVisibility(View.GONE);
        }else
            binding.cryptoCurrencyError.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        return binding.getRoot();

    }

}
