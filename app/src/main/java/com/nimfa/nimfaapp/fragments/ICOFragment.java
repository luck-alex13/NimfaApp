package com.nimfa.nimfaapp.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.MyApp;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.adapter.CurrencyRVAdapter;
import com.nimfa.nimfaapp.databinding.FragmentIcoBinding;
import com.nimfa.nimfaapp.io_package.Utils;

public class ICOFragment extends Fragment {


    public ICOFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.ico);
        ((MainActivity)getActivity()).setSubTitle(R.string.bye_tokens_from_list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentIcoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ico, container, false);
        if(MyApp.Instance.getLoanAmoumt() > 0) {
            binding.icoCurrencyError.setVisibility(View.GONE);
            binding.icoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.icoRecyclerView.setAdapter(new CurrencyRVAdapter(Utils.generateItems(getContext()), (MainActivity) getActivity()));
        }else
            binding.icoCurrencyError.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


}
