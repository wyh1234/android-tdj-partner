package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class WithdrawDetalis {
    private int total;
    private List<WithdrawDetalisData> obj;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<WithdrawDetalisData> getObj() {
        return obj;
    }

    public void setObj(List<WithdrawDetalisData> obj) {
        this.obj = obj;
    }
    public static class WithdrawDetalisData {

        /**
         * entityId : 41
         * createTime : 2019-10-14 16:52:47
         * updateTime : 2019-10-14 16:52:47
         * siteId : null
         * bankName : 招商银行
         * idCard : 42102219910923511X
         * accountNo : 6214830272921221
         * bankAddress :
         * capitalWithdrawal : 10000
         * userId : 25653
         * userName : 万义华
         * userPhone : 15002728051
         * isWithdrawal : N
         * status : 0
         * reason : null
         * businessId : 0
         * cmbBusinessNo : APP2019101416475225653
         * reqNbr : null
         * paymentType : 1
         * remark : null
         * partnerLevel : 2
         */

        private int entityId;
        private String createTime;
        private String updateTime;
        private Object siteId;
        private String bankName;
        private String idCard;
        private String accountNo;
        private String bankAddress;
        private BigDecimal capitalWithdrawal;
        private int userId;
        private String userName;
        private String userPhone;
        private String isWithdrawal;
        private int status;
        private Object reason;
        private int businessId;
        private String cmbBusinessNo;
        private Object reqNbr;
        private int paymentType;
        private Object remark;
        private String partnerLevel;

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getSiteId() {
            return siteId;
        }

        public void setSiteId(Object siteId) {
            this.siteId = siteId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public BigDecimal getCapitalWithdrawal() {
            return capitalWithdrawal;
        }

        public void setCapitalWithdrawal(BigDecimal capitalWithdrawal) {
            this.capitalWithdrawal = capitalWithdrawal;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getIsWithdrawal() {
            return isWithdrawal;
        }

        public void setIsWithdrawal(String isWithdrawal) {
            this.isWithdrawal = isWithdrawal;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public String getCmbBusinessNo() {
            return cmbBusinessNo;
        }

        public void setCmbBusinessNo(String cmbBusinessNo) {
            this.cmbBusinessNo = cmbBusinessNo;
        }

        public Object getReqNbr() {
            return reqNbr;
        }

        public void setReqNbr(Object reqNbr) {
            this.reqNbr = reqNbr;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getPartnerLevel() {
            return partnerLevel;
        }

        public void setPartnerLevel(String partnerLevel) {
            this.partnerLevel = partnerLevel;
        }
    }
}
