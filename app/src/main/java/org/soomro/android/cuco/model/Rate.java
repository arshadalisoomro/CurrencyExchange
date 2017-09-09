package org.soomro.android.cuco.model;

/**
 * Created by Arshay on 8/20/2017.
 */

public class Rate {
    private long id;
    private String currencyPostfix;
    private double currencyRate;
    private long currencyTimeStamp;

    public Rate(){}

    public Rate(String currencyPostfix, double currencyRate, long currencyTimeStamp){
        this.currencyPostfix = currencyPostfix;
        this.currencyRate = currencyRate;
        this.currencyTimeStamp = currencyTimeStamp;
    }

    public Rate(long id, String currencyPostfix, double currencyRate){
        this.id = id;
        this.currencyPostfix = currencyPostfix;
        this.currencyRate = currencyRate;

        this.currencyTimeStamp = currencyTimeStamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrencyPostfix() {
        return currencyPostfix;
    }

    public void setCurrencyPostfix(String currencyPostfix) {
        this.currencyPostfix = currencyPostfix;
    }

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public long getCurrencyTimeStamp() {
        return currencyTimeStamp;
    }

    public void setCurrencyTimeStamp(long currencyTimeStamp) {
        this.currencyTimeStamp = currencyTimeStamp;
    }
}
