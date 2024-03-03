package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

public class LatestDataResponse {

    @SerializedName("base")
    private String base;

    @SerializedName("date")
    private String date;

    @SerializedName("rates")
    private Rates rates;

    @SerializedName("success")
    private boolean success;

    @SerializedName("timestamp")
    private long timestamp;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
