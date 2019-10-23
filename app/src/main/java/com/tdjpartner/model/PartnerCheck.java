package com.tdjpartner.model;

import java.util.List;

public class PartnerCheck {
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
     * id : 2
     * userId : null
     * name : null
     * createTime : 2019-09-26 15:03:13
     * updateTime : null
     * remark : null
     * verifyStatus : 1
     * leaderId : null
     * verifyCode : null
     * enterpriseCode : 回民烧烤
     * enterpriseMsg : 康达街7号附38号
     * nickName : 裴双艳
     * leaderName : 李航
     * phone : 13871559691
     * imageUrl : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/1906041614338f0d0f02.jpg
     * bzlicenceUrl : null
     * verificationCode : null
     * createdAt : 2019-06-04 16:18:48
     * verifyRemark : null
     * nodeName : null
     * nodeNumber : null
     * applyId : null
     * applyCode : null
     */
    public static class ObjBean{



    private int id;
    private int userId;
    private String name;
    private String createTime;
    private Object updateTime;
    private String remark;
    private int verifyStatus;
    private Object leaderId;
    private Object verifyCode;
    private String enterpriseCode;
    private String enterpriseMsg;
    private String nickName;
    private String leaderName;
    private String phone;
    private String imageUrl;
    private String bzlicenceUrl;
    private Object verificationCode;
    private String createdAt;
    private Object verifyRemark;
    private Object nodeName;
    private Object nodeNumber;
    private Object applyId;
    private Object applyCode;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }


    public int getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(int verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Object getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Object leaderId) {
        this.leaderId = leaderId;
    }

    public Object getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(Object verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseMsg() {
        return enterpriseMsg;
    }

    public void setEnterpriseMsg(String enterpriseMsg) {
        this.enterpriseMsg = enterpriseMsg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBzlicenceUrl() {
        return bzlicenceUrl;
    }

    public void setBzlicenceUrl(String bzlicenceUrl) {
        this.bzlicenceUrl = bzlicenceUrl;
    }

    public Object getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Object verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(Object verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public Object getNodeName() {
        return nodeName;
    }

    public void setNodeName(Object nodeName) {
        this.nodeName = nodeName;
    }

    public Object getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(Object nodeNumber) {
        this.nodeNumber = nodeNumber;
    }

    public Object getApplyId() {
        return applyId;
    }

    public void setApplyId(Object applyId) {
        this.applyId = applyId;
    }

    public Object getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(Object applyCode) {
        this.applyCode = applyCode;
    }
}
}