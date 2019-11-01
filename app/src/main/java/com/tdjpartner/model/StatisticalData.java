package com.tdjpartner.model;
/*
*
*统计数据
* */
public class StatisticalData  extends Message{
    private int type;
    private String title;
    private String num;

    public StatisticalData(String title, String num) {
        this.title = title;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
