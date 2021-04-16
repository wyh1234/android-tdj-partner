package com.tdjpartner.model;

import java.util.List;

/**
 * Created by LFM on 2021/4/14.
 */
public class HotelAuditPageList {

    public int total;
    public List<HotelAuditPage> obj;

    public static class HotelAuditPage {
        /**
         * {
         *    "region_no":"1",
         *    "is_active":1,
         *    "flag":1,
         *    "image_url":"",
         *    "authStatus":1,
         *    "created_at":"2021-04-09 21:03:45",
         *    "entity_id":2023832,
         *    "verify_info":"审核通过!",
         *    "phone":"13995566152",
         *    "nick_name":"测试152",
         *    "account_code":"13995566152",
         *    "commissioner_name":"测试130",
         *    "enterprise_msg":"华发南路南园街道上步中学对面邮政报亭",
         *    "enterprise_code":"测试门店152",
         *    "remarks":""
         * }
         */
        public int is_active; //1,
        public int flag; //1,
        public int authStatus; //1,
        public int entity_id; //2023832,
        public String region_no; //"1",
        public String image_url; //"",
        public String bzlicence_url; //"",
        public String created_at; //"2021-04-09 21:03:45",
        public String verify_info; //"审核通过!",
        public String phone; //"13995566152",
        public String nick_name; //"测试152",
        public String account_code; //"13995566152",
        public String commissioner_name; //"测试130",
        public String enterprise_msg; //"华发南路南园街道上步中学对面邮政报亭",
        public String enterprise_code; //"测试门店152",
        public String remarks; //""
    }

}
