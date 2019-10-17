package com.tdjpartner.model;

import java.util.List;

public class InvitationHistory {


    /**
     * total : 33
     * obj : [{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-27 17:46:36","businessDate":null,"customerName":"口福小栈","customerNickName":"孙志豪","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"15171417230"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-28 15:06:20","businessDate":null,"customerName":"沙县小吃","customerNickName":"张先生","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"13097201628"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-28 15:46:02","businessDate":null,"customerName":"湘味人家","customerNickName":"吴红霞","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"18271463826"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-28 16:44:08","businessDate":null,"customerName":"盖饭小炒","customerNickName":"韩琴","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"13554286972"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-28 16:52:24","businessDate":null,"customerName":"黄焖鸡","customerNickName":"景老板","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"17740671852"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-29 15:57:37","businessDate":null,"customerName":"传香古色","customerNickName":"陈小华","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"15938208022"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-30 10:46:23","businessDate":null,"customerName":"淑女丰饭","customerNickName":"谢赛","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"17386650992"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-05-31 15:41:51","businessDate":null,"customerName":"三杨烤肉","customerNickName":"刘女士","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"18710559920"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-06-03 17:06:53","businessDate":null,"customerName":"芳芳菜馆","customerNickName":"丁城","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"13247155591"},{"id":null,"customerId":null,"bizId":null,"bizType":null,"commissionType":null,"entityId":null,"amount":null,"amountPercentage":null,"amountCommission":null,"commissionId":null,"createTime":"2019-06-04 15:41:07","businessDate":null,"customerName":"特色腰花馆","customerNickName":"吕老板","siteId":null,"siteName":null,"partnerName":null,"partnerPhone":null,"grade":null,"userId":null,"isActive":1,"customerPhone":"19979056592"}]
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
         * id : null
         * customerId : null
         * bizId : null
         * bizType : null
         * commissionType : null
         * entityId : null
         * amount : null
         * amountPercentage : null
         * amountCommission : null
         * commissionId : null
         * createTime : 2019-05-27 17:46:36
         * businessDate : null
         * customerName : 口福小栈
         * customerNickName : 孙志豪
         * siteId : null
         * siteName : null
         * partnerName : null
         * partnerPhone : null
         * grade : null
         * userId : null
         * isActive : 1
         * customerPhone : 15171417230
         */

        private Object id;
        private Object customerId;
        private Object bizId;
        private Object bizType;
        private Object commissionType;
        private Object entityId;
        private Object amount;
        private Object amountPercentage;
        private Object amountCommission;
        private Object commissionId;
        private String createTime;
        private Object businessDate;
        private String customerName;
        private String customerNickName;
        private Object siteId;
        private Object siteName;
        private Object partnerName;
        private Object partnerPhone;
        private Object grade;
        private Object userId;
        private int isActive;
        private String customerPhone;

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

        public Object getAmountCommission() {
            return amountCommission;
        }

        public void setAmountCommission(Object amountCommission) {
            this.amountCommission = amountCommission;
        }

        public Object getCommissionId() {
            return commissionId;
        }

        public void setCommissionId(Object commissionId) {
            this.commissionId = commissionId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getBusinessDate() {
            return businessDate;
        }

        public void setBusinessDate(Object businessDate) {
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

        public Object getPartnerPhone() {
            return partnerPhone;
        }

        public void setPartnerPhone(Object partnerPhone) {
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

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }

        public String getCustomerPhone() {
            return customerPhone;
        }

        public void setCustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
        }
    }
}
