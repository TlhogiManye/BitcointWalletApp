package com.example.bitcoinwalletapp.repository;

import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.model.FluctuationDataResponse;
import com.example.bitcoinwalletapp.model.LatestDataResponse;

import retrofit2.Callback;

public interface AppRepository {

    void convertCurrency(String currencies, String baseCurrency, double amount, Callback<ConvertDataResponse> callback);

    void getCurrencyRates(String symbols, String baseCurrency, Callback<LatestDataResponse> callback);

    void getFluctuationData(String endDate, String startDate, String baseCurrency, String symbols, Callback<FluctuationDataResponse> callback);
}
