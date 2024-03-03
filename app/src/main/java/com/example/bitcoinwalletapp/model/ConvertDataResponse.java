package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConvertDataResponse implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("historical")
    private boolean historical;

    @SerializedName("info")
    private Info info;

    @SerializedName("query")
    private Query query;

    @SerializedName("result")
    private double result;

    @SerializedName("success")
    private boolean success;

    public String getDate() {
        return date;
    }

    public boolean getHistorical() {
        return historical;
    }

    public Info getInfo() {
        return info;
    }

    public Query getQuery() {
        return query;
    }

    public double getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }




}
