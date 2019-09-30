package com.tdjpartner.model;

import java.util.List;

public class TodayData extends Message {
    private String title;
    private int time;
    private int type;
    private List<TodayDataItem> todayDataItemList;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<TodayDataItem> getTodayDataItemList() {
        return todayDataItemList;
    }

    public void setTodayDataItemList(List<TodayDataItem> todayDataItemList) {
        this.todayDataItemList = todayDataItemList;
    }

    public static class TodayDataItem{
        private int total;
        private String name;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
