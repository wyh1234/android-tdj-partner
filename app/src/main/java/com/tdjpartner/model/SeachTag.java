package com.tdjpartner.model;

import java.io.Serializable;

public class SeachTag implements Serializable {
    private String tag;

    public SeachTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
