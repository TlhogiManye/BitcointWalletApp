package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

public  class CurrencyRate {

    @SerializedName("change")
    private double change;

    @SerializedName("change_pct")
    private double changePercentage;

    @SerializedName("end_rate")
    private double endRate;

    @SerializedName("start_rate")
    private double startRate;

    public double getChange() {
        return change;
    }

    public double getChangePercentage() {
        return changePercentage;
    }

    public double getEndRate() {
        return endRate;
    }

    public double getStartRate() {
        return startRate;
    }
}