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
import com.nimfa.nimfaapp.databinding.FragmentNewCreditBinding;
import com.nimfa.nimfaapp.io_package.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewCreditFragment extends Fragment {


    private static final String KEY_AMOUNT = "amount";
    private int creditAmount, amountNimfa;

    public NewCreditFragment() {
        // Required empty public constructor
    }

    public static NewCreditFragment newInstance(int amount) {
        NewCreditFragment fragment = new NewCreditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_AMOUNT, amount);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            amountNimfa = getArguments().getInt(KEY_AMOUNT);
        } else {
            amountNimfa = savedInstanceState.getInt(KEY_AMOUNT);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentNewCreditBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_credit, container, false);
        String text = String.format(getString(R.string.credit_text), amountNimfa);
        binding.creditTextView.setText(Utils.fromHtml(text));

        final int nimfaProc = (int) (amountNimfa * 0.33f);
        binding.creditFormulaTv.setText(String.format(getString(R.string.credit_formula), nimfaProc, nimfaProc * 5));
        binding.creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApp.Instance.setLoanAmoumt(nimfaProc * 5);
                ((MainActivity) getActivity()).displayFragment(ChangeActionFragment.newInstance(amountNimfa), false);
            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_AMOUNT, amountNimfa);
    }
}
