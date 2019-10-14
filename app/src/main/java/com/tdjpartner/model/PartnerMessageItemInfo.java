package com.tdjpartner.model;

import java.util.List;

public class PartnerMessageItemInfo {


    /**
     * total : 61
     * obj : [{"entityId":71,"userId":23251,"type":4,"title":"审核通知","content":null,"createTime":"2019-10-10 14:32:29","updateTime":"2019-10-12 09:47:15","isRead":1,"detailImgUrl":"http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg"},{"entityId":10,"userId":23251,"type":4,"title":"审核通知","content":null,"createTime":"2019-09-24 23:26:19","updateTime":"2019-10-12 09:47:15","isRead":1,"detailImgUrl":"http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg"},{"entityId":47,"userId":23251,"type":4,"title":"审核通知","content":null,"createTime":"2019-09-25 11:05:38","updateTime":"2019-10-12 09:47:15","isRead":1,"detailImgUrl":"http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg"},{"entityId":58,"userId":23251,"type":4,"title":"审核通知","content":null,"createTime":"2019-09-25 14:34:22","updateTime":"2019-10-12 09:47:15","isRead":1,"detailImgUrl":"http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg"},{"entityId":25,"userId":23251,"type":4,"title":"审核通知","content":null,"createTime":"2019-09-25 09:41:09","updateTime":"2019-10-12 09:47:15","isRead":1,"detailImgUrl":"http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg"}]
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
         * entityId : 71
         * userId : 23251
         * type : 4
         * title : 审核通知
         * content : null
         * createTime : 2019-10-10 14:32:29
         * updateTime : 2019-10-12 09:47:15
         * isRead : 1
         * detailImgUrl : http://i.17173cdn.com/2fhnvk/YWxqaGBf/cms3/jMaTJlbnwuxhCna.jpg!a-3-240x.jpg
         */

        private int entityId;
        private int userId;
        private int type;
        private String title;
        private String content;
        private String createTime;
        private String updateTime;
        private int isRead;
        private String detailImgUrl;

        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public String getDetailImgUrl() {
            return detailImgUrl;
        }

        public void setDetailImgUrl(String detailImgUrl) {
            this.detailImgUrl = detailImgUrl;
        }
    }
}
