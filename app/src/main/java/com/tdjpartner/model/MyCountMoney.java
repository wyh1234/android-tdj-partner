package com.tdjpartner.model;

public class MyCountMoney {

    /**
     * frostAmount : 0.000000
     * withdrawalAmount : 0.000000
     * count : 10000.000000
     * surplusAmount : 10000.000000
     */

    private String frostAmount;
    private String withdrawalAmount;
    private String count;
    private String surplusAmount;

    public String getFrostAmount() {
        return frostAmount;
    }

    public void setFrostAmount(String frostAmount) {
        this.frostAmount = frostAmount;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(String surplusAmount) {
        this.surplusAmount = surplusAmount;
    }
}
