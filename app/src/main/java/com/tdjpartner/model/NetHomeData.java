package com.tdjpartner.model;

import java.util.List;

public class NetHomeData {


    private AllDataBean allData;
    private MonthDataBean monthData;
    private TodayDataBean todayData;
    private List<RegisterTimesTopBean> registerTimesTop;
    private List<PartnerApproachDataBean> partnerApproachData;
    private List<OrdersTimesTopBean> ordersTimesTop;

    public AllDataBean getAllData() {
        return allData;
    }

    public void setAllData(AllDataBean allData) {
        this.allData = allData;
    }

    public MonthDataBean getMonthData() {
        return monthData;
    }

    public void setMonthData(MonthDataBean monthData) {
        this.monthData = monthData;
    }

    public TodayDataBean getTodayData() {
        return todayData;
    }

    public void setTodayData(TodayDataBean todayData) {
        this.todayData = todayData;
    }

    public List<RegisterTimesTopBean> getRegisterTimesTop() {
        return registerTimesTop;
    }

    public void setRegisterTimesTop(List<RegisterTimesTopBean> registerTimesTop) {
        this.registerTimesTop = registerTimesTop;
    }

    public List<PartnerApproachDataBean> getPartnerApproachData() {
        return partnerApproachData;
    }

    public void setPartnerApproachData(List<PartnerApproachDataBean> partnerApproachData) {
        this.partnerApproachData = partnerApproachData;
    }

    public List<OrdersTimesTopBean> getOrdersTimesTop() {
        return ordersTimesTop;
    }

    public void setOrdersTimesTop(List<OrdersTimesTopBean> ordersTimesTop) {
        this.ordersTimesTop = ordersTimesTop;
    }

    public static class TodayDataBean {

        /**
         * {
         * "customerId": null,
         * "auth": null,
         * "name": null,
         * "address": null,
         * "boss": null,
         * "mobile": null,
         * "partnerName": null,
         * "followTime": null,
         * "websiteId": null,
         * "regionCollNo": null,
         * "regionNo": null,
         * "lineCode": null,
         * "customerLineCode": null,
         * "todayAmount": null,
         * "averageAmount": null, //日客单价
         * "todayTimes": null,
         * "todayAfterSaleTimes": null,
         * "monthTimes": null,
         * "notOrderDays": null,
         * "notCallDays": null,
         * "userType": null,
         * "monthAmount": 111095.02,
         * "afterSaleTimes": null,
         * "noCallDay": null,
         * "receiveName": null,
         * "receiveMobile": null,
         * "deliveredTimeBegin": null,
         * "deliveredTimeEnd": null,
         * "punchDistance": null,
         * "dayOrderTimes": null,
         * "dayRegisterTimes": null,
         * "lat": null,
         * "lon": null,
         * "headUrl": null,
         * "lastMonthActiveNum": null,
         * "activeNum": null,
         * "callNum": null,
         * "monthRegisterNum": 178685,
         * "monthActiveNum": 1,
         * "monthCallNum": 2,
         * "lastMonthAvgActiveNum": null,
         * "monthAvgActiveNum": 0.0,
         * "firstOrderNum": null,
         * "lastMonthFirstOrderNum": null,
         * "monthFirstOrderNum": 33,
         * "monthAverageAmount": 3471.72,
         * "monthAfterSaleTimes": 20,
         * "thirtyTimesNum": null,
         * "createTime": null,
         * "yesterdayActiveNum": null, //昨日日活差
         * "addMonthAmount": 0.00,
         * "monthAfterSaleAmount": 11.000000,
         * "afterSaleAmount": null, //售后金额
         * "categoryNum": 0, //新鲜蔬菜
         * "categoryAmount": null,
         * "gradeNextName": "",
         * "grade": 0,
         * "driverName": null,
         * "driverTel": null
         * }
         */

        public int lastMonthActiveNum; //null,
        public int activeNum; //null,
        public int callNum; //null,
        public int monthRegisterNum; //178685,
        public int monthActiveNum; //1,
        public int monthCallNum; //2,
        public int lastMonthAvgActiveNum; //null,
        public int monthAvgActiveNum; //0.0,
        public int firstOrderNum; //null,
        public int lastMonthFirstOrderNum; //null,
        public int monthFirstOrderNum; //33,
        public int thirtyTimesNum; //null,
        public int yesterdayActiveNum; //null,
        public int categoryNum; //0,
        public int userType; //null,
        public int customerId; //null,
        public int followTime; //null,
        public int todayTimes; //null,
        public int todayAfterSaleTimes; //null,
        public int monthTimes; //null,
        public int afterSaleTimes; //null,
        public int deliveredTimeBegin; //null,
        public int deliveredTimeEnd; //null,
        public int dayOrderTimes; //null,
        public int dayRegisterTimes; //null,
        public int monthAfterSaleTimes; //20,

