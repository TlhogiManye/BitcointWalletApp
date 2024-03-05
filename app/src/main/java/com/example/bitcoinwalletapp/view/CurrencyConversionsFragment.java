package com.example.bitcoinwalletapp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bitcoinwalletapp.BuildConfig;
import com.example.bitcoinwalletapp.R;
import com.example.bitcoinwalletapp.adapters.CurrencyAdapter;
import com.example.bitcoinwalletapp.model.CurrencyItem;
import com.example.bitcoinwalletapp.model.CurrencyRate;
import com.example.bitcoinwalletapp.model.FluctuationDataResponse;
import com.example.bitcoinwalletapp.model.Rates;
import com.example.bitcoinwalletapp.network.NetworkClient;
import com.example.bitcoinwalletapp.repository.AppRepository;
import com.example.bitcoinwalletapp.repository.AppRepositoryImpl;
import com.example.bitcoinwalletapp.viewmodel.AppViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private AppViewModel viewModel;
    double inputAmountFromStorage, newInputAmount;
    FluctuationDataResponse fluctuationDataResponse;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_currency_conversions, container, false);

        recyclerView = view.findViewById(R.id.recyclerCurrencies);


        AppRepository appRepository = new AppRepositoryImpl(NetworkClient.getNetworkService(BuildConfig.API_KEY));
        viewModel = new AppViewModel(appRepository, this);

        inputAmountFromStorage = Double.parseDouble(viewModel.getBtcAmountFromStorage());

        currencyList = generateDummyData();
        updateFluctuationDataView();
        currencyAdapter = new CurrencyAdapter(getContext(), currencyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(currencyAdapter);

        return view;
    }

    private void updateFluctuationDataView() {
        if(fluctuationDataResponse!=null){
            updateFluctuationDataView(fluctuationDataResponse);
        }
    }

    private List<CurrencyItem> generateDummyData() {
        List<CurrencyItem> list = new ArrayList<>();

        Rates storedRates = viewModel.getDecryptedRatesFromStorage();

         fluctuationDataResponse = viewModel.getDecryptedFluctuationDataFromStorage();
        //ensure rates are coming from sharedPreferences so that they are stored and not lost when user leaves app
            list.add(new CurrencyItem(R.drawable.icn_flag_zar, "ZAR","R " + decimalFormat.format(storedRates.getZar()* inputAmountFromStorage), "0.0%"));
            list.add(new CurrencyItem(R.drawable.icn_flag_usd, "USD", "$ "+ decimalFormat.format(storedRates.getUsd()* inputAmountFromStorage), "0.0%"));
            list.add(new CurrencyItem(R.drawable.icn_flag_aud, "AUD","$ " + decimalFormat.format(storedRates.getAud()* inputAmountFromStorage), "0.0%"));

        return list;
    }

    public void updateRatesView(Rates newRates) {
        newInputAmount = Double.parseDouble(viewModel.getBtcAmountFromStorage());

        currencyList.get(0).setCurrencyValue("R " + decimalFormat.format(newRates.getZar() * newInputAmount));
        currencyList.get(1).setCurrencyValue("$ " + decimalFormat.format(newRates.getUsd() * newInputAmount));
        currencyList.get(2).setCurrencyValue("$ " + decimalFormat.format(newRates.getAud() * newInputAmount));

        fluctuationDataResponse = viewModel.getDecryptedFluctuationDataFromStorage();
        updateFluctuationDataView();
        currencyAdapter.notifyDataSetChanged();
    }

    public void updateFluctuationDataView(FluctuationDataResponse fluctuationDataResponse) {
        for (Map.Entry<String, CurrencyRate> entry : fluctuationDataResponse.getRates().entrySet()) {
            String currencyCode = entry.getKey();
            CurrencyRate rate = entry.getValue();

            switch (currencyCode) {
                case "ZAR":
                    currencyList.get(0).setFluctuationPercentage(decimalFormat.format(rate.getChangePercentage())+ "%");
                    break;

                case "USD":
                    currencyList.get(1).setFluctuationPercentage(decimalFormat.format(rate.getChangePercentage()) + "%");
                    break;

                case "AUD":
                    currencyList.get(2).setFluctuationPercentage(decimalFormat.format(rate.getChangePercentage()) + "%");
                    break;
                default:
                    break;
            }
        }

    }
}