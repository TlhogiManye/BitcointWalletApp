package com.example.bitcoinwalletapp.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bitcoinwalletapp.BR;
import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.repository.AppRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppViewModel extends BaseObservable {

    private final AppRepository appRepository;

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


    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    // Add other methods and variables...

    public void makeApiCallAndUpdateUI() {
        // Retrieve necessary parameters for the API call
        String currencies = "ZAR,USD,AUD";
        String baseCurrency = "BTC";
        double amount = 233.08;

        // Make the API call through the repository
        appRepository.makeApiCall(currencies, baseCurrency, amount, new Callback<ConvertDataResponse>() {
            @Override
            public void onResponse(Call<ConvertDataResponse> call, Response<ConvertDataResponse> response) {
                // Handle API response and update UI
                ConvertDataResponse currencyConversion = response.body();
                if (currencyConversion != null) {
                    // Update the ViewModel with the new BTC value
                    //setBtcValue(""); // Assuming USD is the target currency
                }
            }

            @Override
            public void onFailure(Call<ConvertDataResponse> call, Throwable t) {
                // Handle API call failure
            }
        });
    }
}
