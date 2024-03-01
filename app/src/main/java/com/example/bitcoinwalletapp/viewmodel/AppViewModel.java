package com.example.bitcoinwalletapp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bitcoinwalletapp.BR;
import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.repository.AppRepository;
import com.example.bitcoinwalletapp.view.CurrencyConversionsFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppViewModel extends BaseObservable {

    private final AppRepository appRepository;
    private final CurrencyConversionsFragment currencyConversionsFragment;
    private String btcAmount;
    private String btcValue;

    // ... Other code

    @Bindable
    public String getBtcAmount() {
        return btcAmount;
    }

    public void setBtcAmount(String btcAmount) {
        this.btcAmount = btcAmount;
        notifyPropertyChanged(BR.btcAmount);
    }

    @Bindable
    public String getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(String btcValue) {
        this.btcValue = btcValue;
        notifyPropertyChanged(BR.btcValue);
    }


    public AppViewModel(AppRepository appRepository, CurrencyConversionsFragment currencyConversionsFragment) {
        this.appRepository = appRepository;
        this.currencyConversionsFragment = currencyConversionsFragment;
    }

    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
        this.currencyConversionsFragment = null; // Assuming no direct connection without passing the fragment
    }

    // Add other methods and variables...

    public void makeApiCall() {
        // Retrieve necessary parameters for the API call
        String currencies = "ZAR";
        String baseCurrency = "BTC";
        double amount = 233.08;

        // Make the API call through the repository
        appRepository.convertCurrency(currencies, baseCurrency, amount, new Callback<ConvertDataResponse>() {
            @Override
            public void onResponse(Call<ConvertDataResponse> call, Response<ConvertDataResponse> response) {
                // Handle API response
                ConvertDataResponse currencyConversion = response.body();
                if (currencyConversion != null) {
                    //updateBtcValue(Double.toString(currencyConversion.getResult()));
                    updateRecyclerView(Double.toString(currencyConversion.getResult()));
                }
            }

            @Override
            public void onFailure(Call<ConvertDataResponse> call, Throwable t) {
                // Handle API call failure
            }
        });
    }

    private void updateBtcValue(String newBtcValue) {
        // Update the ViewModel with the new BTC value
        setBtcValue(newBtcValue);
    }

    private void updateRecyclerView(String newBtcValue) {
        if (currencyConversionsFragment != null) {
            currencyConversionsFragment.updateRecyclerView(newBtcValue);
        }
    }
}
