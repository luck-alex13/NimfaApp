package com.nimfa.nimfaapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.MyApp;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.dataModels.Currency;
import com.nimfa.nimfaapp.databinding.FragmentCostBagBinding;
import com.nimfa.nimfaapp.io_package.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CostBagFragment extends Fragment {


    public CostBagFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.cost_bag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCostBagBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cost_bag, container, false);

        int costBag = 0;
        ArrayList<Currency> list = Utils.generateBagItems();
        for (Currency currency : list) {
            costBag += currency.getCost() * currency.getAmount();
        }
        int comission = (int) (costBag * 0.1f);
        //@todo ПЕРЕПРАВИТЬ loan

        final int profit = costBag - comission - MyApp.Instance.getLoanAmoumt();
        binding.bagCostTextView.setText(String.format(getString(R.string.dollar_s), String.valueOf(costBag)));
        binding.comissionText.setText(String.format(getString(R.string.dollar_s), String.valueOf(comission)));
        binding.returnLoanText.setText(String.format(getString(R.string.dollar_s), String.valueOf(MyApp.Instance.getLoanAmoumt())));
        binding.profitText.setText(String.format(getString(R.string.dollar_s), String.valueOf(profit)));
        binding.getProfitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).displayFragment(ProfitFragment.newInstance(profit), false);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}
