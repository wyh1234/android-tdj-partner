package com.tdjpartner.model;

import java.util.List;

public class BaiFangHistory {

    /**
     * total : 10
     * obj : [{"id":36,"userId":14,"userName":"test","buyPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png","callDate":"2019-09-26 16:54:24","address":"紫贞街道长虹北路9号005","buyId":25714,"buyName":"hotel1","callId":4,"callName":"拜访人4","callMobile":"18907185633","matters":"事宜4","results":"结果4","callPic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png"}]
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
         * id : 36
         * userId : 14
         * userName : test
         * buyPic : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png
         * callDate : 2019-09-26 16:54:24
         * address : 紫贞街道长虹北路9号005
         * buyId : 25714
         * buyName : hotel1
         * callId : 4
         * callName : 拜访人4
         * callMobile : 18907185633
         * matters : 事宜4
         * results : 结果4
         * callPic : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png
         */

        private int id;
        private int userId;
        private String userName;
        private String buyPic;
        private String callDate;
        private String address;
        private int buyId;
        private String buyName;
        private int callId;
        private String callName;
        private String callMobile;
        private String matters;
        private String results;
        private String callPic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBuyPic() {
            return buyPic;
        }

        public void setBuyPic(String buyPic) {
            this.buyPic = buyPic;
        }

        public String getCallDate() {
            return callDate;
        }

        public void setCallDate(String callDate) {
            this.callDate = callDate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getBuyId() {
            return buyId;
        }

        public void setBuyId(int buyId) {
            this.buyId = buyId;
        }

        public String getBuyName() {
            return buyName;
        }

        public void setBuyName(String buyName) {
            this.buyName = buyName;
        }

        public int getCallId() {
            return callId;
        }

        public void setCallId(int callId) {
            this.callId = callId;
        }

        public String getCallName() {
            return callName;
        }

        public void setCallName(String callName) {
            this.callName = callName;
        }

        public String getCallMobile() {
            return callMobile;
        }

        public void setCallMobile(String callMobile) {
            this.callMobile = callMobile;
        }

        public String getMatters() {
            return matters;
        }

        public void setMatters(String matters) {
            this.matters = matters;
        }

        public String getResults() {
            return results;
        }

        public void setResults(String results) {
            this.results = results;
        }

        public String getCallPic() {
            return callPic;
        }

        public void setCallPic(String callPic) {
            this.callPic = callPic;
        }
    }
}
