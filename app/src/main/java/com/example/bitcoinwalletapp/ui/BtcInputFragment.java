/**
 * @author Matlhogonolo Manye
 * @date 27/02/2024
 *
 * This fragment is solely responsible for managing the input of the BTC value from the user.
 */

package com.example.bitcoinwalletapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.R;

public class BtcInputFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_btc_input, container, false);
    }
}