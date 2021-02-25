package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class DistinctList {


    /**
     * err : 0
     * data : {"order_total_money":0,"list":[{"buy_id":119,"buy_name":"襄阳圣祥","address":"测试地址0","call_date":"2019-09-19 10:34:58","order_status":0,"order_amount":0,"call_mobile":"13797635252","mobile":"13797635252","buy_pic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png","call_id":25643,"call_name":"拜访人0","matters":"主要事宜0","results":"拜访结果0"}],"conversion_num":0,"call_num":1}
     * error : null
     * msg : Success
     * errorCode : null
     */



        /**
         * order_total_money : 0
         * list : [{"buy_id":119,"buy_name":"襄阳圣祥","address":"测试地址0","call_date":"2019-09-19 10:34:58","order_status":0,"order_amount":0,"call_mobile":"13797635252","mobile":"13797635252","buy_pic":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png","call_id":25643,"call_name":"拜访人0","matters":"主要事宜0","results":"拜访结果0"}]
         * conversion_num : 0
         * call_num : 1
         */

        private BigDecimal order_total_money;
        private int conversion_num;
        private int call_num;
        private List<ListBean> list;

        public BigDecimal getOrder_total_money() {
            return order_total_money;
        }

        public void setOrder_total_money(BigDecimal order_total_money) {
            this.order_total_money = order_total_money;
        }

        public int getConversion_num() {
            return conversion_num;
        }

        public void setConversion_num(int conversion_num) {
            this.conversion_num = conversion_num;
        }

        public int getCall_num() {
            return call_num;
        }

        public void setCall_num(int call_num) {
            this.call_num = call_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * buy_id : 119
             * buy_name : 襄阳圣祥
             * address : 测试地址0
             * call_date : 2019-09-19 10:34:58
             * order_status : 0
             * order_amount : 0
             * call_mobile : 13797635252
             * mobile : 13797635252
             * buy_pic : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/190805132049f7117f84.png
             * call_id : 25643
             * call_name : 拜访人0
             * matters : 主要事宜0
             * results : 拜访结果0
             */

            private int buy_id;
            private String buy_name;
            private String address;
            private String call_date;
            private int order_status;
            private BigDecimal order_amount;
            private String call_mobile;
            private String mobile;
            private String call_pic;
            private int call_id;
            private String call_name;
            private String matters;
            private String results;
            private String user_name;

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getCall_pic() {
                return call_pic;
            }

            public void setCall_pic(String call_pic) {
                this.call_pic = call_pic;
            }

            public int getBuy_id() {
                return buy_id;
            }

            public void setBuy_id(int buy_id) {
                this.buy_id = buy_id;
            }

            public String getBuy_name() {
                return buy_name;
            }

            public void setBuy_name(String buy_name) {
                this.buy_name = buy_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCall_date() {
                return call_date;
            }

            public void setCall_date(String call_date) {
                this.call_date = call_date;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public BigDecimal getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(BigDecimal order_amount) {
                this.order_amount = order_amount;
            }

            public String getCall_mobile() {
                return call_mobile;
            }

            public void setCall_mobile(String call_mobile) {
                this.call_mobile = call_mobile;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }


            public int getCall_id() {
                return call_id;
            }

            public void setCall_id(int call_id) {
                this.call_id = call_id;
            }

            public String getCall_name() {
                return call_name;
            }

            public void setCall_name(String call_name) {
                this.call_name = call_name;
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
        }
}
