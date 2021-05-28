package com.tdjpartner.model;

import java.util.List;

public class IronDayAndMonthData {

    /**
     * {
     * "amount": 0.000000,
     * "addAmount": 0,
     * "times": 0,
     * "afterSaleAmount": 0.000000,
     * "afterSaleTimes": 0,
     * "amountCommission": 0,
     * "registerNum": 0,
     * "callNum": 0,
     * "activeNum": 0,
     * "addActiveNum": 0,
     * "reviewNum": 0,
     * "firstOrderNum": 0,
     * "userNum": 0,
     * "orderNum": 0,
     * "noOrderNum": 0,
     * "examineNum": 0,
     * "nickName": null,
     * "partnerId": null,
     * "parentId": null,
     * "grade": null,
     * "gradeName": null,
     * "averageAmount": 0.00,
     * "yesterdayActiveNum": 0,
     * "addMonthAmount": 0,
     * "monthAvgActiveNum": 0.0,
     * "categoryNum": 0,
     * "gradeChineseName": null,
     * "totalNum": 0,
     * "teamViewList": [
     * {
     * "amount": 0.000000,
     * "addAmount": 0,
     * "times": 0,
     * "afterSaleAmount": 0.000000,
     * "afterSaleTimes": 0,
     * "amountCommission": 0,
     * "registerNum": 0,
     * "callNum": 0,
     * "activeNum": 0,
     * "addActiveNum": 0,
     * "reviewNum": 0,
     * "firstOrderNum": 0,
     * "userNum": 0,
     * "orderNum": 0,
     * "noOrderNum": 0,
     * "examineNum": 0,
     * "nickName": "12",
     * "partnerId": 25749,
     * "parentId": 258333,
     * "grade": 3,
     * "gradeName": "",
     * "averageAmount": 0.00,
     * "yesterdayActiveNum": 0,
     * "addMonthAmount": 0,
     * "monthAvgActiveNum": 0.0,
     * "categoryNum": 0,
     * "gradeChineseName": "BD",
     * "totalNum": 0,
     * "teamViewList": null
     * }]
     * "partnerId": 258333,
     * "parentId": null,
     * "grade": 4,
     * "gradeName": null,
     * "headGrade": "M2日统计"
     * }
     */

    public TeamView teamView;
    public TeamView othersTeamView;
    public List<TeamView> teamViewList;
    public int partnerId; //258333,
    public int parentId; //null,
    public int grade; //4,
    public String gradeName; //null,
    public String headGrade; //"M2日统计"

    public static class TeamView {
        public int registerNum; //0,
        public int callNum; //0,
        public int activeNum; //0,
        public int addActiveNum; //0,
        public int reviewNum; //0,
        public int firstOrderNum; //0,
        public int userNum; //0,
        public int orderNum; //0,
        public int noOrderNum; //0,
        public int examineNum; //0,
        public int yesterdayActiveNum; //0,
        public int categoryNum; //0,
        public int totalNum; //0,
        public int grade; //null,
        public int partnerId; //null,
        public int parentId; //null,
        public int times; //0,
        public float monthAvgActiveNum; //0.0,
        public float averageAmount; //0.00,
        public float amount; //0.000000,
        public float afterSaleAmount; //0.000000,
        public float addAmount; //0,
        public float afterSaleTimes; //0,
        public float amountCommission; //0,
        public float addMonthAmount; //0,
        public String nickName; //null,
        public String gradeName; //null,
        public String gradeChineseName; //null,
        public List<TeamView> teamViewList; //null,
    }
}