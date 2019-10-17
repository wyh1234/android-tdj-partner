package com.tdjpartner.model;

public class CouponsStatistics {
    /**
     * unused : 2
     * used : 0
     * expired : 0
     * 说明：unused：未使用，used：已使用，expired：已过期
     */
    private int unused;
    private int used;
    private int expired;

    public int getUnused() {
        return unused;
    }

    public void setUnused(int unused) {
        this.unused = unused;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }
}
