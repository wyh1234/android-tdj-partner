package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class HomeData  {


    /**
     * allData : {"notOrderCustomer":1,"countCustomer":1,"orderCustomer":0}
     * registerTimesTop : [{"customerId":null,"auth":null,"name":null,"address":null,"boss":null,"mobile":"12345678761","partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":null,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":null,"monthAfterSaleTimes":null,"thirtyTimesNum":null,"createTime":null},{"customerId":null,"auth":null,"name":null,"address":null,"boss":null,"mobile":"12345678761","partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":null,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":null,"monthAfterSaleTimes":null,"thirtyTimesNum":null,"createTime":null},null]
     * monthData : {"customerId":null,"auth":null,"name":null,"address":null,"boss":null,"mobile":null,"partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":null,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":"0.0","monthAvgActiveNum":"0","firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":0,"monthAverageAmount":null,"monthAfterSaleTimes":null,"thirtyTimesNum":null,"createTime":null}
     * partnerApproachData : [{"id":1,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190815122111db4a3126.png","sort":1,"title":"商品/店铺","context":"测试","linkTitle":"测试","linkType":"2","linkUrl":"http://47.111.22.162:9000/tdj-partner/partner/collect/products","status":"1","seatType":"2"},{"id":2,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/1910220912066f4844af.png","sort":2,"title":"订单记录","context":"测试","linkTitle":"测试","linkType":"2","linkUrl":"http://47.111.22.162:9000/tdj-partner/partner/order/pageList","status":"1","seatType":"2"},{"id":3,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191022091255ce69f600.png","sort":3,"title":"使用卷数","context":"测试","linkTitle":"测试","linkType":"2","linkUrl":"http://47.111.22.162:9000/tdj-partner/partner/coupons/findByUser","status":"1","seatType":"2"},{"id":4,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191022091318c3e53d70.png","sort":4,"title":"拜访记录","context":"测试","linkTitle":"测试","linkType":"2","linkUrl":"http://47.111.22.162:9000/tdj-partner/partner/call/list","status":"1","seatType":"2"},{"id":7,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/19102220590565057b59.png","sort":1,"title":"即将掉落","context":null,"linkTitle":null,"linkType":"2","linkUrl":null,"status":"1","seatType":"1","downDataNum":0},{"id":8,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/19102221001647680bd8.png","sort":2,"title":"公海跟进","context":null,"linkTitle":null,"linkType":"2","linkUrl":null,"status":"1","seatType":"1","followDataNum":4},{"id":9,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191022210053f1ecc6a6.png","sort":3,"title":"合伙人审核","context":null,"linkTitle":null,"linkType":"2","linkUrl":null,"status":"1","seatType":"1","noVerifyNum":0},{"id":10,"menuPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191022210119ee440f84.png","sort":4,"title":"设置专员","context":null,"linkTitle":null,"linkType":"2","linkUrl":null,"status":"1","seatType":"1"}]
     * todayData : null
     * ordersTimesTop : [{"customerId":null,"auth":null,"name":null,"address":null,"boss":null,"mobile":"12345678761","partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":null,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":null,"monthAfterSaleTimes":null,"thirtyTimesNum":null,"createTime":null},null,{"customerId":null,"auth":null,"name":null,"address":null,"boss":null,"mobile":"12345678761","partnerName":null,"followTime":null,"websiteId":null,"regionCollNo":null,"regionNo":null,"lineCode":null,"customerLineCode":null,"todayAmount":null,"averageAmount":null,"todayTimes":null,"todayAfterSaleTimes":null,"monthTimes":null,"notOrderDays":null,"notCallDays":null,"userType":null,"monthAmount":null,"afterSaleTimes":null,"noCallDay":null,"receiveName":null,"receiveMobile":null,"deliveredTimeBegin":null,"deliveredTimeEnd":null,"punchDistance":null,"dayOrderTimes":null,"dayRegisterTimes":null,"lat":null,"lon":null,"headUrl":null,"lastMonthActiveNum":null,"activeNum":null,"callNum":null,"monthRegisterNum":null,"monthActiveNum":null,"monthCallNum":null,"lastMonthAvgActiveNum":null,"monthAvgActiveNum":null,"firstOrderNum":null,"lastMonthFirstOrderNum":null,"monthFirstOrderNum":null,"monthAverageAmount":null,"monthAfterSaleTimes":null,"thirtyTimesNum":null,"createTime":null}]
     */

    private AllDataBean allData;
    private MonthDataBean monthData;
    private HomeTodayData todayData;
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

    public HomeTodayData getTodayData() {
        return todayData;
    }

    public void setTodayData(HomeTodayData todayData) {
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

    public static class HomeTodayData{

        /**
         * customerId : null
         * auth : null
         * name : null
         * address : null
         * boss : null
         * mobile : null
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
         * activeNum : 19
         * callNum : 18
         * monthRegisterNum : null
         * monthActiveNum : null
         * monthCallNum : null
         * lastMonthAvgActiveNum : null
         * monthAvgActiveNum : null
         * firstOrderNum : 21
         * lastMonthFirstOrderNum : null
         * monthFirstOrderNum : null
         * monthAverageAmount : null
         * monthAfterSaleTimes : null
         * thirtyTimesNum : null
         * createTime : null
         */

        private Object customerId;
        private Object auth;
        private Object name;
        private Object address;
        private Object boss;
        private Object mobile;
        private Object partnerName;
        private Object followTime;
        private Object websiteId;
        private Object regionCollNo;
        private Object regionNo;
        private Object lineCode;
        private Object customerLineCode;
        private BigDecimal todayAmount;
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
        private Integer activeNum;
        private Integer callNum;
        private Object monthRegisterNum;
        private Object monthActiveNum;
        private Object monthCallNum;
        private Object lastMonthAvgActiveNum;
        private Object monthAvgActiveNum;
        private Integer firstOrderNum;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
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

        public BigDecimal getTodayAmount() {
            return todayAmount==null? BigDecimal.valueOf(0) :todayAmount;
        }

        public void setTodayAmount(BigDecimal todayAmount) {
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
            return dayRegisterTimes==null?0:dayRegisterTimes;
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

        public Integer getActiveNum() {
            return activeNum==null?0:activeNum;
        }

        public void setActiveNum(Integer activeNum) {
            this.activeNum = activeNum;
        }

        public Integer getCallNum() {
            return callNum==null?0:callNum;
        }

        public void setCallNum(Integer callNum) {
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

        public Integer getFirstOrderNum() {
            return firstOrderNum==null?0:firstOrderNum;
        }

        public void setFirstOrderNum(Integer firstOrderNum) {
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
            return countCustomer==null?0:countCustomer;
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
         * customerId : null
         * auth : null
         * name : null
         * address : null
         * boss : null
         * mobile : null
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
         * lastMonthAvgActiveNum : 0.0
         * monthAvgActiveNum : 0
         * firstOrderNum : null
         * lastMonthFirstOrderNum : null
         * monthFirstOrderNum : 0
         * monthAverageAmount : null
         * monthAfterSaleTimes : null
         * thirtyTimesNum : null
         * createTime : null
         */

        private Object customerId;
        private Object auth;
        private Object name;
        private Object address;
        private Object boss;
        private Object mobile;
        private Object partnerName;
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
        private BigDecimal monthAmount;
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
        private Integer monthRegisterNum;
        private Object monthActiveNum;
        private Integer monthCallNum;
        private Float lastMonthAvgActiveNum;
        private Float monthAvgActiveNum;
        private Integer firstOrderNum;
        private Object lastMonthFirstOrderNum;
        private Integer monthFirstOrderNum;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
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

        public BigDecimal getMonthAmount() {
            return monthAmount==null? BigDecimal.valueOf(0) :monthAmount;
        }

        public void setMonthAmount(BigDecimal monthAmount) {
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
            return dayRegisterTimes;
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

        public Integer getMonthRegisterNum() {
            return monthRegisterNum==null?0:monthRegisterNum;
        }

        public void setMonthRegisterNum(Integer monthRegisterNum) {
            this.monthRegisterNum = monthRegisterNum;
        }

        public Object getMonthActiveNum() {
            return monthActiveNum;
        }

        public void setMonthActiveNum(Object monthActiveNum) {
            this.monthActiveNum = monthActiveNum;
        }

        public Integer getMonthCallNum() {
            return monthCallNum==null?0:monthCallNum;
        }

        public void setMonthCallNum(Integer monthCallNum) {
            this.monthCallNum = monthCallNum;
        }

        public Float getLastMonthAvgActiveNum() {
            return lastMonthAvgActiveNum==null? 0 :lastMonthAvgActiveNum;
        }

        public void setLastMonthAvgActiveNum(Float lastMonthAvgActiveNum) {
            this.lastMonthAvgActiveNum = lastMonthAvgActiveNum;
        }

        public Float getMonthAvgActiveNum() {
            return monthAvgActiveNum==null?0:monthAvgActiveNum;
        }

        public void setMonthAvgActiveNum(Float monthAvgActiveNum) {
            this.monthAvgActiveNum = monthAvgActiveNum;
        }

        public Integer getFirstOrderNum() {
            return firstOrderNum;
        }

        public void setFirstOrderNum(Integer firstOrderNum) {
            this.firstOrderNum = firstOrderNum;
        }

        public Object getLastMonthFirstOrderNum() {
            return lastMonthFirstOrderNum;
        }

        public void setLastMonthFirstOrderNum(Object lastMonthFirstOrderNum) {
            this.lastMonthFirstOrderNum = lastMonthFirstOrderNum;
        }

        public Integer getMonthFirstOrderNum() {
            return monthFirstOrderNum==null?0:monthFirstOrderNum;
        }

        public void setMonthFirstOrderNum(Integer monthFirstOrderNum) {
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
        private Object name;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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
            return dayRegisterTimes==null?0:dayRegisterTimes;
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
        private String menuPic;
        private int sort;
        private String title;
        private String context;
        private String linkTitle;
        private String linkType;
        private String linkUrl;
        private String status;
        private String seatType;
        private int downDataNum;
        private int followDataNum;
        private int noVerifyNum;
        private Integer subscriptNum;

        public Integer getSubscriptNum() {
            return subscriptNum;
        }

        public void setSubscriptNum(Integer subscriptNum) {
            this.subscriptNum = subscriptNum;
        }

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
            return dayOrderTimes==null?0:dayOrderTimes;
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
