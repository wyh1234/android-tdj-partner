package com.tdjpartner.model;

import java.util.List;

public class HomeDataDetails {


    /**
     * total : 3
     * obj : {"list":[{"customerId":0,"auth":null,"name":null,"address":null,"boss":null,"mobile":null,"partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":0,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":3,"notOrderDays":0,"notCallDays":null,"userType":null,"monthAmount":0,"afterSaleTimes":0,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":0,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":0,"monthAfterSaleTimes":0,"thirtyTimesNum":0,"createTime":null}],"notOrderCustomerNum":1,"orderCustomerNum":2}
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
         * list : [{"customerId":0,"auth":null,"name":null,"address":null,"boss":null,"mobile":null,"partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":0,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":3,"notOrderDays":0,"notCallDays":null,"userType":null,"monthAmount":0,"afterSaleTimes":0,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":0,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":0,"monthAfterSaleTimes":0,"thirtyTimesNum":0,"createTime":null}]
         * notOrderCustomerNum : 1
         * orderCustomerNum : 2
         */

        private int notOrderCustomerNum;
        private int orderCustomerNum;
        private int totalCustomerNum;
        private int dayRegisterTimes;
        private int callNum;
        private List<ListBean> list;

        public int getDayRegisterTimes() {
            return dayRegisterTimes;
        }

        public void setDayRegisterTimes(int dayRegisterTimes) {
            this.dayRegisterTimes = dayRegisterTimes;
        }

        public int getCallNum() {
            return callNum;
        }

        public void setCallNum(int callNum) {
            this.callNum = callNum;
        }

        public int getTotalCustomerNum() {
            return totalCustomerNum;
        }

        public void setTotalCustomerNum(int totalCustomerNum) {
            this.totalCustomerNum = totalCustomerNum;
        }

        public int getNotOrderCustomerNum() {
            return notOrderCustomerNum;
        }

        public void setNotOrderCustomerNum(int notOrderCustomerNum) {
            this.notOrderCustomerNum = notOrderCustomerNum;
        }

        public int getOrderCustomerNum() {
            return orderCustomerNum;
        }

