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

        private String description;
        private int specialId;
        private String bannerImage;
        private String credentialsImage;
        private int allowPurchase;
        private String storeName;
        private String categories;
        private String packageName;
        private int specialPrice;
        private int monthSaleNumbers;
        private Object isF;
        private int productType;
        private String image;
        private int storeType;
        private String nickName;
        private int isP;
        private int authStatus;
        private int storeStation;
        private String catalogCategoryName;
        private int entityId;
        private long updateTime;
        private int commodityId;
        private int store;
        private int saleNum;
        private int alreadyPurchase;
        private String verifyInfo;
        private String realName;
        private String unit;
        private long createTime;
        private String phone;
        private String productCriteria;
        private int minPrice;
        private String name;
        private BigDecimal maxPrice;
        private String categoryId;
        private int status;
        private int foregift;
        private List<SpecsBean> specs;
        private List<String> gallery;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSpecialId() {
            return specialId;
        }

        public void setSpecialId(int specialId) {
            this.specialId = specialId;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getCredentialsImage() {
            return credentialsImage;
        }

        public void setCredentialsImage(String credentialsImage) {
            this.credentialsImage = credentialsImage;
        }

        public int getAllowPurchase() {
            return allowPurchase;
        }

        public void setAllowPurchase(int allowPurchase) {
            this.allowPurchase = allowPurchase;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(int specialPrice) {
            this.specialPrice = specialPrice;
        }

        public int getMonthSaleNumbers() {
            return monthSaleNumbers;
        }

        public void setMonthSaleNumbers(int monthSaleNumbers) {
            this.monthSaleNumbers = monthSaleNumbers;
        }

        public Object getIsF() {
            return isF;
        }

        public void setIsF(Object isF) {
            this.isF = isF;
        }

        public int getProductType() {
            return productType;
        }

        public void setProductType(int productType) {
            this.productType = productType;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getStoreType() {
            return storeType;
        }

        public void setStoreType(int storeType) {
            this.storeType = storeType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getIsP() {
            return isP;
        }

        public void setIsP(int isP) {
            this.isP = isP;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public int getStoreStation() {
            return storeStation;
        }

        public void setStoreStation(int storeStation) {
            this.storeStation = storeStation;
        }

        public String getCatalogCategoryName() {
            return catalogCategoryName;
        }

        public void setCatalogCategoryName(String catalogCategoryName) {
            this.catalogCategoryName = catalogCategoryName;
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

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public int getStore() {
            return store;
        }

        public void setStore(int store) {
            this.store = store;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getAlreadyPurchase() {
            return alreadyPurchase;
        }

        public void setAlreadyPurchase(int alreadyPurchase) {
            this.alreadyPurchase = alreadyPurchase;
        }

        public String getVerifyInfo() {
            return verifyInfo;
        }

        public void setVerifyInfo(String verifyInfo) {
            this.verifyInfo = verifyInfo;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
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

        public String getProductCriteria() {
            return productCriteria;
        }

        public void setProductCriteria(String productCriteria) {
            this.productCriteria = productCriteria;
        }

        public int getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(int minPrice) {
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

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getForegift() {
            return foregift;
        }

        public void setForegift(int foregift) {
            this.foregift = foregift;
        }

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
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

            private int specId;
            private int productId;
            private int salesNumber;
            private double avgPrice;
            private int level3Value;
            private String level3Unit;
            private double level2Value;
            private String level1Unit;
            private int levelType;
            private int price;
            private int siteId;
            private String avgUnit;
            private int stock;
            private String level2Unit;

            public int getSpecId() {
                return specId;
            }

            public void setSpecId(int specId) {
                this.specId = specId;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            public int getSalesNumber() {
                return salesNumber;
            }

            public void setSalesNumber(int salesNumber) {
                this.salesNumber = salesNumber;
            }

            public double getAvgPrice() {
                return avgPrice;
            }

            public void setAvgPrice(double avgPrice) {
                this.avgPrice = avgPrice;
            }

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

            public String getLevel1Unit() {
                return level1Unit;
            }

            public void setLevel1Unit(String level1Unit) {
                this.level1Unit = level1Unit;
            }

            public int getLevelType() {
                return levelType;
            }

            public void setLevelType(int levelType) {
                this.levelType = levelType;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSiteId() {
                return siteId;
            }

            public void setSiteId(int siteId) {
                this.siteId = siteId;
            }

            public String getAvgUnit() {
                return avgUnit;
            }

            public void setAvgUnit(String avgUnit) {
                this.avgUnit = avgUnit;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
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
