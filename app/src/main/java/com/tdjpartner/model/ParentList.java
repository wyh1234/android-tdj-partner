package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class ParentList {


    /**
     * user_num : 6
     * customer_order_num : 1
     * user_call_num : 1
     * customer_list : [{"buy_id":119,"buy_name":"襄阳圣祥","amount":10,"call_id":25643,"call_name":"003"}]
     * customer_num : 1
     * user_list : [{"user_id":25643,"phone":"13995566003","nick_name":"003","customer_num":1,"id":653,"call_num":3},{"user_id":25642,"phone":"13995566004","nick_name":"004","customer_num":0,"id":652,"call_num":0},{"user_id":25656,"phone":"13995566102","nick_name":"102","customer_num":0,"id":821,"call_num":0},{"user_id":25826,"phone":"13995566104","nick_name":"104","customer_num":0,"id":853,"call_num":0},{"user_id":25827,"phone":"13995566105","nick_name":"105","customer_num":0,"id":829,"call_num":0},{"user_id":25828,"phone":"13995566106","nick_name":"106","customer_num":0,"id":846,"call_num":0}]
     */

    private int user_num;
    private int customer_order_num;
    private int user_call_num;
    private int customer_num;
    private String system_time;
    private List<CustomerListBean> customer_list;
    private List<UserListBean> user_list;
    private Headinfo headinfoList;

    public String getSystem_time() {
        return system_time;
    }

    public void setSystem_time(String system_time) {
        this.system_time = system_time;
    }

    public Headinfo getHeadinfoList() {
        return headinfoList;
    }

    public void setHeadinfoList(Headinfo headinfoList) {
        this.headinfoList = headinfoList;
    }

    public int getUser_num() {
        return user_num;
    }

    public void setUser_num(int user_num) {
        this.user_num = user_num;
    }

    public int getCustomer_order_num() {
        return customer_order_num;
    }

    public void setCustomer_order_num(int customer_order_num) {
        this.customer_order_num = customer_order_num;
    }

    public int getUser_call_num() {
        return user_call_num;
    }

    public void setUser_call_num(int user_call_num) {
        this.user_call_num = user_call_num;
    }

    public int getCustomer_num() {
        return customer_num;
    }

    public void setCustomer_num(int customer_num) {
        this.customer_num = customer_num;
    }

    public List<CustomerListBean> getCustomer_list() {
        return customer_list;
    }

    public void setCustomer_list(List<CustomerListBean> customer_list) {
        this.customer_list = customer_list;
    }

    public List<UserListBean> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<UserListBean> user_list) {
        this.user_list = user_list;
    }
    public static class Headinfo extends Message {
      private   int curr;
        private  int total;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCurr() {
            return curr;
        }

        public void setCurr(int curr) {
            this.curr = curr;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
    public static class CustomerListBean extends Message {
        /**
         * buy_id : 119
         * buy_name : 襄阳圣祥
         * amount : 10.0
         * call_id : 25643
         * call_name : 003
         */

        private int buy_id;
        private String buy_name;
        private BigDecimal amount;
        private int call_id;
        private String call_name;
        private boolean f;

        public boolean isF() {
            return f;
        }

        public void setF(boolean f) {
            this.f = f;
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

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
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
    }

    public static class UserListBean extends Message {
        /**
         * user_id : 25643
         * phone : 13995566003
         * nick_name : 003
         * customer_num : 1
         * id : 653
         * call_num : 3
         */

        private int user_id;
        private String phone;
        private String nick_name;
        private int customer_num;
        private int conversion_num;
        private int id;
        private int call_num;
        private boolean f;
        private String system_time;

        public int getConversion_num() {
            return conversion_num;
        }

        public void setConversion_num(int conversion_num) {
            this.conversion_num = conversion_num;
        }

        public String getSystem_time() {
            return system_time;
        }

        public void setSystem_time(String system_time) {
            this.system_time = system_time;
        }

        public boolean isF() {
            return f;
        }

        public void setF(boolean f) {
            this.f = f;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getCustomer_num() {
            return customer_num;
        }

        public void setCustomer_num(int customer_num) {
            this.customer_num = customer_num;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCall_num() {
            return call_num;
        }

        public void setCall_num(int call_num) {
            this.call_num = call_num;
        }
    }
}
