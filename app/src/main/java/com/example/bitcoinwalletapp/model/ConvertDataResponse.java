package com.example.bitcoinwalletapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ConvertDataResponse implements Serializable {

    @SerializedName("date")
    private String date;

    @SerializedName("historical")
    private String historical;

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

    public String getHistorical() {
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

    public static class Info {
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

    public static class Query {
        @SerializedName("amount")
        private int amount;

        @SerializedName("from")
        private String from;

        @SerializedName("to")
        private String to;

        public int getAmount() {
            return amount;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }
}
