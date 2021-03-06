package com.tdjpartner.model;

import java.util.List;

public class CustomerInfo {
    public int count;
    public List<ClientInfo> partnerCustomerList;
//    public List<Customer> partnerCustomerList;



    /**
     * {
     * "customerId": 256,
     * "auth": 0,
     * "name": "黄家小院",
     * "address": "丹江路苏宁电器对面雅斯特院内汉水上景酒店",
     * "boss": "黄艳伟",
     * "mobile": "13476404512",
     * "partnerName": null,
     * "followTime": null,
     * "websiteId": 2,
     * "regionCollNo": "F39区-",
     * "regionNo": "39",
     * "lineCode": null,
     * "customerLineCode": null,
     * "todayAmount": 0.00,
     * "averageAmount": 0.00,
     * "todayTimes": null,
     * "todayAfterSaleTimes": null,
     * "monthTimes": 0,
     * "notOrderDays": null,
     * "notCallDays": null,
     * "userType": 2,
     * "monthAmount": null,
     * "afterSaleTimes": null,
     * "noCallDay": null,
     * "receiveName": "黄伟",
     * "receiveMobile": "13476404512",
     * "deliveredTimeBegin": "8:00",
     * "deliveredTimeEnd": "9:00",
     * "punchDistance": null,
     * "dayOrderTimes": null,
     * "dayRegisterTimes": null,
     * "lat": "32.058401",
     * "lon": "112.124531",
     * "headUrl": null,
     * "lastMonthActiveNum": null,
     * "activeNum": null,
     * "callNum": null,
     * "monthRegisterNum": null,
     * "monthActiveNum": null,
     * "monthCallNum": null,
     * "lastMonthAvgActiveNum": null,
     * "monthAvgActiveNum": 0.0,
     * "firstOrderNum": null,
     * "lastMonthFirstOrderNum": null,
     * "monthFirstOrderNum": null,
     * "monthAverageAmount": null,
     * "monthAfterSaleTimes": 0,
     * "thirtyTimesNum": null,
     * "createTime": "2021-04-16 14:46:07",
     * "yesterdayActiveNum": null,
     * "addMonthAmount": null,
     * "monthAfterSaleAmount": null,
     * "afterSaleAmount": null,
     * "categoryNum": null,
     * "categoryAmount": null,
     * "gradeNextName": null,
     * "grade": 0,
     * "driverName": null,
     * "driverTel": null,
     * "partnerPhone": null
     * }
     */
    static public class Customer {

        public String name; //"黄家小院",
        public String address; //"丹江路苏宁电器对面雅斯特院内汉水上景酒店",
        public String boss; //"黄艳伟",
        public String mobile; //"13476404512",
        public String regionCollNo; //"F39区-",
        public String regionNo; //"39",
        public String receiveName; //"黄伟",
        public String receiveMobile; //"13476404512",
        public String deliveredTimeBegin; //"8:00",
        public String deliveredTimeEnd; //"9:00",
        public String lat; //"32.058401",
        public String lon; //"112.124531",
        public String createTime; //"2021-04-16 14:46:07",
        public String partnerName; //null,
        public String gradeNextName; //null,
        public String driverName; //null,
        public String lineCode; //null,
        public String customerLineCode; //null,
        public String lastMonthActiveNum; //null,
        public String activeNum; //null,
        public String callNum; //null,
        public String monthRegisterNum; //null,
        public String monthActiveNum; //null,
        public String monthCallNum; //null,
        public String lastMonthAvgActiveNum; //null,
        public String monthAvgActiveNum; //0.0,
        public String firstOrderNum; //null,
        public String lastMonthFirstOrderNum; //null,
        public String monthFirstOrderNum; //null,
        public String yesterdayActiveNum; //null,
        public String categoryNum; //null,
        public String driverTel; //null,
        public String partnerPhone; //null
        public String headUrl; //null,
        public int todayTimes; //null,
        public int todayAfterSaleTimes; //null,
        public int monthTimes; //0,
        public int afterSaleTimes; //null,
        public int dayOrderTimes; //null,
        public int dayRegisterTimes; //null,
        public int monthAfterSaleTimes; //0,
        public int thirtyTimesNum; //null,
        public int customerId; //256,
        public int websiteId; //2,
        public int auth; //0,
        public int followTime; //null,
        public int notOrderDays; //null,
        public int notCallDays; //null,
        public int userType; //2,
        public int noCallDay; //null,
        public int grade; //0,
        public float todayAmount; //0.00,
        public float averageAmount; //0.00,
        public float monthAmount; //null,
        public float monthAverageAmount; //null,
        public float addMonthAmount; //null,
        public float monthAfterSaleAmount; //null,
        public float afterSaleAmount; //null,
        public float categoryAmount; //null,
        public float punchDistance; //null,

    }
}