        public String createTime; //null,
        public String auth; //null,
        public String name; //null,
        public String address; //null,
        public String boss; //null,
        public String mobile; //null,
        public String partnerName; //null,
        public String websiteId; //null,
        public String regionCollNo; //null,
        public String regionNo; //null,
        public String lineCode; //null,
        public String customerLineCode; //null,
        public String notOrderDays; //null,
        public String notCallDays; //null,
        public String noCallDay; //null,
        public String receiveName; //null,
        public String receiveMobile; //null,
        public String punchDistance; //null,
        public String lat; //null,
        public String lon; //null,
        public String headUrl; //null,
        public String gradeNextName; //"",
        public String grade; //0,
        public String driverName; //null,
        public String driverTel; //null

        public float todayAmount; //null,
        public float averageAmount; //null,
        public float monthAmount; //111095.02,
        public float monthAverageAmount; //3471.72,
        public float addMonthAmount; //0.00,
        public float monthAfterSaleAmount; //11.000000,
        public float afterSaleAmount; //null,
        public float categoryAmount; //null,

        @Override
        public String toString() {
            return "TodayDataBean{" +
                    "lastMonthActiveNum=" + lastMonthActiveNum +
                    ", activeNum=" + activeNum +
                    ", callNum=" + callNum +
                    ", monthRegisterNum=" + monthRegisterNum +
                    ", monthActiveNum=" + monthActiveNum +
                    ", monthCallNum=" + monthCallNum +
                    ", lastMonthAvgActiveNum=" + lastMonthAvgActiveNum +
                    ", monthAvgActiveNum=" + monthAvgActiveNum +
                    ", firstOrderNum=" + firstOrderNum +
                    ", lastMonthFirstOrderNum=" + lastMonthFirstOrderNum +
                    ", monthFirstOrderNum=" + monthFirstOrderNum +
                    ", thirtyTimesNum=" + thirtyTimesNum +
                    ", yesterdayActiveNum=" + yesterdayActiveNum +
                    ", categoryNum=" + categoryNum +
                    ", userType=" + userType +
                    ", customerId=" + customerId +
                    ", followTime='" + followTime + '\'' +
                    ", todayTimes='" + todayTimes + '\'' +
                    ", todayAfterSaleTimes='" + todayAfterSaleTimes + '\'' +
                    ", monthTimes='" + monthTimes + '\'' +
                    ", afterSaleTimes='" + afterSaleTimes + '\'' +
                    ", deliveredTimeBegin='" + deliveredTimeBegin + '\'' +
                    ", deliveredTimeEnd='" + deliveredTimeEnd + '\'' +
                    ", dayOrderTimes='" + dayOrderTimes + '\'' +
                    ", dayRegisterTimes='" + dayRegisterTimes + '\'' +
                    ", monthAfterSaleTimes='" + monthAfterSaleTimes + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", auth='" + auth + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", boss='" + boss + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", partnerName='" + partnerName + '\'' +
                    ", websiteId='" + websiteId + '\'' +
                    ", regionCollNo='" + regionCollNo + '\'' +
                    ", regionNo='" + regionNo + '\'' +
                    ", lineCode='" + lineCode + '\'' +
                    ", customerLineCode='" + customerLineCode + '\'' +
                    ", notOrderDays='" + notOrderDays + '\'' +
                    ", notCallDays='" + notCallDays + '\'' +
                    ", noCallDay='" + noCallDay + '\'' +
                    ", receiveName='" + receiveName + '\'' +
                    ", receiveMobile='" + receiveMobile + '\'' +
                    ", punchDistance='" + punchDistance + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", headUrl='" + headUrl + '\'' +
                    ", gradeNextName='" + gradeNextName + '\'' +
                    ", grade='" + grade + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", driverTel='" + driverTel + '\'' +
                    ", todayAmount=" + todayAmount +
                    ", averageAmount=" + averageAmount +
                    ", monthAmount=" + monthAmount +
                    ", monthAverageAmount=" + monthAverageAmount +
                    ", addMonthAmount=" + addMonthAmount +
                    ", monthAfterSaleAmount=" + monthAfterSaleAmount +
                    ", afterSaleAmount=" + afterSaleAmount +
                    ", categoryAmount=" + categoryAmount +
                    '}';
        }
    }

