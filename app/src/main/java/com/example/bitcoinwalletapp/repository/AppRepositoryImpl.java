package com.example.bitcoinwalletapp.repository;

import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.model.FluctuationDataResponse;
import com.example.bitcoinwalletapp.model.LatestDataResponse;
import com.example.bitcoinwalletapp.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;

public class AppRepositoryImpl implements AppRepository {

    private final NetworkService networkService;

    public AppRepositoryImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void convertCurrency(String currencies, String baseCurrency, double amount, Callback<ConvertDataResponse> callback) {
        Call<ConvertDataResponse> call = networkService.getConvertData(currencies, baseCurrency, amount);
        call.enqueue(callback);
    }

    @Override
    public void getCurrencyRates(String symbols, String baseCurrency, Callback<LatestDataResponse> callback) {
        Call<LatestDataResponse> call = networkService.getLatestData(symbols, baseCurrency);
        call.enqueue(callback);
    }

    @Override
    public void getFluctuationData(String endDate, String startDate,String baseCurrency,String symbols, Callback<FluctuationDataResponse> callback) {
        Call<FluctuationDataResponse> call = networkService.getFluctuationData(endDate, startDate, baseCurrency,symbols);
        call.enqueue(callback);
    }

}
