package com.tdjpartner.model;

public class HistoryInfo {
    private String title;
    private int res;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public HistoryInfo(String title, int res) {
        this.title = title;
        this.res = res;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
