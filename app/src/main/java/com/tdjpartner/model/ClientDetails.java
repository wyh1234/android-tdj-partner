package com.tdjpartner.model;

import java.io.Serializable;

public class ClientDetails implements Serializable {

    /**
     * customerId : 21
     * auth : 0
     * name : 淘大集 周万波
     * address : 汉江创业创新产业园二楼 淘大集 周万波 15587773319
     * boss : 周万波
     * mobile : 15587773319
     * partnerName : null
     * followTime : null
     * websiteId : 2
     * regionCollNo : null
     * regionNo : null
     * lineCode : null
     * customerLineCode : null
     * todayAmount : 0.0
     * averageAmount : 0.0
     * monthTimes : 0
     * notOrderDays : null
     * notCallDays : null
     * userType : null
     * monthAmount : null
     * afterSaleTimes : null
     * noCallDay : null
     * receiveName : 周万波
     * receiveMobile : 15587773319
     * deliveredTimeBegin :
     * deliveredTimeEnd : null
     * punchDistance : 100
     */

    private int customerId;
    private String headUrl;
    private int auth;
    private String name;
    private String address;
    private String boss;
    private String mobile;
    private Object partnerName;
    private Object followTime;
    private int websiteId;
    private String regionCollNo;
    private Object regionNo;
    private Object lineCode;
    private Object customerLineCode;
    private double todayAmount;
    private double averageAmount;
    private int monthTimes;
    private Integer notOrderDays;
    private Object notCallDays;
    private Object userType;
    private Integer monthAmount;
    private Integer afterSaleTimes;
    private Integer noCallDay;
    private String receiveName;
    private String receiveMobile;
    private String deliveredTimeBegin;
    private Object deliveredTimeEnd;
    private int punchDistance;
    private String lat;
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Object getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(Object partnerName) {
        this.partnerName = partnerName;
    }

    public Object getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Object followTime) {
        this.followTime = followTime;
    }

    public int getWebsiteId() {
        return websiteId;
    }

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

    public String getRegionCollNo() {
        return regionCollNo;
    }

    public void setRegionCollNo(String regionCollNo) {
        this.regionCollNo = regionCollNo;
    }

    public Object getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(Object regionNo) {
        this.regionNo = regionNo;
    }

    public Object getLineCode() {
        return lineCode;
    }

    public void setLineCode(Object lineCode) {
        this.lineCode = lineCode;
    }

    public Object getCustomerLineCode() {
        return customerLineCode;
    }

    public void setCustomerLineCode(Object customerLineCode) {
        this.customerLineCode = customerLineCode;
    }

    public double getTodayAmount() {
        return todayAmount;
    }

    public void setTodayAmount(double todayAmount) {
        this.todayAmount = todayAmount;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount) {
        this.averageAmount = averageAmount;
    }

    public int getMonthTimes() {
        return monthTimes;
    }

    public void setMonthTimes(int monthTimes) {
        this.monthTimes = monthTimes;
    }

    public Integer getNotOrderDays() {
        return notOrderDays;
    }

    public void setNotOrderDays(Integer notOrderDays) {
        this.notOrderDays = notOrderDays;
    }

    public Object getNotCallDays() {
        return notCallDays;
    }

    public void setNotCallDays(Object notCallDays) {
        this.notCallDays = notCallDays;
    }

    public Object getUserType() {
        return userType;
    }

    public void setUserType(Object userType) {
        this.userType = userType;
    }

    public Integer getMonthAmount() {
        return monthAmount;
    }

    public void setMonthAmount(Integer monthAmount) {
        this.monthAmount = monthAmount;
    }

    public Integer getAfterSaleTimes() {
        return afterSaleTimes;
    }

    public void setAfterSaleTimes(Integer afterSaleTimes) {
        this.afterSaleTimes = afterSaleTimes;
    }

    public Integer getNoCallDay() {
        return noCallDay;
    }

    public void setNoCallDay(Integer noCallDay) {
        this.noCallDay = noCallDay;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getDeliveredTimeBegin() {
        return deliveredTimeBegin;
    }

    public void setDeliveredTimeBegin(String deliveredTimeBegin) {
        this.deliveredTimeBegin = deliveredTimeBegin;
    }

    public Object getDeliveredTimeEnd() {
        return deliveredTimeEnd;
    }

    public void setDeliveredTimeEnd(Object deliveredTimeEnd) {
        this.deliveredTimeEnd = deliveredTimeEnd;
    }

    public int getPunchDistance() {
        return punchDistance;
    }

    public void setPunchDistance(int punchDistance) {
        this.punchDistance = punchDistance;
    }
}
