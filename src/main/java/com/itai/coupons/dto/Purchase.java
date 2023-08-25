package com.itai.coupons.dto;

import com.itai.coupons.entities.PurchaseEntity;

import java.util.Date;

public class Purchase {

    private int id;
    private int userId;
    private int couponId;
    private int amountOfProducts;
    private Date dateOfPurchase;
    private int companyId;

    public Purchase() {
    }

    public Purchase(int userId, int couponId, int amountOfProducts, int companyId, Date dateOfPurchase) {
        this.userId = userId;
        this.couponId = couponId;
        this.amountOfProducts = amountOfProducts;
        this.companyId = companyId;
        this.dateOfPurchase = dateOfPurchase;
    }

    public Purchase(int couponId, int amountOfProducts) {
        this.couponId = couponId;
        this.amountOfProducts = amountOfProducts;
    }

    public Purchase(int id, int userId, int couponId, int amountOfProducts, int companyId, Date dateOfPurchase) {
        this(userId, couponId, amountOfProducts, companyId, dateOfPurchase);
        this.id = id;
    }

    public Purchase(PurchaseEntity purchaseEntity) {
        this.dateOfPurchase = purchaseEntity.getDateOfPurchase();
        this.amountOfProducts = purchaseEntity.getAmountOfProducts();
        this.userId = purchaseEntity.getUser().getId();
        this.id = purchaseEntity.getId();
        this.couponId = purchaseEntity.getCoupon().getId();
    }

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

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getAmountOfProducts() {
        return amountOfProducts;
    }

    public void setAmountOfProducts(int amountOfProducts) {
        this.amountOfProducts = amountOfProducts;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", userId=" + userId +
                ", couponId=" + couponId +
                ", amountOfProducts=" + amountOfProducts +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }
}
