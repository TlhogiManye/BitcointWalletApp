package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

public class Rates {

    @SerializedName("ZAR")
    private double zar;

    @SerializedName("USD")
    private double usd;

    @SerializedName("AUD")
    private double aud;

    public double getAud() {
        return aud;
    }

    public double getUsd() {
        return usd;
    }

    public double getZar() {
        return zar;
    }
}
