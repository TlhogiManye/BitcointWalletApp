package com.example.bitcoinwalletapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.R;
import com.example.bitcoinwalletapp.adapters.CurrencyAdapter;
import com.example.bitcoinwalletapp.model.CurrencyItem;

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


        list.add(new CurrencyItem(R.drawable.icn_flag_zar, "ZAR", "120.00", "-0.8%"));
        list.add(new CurrencyItem(R.drawable.icn_flag_usd, "USD", "100.00", "+1.5%"));
        list.add(new CurrencyItem(R.drawable.icn_flag_aud, "AUD", "120.00", "-0.8%"));
        // Add more items...

        return list;
    }

    public void updateRecyclerView(String newBtcValue) {
        // Update the currency list with the new BTC value
        for (CurrencyItem currencyItem : currencyList) {
            // Update the currency value based on the new BTC value
            // You might need to implement a method in CurrencyItem to calculate the new value
            currencyItem.setCurrencyValue(newBtcValue);
        }

        // Notify the adapter about the data change
        currencyAdapter.notifyDataSetChanged();
    }
}