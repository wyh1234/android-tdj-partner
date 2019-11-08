package com.tdjpartner.model;

import java.io.Serializable;
import java.util.List;

public class SettingPerson implements Serializable {


    /**
     * total : 20501
     * obj : {"list":[{"customerId":43,"auth":0,"name":"万达诸葛烤鱼","address":"湖北省襄阳市长虹北路9号万达广场室内步行街3-367.368","boss":"李辉","mobile":"18327550307","partnerName":null,"followTime":null,"websiteId":2,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":0,"averageAmount":0,"monthTimes":0,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null}],"newCustomerNum":17535,"oldCustomerNum":2966}
     */

    private int total;
    private ObjBean obj;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * list : [{"customerId":43,"auth":0,"name":"万达诸葛烤鱼","address":"湖北省襄阳市长虹北路9号万达广场室内步行街3-367.368","boss":"李辉","mobile":"18327550307","partnerName":null,"followTime":null,"websiteId":2,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":0,"averageAmount":0,"monthTimes":0,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null}]
         * newCustomerNum : 17535
         * oldCustomerNum : 2966
         */

        private int newCustomerNum;
        private int oldCustomerNum;
        private List<ListBean> list;

        public int getNewCustomerNum() {
            return newCustomerNum;
        }

        public void setNewCustomerNum(int newCustomerNum) {
            this.newCustomerNum = newCustomerNum;
        }

        public int getOldCustomerNum() {
            return oldCustomerNum;
        }

        public void setOldCustomerNum(int oldCustomerNum) {
            this.oldCustomerNum = oldCustomerNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
            /**
             * customerId : 43
             * auth : 0
             * name : 万达诸葛烤鱼
             * address : 湖北省襄阳市长虹北路9号万达广场室内步行街3-367.368
             * boss : 李辉
             * mobile : 18327550307
             * partnerName : null
             * followTime : null
             * websiteId : 2
             * regionCollNo : null
             * regionNo : null
             * lineCode : null
             * customerLineCode : null
             * todayAmount : 0.0
             * averageAmount : 0.0
             * monthTimes : 0
             * notOrderDays : null
             * notCallDays : null
             * userType : null
             * monthAmount : null
             * afterSaleTimes : null
             * noCallDay : null
             * receiveName : null
             * receiveMobile : null
             * deliveredTimeBegin : null
             * deliveredTimeEnd : null
             * punchDistance : null
             * dayOrderTimes : null
             * dayRegisterTimes : null
             * lat : null
             * lon : null
             * headUrl : null
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
            private String regionCollNo;
            private Object regionNo;
            private Object lineCode;
            private Object customerLineCode;
            private double todayAmount;
            private double averageAmount;
            private int monthTimes;
            private Object notOrderDays;
            private Object notCallDays;
            private Object userType;
            private Object monthAmount;
            private Object afterSaleTimes;
            private Object noCallDay;
            private Object receiveName;
            private Object receiveMobile;
            private Object deliveredTimeBegin;
            private Object deliveredTimeEnd;
            private Object punchDistance;
            private Object dayOrderTimes;
            private Object dayRegisterTimes;
            private Object lat;
            private Object lon;
            private Object headUrl;
            private int monthAfterSaleTimes;
            private int todayTimes;
            private int todayAfterSaleTimes;

            public int getTodayTimes() {
                return todayTimes;
            }

            public void setTodayTimes(int todayTimes) {
                this.todayTimes = todayTimes;
            }

            public int getTodayAfterSaleTimes() {
                return todayAfterSaleTimes;
            }

            public void setTodayAfterSaleTimes(int todayAfterSaleTimes) {
                this.todayAfterSaleTimes = todayAfterSaleTimes;
            }

            public int getMonthAfterSaleTimes() {
                return monthAfterSaleTimes;
            }

            public void setMonthAfterSaleTimes(int monthAfterSaleTimes) {
                this.monthAfterSaleTimes = monthAfterSaleTimes;
            }

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

            public String getRegionCollNo() {
                return regionCollNo;
            }

            public void setRegionCollNo(String regionCollNo) {
                this.regionCollNo = regionCollNo;
            }

            public Object getRegionNo() {
                return regionNo;
            }

            public void setRegionNo(Object regionNo) {
                this.regionNo = regionNo;
            }

            public Object getLineCode() {
                return lineCode;
            }

            public void setLineCode(Object lineCode) {
                this.lineCode = lineCode;
            }

            public Object getCustomerLineCode() {
                return customerLineCode;
            }

            public void setCustomerLineCode(Object customerLineCode) {
                this.customerLineCode = customerLineCode;
            }

            public double getTodayAmount() {
                return todayAmount;
            }

            public void setTodayAmount(double todayAmount) {
                this.todayAmount = todayAmount;
            }

            public double getAverageAmount() {
                return averageAmount;
            }

            public void setAverageAmount(double averageAmount) {
                this.averageAmount = averageAmount;
            }

            public int getMonthTimes() {
                return monthTimes;
            }

            public void setMonthTimes(int monthTimes) {
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

            public Object getReceiveName() {
                return receiveName;
            }

            public void setReceiveName(Object receiveName) {
                this.receiveName = receiveName;
            }

            public Object getReceiveMobile() {
                return receiveMobile;
            }

            public void setReceiveMobile(Object receiveMobile) {
                this.receiveMobile = receiveMobile;
            }

            public Object getDeliveredTimeBegin() {
                return deliveredTimeBegin;
            }

            public void setDeliveredTimeBegin(Object deliveredTimeBegin) {
                this.deliveredTimeBegin = deliveredTimeBegin;
            }

            public Object getDeliveredTimeEnd() {
                return deliveredTimeEnd;
            }

            public void setDeliveredTimeEnd(Object deliveredTimeEnd) {
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

            public Object getLat() {
                return lat;
            }

            public void setLat(Object lat) {
                this.lat = lat;
            }

            public Object getLon() {
                return lon;
            }

            public void setLon(Object lon) {
                this.lon = lon;
            }

            public Object getHeadUrl() {
                return headUrl;
            }

            public void setHeadUrl(Object headUrl) {
                this.headUrl = headUrl;
            }
        }
    }
}
