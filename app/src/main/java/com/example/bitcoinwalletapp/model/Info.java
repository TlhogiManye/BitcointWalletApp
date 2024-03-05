package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

public class Info {
    @SerializedName("rate")
    private double rate;

    @SerializedName("timestamp")
    private long timestamp;

    public double getRate() {
        return rate;
    }

    public long getTimestamp() {
        return timestamp;
    }
}