    public static class AllDataBean {
        /**
         * notOrderCustomer : 1
         * countCustomer : 1
         * orderCustomer : 0
         */

        private Integer notOrderCustomer;
        private Integer countCustomer;
        private Integer orderCustomer;
        private Integer noVerifyNum;

        public Integer getNoVerifyNum() {
            return noVerifyNum;
        }

        public void setNoVerifyNum(Integer noVerifyNum) {
            this.noVerifyNum = noVerifyNum;
        }

        public Integer getNotOrderCustomer() {
            return notOrderCustomer;
        }

        public void setNotOrderCustomer(Integer notOrderCustomer) {
            this.notOrderCustomer = notOrderCustomer;
        }

        public Integer getCountCustomer() {
            return countCustomer == null ? 0 : countCustomer;
        }

        public void setCountCustomer(Integer countCustomer) {
            this.countCustomer = countCustomer;
        }

        public Integer getOrderCustomer() {
            return orderCustomer;
        }

        public void setOrderCustomer(Integer orderCustomer) {
            this.orderCustomer = orderCustomer;
        }
    }

    public static class MonthDataBean {
        /**
         * {
         * "customerId": null,
         * "auth": null,
         * "name": null,
         * "address": null,
         * "boss": null,
         * "mobile": null,
         * "partnerName": null,
         * "followTime": null,
         * "websiteId": null,
         * "regionCollNo": null,
         * "regionNo": null,
         * "lineCode": null,
         * "customerLineCode": null,
         * "todayAmount": null,
         * "averageAmount": null,
         * "todayTimes": null,
         * "todayAfterSaleTimes": null,
         * "monthTimes": null,
         * "notOrderDays": null,
         * "notCallDays": null,
         * "userType": null,
         * "monthAmount": 111095.02,
         * "afterSaleTimes": null,
         * "noCallDay": null,
         * "receiveName": null,
         * "receiveMobile": null,
         * "deliveredTimeBegin": null,
         * "deliveredTimeEnd": null,
         * "punchDistance": null,
         * "dayOrderTimes": null,
         * "dayRegisterTimes": null,
         * "lat": null,
         * "lon": null,
         * "headUrl": null,
         * "lastMonthActiveNum": null,
         * "activeNum": null,
         * "callNum": null,
         * "monthRegisterNum": 178685,
         * "monthActiveNum": 1,
         * "monthCallNum": 2,
         * "lastMonthAvgActiveNum": null,
         * "monthAvgActiveNum": 0.0,
         * "firstOrderNum": null,
         * "lastMonthFirstOrderNum": null,
         * "monthFirstOrderNum": 33,
         * "monthAverageAmount": 3471.72,
         * "monthAfterSaleTimes": 20,
         * "thirtyTimesNum": null,
         * "createTime": null,
         * "yesterdayActiveNum": null,
         * "addMonthAmount": 0.00,
         * "monthAfterSaleAmount": 11.000000,
         * "afterSaleAmount": null,
         * "categoryNum": 0,
         * "categoryAmount": null,
         * "gradeNextName": "",
         * "grade": 0,
         * "driverName": null,
         * "driverTel": null
         * }
         */

        public int lastMonthActiveNum; //null,
        public int activeNum; //null,
        public int callNum; //null,
        public int monthRegisterNum; //178685,
        public int monthActiveNum; //1,
        public int monthCallNum; //2,
        public int lastMonthAvgActiveNum; //null,
        public int firstOrderNum; //null,
        public int lastMonthFirstOrderNum; //null,
        public int monthFirstOrderNum; //33,
        public int thirtyTimesNum; //null,
        public int yesterdayActiveNum; //null,
        public int categoryNum; //0,
        public int regionCollNo; //null,
        public int regionNo; //null,
        public int notOrderDays; //null,
        public int notCallDays; //null,
        public int noCallDay; //null,
        public int customerId; //null,
        public int websiteId; //null,
        public int lineCode; //null,
        public int customerLineCode; //null,
        public int todayTimes; //null,
        public int todayAfterSaleTimes; //null,
        public int monthTimes; //null,
        public int afterSaleTimes; //null,
        public int dayOrderTimes; //null,
        public int dayRegisterTimes; //null,
        public int monthAfterSaleTimes; //20,
        public int userType; //null,

