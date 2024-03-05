package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class FluctuationDataResponse {

    @SerializedName("base")
    private String base;

    @SerializedName("end_date")
    private String endDate;

    @SerializedName("fluctuation")
    private boolean fluctuation;

    @SerializedName("rates")
    private Map<String, CurrencyRate> rates;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("success")
    private boolean success;

    public String getBase() {
        return base;
    }

    public String getEndDate() {
        return endDate;
    }

    public boolean isFluctuation() {
        return fluctuation;
    }

    public Map<String, CurrencyRate> getRates() {
        return rates;
    }

    public String getStartDate() {
        return startDate;
    }

    public boolean isSuccess() {
        return success;
    }

}
