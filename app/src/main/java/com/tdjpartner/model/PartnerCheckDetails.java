package com.tdjpartner.model;

import java.util.List;

public class PartnerCheckDetails {


    private List<UserApplyBean> userApply;

    public List<UserApplyBean> getUserApply() {
        return userApply;
    }

    public void setUserApply(List<UserApplyBean> userApply) {
        this.userApply = userApply;
    }

    public static class UserApplyBean {
        /**
         * id : 21
         * userId : null
         * name : null
         * createTime : 2019-10-10 15:13:32
         * updateTime : null
         * remark : null
         * verifyStatus : 0
         * leaderId : null
         * verifyCode : null
         * enterpriseCode : 测试
         * enterpriseMsg : (在建)11号线二期;(在建)12号线;4号线;7号线
         * nickName : 大圣
         * leaderName : null
         * phone : 13241810994
         * imageUrl : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/1908270847334a175d9e.jpg
         * bzlicenceUrl : null
         * verificationCode : null
         * createdAt : 2019-08-27 08:49:48
         * verifyRemark : null
         * nodeName : null
         * nodeNumber : null
         * applyId : null
         * applyCode : null
         */
        private int isVerify;
        private int id;
        private boolean b;//驳回
        private boolean f=true;//通过
        private Object userId;
        private Object name;
        private String createTime;
        private Object updateTime;
        private String remark;
        private int verifyStatus;
        private Object leaderId;
        private Object verifyCode;
        private String enterpriseCode;
        private String enterpriseMsg;
        private String nickName;
        private Object leaderName;
        private String phone;
        private String imageUrl;
        private String bzlicenceUrl;
        private Object verificationCode;
        private String createdAt;
        private String verifyRemark;
        private String nodeName;
        private Object nodeNumber;
        private Object applyId;
        private Object applyCode;
        private String regionCollNo;

        public int getIsVerify() {
            return isVerify;
        }

        public void setIsVerify(int isVerify) {
            this.isVerify = isVerify;
        }

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }

        public boolean isF() {
            return f;
        }

        public void setF(boolean f) {
            this.f = f;
        }

        public String getRegionCollNo() {
            return regionCollNo;
        }

        public void setRegionCollNo(String regionCollNo) {
            regionCollNo = regionCollNo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public Object getLeaderName() {
            return leaderName;
        }

        public void setLeaderName(Object leaderName) {
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

        public String getVerifyRemark() {
            return verifyRemark;
        }

        public void setVerifyRemark(String verifyRemark) {
            this.verifyRemark = verifyRemark;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
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
