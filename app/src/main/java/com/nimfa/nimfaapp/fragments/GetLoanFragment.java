package com.nimfa.nimfaapp.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.LoginActivity;
import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.databinding.FragmentGetLoanBinding;
import com.nimfa.nimfaapp.io_package.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetLoanFragment extends Fragment {


    public GetLoanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.app_name);
        ((MainActivity) getActivity()).setSubTitle("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentGetLoanBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_loan, container, false);

        binding.getLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loanAmountEt.setError(null);
                String amount = binding.loanAmountEt.getText().toString();
                if (amount.length() == 0) {
                    binding.loanAmountEt.setError(getString(R.string.error_field_required));

                } else if (Integer.parseInt(amount) > 30000) {
                    binding.loanAmountEt.setError(getString(R.string.error_loan));
                } else {
                    ((MainActivity) getActivity()).displayFragment(NewCreditFragment.newInstance(Integer.parseInt(amount)), false);
                }
            }
        });

        return binding.getRoot();
    }

}
