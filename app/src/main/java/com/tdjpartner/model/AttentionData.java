package com.tdjpartner.model;
/*
* 关注信息
*
* */
public class AttentionData extends Message {
    private String title;

    public AttentionData(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
