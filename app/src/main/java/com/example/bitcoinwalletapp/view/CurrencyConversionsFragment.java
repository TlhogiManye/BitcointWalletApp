package com.example.bitcoinwalletapp.view;

import static com.example.bitcoinwalletapp.BR.btcAmount;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.R;
import com.example.bitcoinwalletapp.adapters.CurrencyAdapter;
import com.example.bitcoinwalletapp.model.CurrencyItem;
import com.example.bitcoinwalletapp.model.Rates;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matlhogonolo Manye
 * @date 27/02/2024
 * <p>
 * This fragment is solely responsible for producing the different prices for currencies after BTC input.
 */
public class CurrencyConversionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CurrencyAdapter currencyAdapter;
    private List<CurrencyItem> currencyList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_currency_conversions, container, false);

        recyclerView = view.findViewById(R.id.recyclerCurrencies);
        currencyList = generateDummyData();


        currencyAdapter = new CurrencyAdapter(getContext(), currencyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(currencyAdapter);

        return view;
    }

    private List<CurrencyItem> generateDummyData() {
        List<CurrencyItem> list = new ArrayList<>();


        list.add(new CurrencyItem(R.drawable.icn_flag_zar, "ZAR", "0.00", "0.0%"));
        list.add(new CurrencyItem(R.drawable.icn_flag_usd, "USD", "0.00", "0.0%"));
        list.add(new CurrencyItem(R.drawable.icn_flag_aud, "AUD", "0.00", "0.0%"));
        // Add more items...
        return list;
    }

    public void updateRecyclerView(Rates newRates) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");


        currencyList.get(0).setCurrencyValue(decimalFormat.format(newRates.getZar()*btcAmount));
        currencyList.get(1).setCurrencyValue(decimalFormat.format(newRates.getUsd()*btcAmount));
        currencyList.get(2).setCurrencyValue(decimalFormat.format(newRates.getAud()*btcAmount));
        currencyAdapter.notifyDataSetChanged();
    }

}