package com.tdjpartner.model;

public class PartnerMessageInfo {


    /**
     * entityId : 2
     * type : 2
     * imgUrl : 123
     * title : 到账通知
     * content : 余额提现
     * status : 1
     * remarks : 提现到账
     * countNum : 1
     */

    private int entityId;
    private int type;
    private String imgUrl;
    private String title;
    private String content;
    private int status;
    private String remarks;
    private int countNum;


    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }
}
