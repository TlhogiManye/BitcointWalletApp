package com.example.bitcoinwalletapp.viewmodel;

import static java.security.AccessController.getContext;

import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bitcoinwalletapp.BR;
import com.example.bitcoinwalletapp.model.ConvertDataResponse;
import com.example.bitcoinwalletapp.model.LatestDataResponse;
import com.example.bitcoinwalletapp.model.Rates;
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

    public void getRatesApiCall() {
        String currencies = "ZAR,USD,AUD";
        String baseCurrency = "BTC";
        double amount = Double.parseDouble(btcAmount); // Convert user input to double

        Log.d("convFrag: ", "BeforeOnResponse:  currencyConversion");

        appRepository.getCurrencyRates(currencies, baseCurrency, new Callback<LatestDataResponse>() {
            @Override
            public void onResponse(Call<LatestDataResponse> call, Response<LatestDataResponse> response) {
                Log.d("convFrag: ", "onResponse:  currencyConversion" + response);
                try {
                    LatestDataResponse currencyRateConversions = response.body();
                    if (currencyRateConversions != null) {
                        // Ensure UI updates on the main thread
                        if (currencyConversionsFragment != null) {
                            currencyConversionsFragment.getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateBtcValue(btcAmount);
                                    updateRecyclerView(currencyRateConversions.getRates());
                                }
                            });
                        }
                    }
                } catch(Exception e){
                    Log.e("convFrag: ", "Exception in onResponse", e);
                }
            }

            @Override
            public void onFailure(Call<LatestDataResponse> call, Throwable t) {
                Log.e("convFrag", "API call failed", t);

                String errorMessage = "Failed to retrieve data. Please check your internet connection.";

                if (t.getMessage() != null) {
                    errorMessage += "\nError: " + t.getMessage();
                }

                Log.e("error API:", errorMessage, t);
            }

        });
    }

    private void updateBtcValue(String newBtcValue) {
        setBtcValue(newBtcValue);
    }

    private void updateRecyclerView(Rates newRates) {
        if (currencyConversionsFragment != null) {
            currencyConversionsFragment.updateRecyclerView(newRates);
        }
    }

}
