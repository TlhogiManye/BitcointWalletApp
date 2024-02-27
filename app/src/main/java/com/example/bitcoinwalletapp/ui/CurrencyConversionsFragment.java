package com.example.bitcoinwalletapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.R;

/**
 * @author Matlhogonolo Manye
 * @date 27/02/2024
 *
 * This fragment is solely responsible producing the different prices for currencies after BTC input.
 *
 */
public class CurrencyConversionsFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_conversions, container, false);
    }
}