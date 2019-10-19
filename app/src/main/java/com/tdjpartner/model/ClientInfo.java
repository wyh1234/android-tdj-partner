package com.tdjpartner.model;

public class ClientInfo {


    /**
     * customerId : 16543
     * auth : 0
     * name : 良品铺子
     * address : 汇通新长江中心营销中心汇通新长江中心1层
     * boss : 张梦雪
     * mobile : 13671221279
     * partnerName : null
     * websiteId : 3
     * regionCollNo : null
     * regionNo : null
     * lineCode : null
     * customerLineCode : null
     * todayAmount : null
     * averageAmount : null
     * monthTimes : null
     * notOrderDays : 30
     * notCallDays : null
     * lat : 30.593068
     * lon : 114.349047
     */

    private int customerId;
    private int auth;
    private String name;
    private String address;
    private String boss;
    private String mobile;
    private Object partnerName;
    private int websiteId;
    private String regionCollNo;
    private String regionNo;
    private String lineCode;
    private Object customerLineCode;
    private Object todayAmount;
    private Object averageAmount;
    private Object monthTimes;
    private int notOrderDays;
    private Object notCallDays;
    private String lat;
    private String lon;
    private int userType;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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

    public String getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(String regionNo) {
        this.regionNo = regionNo;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public Object getCustomerLineCode() {
        return customerLineCode;
    }

    public void setCustomerLineCode(Object customerLineCode) {
        this.customerLineCode = customerLineCode;
    }

    public Object getTodayAmount() {
        return todayAmount;
    }

    public void setTodayAmount(Object todayAmount) {
        this.todayAmount = todayAmount;
    }

    public Object getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(Object averageAmount) {
        this.averageAmount = averageAmount;
    }

    public Object getMonthTimes() {
        return monthTimes;
    }

    public void setMonthTimes(Object monthTimes) {
        this.monthTimes = monthTimes;
    }

    public int getNotOrderDays() {
        return notOrderDays;
    }

    public void setNotOrderDays(int notOrderDays) {
        this.notOrderDays = notOrderDays;
    }

    public Object getNotCallDays() {
        return notCallDays;
    }

    public void setNotCallDays(Object notCallDays) {
        this.notCallDays = notCallDays;
    }

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
}
