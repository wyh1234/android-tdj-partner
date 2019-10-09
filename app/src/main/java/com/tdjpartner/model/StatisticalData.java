package com.tdjpartner.model;
/*
*
*统计数据
* */
public class StatisticalData  extends Message{
    private int type;
    private String title;

    public StatisticalData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