        public void setOrderCustomerNum(int orderCustomerNum) {
            this.orderCustomerNum = orderCustomerNum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * customerId : 0
             * auth : null
             * name : null
             * address : null
             * boss : null
             * mobile : null
             * partnerName : null
             * followTime : null
             * websiteId : null
             * regionCollNo : null
             * regionNo : null
             * lineCode : null
             * customerLineCode : null
             * todayAmount : 0.0
             * averageAmount : null
             * todayTimes : null
             * todayAfterSaleTimes : null
             * monthTimes : 3
             * notOrderDays : 0
             * notCallDays : null
             * userType : null
             * monthAmount : 0.0
             * afterSaleTimes : 0
             * noCallDay : null
             * receiveName : null
             * receiveMobile : null
             * deliveredTimeBegin : null
             * deliveredTimeEnd : null
             * punchDistance : null
             * dayOrderTimes : 0
             * dayRegisterTimes : null
             * lat : null
             * lon : null
             * headUrl : null
             * lastMonthActiveNum : null
             * activeNum : null
             * callNum : null
             * monthRegisterNum : null
             * monthActiveNum : null
             * monthCallNum : null
             * lastMonthAvgActiveNum : null
             * monthAvgActiveNum : null
             * firstOrderNum : null
             * lastMonthFirstOrderNum : null
             * monthFirstOrderNum : null
             * monthAverageAmount : 0.0
             * monthAfterSaleTimes : 0
             * thirtyTimesNum : 0
             * createTime : null
             */

            private int customerId;
            private Integer auth;
            private String name;
            private String address;
            private String boss;
            private String mobile;
            private String partnerName;
            private Object followTime;
            private Object websiteId;
            private String regionCollNo;
            private Object regionNo;
            private Object lineCode;
            private Object customerLineCode;
            private double todayAmount;
            private Object averageAmount;
            private Object todayTimes;
            private Object todayAfterSaleTimes;
            private int monthTimes;
            private int notOrderDays;
            private Object notCallDays;
            private Object userType;
            private double monthAmount;
            private int afterSaleTimes;
            private Object noCallDay;
            private Object receiveName;
            private Object receiveMobile;
            private Object deliveredTimeBegin;
            private Object deliveredTimeEnd;
            private Object punchDistance;
            private int dayOrderTimes;
            private Object dayRegisterTimes;
            private Object lat;
            private Object lon;
            private Object headUrl;
            private Object lastMonthActiveNum;
            private Object activeNum;
            private Object callNum;
            private Object monthRegisterNum;
            private Object monthActiveNum;
            private Object monthCallNum;
            private Object lastMonthAvgActiveNum;
            private Object monthAvgActiveNum;
            private Object firstOrderNum;
            private Object lastMonthFirstOrderNum;
            private Object monthFirstOrderNum;
            private double monthAverageAmount;
            private int monthAfterSaleTimes;
            private int thirtyTimesNum;
            private Object createTime;

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public Integer getAuth() {
                return auth==null?0:auth;
            }

            public void setAuth(Integer auth) {
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
                return partnerName==null?"无":partnerName;
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

            public Object getWebsiteId() {
                return websiteId;
            }

            public void setWebsiteId(Object websiteId) {
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

            public Object getAverageAmount() {
                return averageAmount;
            }

            public void setAverageAmount(Object averageAmount) {
                this.averageAmount = averageAmount;
            }

            public Object getTodayTimes() {
                return todayTimes;
            }

            public void setTodayTimes(Object todayTimes) {
                this.todayTimes = todayTimes;
            }

            public Object getTodayAfterSaleTimes() {
                return todayAfterSaleTimes;
            }

            public void setTodayAfterSaleTimes(Object todayAfterSaleTimes) {
                this.todayAfterSaleTimes = todayAfterSaleTimes;
            }

            public int getMonthTimes() {
                return monthTimes;
            }

            public void setMonthTimes(int monthTimes) {
                this.monthTimes = monthTimes;
            }

            public int getNotOrderDays() {
                return notOrderDays;
            }

            public void setNotOrderDays(int notOrderDays) {
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

            public double getMonthAmount() {
                return monthAmount;
            }

            public void setMonthAmount(double monthAmount) {
                this.monthAmount = monthAmount;
            }

            public int getAfterSaleTimes() {
                return afterSaleTimes;
            }

            public void setAfterSaleTimes(int afterSaleTimes) {
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

            public int getDayOrderTimes() {
                return dayOrderTimes;
            }

            public void setDayOrderTimes(int dayOrderTimes) {
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

            public Object getLastMonthActiveNum() {
                return lastMonthActiveNum;
            }

            public void setLastMonthActiveNum(Object lastMonthActiveNum) {
                this.lastMonthActiveNum = lastMonthActiveNum;
            }

            public Object getActiveNum() {
                return activeNum;
            }

            public void setActiveNum(Object activeNum) {
                this.activeNum = activeNum;
            }

            public Object getCallNum() {
                return callNum;
            }

            public void setCallNum(Object callNum) {
                this.callNum = callNum;
            }

            public Object getMonthRegisterNum() {
                return monthRegisterNum;
            }

            public void setMonthRegisterNum(Object monthRegisterNum) {
                this.monthRegisterNum = monthRegisterNum;
            }

            public Object getMonthActiveNum() {
                return monthActiveNum;
            }

            public void setMonthActiveNum(Object monthActiveNum) {
                this.monthActiveNum = monthActiveNum;
            }

            public Object getMonthCallNum() {
                return monthCallNum;
            }

            public void setMonthCallNum(Object monthCallNum) {
                this.monthCallNum = monthCallNum;
            }

            public Object getLastMonthAvgActiveNum() {
                return lastMonthAvgActiveNum;
            }

            public void setLastMonthAvgActiveNum(Object lastMonthAvgActiveNum) {
                this.lastMonthAvgActiveNum = lastMonthAvgActiveNum;
            }

            public Object getMonthAvgActiveNum() {
                return monthAvgActiveNum;
            }

            public void setMonthAvgActiveNum(Object monthAvgActiveNum) {
                this.monthAvgActiveNum = monthAvgActiveNum;
            }

            public Object getFirstOrderNum() {
                return firstOrderNum;
            }

            public void setFirstOrderNum(Object firstOrderNum) {
                this.firstOrderNum = firstOrderNum;
            }

            public Object getLastMonthFirstOrderNum() {
                return lastMonthFirstOrderNum;
            }

            public void setLastMonthFirstOrderNum(Object lastMonthFirstOrderNum) {
                this.lastMonthFirstOrderNum = lastMonthFirstOrderNum;
            }

            public Object getMonthFirstOrderNum() {
                return monthFirstOrderNum;
            }

            public void setMonthFirstOrderNum(Object monthFirstOrderNum) {
                this.monthFirstOrderNum = monthFirstOrderNum;
            }

            public double getMonthAverageAmount() {
                return monthAverageAmount;
            }

            public void setMonthAverageAmount(double monthAverageAmount) {
                this.monthAverageAmount = monthAverageAmount;
            }

            public int getMonthAfterSaleTimes() {
                return monthAfterSaleTimes;
            }

            public void setMonthAfterSaleTimes(int monthAfterSaleTimes) {
                this.monthAfterSaleTimes = monthAfterSaleTimes;
            }

            public int getThirtyTimesNum() {
                return thirtyTimesNum;
            }

            public void setThirtyTimesNum(int thirtyTimesNum) {
                this.thirtyTimesNum = thirtyTimesNum;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }
        }
    }
}
