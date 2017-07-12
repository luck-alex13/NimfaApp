package com.nimfa.nimfaapp.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nimfa.nimfaapp.MainActivity;
import com.nimfa.nimfaapp.R;
import com.nimfa.nimfaapp.databinding.FragmentChangeActionBinding;
import com.nimfa.nimfaapp.io_package.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChangeActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeActionFragment extends Fragment {

    private static final String KEY_AMOUNT = "amount";

    private int amountNimfa;


    public ChangeActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 AMOUNT
     * @return A new instance of fragment ChangeActionFragment.
     */
    public static ChangeActionFragment newInstance(int param1) {
        ChangeActionFragment fragment = new ChangeActionFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_AMOUNT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            amountNimfa = getArguments().getInt(KEY_AMOUNT);
        } else {
            amountNimfa = savedInstanceState.getInt(KEY_AMOUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final FragmentChangeActionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_action, container, false);
        String str = String.format(getString(R.string.change_action_fragment_text),amountNimfa);
        binding.changeActionTextView.setText(Utils.fromHtml(str));
        binding.beginTradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).displayFragment(new ExchangeFragment(), false);
            }
        });
        binding.buyTokenIcoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).displayFragment(new ICOFragment(), false);
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
