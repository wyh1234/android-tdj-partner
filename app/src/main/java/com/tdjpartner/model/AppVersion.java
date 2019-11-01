package com.tdjpartner.model;

public class AppVersion {


    /**
     * id : 1
     * type : ios
     * url : www.baidu.com
     * version : 1.0
     * name : 淘大集创客
     * remark : 1.0上线
     * createTime : 2019-10-29 11:17:37
     * size : 22.22
     */

    private int id;
    private String type;
    private String url;
    private String version;
    private String name;
    private String remark;
    private String createTime;
    private double size;
    private String qrcodeImage;

    public String getQrcodeImage() {
        return qrcodeImage;
    }

    public void setQrcodeImage(String qrcodeImage) {
        this.qrcodeImage = qrcodeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
