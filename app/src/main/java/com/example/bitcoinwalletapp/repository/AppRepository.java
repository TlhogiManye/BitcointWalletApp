package com.example.bitcoinwalletapp.repository;

import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import retrofit2.Callback;

public interface AppRepository {
    void makeApiCall(String currencies, String baseCurrency, double amount, Callback<ConvertDataResponse> callback);
}
