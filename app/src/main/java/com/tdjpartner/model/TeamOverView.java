package com.tdjpartner.model;

import java.math.BigDecimal;

public class TeamOverView{


    /**
     * amount : 1.0
     * addAmount : 0
     * times : 3
     * afterSaleAmount : 4.0
     * afterSaleTimes : 0
     * amountCommission : 5.0
     * registerNum : 7
     * callNum : 8
     * activeNum : 9
     * addActiveNum : 0
     * reviewNum : 0
     * firstOrderNum : 0
     * userNum : 1
     * orderNum : 0
     * noOrderNum : 0
     * examineNum : 0
     * totalNum : 0
     */

    private BigDecimal amount;
    private BigDecimal addAmount;
    private int times;
    private BigDecimal afterSaleAmount;
    private int afterSaleTimes;
    private BigDecimal amountCommission;
    private int registerNum;
    private int callNum;
    private int activeNum;
    private int addActiveNum;
    private int reviewNum;
    private int firstOrderNum;
    private int userNum;
    private int orderNum;
    private int noOrderNum;
    private int examineNum;
    private int totalNum;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public BigDecimal getAfterSaleAmount() {
        return afterSaleAmount;
    }

    public void setAfterSaleAmount(BigDecimal afterSaleAmount) {
        this.afterSaleAmount = afterSaleAmount;
    }

    public int getAfterSaleTimes() {
        return afterSaleTimes;
    }

    public void setAfterSaleTimes(int afterSaleTimes) {
        this.afterSaleTimes = afterSaleTimes;
    }

    public BigDecimal getAmountCommission() {
        return amountCommission;
    }

    public void setAmountCommission(BigDecimal amountCommission) {
        this.amountCommission = amountCommission;
    }

    public int getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(int registerNum) {
        this.registerNum = registerNum;
    }

    public int getCallNum() {
        return callNum;
    }

    public void setCallNum(int callNum) {
        this.callNum = callNum;
    }

    public int getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(int activeNum) {
        this.activeNum = activeNum;
    }

    public int getAddActiveNum() {
        return addActiveNum;
    }

    public void setAddActiveNum(int addActiveNum) {
        this.addActiveNum = addActiveNum;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getFirstOrderNum() {
        return firstOrderNum;
    }

    public void setFirstOrderNum(int firstOrderNum) {
        this.firstOrderNum = firstOrderNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getNoOrderNum() {
        return noOrderNum;
    }

    public void setNoOrderNum(int noOrderNum) {
        this.noOrderNum = noOrderNum;
    }

    public int getExamineNum() {
        return examineNum;
    }

    public void setExamineNum(int examineNum) {
        this.examineNum = examineNum;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