        public float monthAvgActiveNum; //0.0,
        public float todayAmount; //null,
        public float averageAmount; //null,
        public float monthAmount; //111095.02,
        public float monthAverageAmount; //3471.72,
        public float addMonthAmount; //0.00,
        public float monthAfterSaleAmount; //11.000000,
        public float afterSaleAmount; //null,
        public float categoryAmount; //null,

        public String auth; //null,
        public String name; //null,
        public String address; //null,
        public String boss; //null,
        public String mobile; //null,
        public String partnerName; //null,
        public String followTime; //null,
        public String receiveName; //null,
        public String receiveMobile; //null,
        public String deliveredTimeBegin; //null,
        public String deliveredTimeEnd; //null,
        public String punchDistance; //null,
        public String lat; //null,
        public String lon; //null,
        public String headUrl; //null,
        public String createTime; //null,
        public String gradeNextName; //"",
        public String grade; //0,
        public String driverName; //null,
        public String driverTel; //null
    }

    public static class RegisterTimesTopBean {
        /**
         * customerId : null
         * auth : null
         * name : null
         * address : null
         * boss : null
         * mobile : 12345678761
         * partnerName : null
         * followTime : null
         * websiteId : null
         * regionCollNo : null
         * regionNo : null
         * lineCode : null
         * customerLineCode : null
         * todayAmount : null
         * averageAmount : null
         * todayTimes : null
         * todayAfterSaleTimes : null
         * monthTimes : null
         * notOrderDays : null
         * notCallDays : null
         * userType : null
         * monthAmount : null
         * afterSaleTimes : null
         * noCallDay : null
         * receiveName : null
         * receiveMobile : null
         * deliveredTimeBegin : null
         * deliveredTimeEnd : null
         * punchDistance : null
         * dayOrderTimes : null
         * dayRegisterTimes : null
         * lat : null
         * lon : null
         * headUrl : null
         * lastMonthActiveNum : null
         * activeNum : null
         * callNum : null
         * monthRegisterNum : null
         * monthActiveNum : null
         * monthCallNum : null
         * lastMonthAvgActiveNum : null
         * monthAvgActiveNum : null
         * firstOrderNum : null
         * lastMonthFirstOrderNum : null
         * monthFirstOrderNum : null
         * monthAverageAmount : null
         * monthAfterSaleTimes : null
         * thirtyTimesNum : null
         * createTime : null
         */

        private Object customerId;
        private Object auth;
        private String name;
        private Object address;
        private Object boss;
        private String mobile;
        private String partnerName;
        private Object followTime;
        private Object websiteId;
        private Object regionCollNo;
        private Object regionNo;
        private Object lineCode;
        private Object customerLineCode;
        private Object todayAmount;
        private Object averageAmount;
        private Object todayTimes;
        private Object todayAfterSaleTimes;
        private Object monthTimes;
        private Object notOrderDays;
        private Object notCallDays;
        private Object userType;
        private Object monthAmount;
        private Object afterSaleTimes;
        private Object noCallDay;
        private Object receiveName;
        private Object receiveMobile;
        private Object deliveredTimeBegin;
        private Object deliveredTimeEnd;
        private Object punchDistance;
        private Object dayOrderTimes;
        private Integer dayRegisterTimes;
        private Object lat;
        private Object lon;
        private Object headUrl;
        private Object lastMonthActiveNum;
        private Object activeNum;
        private Object callNum;
        private Object monthRegisterNum;
        private Object monthActiveNum;
        private Object monthCallNum;
        private Object lastMonthAvgActiveNum;
        private Object monthAvgActiveNum;
        private Object firstOrderNum;
        private Object lastMonthFirstOrderNum;
        private Object monthFirstOrderNum;
        private Object monthAverageAmount;
        private Object monthAfterSaleTimes;
        private Object thirtyTimesNum;
        private Object createTime;

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getAuth() {
            return auth;
        }

