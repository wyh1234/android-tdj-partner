package com.tdjpartner.model;

public class BankList {

    /**
     * entityId : 1
     * imgUrl : null
     * bankCode : BOC
     * bankName : 中国银行
     * description : 还款单笔限额50000，每日限额50000
     * isRecommend : 1
     * status : 1
     * sort : 1
     */

    private int entityId;
    private String imgUrl;
    private String bankCode;
    private String bankName;
    private String description;
    private int isRecommend;
    private int status;
    private int sort;

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
