package com.tdjpartner.model;

import java.util.List;

public class NewMyTeam {
    private int id;
    private int partnerId;
    private String nickName;
    private String phone;
    private int grade;
    private int parentId;
    private String createTime;
    private String gradeName;
    private String webStieName;
    private boolean f;
    private List<NewMyTeam> childPartnerTree;

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public String getWebStieName() {
        return webStieName;
    }

    public void setWebStieName(String webStieName) {
        this.webStieName = webStieName;
    }

    public List<NewMyTeam> getChildPartnerTree() {
        return childPartnerTree;
    }

    public void setChildPartnerTree(List<NewMyTeam> childPartnerTree) {
        this.childPartnerTree = childPartnerTree;
    }

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
