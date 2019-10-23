package com.tdjpartner.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartGoodsBean  implements Serializable {

    private int entityId;

    private String productImage;
    private String productUnit;
    private String nickName = "";
    private String productName = "";
    private BigDecimal productPrice = BigDecimal.ZERO;
    private int status;//1表示上架 2,下架,3,审核中
    private String level2Unit;
    private BigDecimal level3Value = BigDecimal.ZERO;
    private int levelType;
    private BigDecimal level2Value = BigDecimal.ZERO;
    private String level3Unit;
    private String storeName;
    private int type;//自定义区分押金列表分类
    private int productQty;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public String getLevel2Unit() {
        return level2Unit;
    }

    public void setLevel2Unit(String level2Unit) {
        this.level2Unit = level2Unit;
    }

    public BigDecimal getLevel3Value() {
        return level3Value;
    }

    public void setLevel3Value(BigDecimal level3Value) {
        this.level3Value = level3Value;
    }

    public int getLevelType() {
        return levelType;
    }

    public void setLevelType(int levelType) {
        this.levelType = levelType;
    }

    public BigDecimal getLevel2Value() {
        return level2Value;
    }

    public void setLevel2Value(BigDecimal level2Value) {
        this.level2Value = level2Value;
    }

    public String getLevel3Unit() {
        return level3Unit;
    }

    public void setLevel3Unit(String level3Unit) {
        this.level3Unit = level3Unit;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }


    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public int getProductQty() {
        return productQty;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }


}
