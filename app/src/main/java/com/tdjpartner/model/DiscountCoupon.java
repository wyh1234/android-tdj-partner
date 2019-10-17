package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class DiscountCoupon {

    private int total;
    private List<ItemsBean> obj;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemsBean> getObj() {
        return obj;
    }

    public void setObj(List<ItemsBean> obj) {
        this.obj = obj;
    }

    public static class ItemsBean {
        /**
         * entityId : 2
         * title : 满1000使用
         * startTime : 2017-09-04 15:11
         * endTime : 2017-10-04 15:11
         * status : 0
         * amount : 250
         * purchaseAmount : 1000
         * couponDesc : 仅适用新鲜蔬菜
         * createTime : 2017-09-04 15:11
         * receiveTime : 2017-09-04 15:09
         */

        private int entityId;
        private String title;
        private String startTime;
        private String endTime;
        private int status;
        private BigDecimal amount = BigDecimal.ZERO;
        private BigDecimal purchaseAmount = BigDecimal.ZERO;
        private String couponDesc;
        private String createTime;
        private String receiveTime;

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getPurchaseAmount() {
            return purchaseAmount;
        }

        public void setPurchaseAmount(BigDecimal purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }
    }
}
