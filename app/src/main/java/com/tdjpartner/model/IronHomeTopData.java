package com.tdjpartner.model;

import java.util.List;

public class IronHomeTopData {
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
        public int customerId; //9561, //用户id
        public int websiteId; //null,
        public int lineCode; //null,
        public int customerLineCode; //null,
        public int regionCollNo; //null,monthAfterSaleTimes
        public int todayTimes; //null,
        public int todayAfterSaleTimes; //null,
        public int monthTimes; //null,
        public int afterSaleTimes; //null,
        public int dayOrderTimes; //null,
        public int dayRegisterTimes; //0, //日注册数
        public int monthAfterSaleTimes; //null,
        public int thirtyTimesNum; //null,
        public int notOrderDays; //null,
        public int notCallDays; //null,
        public int userType; //null,
        public int lastMonthActiveNum; //null,
        public int activeNum; //null,
        public int callNum; //null,
        public int monthRegisterNum; //null,
        public int monthActiveNum; //100, //月日活
        public int monthCallNum; //null,
        public int lastMonthAvgActiveNum; //null,
        public int monthAvgActiveNum; //null, //月均日活
        public int firstOrderNum; //0, //日新开数
        public int lastMonthFirstOrderNum; //null,
        public int monthFirstOrderNum; //null,
        public int yesterdayActiveNum; //null,
        public int noCallDay; //null,

        public float todayAmount; //0.000000, //GMV
        public float averageAmount; //null,
        public float monthAmount; //0.00, //月GMV
        public float monthAverageAmount; //null,
        public float addMonthAmount; //null,
        public float monthAfterSaleAmount; //null

        public String auth; //null,
        public String name; //null, //上级名字
        public String address; //null,
        public String boss; //null,
        public String mobile; //null,
        public String partnerName; //"潘朝刚", //业务员名字
        public String followTime; //null,
        public String regionNo; //null,
        public String receiveName; //null,
        public String receiveMobile; //null,
        public String deliveredTimeBegin; //null,
        public String deliveredTimeEnd; //null,
        public String punchDistance; //null,
        public String lat; //null,
        public String lon; //null,
        public String headUrl; //null,
        public String createTime; //null,

        @Override
        public String toString() {
            return "RegisterTimesTopListBean{" +
                    "customerId=" + customerId +
                    ", websiteId=" + websiteId +
                    ", lineCode=" + lineCode +
                    ", customerLineCode=" + customerLineCode +
                    ", regionCollNo=" + regionCollNo +
                    ", todayTimes=" + todayTimes +
                    ", todayAfterSaleTimes=" + todayAfterSaleTimes +
                    ", monthTimes=" + monthTimes +
                    ", afterSaleTimes=" + afterSaleTimes +
                    ", dayOrderTimes=" + dayOrderTimes +
                    ", dayRegisterTimes=" + dayRegisterTimes +
                    ", monthAfterSaleTimes=" + monthAfterSaleTimes +
                    ", thirtyTimesNum=" + thirtyTimesNum +
                    ", notOrderDays=" + notOrderDays +
                    ", notCallDays=" + notCallDays +
                    ", userType=" + userType +
                    ", lastMonthActiveNum=" + lastMonthActiveNum +
                    ", activeNum=" + activeNum +
                    ", callNum=" + callNum +
                    ", monthRegisterNum=" + monthRegisterNum +
                    ", monthActiveNum=" + monthActiveNum +
                    ", monthCallNum=" + monthCallNum +
                    ", lastMonthAvgActiveNum=" + lastMonthAvgActiveNum +
                    ", monthAvgActiveNum=" + monthAvgActiveNum +
                    ", firstOrderNum=" + firstOrderNum +
                    ", lastMonthFirstOrderNum=" + lastMonthFirstOrderNum +
                    ", monthFirstOrderNum=" + monthFirstOrderNum +
                    ", yesterdayActiveNum=" + yesterdayActiveNum +
                    ", noCallDay=" + noCallDay +
                    ", todayAmount=" + todayAmount +
                    ", averageAmount=" + averageAmount +
                    ", monthAmount=" + monthAmount +
                    ", monthAverageAmount=" + monthAverageAmount +
                    ", addMonthAmount=" + addMonthAmount +
                    ", monthAfterSaleAmount=" + monthAfterSaleAmount +
                    ", auth='" + auth + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", boss='" + boss + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", followTime='" + followTime + '\'' +
                    ", regionNo='" + regionNo + '\'' +
                    ", receiveName='" + receiveName + '\'' +
                    ", receiveMobile='" + receiveMobile + '\'' +
                    ", deliveredTimeBegin='" + deliveredTimeBegin + '\'' +
                    ", deliveredTimeEnd='" + deliveredTimeEnd + '\'' +
                    ", punchDistance='" + punchDistance + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", headUrl='" + headUrl + '\'' +
                    ", createTime='" + createTime + '\'' +
                    '}';
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
