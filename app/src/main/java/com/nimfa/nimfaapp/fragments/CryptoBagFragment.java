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
import com.nimfa.nimfaapp.adapter.CurrencyBagRVAdapter;
import com.nimfa.nimfaapp.databinding.FragmentCryptoBagBinding;
import com.nimfa.nimfaapp.io_package.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CryptoBagFragment extends Fragment {


    public CryptoBagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.my_portfolio_crypto);
        ((MainActivity) getActivity()).setSubTitle("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCryptoBagBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_crypto_bag, container, false);
        if(MyApp.Instance.getLoanAmoumt() > 0) {
            binding.getProfit.setVisibility(View.VISIBLE);
            binding.cryptoBagRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.cryptoBagRecyclerView.setAdapter(new CurrencyBagRVAdapter(Utils.generateBagItems()));
            binding.getProfit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) getActivity()).displayFragment(new CostBagFragment(), false);
                }
            });
            binding.cryptoBagError.setVisibility(View.GONE);
        }else {
            binding.cryptoBagError.setVisibility(View.VISIBLE);
            binding.getProfit.setVisibility(View.GONE);
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
