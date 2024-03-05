package com.example.bitcoinwalletapp.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.bitcoinwalletapp.BR;
import com.example.bitcoinwalletapp.BuildConfig;
import com.example.bitcoinwalletapp.model.FluctuationDataResponse;
import com.example.bitcoinwalletapp.model.LatestDataResponse;
import com.example.bitcoinwalletapp.model.Rates;
import com.example.bitcoinwalletapp.repository.AppRepository;
import com.example.bitcoinwalletapp.view.CurrencyConversionsFragment;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.content.Context;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppViewModel extends BaseObservable {

    private final AppRepository appRepository;
    private final CurrencyConversionsFragment currencyConversionsFragment;
    private String btcAmount;
    private String btcValue;
    private ProgressDialog progressDialog;
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS7Padding";
    private static final String SECRET_KEY = BuildConfig.SECRET_KEY;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String BTC_AMOUNT_KEY = "btcAmount";

    private final SharedPreferences preferences;

    // Constructor
    public AppViewModel(AppRepository appRepository, CurrencyConversionsFragment currencyConversionsFragment) {
        this.appRepository = appRepository;
        this.currencyConversionsFragment = currencyConversionsFragment;

        // Initialize progress dialog for loading indication
        progressDialog = new ProgressDialog(currencyConversionsFragment.requireActivity());
        progressDialog.setMessage("Loading..."); // Set a loading message
        progressDialog.setCancelable(false);
        preferences = currencyConversionsFragment.requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Getter and Setter for BTC Amount
    @Bindable
    public String getBtcAmount() {
        return btcAmount;
    }

    public void setBtcAmount(String btcAmount) {
        this.btcAmount = btcAmount;
        notifyPropertyChanged(BR.btcAmount);
    }

    // Getter and Setter for BTC Value
    @Bindable
    public String getBtcValue() {
        return btcValue;
    }

    public void setBtcValue(String btcValue) {
        this.btcValue = btcValue;
        notifyPropertyChanged(BR.btcValue);
    }

    // API call to get currency conversion rates
    public void getRatesApiCall() {
        progressDialog.show();

        String currencies = "ZAR,USD,AUD";
        String baseCurrency = "BTC";
        Log.d("convFrag", "BeforeOnResponse: currencyConversion");

        appRepository.getCurrencyRates(currencies, baseCurrency, new Callback<LatestDataResponse>() {
            @Override
            public void onResponse(Call<LatestDataResponse> call, Response<LatestDataResponse> response) {
                Log.d("convFrag", "onResponse: currencyConversion" + response);
                try {
                    LatestDataResponse currencyRateConversions = response.body();
                    if (currencyRateConversions != null) {

                        // Ensure UI updates on the main thread
                        currencyConversionsFragment.requireActivity().runOnUiThread(() -> {
                            updateBtcValue(btcAmount);
                            updateRecyclerView(currencyRateConversions.getRates());
                            progressDialog.dismiss();

                            saveEncryptedRatesToStorage(currencyRateConversions.getRates());
                        });
                    }
                } catch (Exception e) {
                    Log.e("convFrag", "Exception in onResponse", e);
                }
            }

            @Override
            public void onFailure(Call<LatestDataResponse> call, Throwable t) {
                Log.e("convFrag", "API call failed", t);

                // Handle failure and dismiss progress dialog on the UI thread
                String errorMessage = "Failed to retrieve data. Please check your internet connection.";

                if (t.getMessage() != null) {
                    errorMessage += "\nError: " + t.getMessage();
                }

                Log.e("error API:", errorMessage, t);

                currencyConversionsFragment.requireActivity().runOnUiThread(() -> progressDialog.dismiss());
            }
        });
    }

    // API call to get fluctuation data
    public void getFluctuationApiCall() {
        progressDialog.show();

        String symbols = "ZAR,USD,AUD";
        String baseCurrency = "BTC";
        String todayDate = getTodayDate();
        String yesterdayDate = getYesterdayDate();

        Log.d("convFrag", "BeforeOnResponse: fluctuationData");

        appRepository.getFluctuationData(todayDate, yesterdayDate, baseCurrency, symbols, new Callback<FluctuationDataResponse>() {
            @Override
            public void onResponse(Call<FluctuationDataResponse> call, Response<FluctuationDataResponse> response) {
                Log.d("convFrag", "onResponse: fluctuationData" + response);
                try {
                    FluctuationDataResponse fluctuationDataResponse = response.body();
                    if (fluctuationDataResponse != null) {
                        saveEncryptedFluctuationDataToStorage(fluctuationDataResponse);
                        // Ensure UI updates on the main thread
                        currencyConversionsFragment.requireActivity().runOnUiThread(() -> {
                            updateFluctuationData(fluctuationDataResponse);
                        });
                    }
                    progressDialog.dismiss();
                } catch (Exception e) {
                    Log.e("convFrag", "Exception in onResponse", e);
                }
            }

            @Override
            public void onFailure(Call<FluctuationDataResponse> call, Throwable t) {
                Log.e("convFrag", "API call failed", t);

                // Handle failure and dismiss progress dialog on the UI thread
                String errorMessage = "Failed to retrieve data. Please check your internet connection.";

                if (t.getMessage() != null) {
                    errorMessage += "\nError: " + t.getMessage();
                }

                Log.e("error API:", errorMessage, t);

                currencyConversionsFragment.requireActivity().runOnUiThread(() -> progressDialog.dismiss());
            }
        });
    }

    // Update UI with fluctuation data
    private void updateFluctuationData(FluctuationDataResponse fluctuationDataResponse) {
        if (currencyConversionsFragment != null) {
            currencyConversionsFragment.updateFluctuationDataView(fluctuationDataResponse);
        }
    }

    // Get today's date in the required format
    private String getTodayDate() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(currentDate);
    }

    // Get yesterday's date in the required format
    private String getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, -1); // Subtract 1 day
        Date yesterdayDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(yesterdayDate);
    }

    // Update BTC value in the UI
    private void updateBtcValue(String newBtcValue) {
        setBtcValue(newBtcValue);
    }

    // Update currency conversion rates in the UI
    private void updateRecyclerView(Rates newRates) {
        if (currencyConversionsFragment != null) {
            currencyConversionsFragment.updateRatesView(newRates);
        }
    }

    // Encryption and Decryption methods
    public static String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.decode(encrypted, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Save BTC amount to local storage
    public void saveBtcAmountToStorage(String btcAmount) {
        String encryptedBtcAmount = encrypt(btcAmount);
        preferences.edit().putString(BTC_AMOUNT_KEY, encryptedBtcAmount).apply();
    }

    // Get BTC amount from local storage
    public String getBtcAmountFromStorage() {
        String encryptedBtcAmount = preferences.getString(BTC_AMOUNT_KEY, null);
        if (encryptedBtcAmount != null) {
            return decrypt(encryptedBtcAmount);
        }
        return null;
    }

    public void saveEncryptedRatesToStorage(Rates rates) {
        // Convert Rates object to JSON String
        String ratesJson = new Gson().toJson(rates);

        // Encrypt rates JSON string
        String encryptedRates = encrypt(ratesJson);

        // Save encrypted rates to storage
        preferences.edit().putString("encryptedRates", encryptedRates).apply();
    }

    public Rates getDecryptedRatesFromStorage() {
        // Retrieve encrypted rates from storage
        String encryptedRates = preferences.getString("encryptedRates", null);

        if (encryptedRates != null) {
            // Decrypt rates JSON string
            String decryptedRatesJson = decrypt(encryptedRates);

            // Convert JSON string to Rates object
            return new Gson().fromJson(decryptedRatesJson, Rates.class);
        }

        return null;
    }

    public void saveEncryptedFluctuationDataToStorage(FluctuationDataResponse fluctuationDataResponse) {
        // Convert FluctuationDataResponse object to JSON String
        String fluctuationDataJson = new Gson().toJson(fluctuationDataResponse);

        // Encrypt fluctuation data JSON string
        String encryptedFluctuationData = encrypt(fluctuationDataJson);

        // Save encrypted fluctuation data to storage
        preferences.edit().putString("encryptedFluctuationData", encryptedFluctuationData).apply();
    }

    public FluctuationDataResponse getDecryptedFluctuationDataFromStorage() {
        // Retrieve encrypted fluctuation data from storage
        String encryptedFluctuationData = preferences.getString("encryptedFluctuationData", null);

        if (encryptedFluctuationData != null) {
            // Decrypt fluctuation data JSON string
            String decryptedFluctuationDataJson = decrypt(encryptedFluctuationData);

            // Convert JSON string to FluctuationDataResponse object
            return new Gson().fromJson(decryptedFluctuationDataJson, FluctuationDataResponse.class);
        }

        return null;
    }
}
