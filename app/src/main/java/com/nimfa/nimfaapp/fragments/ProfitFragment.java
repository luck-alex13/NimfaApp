package com.nimfa.nimfaapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.MyApp;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.databinding.FragmentProfitBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM = "profit";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int profit;
    private String mParam2;


    public ProfitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param profit Parameter 1.
     * @return A new instance of fragment ProfitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfitFragment newInstance(int profit) {
        ProfitFragment fragment = new ProfitFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, profit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profit = getArguments().getInt(ARG_PARAM);
        }else {
            profit = savedInstanceState.getInt(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentProfitBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profit, container, false);
        binding.profitTv.setText(String.format(getString(R.string.dollar_s), String.valueOf(profit)));
        MyApp.Instance.setLoanAmoumt(0);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_PARAM, profit);
    }

}
