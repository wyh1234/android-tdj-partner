package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class EarningsHistory {


    /**
     * total : 1
     * obj : {"orderMoney":1.9E-5,"money":1.9E-5,"list":[{"id":2,"customerId":25407,"bizId":"65503006606282014728","bizType":1,"commissionType":3,"entityId":25050,"amount":0.09296,"amountPercentage":2.0E-4,"amountCommission":1.9E-5,"commissionId":25653,"createTime":"2019-10-16 17:16:16","businessDate":"2019-10-16 00:00:00","customerName":"五谷荟","customerNickName":"胡文松","siteId":3,"siteName":"武汉","partnerName":"hahhaa","partnerPhone":"18272052542","grade":2,"userId":25653}],"subMoney":0}
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
         * orderMoney : 1.9E-5
         * money : 1.9E-5
         * list : [{"id":2,"customerId":25407,"bizId":"65503006606282014728","bizType":1,"commissionType":3,"entityId":25050,"amount":0.09296,"amountPercentage":2.0E-4,"amountCommission":1.9E-5,"commissionId":25653,"createTime":"2019-10-16 17:16:16","businessDate":"2019-10-16 00:00:00","customerName":"五谷荟","customerNickName":"胡文松","siteId":3,"siteName":"武汉","partnerName":"hahhaa","partnerPhone":"18272052542","grade":2,"userId":25653}]
         * subMoney : 0
         */

        private BigDecimal orderMoney;
        private BigDecimal money;
        private BigDecimal subMoney;
        private List<ListBean> list;

        public BigDecimal getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(BigDecimal orderMoney) {
            this.orderMoney = orderMoney;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public BigDecimal getSubMoney() {
            return subMoney;
        }

        public void setSubMoney(BigDecimal subMoney) {
            this.subMoney = subMoney;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * customerId : 25407
             * bizId : 65503006606282014728
             * bizType : 1
             * commissionType : 3
             * entityId : 25050
             * amount : 0.09296
             * amountPercentage : 2.0E-4
             * amountCommission : 1.9E-5
             * commissionId : 25653
             * createTime : 2019-10-16 17:16:16
             * businessDate : 2019-10-16 00:00:00
             * customerName : 五谷荟
             * customerNickName : 胡文松
             * siteId : 3
             * siteName : 武汉
             * partnerName : hahhaa
             * partnerPhone : 18272052542
             * grade : 2
             * userId : 25653
             */

            private int id;
            private int customerId;
            private String bizId;
            private int bizType;
            private int commissionType;
            private int entityId;
            private double amount;
            private double amountPercentage;
            private BigDecimal amountCommission;
            private int commissionId;
            private String createTime;
            private String businessDate;
            private String customerName;
            private String customerNickName;
            private int siteId;
            private String siteName;
            private String partnerName;
            private String partnerPhone;
            private int grade;
            private int userId;
            private String customerPhone;

            public String getCustomerPhone() {
                return customerPhone;
            }

            public void setCustomerPhone(String customerPhone) {
                this.customerPhone = customerPhone;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCustomerId() {
                return customerId;
            }

            public void setCustomerId(int customerId) {
                this.customerId = customerId;
            }

            public String getBizId() {
                return bizId;
            }

            public void setBizId(String bizId) {
                this.bizId = bizId;
            }

            public int getBizType() {
                return bizType;
            }

            public void setBizType(int bizType) {
                this.bizType = bizType;
            }

            public int getCommissionType() {
                return commissionType;
            }

            public void setCommissionType(int commissionType) {
                this.commissionType = commissionType;
            }

            public int getEntityId() {
                return entityId;
            }

            public void setEntityId(int entityId) {
                this.entityId = entityId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getAmountPercentage() {
                return amountPercentage;
            }

            public void setAmountPercentage(double amountPercentage) {
                this.amountPercentage = amountPercentage;
            }

            public BigDecimal getAmountCommission() {
                return amountCommission;
            }

            public void setAmountCommission(BigDecimal amountCommission) {
                this.amountCommission = amountCommission;
            }

            public int getCommissionId() {
                return commissionId;
            }

            public void setCommissionId(int commissionId) {
                this.commissionId = commissionId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getBusinessDate() {
                return businessDate;
            }

            public void setBusinessDate(String businessDate) {
                this.businessDate = businessDate;
            }

            public String getCustomerName() {
                return customerName;
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getCustomerNickName() {
                return customerNickName;
            }

            public void setCustomerNickName(String customerNickName) {
                this.customerNickName = customerNickName;
            }

            public int getSiteId() {
                return siteId;
            }

            public void setSiteId(int siteId) {
                this.siteId = siteId;
            }

            public String getSiteName() {
                return siteName;
            }

            public void setSiteName(String siteName) {
                this.siteName = siteName;
            }

            public String getPartnerName() {
                return partnerName;
            }

            public void setPartnerName(String partnerName) {
                this.partnerName = partnerName;
            }

            public String getPartnerPhone() {
                return partnerPhone;
            }

            public void setPartnerPhone(String partnerPhone) {
                this.partnerPhone = partnerPhone;
            }

            public int getGrade() {
                return grade;
            }

            public void setGrade(int grade) {
                this.grade = grade;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
