package com.tdjpartner.model;

import java.util.List;


public class ShareShopListBean {

    private Integer total;
    private Integer ps;
    private Integer applyCount;
    private Integer orderCount;
    private Integer noOrderCount;
    private List<ListBean> list;
    private Integer pn;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        this.ps = ps;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getNoOrderCount() {
        return noOrderCount;
    }

    public void setNoOrderCount(Integer noOrderCount) {
        this.noOrderCount = noOrderCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public static class ListBean {
        private String accountCode;
        private String enterpriseCode;
        private String nickName;
        private Integer customerId;

        public String getAccountCode() {
            return accountCode;
        }

        public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
        }

        public String getEnterpriseCode() {
            return enterpriseCode;
        }

        public void setEnterpriseCode(String enterpriseCode) {
            this.enterpriseCode = enterpriseCode;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }
    }
}
