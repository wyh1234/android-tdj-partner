package com.tdjpartner.model;

public class UserInfo  {
    private String username;;
    private String realname;
    private String phoneNumber;
    private int entityId;
    private String headUrl;//淘大集用户图像
    private int loginUserId;//第一次登陆ID
    private String alias;  //昵称
    private int empRole;//所属门店角色
    private int site;
    private Integer grade;//用户级别1经理2主管3业务员(创客)
    private Long surplusAmount 	;//总提成收益
    private String idCard 	;//非空，已实名
    private String avatarUrl 	;//自定义创客图片
    private Long pmCount 	;//未读消息数
    private String siteName;
    private String cardUrlPositive;//身份证正面
    private String cardUrlNegative;//身份证反面
    private String verifyCode;;

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
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Long getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Long surplusAmount) {
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

    public int getEmpRole() {
        return empRole;
    }

    public void setEmpRole(int empRole) {
        this.empRole = empRole;
    }
}
