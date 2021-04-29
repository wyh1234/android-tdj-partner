package com.tdjpartner.model;

import java.util.List;

/**
 * Created by LFM on 2021/4/26.
 */
public class AfterDetailData {

    public int entityId; //71969
//    public List<Order> order;
    public AfterSaleInfoData.AfterSaleInfo order;
    public List<AfterSale> afterSale;

    public static class Order{
        public String store_name = ""; //测试环境店铺
    }

    public static class AfterSale{
        public int id; //145,
        public int storeId; //2716,
        public int customerId; //258277,
        public int entityId; //71969,
        public int lineId; //null,
        public int ps; //null,
        public int pn; //null
        public String createTime = ""; //2021-03-08 09:26:14,
        public String storeName = ""; //测试环境店铺,
        public String customerUserName = ""; //cs033,
        public String customerName = ""; //fffffffffff33,
        public String customerTel = ""; //12345676033,
        public String customerAddress = ""; //和平大道与徐东大街交叉口南150米,
        public String orderId = ""; //10003538,
        public String customerNo = ""; //E107区95-5,
        public String sku = ""; //,
        public String googsName = ""; //大米,
        public String nickName = ""; //,
        public String unit = ""; //斤,
        public String site = ""; //3,
        public String price = ""; //3.00,
        public String afterNum = ""; //3,
        public String type = ""; //4,
        public String dirverName = ""; //张家虎(专员),
        public String dirverNo = ""; //7659,
        public String driverTel = ""; //13197189666,
        public String purchasePrice = ""; //11.00,
        public String purchaseNum = ""; //1,
        public String purchaseMoney = ""; //11,
        public String images = ""; //123123,123123,12331,
        public String status = ""; //10,
        public String afterSalesNo = ""; //1614566211557,
        public String orderItemId = ""; //11876138,
        public String remark = ""; //123ceshi,
    }
}
