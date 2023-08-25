package com.itai.coupons.dto;

import com.itai.coupons.entities.PurchaseEntity;

import java.util.Date;

public class ExtendedPurchase extends Purchase {
    private String couponName;
    private String couponDescription;
    private float couponPrice;
    private Date startDate;
    private Date endDate;
    private String companyName;

    private String userName;
    private int categoryId;

    public ExtendedPurchase() {
    }

    public ExtendedPurchase(PurchaseEntity purchaseEntity) {
        super(purchaseEntity.getId(), purchaseEntity.getUser().getId(), purchaseEntity.getCoupon().getId(), purchaseEntity.getAmountOfProducts(), purchaseEntity.getDateOfPurchase());
        this.companyName = purchaseEntity.getCoupon().getCompany().getCompanyName();
        this.couponDescription = purchaseEntity.getCoupon().getCouponDescription();
        this.couponPrice = purchaseEntity.getCoupon().getCouponPrice();
        this.startDate = purchaseEntity.getCoupon().getStartDate();
        this.endDate = purchaseEntity.getCoupon().getEndDate();

    }

    public ExtendedPurchase(int id, int userId, int couponId, int amountOfProducts, Date dateOfPurchase, String couponName, String couponDescription, float couponPrice, Date startDate, Date endDate, int companyId, String companyName, String userName, int categoryId) {
        super(id, userId, couponId, amountOfProducts, companyId, dateOfPurchase);
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.couponPrice = couponPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyName = companyName;
        this.userName = userName;
        this.categoryId = categoryId;
    }


    public String getCouponName() {
        return couponName;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public float getCouponPrice() {
        return couponPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUserName() {
        return userName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public void setCouponPrice(float couponPrice) {
        this.couponPrice = couponPrice;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ExtendedPurchase{" +
                "couponName='" + couponName + '\'' +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponPrice=" + couponPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", companyName='" + companyName + '\'' +
                ", userName='" + userName + '\'' +
                ", categoryId=" + categoryId + super.toString() +
                '}';
    }
}
