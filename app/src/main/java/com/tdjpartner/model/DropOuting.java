package com.tdjpartner.model;

import android.content.Intent;

import java.math.BigDecimal;
import java.util.List;

public class DropOuting {


    /**
     * total : 2
     * obj : {"list":[{}]}
     */

    private int total;
    private List<ObjBean> obj;
    private int orderNum;
    private int callNum;
    private int followNum;
    private int followedNum;

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }

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

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getCallNum() {
        return callNum;
    }

    public void setCallNum(int callNum) {
        this.callNum = callNum;
    }

    public static class ObjBean{

        /**
         * customerId : 25741
         * auth : 0
         * name : lt门店03
         * address : 中山路534号
         * boss : lt03
         * mobile : 17799770003
         * partnerName : cscgs03
         * websiteId : 3
         * regionCollNo : E
         * regionNo : 99
         * lineCode : null
         * customerLineCode : null
         * todayAmount : 0.0
         * averageAmount : 0.0
         * monthTimes : 0
         * notOrderDays : null
         * notCallDays : 0
         */

        private int customerId;
        private int auth;
        private String name;
        private String address;
        private String boss;
        private String mobile;
        private String partnerName;
        private int websiteId;
        private String regionCollNo;
        private String regionNo;
        private Object lineCode;
        private Object customerLineCode;
        private BigDecimal todayAmount;
        private BigDecimal averageAmount;
        private int monthTimes;
        private Integer notOrderDays;
        private Integer notCallDays;

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

        public String getRegionNo() {
            return regionNo;
        }

        public void setRegionNo(String regionNo) {
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

        public BigDecimal getTodayAmount() {
            return todayAmount;
        }

        public void setTodayAmount(BigDecimal todayAmount) {
            this.todayAmount = todayAmount;
        }

        public BigDecimal getAverageAmount() {
            return averageAmount;
        }

        public void setAverageAmount(BigDecimal averageAmount) {
            this.averageAmount = averageAmount;
        }

        public int getMonthTimes() {
            return monthTimes;
        }

        public void setMonthTimes(int monthTimes) {
            this.monthTimes = monthTimes;
        }

        public Integer getNotOrderDays() {
            return notOrderDays;
        }

        public void setNotOrderDays(Integer notOrderDays) {
            this.notOrderDays = notOrderDays;
        }

        public Integer getNotCallDays() {
            return notCallDays;
        }

        public void setNotCallDays(Integer notCallDays) {
            this.notCallDays = notCallDays;
        }
    }
}
