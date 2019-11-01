package com.tdjpartner.model;

import java.io.Serializable;

public class SeachTag implements Serializable {
    private String tag;
    private String monthTime;
    private String dayDate;
    private int userId;


    public SeachTag() {
    }

    public SeachTag(String tag) {
        this.tag = tag;
    }

    public SeachTag(String tag, String monthTime, String dayDate) {
        this.tag = tag;
        this.monthTime = monthTime;
        this.dayDate = dayDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMonthTime() {
        return monthTime;
    }

    public void setMonthTime(String monthTime) {
        this.monthTime = monthTime;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
