package com.tdjpartner.model;

import java.util.List;

public class MyTeam {

    /**
     * total : 1
     * obj : [{"id":8,"partnerId":25653,"nickName":"hahaha","phone":"12345678761","grade":2,"parentId":11,"createTime":"2019-10-24 05:18:54"}]
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
         * id : 8
         * partnerId : 25653
         * nickName : hahaha
         * phone : 12345678761
         * grade : 2
         * parentId : 11
         * createTime : 2019-10-24 05:18:54
         */

        private int id;
        private int partnerId;
        private String nickName;
        private String phone;
        private int grade;
        private int parentId;
        private String createTime;
        private String gradeName;

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(int partnerId) {
            this.partnerId = partnerId;
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

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