        public void setAuth(Object auth) {
            this.auth = auth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getBoss() {
            return boss;
        }

        public void setBoss(Object boss) {
            this.boss = boss;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public Object getFollowTime() {
            return followTime;
        }

        public void setFollowTime(Object followTime) {
            this.followTime = followTime;
        }

        public Object getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(Object websiteId) {
            this.websiteId = websiteId;
        }

        public Object getRegionCollNo() {
            return regionCollNo;
        }

        public void setRegionCollNo(Object regionCollNo) {
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

        public Object getTodayTimes() {
            return todayTimes;
        }

        public void setTodayTimes(Object todayTimes) {
            this.todayTimes = todayTimes;
        }

        public Object getTodayAfterSaleTimes() {
            return todayAfterSaleTimes;
        }

        public void setTodayAfterSaleTimes(Object todayAfterSaleTimes) {
            this.todayAfterSaleTimes = todayAfterSaleTimes;
        }

        public Object getMonthTimes() {
            return monthTimes;
        }

        public void setMonthTimes(Object monthTimes) {
            this.monthTimes = monthTimes;
        }

        public Object getNotOrderDays() {
            return notOrderDays;
        }

        public void setNotOrderDays(Object notOrderDays) {
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

        public Object getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(Object monthAmount) {
            this.monthAmount = monthAmount;
        }

        public Object getAfterSaleTimes() {
            return afterSaleTimes;
        }

        public void setAfterSaleTimes(Object afterSaleTimes) {
            this.afterSaleTimes = afterSaleTimes;
        }

        public Object getNoCallDay() {
            return noCallDay;
        }

        public void setNoCallDay(Object noCallDay) {
            this.noCallDay = noCallDay;
        }

        public Object getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(Object receiveName) {
            this.receiveName = receiveName;
        }

        public Object getReceiveMobile() {
            return receiveMobile;
        }

        public void setReceiveMobile(Object receiveMobile) {
            this.receiveMobile = receiveMobile;
        }

        public Object getDeliveredTimeBegin() {
            return deliveredTimeBegin;
        }

        public void setDeliveredTimeBegin(Object deliveredTimeBegin) {
            this.deliveredTimeBegin = deliveredTimeBegin;
        }

        public Object getDeliveredTimeEnd() {
            return deliveredTimeEnd;
        }

        public void setDeliveredTimeEnd(Object deliveredTimeEnd) {
            this.deliveredTimeEnd = deliveredTimeEnd;
        }

        public Object getPunchDistance() {
            return punchDistance;
        }

        public void setPunchDistance(Object punchDistance) {
            this.punchDistance = punchDistance;
        }

        public Object getDayOrderTimes() {
            return dayOrderTimes;
        }

        public void setDayOrderTimes(Object dayOrderTimes) {
            this.dayOrderTimes = dayOrderTimes;
        }

        public Integer getDayRegisterTimes() {
            return dayRegisterTimes == null ? 0 : dayRegisterTimes;
        }

        public void setDayRegisterTimes(Integer dayRegisterTimes) {
            this.dayRegisterTimes = dayRegisterTimes;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLon() {
            return lon;
        }

        public void setLon(Object lon) {
            this.lon = lon;
        }

        public Object getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(Object headUrl) {
            this.headUrl = headUrl;
        }

        public Object getLastMonthActiveNum() {
            return lastMonthActiveNum;
        }

        public void setLastMonthActiveNum(Object lastMonthActiveNum) {
            this.lastMonthActiveNum = lastMonthActiveNum;
        }

        public Object getActiveNum() {
            return activeNum;
        }

        public void setActiveNum(Object activeNum) {
            this.activeNum = activeNum;
        }

        public Object getCallNum() {
            return callNum;
        }

        public void setCallNum(Object callNum) {
            this.callNum = callNum;
        }

        public Object getMonthRegisterNum() {
            return monthRegisterNum;
        }

        public void setMonthRegisterNum(Object monthRegisterNum) {
            this.monthRegisterNum = monthRegisterNum;
        }

        public Object getMonthActiveNum() {
            return monthActiveNum;
        }

        public void setMonthActiveNum(Object monthActiveNum) {
            this.monthActiveNum = monthActiveNum;
        }

        public Object getMonthCallNum() {
            return monthCallNum;
        }

        public void setMonthCallNum(Object monthCallNum) {
            this.monthCallNum = monthCallNum;
        }

        public Object getLastMonthAvgActiveNum() {
            return lastMonthAvgActiveNum;
        }

        public void setLastMonthAvgActiveNum(Object lastMonthAvgActiveNum) {
            this.lastMonthAvgActiveNum = lastMonthAvgActiveNum;
        }

        public Object getMonthAvgActiveNum() {
            return monthAvgActiveNum;
        }

        public void setMonthAvgActiveNum(Object monthAvgActiveNum) {
            this.monthAvgActiveNum = monthAvgActiveNum;
        }

        public Object getFirstOrderNum() {
            return firstOrderNum;
        }

        public void setFirstOrderNum(Object firstOrderNum) {
            this.firstOrderNum = firstOrderNum;
        }

        public Object getLastMonthFirstOrderNum() {
            return lastMonthFirstOrderNum;
        }

        public void setLastMonthFirstOrderNum(Object lastMonthFirstOrderNum) {
            this.lastMonthFirstOrderNum = lastMonthFirstOrderNum;
        }

        public Object getMonthFirstOrderNum() {
            return monthFirstOrderNum;
        }

        public void setMonthFirstOrderNum(Object monthFirstOrderNum) {
            this.monthFirstOrderNum = monthFirstOrderNum;
        }

        public Object getMonthAverageAmount() {
            return monthAverageAmount;
        }

        public void setMonthAverageAmount(Object monthAverageAmount) {
            this.monthAverageAmount = monthAverageAmount;
        }

        public Object getMonthAfterSaleTimes() {
            return monthAfterSaleTimes;
        }

        public void setMonthAfterSaleTimes(Object monthAfterSaleTimes) {
            this.monthAfterSaleTimes = monthAfterSaleTimes;
        }

        public Object getThirtyTimesNum() {
            return thirtyTimesNum;
        }

        public void setThirtyTimesNum(Object thirtyTimesNum) {
            this.thirtyTimesNum = thirtyTimesNum;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }

    public static class PartnerApproachDataBean {
        /**
         * id : 1
         * menuPic : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190815122111db4a3126.png
         * sort : 1
         * title : 商品/店铺
         * context : 测试
         * linkTitle : 测试
         * linkType : 2
         * linkUrl : http://47.111.22.162:9000/tdj-partner/partner/collect/products
         * status : 1
         * seatType : 2
         * downDataNum : 0
         * followDataNum : 4
         * noVerifyNum : 0
         */

        private int id;
        private int sort;
        private int downDataNum;
        private int followDataNum;
        private int noVerifyNum;
        private String subscriptNum;
        private String menuPic;
        private String title;
        private String context;
        private String linkTitle;
        private String linkType;
        private String linkUrl;
        private String status;
        private String seatType;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMenuPic() {
            return menuPic;
        }

        public void setMenuPic(String menuPic) {
            this.menuPic = menuPic;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLinkTitle() {
            return linkTitle;
        }

        public void setLinkTitle(String linkTitle) {
            this.linkTitle = linkTitle;
        }

        public String getLinkType() {
            return linkType;
        }

        public void setLinkType(String linkType) {
            this.linkType = linkType;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSeatType() {
            return seatType;
        }

        public void setSeatType(String seatType) {
            this.seatType = seatType;
        }

        public int getDownDataNum() {
            return downDataNum;
        }

        public void setDownDataNum(int downDataNum) {
            this.downDataNum = downDataNum;
        }

        public int getFollowDataNum() {
            return followDataNum;
        }

        public void setFollowDataNum(int followDataNum) {
            this.followDataNum = followDataNum;
        }

        public int getNoVerifyNum() {
            return noVerifyNum;
        }

        public void setNoVerifyNum(int noVerifyNum) {
            this.noVerifyNum = noVerifyNum;
        }

        public String getSubscriptNum() {
            return subscriptNum;
        }

        public void setSubscriptNum(String subscriptNum) {
            this.subscriptNum = subscriptNum;
        }
    }

    public static class OrdersTimesTopBean {
        /**
         * customerId : null
         * auth : null
         * name : null
         * address : null
         * boss : null
         * mobile : 12345678761
         * partnerName : null
         * followTime : null
         * websiteId : null
         * regionCollNo : null
         * regionNo : null
         * lineCode : null
         * customerLineCode : null
         * todayAmount : null
         * averageAmount : null
         * todayTimes : null
         * todayAfterSaleTimes : null
         * monthTimes : null
         * notOrderDays : null
         * notCallDays : null
         * userType : null
         * monthAmount : null
         * afterSaleTimes : null
         * noCallDay : null
         * receiveName : null
         * receiveMobile : null
         * deliveredTimeBegin : null
         * deliveredTimeEnd : null
         * punchDistance : null
         * dayOrderTimes : null
         * dayRegisterTimes : null
         * lat : null
         * lon : null
         * headUrl : null
         * lastMonthActiveNum : null
         * activeNum : null
         * callNum : null
         * monthRegisterNum : null
         * monthActiveNum : null
         * monthCallNum : null
         * lastMonthAvgActiveNum : null
         * monthAvgActiveNum : null
         * firstOrderNum : null
         * lastMonthFirstOrderNum : null
         * monthFirstOrderNum : null
         * monthAverageAmount : null
         * monthAfterSaleTimes : null
         * thirtyTimesNum : null
         * createTime : null
         */

        private Object customerId;
        private Object auth;
        private String name;
        private Object address;
        private Object boss;
        private String mobile;
        private String partnerName;
        private Object followTime;
        private Object websiteId;
        private Object regionCollNo;
        private Object regionNo;
        private Object lineCode;
        private Object customerLineCode;
        private Object todayAmount;
        private Object averageAmount;
        private Object todayTimes;
        private Object todayAfterSaleTimes;
        private Object monthTimes;
        private Object notOrderDays;
        private Object notCallDays;
        private Object userType;
        private Object monthAmount;
        private Object afterSaleTimes;
        private Object noCallDay;
        private Object receiveName;
        private Object receiveMobile;
        private Object deliveredTimeBegin;
        private Object deliveredTimeEnd;
        private Object punchDistance;
        private Integer dayOrderTimes;
        private Object dayRegisterTimes;
        private Object lat;
        private Object lon;
        private Object headUrl;
        private Object lastMonthActiveNum;
        private Object activeNum;
        private Object callNum;
        private Object monthRegisterNum;
        private Object monthActiveNum;
        private Object monthCallNum;
        private Object lastMonthAvgActiveNum;
        private Object monthAvgActiveNum;
        private Object firstOrderNum;
        private Object lastMonthFirstOrderNum;
        private Object monthFirstOrderNum;
        private Object monthAverageAmount;
        private Object monthAfterSaleTimes;
        private Object thirtyTimesNum;
        private Object createTime;

        public Object getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Object customerId) {
            this.customerId = customerId;
        }

        public Object getAuth() {
            return auth;
        }

        public void setAuth(Object auth) {
            this.auth = auth;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getBoss() {
            return boss;
        }

        public void setBoss(Object boss) {
            this.boss = boss;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPartnerName() {
            return partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        public Object getFollowTime() {
            return followTime;
        }

        public void setFollowTime(Object followTime) {
            this.followTime = followTime;
        }

        public Object getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(Object websiteId) {
            this.websiteId = websiteId;
        }

        public Object getRegionCollNo() {
            return regionCollNo;
        }

        public void setRegionCollNo(Object regionCollNo) {
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

        public Object getTodayTimes() {
            return todayTimes;
        }

        public void setTodayTimes(Object todayTimes) {
            this.todayTimes = todayTimes;
        }

        public Object getTodayAfterSaleTimes() {
            return todayAfterSaleTimes;
        }

        public void setTodayAfterSaleTimes(Object todayAfterSaleTimes) {
            this.todayAfterSaleTimes = todayAfterSaleTimes;
        }

        public Object getMonthTimes() {
            return monthTimes;
        }

        public void setMonthTimes(Object monthTimes) {
            this.monthTimes = monthTimes;
        }

        public Object getNotOrderDays() {
            return notOrderDays;
        }

        public void setNotOrderDays(Object notOrderDays) {
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

        public Object getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(Object monthAmount) {
            this.monthAmount = monthAmount;
        }

        public Object getAfterSaleTimes() {
            return afterSaleTimes;
        }

        public void setAfterSaleTimes(Object afterSaleTimes) {
            this.afterSaleTimes = afterSaleTimes;
        }

        public Object getNoCallDay() {
            return noCallDay;
        }

        public void setNoCallDay(Object noCallDay) {
            this.noCallDay = noCallDay;
        }

        public Object getReceiveName() {
            return receiveName;
        }

        public void setReceiveName(Object receiveName) {
            this.receiveName = receiveName;
        }

        public Object getReceiveMobile() {
            return receiveMobile;
        }

        public void setReceiveMobile(Object receiveMobile) {
            this.receiveMobile = receiveMobile;
        }

        public Object getDeliveredTimeBegin() {
            return deliveredTimeBegin;
        }

        public void setDeliveredTimeBegin(Object deliveredTimeBegin) {
            this.deliveredTimeBegin = deliveredTimeBegin;
        }

        public Object getDeliveredTimeEnd() {
            return deliveredTimeEnd;
        }

        public void setDeliveredTimeEnd(Object deliveredTimeEnd) {
            this.deliveredTimeEnd = deliveredTimeEnd;
        }

        public Object getPunchDistance() {
            return punchDistance;
        }

        public void setPunchDistance(Object punchDistance) {
            this.punchDistance = punchDistance;
        }

        public Integer getDayOrderTimes() {
            return dayOrderTimes == null ? 0 : dayOrderTimes;
        }

        public void setDayOrderTimes(Integer dayOrderTimes) {
            this.dayOrderTimes = dayOrderTimes;
        }

        public Object getDayRegisterTimes() {
            return dayRegisterTimes;
        }

        public void setDayRegisterTimes(Object dayRegisterTimes) {
            this.dayRegisterTimes = dayRegisterTimes;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLon() {
            return lon;
        }

        public void setLon(Object lon) {
            this.lon = lon;
        }

        public Object getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(Object headUrl) {
            this.headUrl = headUrl;
        }

        public Object getLastMonthActiveNum() {
            return lastMonthActiveNum;
        }

        public void setLastMonthActiveNum(Object lastMonthActiveNum) {
            this.lastMonthActiveNum = lastMonthActiveNum;
        }

        public Object getActiveNum() {
            return activeNum;
        }

        public void setActiveNum(Object activeNum) {
            this.activeNum = activeNum;
        }

        public Object getCallNum() {
            return callNum;
        }

        public void setCallNum(Object callNum) {
            this.callNum = callNum;
        }

        public Object getMonthRegisterNum() {
            return monthRegisterNum;
        }

        public void setMonthRegisterNum(Object monthRegisterNum) {
            this.monthRegisterNum = monthRegisterNum;
        }

        public Object getMonthActiveNum() {
            return monthActiveNum;
        }

        public void setMonthActiveNum(Object monthActiveNum) {
            this.monthActiveNum = monthActiveNum;
        }

        public Object getMonthCallNum() {
            return monthCallNum;
        }

        public void setMonthCallNum(Object monthCallNum) {
            this.monthCallNum = monthCallNum;
        }

        public Object getLastMonthAvgActiveNum() {
            return lastMonthAvgActiveNum;
        }

        public void setLastMonthAvgActiveNum(Object lastMonthAvgActiveNum) {
            this.lastMonthAvgActiveNum = lastMonthAvgActiveNum;
        }

        public Object getMonthAvgActiveNum() {
            return monthAvgActiveNum;
        }

        public void setMonthAvgActiveNum(Object monthAvgActiveNum) {
            this.monthAvgActiveNum = monthAvgActiveNum;
        }

        public Object getFirstOrderNum() {
            return firstOrderNum;
        }

        public void setFirstOrderNum(Object firstOrderNum) {
            this.firstOrderNum = firstOrderNum;
        }

        public Object getLastMonthFirstOrderNum() {
            return lastMonthFirstOrderNum;
        }

        public void setLastMonthFirstOrderNum(Object lastMonthFirstOrderNum) {
            this.lastMonthFirstOrderNum = lastMonthFirstOrderNum;
        }

        public Object getMonthFirstOrderNum() {
            return monthFirstOrderNum;
        }

        public void setMonthFirstOrderNum(Object monthFirstOrderNum) {
            this.monthFirstOrderNum = monthFirstOrderNum;
        }

        public Object getMonthAverageAmount() {
            return monthAverageAmount;
        }

        public void setMonthAverageAmount(Object monthAverageAmount) {
            this.monthAverageAmount = monthAverageAmount;
        }

        public Object getMonthAfterSaleTimes() {
            return monthAfterSaleTimes;
        }

        public void setMonthAfterSaleTimes(Object monthAfterSaleTimes) {
            this.monthAfterSaleTimes = monthAfterSaleTimes;
        }

        public Object getThirtyTimesNum() {
            return thirtyTimesNum;
        }

        public void setThirtyTimesNum(Object thirtyTimesNum) {
            this.thirtyTimesNum = thirtyTimesNum;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }
    }
}
