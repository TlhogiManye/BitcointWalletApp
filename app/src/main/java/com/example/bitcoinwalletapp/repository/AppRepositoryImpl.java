package com.example.bitcoinwalletapp.repository;

import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;

public class AppRepositoryImpl implements AppRepository {

    private final NetworkService networkService;

    public AppRepositoryImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void makeApiCall(String currencies, String baseCurrency, double amount, Callback<ConvertDataResponse> callback) {
        Call<ConvertDataResponse> call = networkService.getConvertData(currencies, baseCurrency, amount);
        call.enqueue(callback);
    }
}
