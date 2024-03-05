package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

public  class Query {
    @SerializedName("amount")
    private double amount;

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    public double getAmount() {
        return amount;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}