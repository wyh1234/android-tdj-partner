package com.tdjpartner.model;


import java.util.List;

public class SelectPerson {

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

    /**
     * id : null
     * userId : null
     * parentId : null
     * withdrawalAmount : null
     * surplusAmount : null
     * frostAmount : null
     * idCard : null
     * grade : null
     * nickName : 胡杰
     * phone : 15271967861
     * number : null
     * applyCode : null
     * avatarUrl : null
     * createTime : null
     * name : null
     * status : null
     * statusName : null
     * enterpriseCode : null
     * enterpriseMsg : null
     * isAuth : null
     * isFullTime : 0
     * verifyCode : SDFIGF11
     */
    public static class ObjBean{
    private boolean f;
    private int id;
    private int userId;
    private int parentId;
    private String gradeName;
    private Object withdrawalAmount;
    private Object surplusAmount;
    private Object frostAmount;
    private Object idCard;
    private Object grade;
    private String nickName;
    private String phone;
    private Object number;
    private Object applyCode;
    private Object avatarUrl;
    private Object createTime;
    private Object name;
    private Object status;
    private Object statusName;
    private Object enterpriseCode;
    private Object enterpriseMsg;
    private Object isAuth;
    private int isFullTime;
    private String verifyCode;
        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }


    public Object getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Object getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(Object withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Object getSurplusAmount() {
        return surplusAmount;
    }

    public void setSurplusAmount(Object surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    public Object getFrostAmount() {
        return frostAmount;
    }

    public void setFrostAmount(Object frostAmount) {
        this.frostAmount = frostAmount;
    }

    public Object getIdCard() {
        return idCard;
    }

    public void setIdCard(Object idCard) {
        this.idCard = idCard;
    }

    public Object getGrade() {
        return grade;
    }

    public void setGrade(Object grade) {
        this.grade = grade;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }

    public Object getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(Object applyCode) {
        this.applyCode = applyCode;
    }

    public Object getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(Object avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getStatusName() {
        return statusName;
    }

    public void setStatusName(Object statusName) {
        this.statusName = statusName;
    }

    public Object getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(Object enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Object getEnterpriseMsg() {
        return enterpriseMsg;
    }

    public void setEnterpriseMsg(Object enterpriseMsg) {
        this.enterpriseMsg = enterpriseMsg;
    }

    public Object getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(Object isAuth) {
        this.isAuth = isAuth;
    }

    public int getIsFullTime() {
        return isFullTime;
    }

    public void setIsFullTime(int isFullTime) {
        this.isFullTime = isFullTime;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
}