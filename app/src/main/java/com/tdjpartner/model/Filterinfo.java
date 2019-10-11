package com.tdjpartner.model;

public class Filterinfo {
    private boolean f;
    private String title;

    public Filterinfo(String title) {
        this.title = title;
    }

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
