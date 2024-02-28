package com.example.bitcoinwalletapp.models;

public class CurrencyItem {

    private int iconResourceId;
    private String currencyCode;
    private String currencyValue;
    private String fluctuationPercentage;

    public CurrencyItem(int iconResourceId, String currencyCode, String currencyValue, String fluctuationPercentage) {
        this.iconResourceId = iconResourceId;
        this.currencyCode = currencyCode;
        this.currencyValue = currencyValue;
        this.fluctuationPercentage = fluctuationPercentage;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(String currencyValue) {
        this.currencyValue = currencyValue;
    }

    public String getFluctuationPercentage() {
        return fluctuationPercentage;
    }

    public void setFluctuationPercentage(String fluctuationPercentage) {
        this.fluctuationPercentage = fluctuationPercentage;
    }
}
