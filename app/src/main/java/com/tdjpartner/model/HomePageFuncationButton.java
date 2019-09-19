package com.tdjpartner.model;

/**
 * Created by yangkuo on 2018-05-18.
 */

public class HomePageFuncationButton {
        /**
         * categoryId : 10
         * imageUrl :
         * name : 新鲜蔬菜
         */

        private int categoryId;
        private String imageUrl;
        private String name;
        private int type;
        private String url;//信息发布的跳转url

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
