package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class ToMakeMoney {


    /**
     * userId : 25653
     * customerCount : 0
     * myCountMoney : 10000.0
     * newTenDate : ["恭喜123****13获得0.092960收益奖励","恭喜123****876获得0.000019收益奖励"]
     * topTenDate : [{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":0.09296,"commissionId":null,"createTime":null,"businessDate":null,"customerName":null,"customerNickName":null,"siteId":null,"siteName":null,"partnerName":null,"partnerPhone":"123****13","grade":null,"userId":null},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":1.9E-5,"commissionId":null,"createTime":null,"businessDate":null,"customerName":null,"customerNickName":null,"siteId":null,"siteName":null,"partnerName":null,"partnerPhone":"123****876","grade":null,"userId":null}]
     */

    private String userId;
    private int customerCount;
    private BigDecimal myCountMoney;
    private BigDecimal maxMoney;
    private List<String> tenNewData;
    private List<TopTenDateBean> topTenData;

    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public BigDecimal getMyCountMoney() {
        return myCountMoney;
    }

    public void setMyCountMoney(BigDecimal myCountMoney) {
        this.myCountMoney = myCountMoney;
    }

    public List<String> getNewTenDate() {
        return tenNewData;
    }

    public void setNewTenDate(List<String> tenNewData) {
        this.tenNewData = tenNewData;
    }

    public List<TopTenDateBean> getTopTenDate() {
        return topTenData;
    }

    public void setTopTenDate(List<TopTenDateBean> tenNewData) {
        this.topTenData = topTenData;
    }

    public static class TopTenDateBean {
        /**
         * id : null
         * customerId : null
         * bizId : null
         * bizType : null
         * commissionType : null
         * entityId : null
         * amount : null
         * amountPercentage : null
         * amountCommission : 0.09296
         * commissionId : null
         * createTime : null
         * businessDate : null
         * customerName : null
         * customerNickName : null
         * siteId : null
         * siteName : null
         * partnerName : null
         * partnerPhone : 123****13
         * grade : null
         * userId : null
         */

        private Object id;
        private Object customerId;
        private Object bizId;
        private Object bizType;
        private Object commissionType;
        private Object entityId;
        private Object amount;
        private Object amountPercentage;
        private double amountCommission;
        private Object commissionId;
        private Object createTime;
        private Object businessDate;
        private Object customerName;
        private Object customerNickName;
        private Object siteId;
        private Object siteName;
        private Object partnerName;
        private String partnerPhone;
        private Object grade;
        private Object userId;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getBizId() {
            return bizId;
        }

        public void setBizId(Object bizId) {
            this.bizId = bizId;
        }

        public Object getBizType() {
            return bizType;
        }

        public void setBizType(Object bizType) {
            this.bizType = bizType;
        }

        public Object getCommissionType() {
            return commissionType;
        }

        public void setCommissionType(Object commissionType) {
            this.commissionType = commissionType;
        }

        public Object getEntityId() {
            return entityId;
        }

        public void setEntityId(Object entityId) {
            this.entityId = entityId;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getAmountPercentage() {
            return amountPercentage;
        }

        public void setAmountPercentage(Object amountPercentage) {
            this.amountPercentage = amountPercentage;
        }

        public double getAmountCommission() {
            return amountCommission;
        }

        public void setAmountCommission(double amountCommission) {
            this.amountCommission = amountCommission;
        }

        public Object getCommissionId() {
            return commissionId;
        }

        public void setCommissionId(Object commissionId) {
            this.commissionId = commissionId;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getBusinessDate() {
            return businessDate;
        }

        public void setBusinessDate(Object businessDate) {
            this.businessDate = businessDate;
        }

        public Object getCustomerName() {
            return customerName;
        }

        public void setCustomerName(Object customerName) {
            this.customerName = customerName;
        }

        public Object getCustomerNickName() {
            return customerNickName;
        }

        public void setCustomerNickName(Object customerNickName) {
            this.customerNickName = customerNickName;
        }

        public Object getSiteId() {
            return siteId;
        }

        public void setSiteId(Object siteId) {
            this.siteId = siteId;
        }

        public Object getSiteName() {
            return siteName;
        }

        public void setSiteName(Object siteName) {
            this.siteName = siteName;
        }

        public Object getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(Object partnerName) {
            this.partnerName = partnerName;
        }

        public String getPartnerPhone() {
            return partnerPhone;
        }

        public void setPartnerPhone(String partnerPhone) {
            this.partnerPhone = partnerPhone;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }
    }
}
