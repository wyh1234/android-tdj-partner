package com.tdjpartner.model;

import java.util.List;

public class NewHomeData {
    private List<RegisterTimesTopListBean> registerTimesTopList;
    private List<OrdersTimesTopList> ordersTimesTopList;

    public List<RegisterTimesTopListBean> getRegisterTimesTopList() {
        return registerTimesTopList;
    }

    public void setRegisterTimesTopList(List<RegisterTimesTopListBean> registerTimesTopList) {
        this.registerTimesTopList = registerTimesTopList;
    }

    public List<OrdersTimesTopList> getOrdersTimesTopList() {
        return ordersTimesTopList;
    }

    public void setOrdersTimesTopList(List<OrdersTimesTopList> ordersTimesTopList) {
        this.ordersTimesTopList = ordersTimesTopList;
    }
    public static class RegisterTimesTopListBean{
        String partnerName;
        String dayRegisterTimes;
        String name;

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public String getDayRegisterTimes() {
            return dayRegisterTimes;
        }

        public void setDayRegisterTimes(String dayRegisterTimes) {
            this.dayRegisterTimes = dayRegisterTimes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    public static class OrdersTimesTopList{
        String partnerName;
        String dayOrderTimes;
        String name;

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public String getDayOrderTimes() {
            return dayOrderTimes;
        }

        public void setDayOrderTimes(String dayOrderTimes) {
            this.dayOrderTimes = dayOrderTimes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
