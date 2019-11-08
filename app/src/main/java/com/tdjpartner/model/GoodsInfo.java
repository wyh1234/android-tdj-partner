package com.tdjpartner.model;

import java.math.BigDecimal;
import java.util.List;

public class GoodsInfo  extends Message{


    /**
     * total : 2
     * obj : [{"description":"","specialId":286,"bannerImage":"","specs":[{"specId":120998,"productId":74813,"salesNumber":219,"avgPrice":8.51,"level3Value":0,"level3Unit":"","level2Value":4.7,"level1Unit":"袋","levelType":2,"price":40,"siteId":3,"avgUnit":"斤","stock":32,"level2Unit":"斤"}],"credentialsImage":"","allowPurchase":6,"storeName":"武汉谦程食品专营店","categories":"1591","packageName":"","specialPrice":0,"monthSaleNumbers":131,"isF":null,"gallery":["http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191012171820e1664771.jpg","http://tsp-img.oss-cn-hangzhou.aliyuncs.com/19101217182962dbc8d5.jpg"],"productType":4,"image":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191012171820e1664771.jpg","storeType":-1,"nickName":"7层巴沙鱼柳全网最低价超少冰","isP":1,"authStatus":2,"storeStation":0,"catalogCategoryName":"","entityId":74813,"updateTime":1571063807000,"commodityId":10001324,"store":1902,"saleNum":219,"alreadyPurchase":0,"verifyInfo":"","realName":"李晓丽","unit":"袋","createTime":1565327442000,"phone":"13545230592","productCriteria":"1","minPrice":40,"name":"巴沙鱼","maxPrice":-1,"categoryId":"1591","status":1,"foregift":-1},{"description":"","specialId":286,"bannerImage":"","specs":[{"specId":105471,"productId":59291,"salesNumber":450,"avgPrice":36,"level3Value":0,"level3Unit":"","level2Value":0,"level1Unit":"个","levelType":1,"price":36,"siteId":3,"avgUnit":"个","stock":927,"level2Unit":""}],"credentialsImage":"","allowPurchase":6,"storeName":"明华鲜品","categories":"246","packageName":"","specialPrice":0,"monthSaleNumbers":101,"isF":null,"gallery":["http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191014202427e4ac0449.jpg","http://tsp-img.oss-cn-hangzhou.aliyuncs.com/19101420243835819d9f.jpg","http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191014202545817025ca.jpg"],"productType":4,"image":"http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191014202427e4ac0449.jpg","storeType":-1,"nickName":"特惠净肉白条鸭4.5-5.5斤","isP":0,"authStatus":2,"storeStation":0,"catalogCategoryName":"","entityId":59291,"updateTime":1571060754000,"commodityId":1771,"store":2334,"saleNum":450,"alreadyPurchase":0,"verifyInfo":"","realName":"陈彬","unit":"个","createTime":1557305806000,"phone":"13971405691","productCriteria":"1","minPrice":36,"name":"白条鸭","maxPrice":-1,"categoryId":"246","status":1,"foregift":-1}]
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

    public static class ObjBean extends Message {
        /**
         * description :
         * specialId : 286
         * bannerImage :
         * specs : [{"specId":120998,"productId":74813,"salesNumber":219,"avgPrice":8.51,"level3Value":0,"level3Unit":"","level2Value":4.7,"level1Unit":"袋","levelType":2,"price":40,"siteId":3,"avgUnit":"斤","stock":32,"level2Unit":"斤"}]
         * credentialsImage :
         * allowPurchase : 6
         * storeName : 武汉谦程食品专营店
         * categories : 1591
         * packageName :
         * specialPrice : 0
         * monthSaleNumbers : 131
         * isF : null
         * gallery : ["http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191012171820e1664771.jpg","http://tsp-img.oss-cn-hangzhou.aliyuncs.com/19101217182962dbc8d5.jpg"]
         * productType : 4
         * image : http://tsp-img.oss-cn-hangzhou.aliyuncs.com/191012171820e1664771.jpg
         * storeType : -1
         * nickName : 7层巴沙鱼柳全网最低价超少冰
         * isP : 1
         * authStatus : 2
         * storeStation : 0
         * catalogCategoryName :
         * entityId : 74813
         * updateTime : 1571063807000
         * commodityId : 10001324
         * store : 1902
         * saleNum : 219
         * alreadyPurchase : 0
         * verifyInfo :
         * realName : 李晓丽
         * unit : 袋
         * createTime : 1565327442000
         * phone : 13545230592
         * productCriteria : 1
         * minPrice : 40
         * name : 巴沙鱼
         * maxPrice : -1
         * categoryId : 1591
         * status : 1
         * foregift : -1
         */

        private String storeName;
        private String packageName;
        private Object isF;
        private String image;
        private String nickName;
        private int entityId;
        private long updateTime;
        private String unit;
        private long createTime;
        private String phone;
        private BigDecimal minPrice;
        private String name;
        private BigDecimal maxPrice;
        private int status;
        private List<SpecsBean> specs;


        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }


        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }





        public Object getIsF() {
            return isF;
        }

        public void setIsF(Object isF) {
            this.isF = isF;
        }



        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }



        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }








        public int getEntityId() {
            return entityId;
        }

        public void setEntityId(int entityId) {
            this.entityId = entityId;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }


        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


        public BigDecimal getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
        }


        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }


        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }


        public static class SpecsBean {
            /**
             * specId : 120998
             * productId : 74813
             * salesNumber : 219
             * avgPrice : 8.51
             * level3Value : 0
             * level3Unit :
             * level2Value : 4.7
             * level1Unit : 袋
             * levelType : 2
             * price : 40
             * siteId : 3
             * avgUnit : 斤
             * stock : 32
             * level2Unit : 斤
             */

            private int level3Value;
            private String level3Unit;
            private double level2Value;
            private int levelType;
            private String level2Unit;





            public int getLevel3Value() {
                return level3Value;
            }

            public void setLevel3Value(int level3Value) {
                this.level3Value = level3Value;
            }

            public String getLevel3Unit() {
                return level3Unit;
            }

            public void setLevel3Unit(String level3Unit) {
                this.level3Unit = level3Unit;
            }

            public double getLevel2Value() {
                return level2Value;
            }

            public void setLevel2Value(double level2Value) {
                this.level2Value = level2Value;
            }


            public int getLevelType() {
                return levelType;
            }

            public void setLevelType(int levelType) {
                this.levelType = levelType;
            }





            public String getLevel2Unit() {
                return level2Unit;
            }

            public void setLevel2Unit(String level2Unit) {
                this.level2Unit = level2Unit;
            }
        }
    }
}
