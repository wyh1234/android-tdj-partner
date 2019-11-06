package com.tdjpartner.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderList {

    /**
     * total : 32
     * items : [{"baseTotalRefund":0,"buyNumber":0,"confirmReceiveTime":0,"createTime":"2017-10-13 09:52","customerContactName":"又又","customerId":1,"customerLogo":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/170331101725484c8ec6.jpeg   ","customerName":"湖北黄牛庄","customerTel":"18571161280","expectDeliveredDate":"2017-10-14","expectDeliveredEarliestTime":"08:00","expectDeliveredLatestTime":"11:00","extraField":[{"stock":999980,"productQty":1,"nickName":"兰州鲜百合","productImage":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/170723072717e6bae0c1.jpg   ","itemStatus":0,"qrCodeId":"9171013000170","sku":"xtb_1500766111162","itemId":104989,"storeId":81,"productUnit":"袋","productName":"百合","productPrice":5,"orderId":54310,"productId":2179}],"itemCount":1,"lastName":"淘大集","orderId":0,"orderIds":"54310","orderNo":"100000133","shipCost":0,"shipStatus":0,"statusCode":"trade_closed","subtotalCost":5,"totalCost":5,"updateTime":0,"outTradeNo":"3bdb0730afb911e7b931578df62c79c9","couponAmount":0,"totalFreight":20,"actualTotalCost":25}]
     * pn : 1
     * ps : 1
     */

    private int total;
    private List<ItemsBean> obj;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemsBean> getObj() {
        return obj;
    }

    public void setObj(List<ItemsBean> obj) {
        this.obj = obj;
    }

    public static class ItemsBean implements Serializable {
        /**
         * baseTotalRefund : 0
         * buyNumber : 0
         * confirmReceiveTime : 0
         * createTime : 2017-10-13 09:52
         * customerContactName : 又又
         * customerId : 1
         * customerLogo : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/170331101725484c8ec6.jpeg
         * customerName : 湖北黄牛庄
         * customerTel : 18571161280
         * expectDeliveredDate : 2017-10-14
         * expectDeliveredEarliestTime : 08:00
         * expectDeliveredLatestTime : 11:00
         * extraField : [{"stock":999980,"productQty":1,"nickName":"兰州鲜百合","productImage":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/170723072717e6bae0c1.jpg   ","itemStatus":0,"qrCodeId":"9171013000170","sku":"xtb_1500766111162","itemId":104989,"storeId":81,"productUnit":"袋","productName":"百合","productPrice":5,"orderId":54310,"productId":2179}]
         * itemCount : 1
         * lastName : 淘大集
         * orderId : 0
         * orderIds : 54310
         * orderNo : 100000133
         * shipCost : 0
         * shipStatus : 0
         * statusCode : trade_closed
         * subtotalCost : 5
         * totalCost : 5
         * updateTime : 0
         * outTradeNo : 3bdb0730afb911e7b931578df62c79c9
         * couponAmount : 0
         * totalFreight : 20
         * actualTotalCost : 25
         */

        private String createTime;
        private int customerId;
        private int itemCount;
        private String lastName;
        private String customerLogo;
        private int orderId;
        private BigDecimal actualTotalCost;
        private String statusCode;
        private List<CartGoodsBean> extraField;
        private String orderNo;

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getCustomerLogo() {
            return customerLogo;
        }

        public void setCustomerLogo(String customerLogo) {
            this.customerLogo = customerLogo;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }


        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }


        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        public BigDecimal getActualTotalCost() {
            return actualTotalCost;
        }

        public void setActualTotalCost(BigDecimal actualTotalCost) {
            this.actualTotalCost = actualTotalCost;
        }

        public List<CartGoodsBean> getExtraField() {
            return extraField;
        }

        public void setExtraField(List<CartGoodsBean> extraField) {
            this.extraField = extraField;
        }

        public static class ExtraFieldBean {
            /**
             * stock : 999980
             * productQty : 1
             * nickName : 兰州鲜百合
             * productImage : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/170723072717e6bae0c1.jpg
             * itemStatus : 0
             * qrCodeId : 9171013000170
             * sku : xtb_1500766111162
             * itemId : 104989
             * storeId : 81
             * productUnit : 袋
             * productName : 百合
             * productPrice : 5
             * orderId : 54310
             * productId : 2179
             */

            private int stock;
            private int productQty;
            private String nickName;
            private String productImage;
            private int itemStatus;
            private String qrCodeId;
            private String sku;
            private int itemId;
            private int storeId;
            private String productUnit;
            private String productName;
            private int productPrice;
            private int orderId;
            private int productId;

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public int getProductQty() {
                return productQty;
            }

            public void setProductQty(int productQty) {
                this.productQty = productQty;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getProductImage() {
                return productImage;
            }

            public void setProductImage(String productImage) {
                this.productImage = productImage;
            }

            public int getItemStatus() {
                return itemStatus;
            }

            public void setItemStatus(int itemStatus) {
                this.itemStatus = itemStatus;
            }

            public String getQrCodeId() {
                return qrCodeId;
            }

            public void setQrCodeId(String qrCodeId) {
                this.qrCodeId = qrCodeId;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public int getStoreId() {
                return storeId;
            }

            public void setStoreId(int storeId) {
                this.storeId = storeId;
            }

            public String getProductUnit() {
                return productUnit;
            }

            public void setProductUnit(String productUnit) {
                this.productUnit = productUnit;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(int productPrice) {
                this.productPrice = productPrice;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }
        }
    }

}
