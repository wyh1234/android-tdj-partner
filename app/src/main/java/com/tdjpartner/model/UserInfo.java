package com.tdjpartner.model;

import java.math.BigDecimal;

public class UserInfo {
    private String username;
    private String realname;
    private String phoneNumber;
    private int entityId;
    private int type;//用户类型（	1网军，2铁军）
    private String headUrl;//淘大集用户图像
    private int loginUserId;//第一次登陆ID
    private String alias;  //昵称
    private int site;
    private Integer grade;//用户级别1经理2主管3业务员(创客)
    private String gradeName;
    private BigDecimal surplusAmount;//总提成收益
    private String idCard;//非空，已实名
    private String avatarUrl;//自定义创客图片
    private Long pmCount;//未读消息数
    private String siteName;
    private String cardUrlPositive;//身份证正面
    private String cardUrlNegative;//身份证反面
    private String verifyCode;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getCardUrlPositive() {
        return cardUrlPositive;
    }

    public void setCardUrlPositive(String cardUrlPositive) {
        this.cardUrlPositive = cardUrlPositive;
    }

    public String getCardUrlNegative() {
        return cardUrlNegative;
    }

    public void setCardUrlNegative(String cardUrlNegative) {
        this.cardUrlNegative = cardUrlNegative;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getGrade() {
        return grade == null ? 0 : grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public BigDecimal getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(BigDecimal surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getPmCount() {
        return pmCount;
    }

    public void setPmCount(Long pmCount) {
        this.pmCount = pmCount;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(int loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
