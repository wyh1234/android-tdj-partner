package com.tdjpartner.model;

import java.util.List;

public class ClientSeachInfo {

    /**
     * total : 31
     * obj : [{"customerId":256,"auth":0,"name":"黄家小院","address":"丹江路苏宁电器对面雅斯特院内汉水上景酒店","boss":"黄艳伟","mobile":"13476404512","partnerName":"周兆琪","followTime":null,"websiteId":2,"regionCollNo":null,"regionNo":null,"lineCode":"15","customerLineCode":"2","todayAmount":null,"averageAmount":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":"黄伟","receiveMobile":"13476404512","deliveredTimeBegin":"8:00","deliveredTimeEnd":"9:00","punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":"32.058401","lon":"112.124531"}]
     */

    private int total;
    private List<ObjBean> obj;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * customerId : 256
         * auth : 0
         * name : 黄家小院
         * address : 丹江路苏宁电器对面雅斯特院内汉水上景酒店
         * boss : 黄艳伟
         * mobile : 13476404512
         * partnerName : 周兆琪
         * followTime : null
         * websiteId : 2
         * regionCollNo : null
         * regionNo : null
         * lineCode : 15
         * customerLineCode : 2
         * todayAmount : null
         * averageAmount : null
         * monthTimes : null
         * notOrderDays : null
         * notCallDays : null
         * userType : null
         * monthAmount : null
         * afterSaleTimes : null
         * noCallDay : null
         * receiveName : 黄伟
         * receiveMobile : 13476404512
         * deliveredTimeBegin : 8:00
         * deliveredTimeEnd : 9:00
         * punchDistance : null
         * dayOrderTimes : null
         * dayRegisterTimes : null
         * lat : 32.058401
         * lon : 112.124531
         */

        private int customerId;
        private int auth;
        private String name;
        private String address;
        private String boss;
        private String mobile;
        private String partnerName;
        private Object followTime;
        private int websiteId;
        private Object regionCollNo;
        private Object regionNo;
        private String lineCode;
        private String customerLineCode;
        private Object todayAmount;
        private Object averageAmount;
        private Object monthTimes;
        private Object notOrderDays;
        private Object notCallDays;
        private Object userType;
        private Object monthAmount;
        private Object afterSaleTimes;
        private Object noCallDay;
        private String receiveName;
        private String receiveMobile;
        private String deliveredTimeBegin;
        private String deliveredTimeEnd;
        private Object punchDistance;
        private Object dayOrderTimes;
        private Object dayRegisterTimes;
        private String lat;
        private String lon;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getAuth() {
            return auth;
        }

        public void setAuth(int auth) {
            this.auth = auth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBoss() {
            return boss;
        }

        public void setBoss(String boss) {
            this.boss = boss;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public Object getFollowTime() {
            return followTime;
        }

        public void setFollowTime(Object followTime) {
            this.followTime = followTime;
        }

        public int getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(int websiteId) {
            this.websiteId = websiteId;
        }

        public Object getRegionCollNo() {
            return regionCollNo;
        }

        public void setRegionCollNo(Object regionCollNo) {
            this.regionCollNo = regionCollNo;
        }

        public Object getRegionNo() {
            return regionNo;
        }

        public void setRegionNo(Object regionNo) {
            this.regionNo = regionNo;
        }

        public String getLineCode() {
            return lineCode;
        }

        public void setLineCode(String lineCode) {
            this.lineCode = lineCode;
        }

        public String getCustomerLineCode() {
            return customerLineCode;
        }

        public void setCustomerLineCode(String customerLineCode) {
            this.customerLineCode = customerLineCode;
        }

        public Object getTodayAmount() {
            return todayAmount;
        }

        public void setTodayAmount(Object todayAmount) {
            this.todayAmount = todayAmount;
        }

        public Object getAverageAmount() {
            return averageAmount;
        }

        public void setAverageAmount(Object averageAmount) {
            this.averageAmount = averageAmount;
        }

        public Object getMonthTimes() {
            return monthTimes;
        }

        public void setMonthTimes(Object monthTimes) {
            this.monthTimes = monthTimes;
        }

        public Object getNotOrderDays() {
            return notOrderDays;
        }

        public void setNotOrderDays(Object notOrderDays) {
            this.notOrderDays = notOrderDays;
        }

        public Object getNotCallDays() {
            return notCallDays;
        }

        public void setNotCallDays(Object notCallDays) {
            this.notCallDays = notCallDays;
        }

        public Object getUserType() {
            return userType;
        }

        public void setUserType(Object userType) {
            this.userType = userType;
        }

        public Object getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(Object monthAmount) {
            this.monthAmount = monthAmount;
        }

        public Object getAfterSaleTimes() {
            return afterSaleTimes;
        }

        public void setAfterSaleTimes(Object afterSaleTimes) {
            this.afterSaleTimes = afterSaleTimes;
        }

        public Object getNoCallDay() {
            return noCallDay;
        }

        public void setNoCallDay(Object noCallDay) {
            this.noCallDay = noCallDay;
        }

        public String getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(String receiveName) {
            this.receiveName = receiveName;
        }

        public String getReceiveMobile() {
            return receiveMobile;
        }

        public void setReceiveMobile(String receiveMobile) {
            this.receiveMobile = receiveMobile;
        }

        public String getDeliveredTimeBegin() {
            return deliveredTimeBegin;
        }

        public void setDeliveredTimeBegin(String deliveredTimeBegin) {
            this.deliveredTimeBegin = deliveredTimeBegin;
        }

        public String getDeliveredTimeEnd() {
            return deliveredTimeEnd;
        }

        public void setDeliveredTimeEnd(String deliveredTimeEnd) {
            this.deliveredTimeEnd = deliveredTimeEnd;
        }

        public Object getPunchDistance() {
            return punchDistance;
        }

        public void setPunchDistance(Object punchDistance) {
            this.punchDistance = punchDistance;
        }

        public Object getDayOrderTimes() {
            return dayOrderTimes;
        }

        public void setDayOrderTimes(Object dayOrderTimes) {
            this.dayOrderTimes = dayOrderTimes;
        }

        public Object getDayRegisterTimes() {
            return dayRegisterTimes;
        }

        public void setDayRegisterTimes(Object dayRegisterTimes) {
            this.dayRegisterTimes = dayRegisterTimes;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }
}
