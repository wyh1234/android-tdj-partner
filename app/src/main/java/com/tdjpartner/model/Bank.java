package com.tdjpartner.model;

import java.io.Serializable;

public class Bank implements Serializable {


    /**
     * entityId : null
     * userId : null
     * cityId : null
     * accountNo : null
     * partnerName : null
     * partnerPhone : null
     * bankName : null
     * bankAddress : null
     * status : null
     * createTime : null
     * updateTime : null
     * isDefault : null
     * idCard : null
     * cardImgUrl : null
     * cashImgUrl : null
     */

    private Integer entityId;
    private Integer userId;
    private Integer cityId;
    private String accountNo;
    private String partnerName;
    private String partnerPhone;
    private String bankName;
    private String bankAddress;
    private Integer status;
    private String createTime;
    private String updateTime;
    private Integer isDefault;
    private String idCard;
    private String cardImgUrl;
    private String cashImgUrl;

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCardImgUrl() {
        return cardImgUrl;
    }

    public void setCardImgUrl(String cardImgUrl) {
        this.cardImgUrl = cardImgUrl;
    }

    public String getCashImgUrl() {
        return cashImgUrl;
    }

    public void setCashImgUrl(String cashImgUrl) {
        this.cashImgUrl = cashImgUrl;
    }
}
