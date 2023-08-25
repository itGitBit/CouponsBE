package com.itai.coupons.dto;

import com.itai.coupons.entities.CouponEntity;

import java.util.Date;
import java.util.Objects;


public class Coupon {
    private String couponName;
    //4-15 characters max
    private int id;
    //
    private String couponDescription;
    //10-100 characters max
    private float couponPrice;
    //can't be negative, zero, or over 100
    private int amountOfCouponsLeft;
    private Date startDate;
    private Date endDate;
    //can't be before startDate
    private int companyId;
    private int categoryId;
    private String categoryName;
    private String companyName;
    private String pictureUrl;


    public Coupon() {
    }

    public Coupon(String couponName, String couponDescription, int companyId, String companyName, int categoryId) {
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.companyId = companyId;
        this.companyName = companyName;
        this.categoryId = categoryId;
    }

    public Coupon(String couponName, String couponDescription, float couponPrice, int amountOfCouponsLeft, Date startDate, Date endDate, String categoryName, String companyName, String pictureUrl) {
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.couponPrice = couponPrice;
        this.amountOfCouponsLeft = amountOfCouponsLeft;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryName = categoryName;
        this.companyName = companyName;
        this.pictureUrl = pictureUrl;
    }

    public Coupon(String couponName, String couponDescription, float couponPrice, Date startDate, Date endDate, int companyId, int amountOfCouponsLeft, int categoryId, String pictureUrl){
        this.couponName = couponName;
        this.couponDescription = couponDescription;
        this.couponPrice = couponPrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyId = companyId;
        this.amountOfCouponsLeft = amountOfCouponsLeft;
        this.categoryId = categoryId;
        this.pictureUrl = pictureUrl;

    }

    public Coupon(String couponName, int id, String couponDescription, float couponPrice, Date startDate, Date endDate, int companyId, String companyName, int amountOfCouponsLeft, int categoryId, String categoryName,String pictureUrl){
        this(couponName, couponDescription, couponPrice, startDate, endDate, companyId, amountOfCouponsLeft, categoryId,pictureUrl);
        this.id = id;
        this.companyName = companyName;
        this.categoryName = categoryName;
    }

    public Coupon(CouponEntity couponEntity) {
        this.id = couponEntity.getId();
        this.couponName=couponEntity.getCouponName();
        this.couponDescription = couponEntity.getCouponDescription();
        this.couponPrice = couponEntity.getCouponPrice();
        this.amountOfCouponsLeft = couponEntity.getAmountOfCouponsLeft();
        this.startDate = couponEntity.getStartDate();
        this.endDate = couponEntity.getEndDate();
        this.categoryId = couponEntity.getCategory().getId();
        this.pictureUrl = couponEntity.getPictureUrl();
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription;
    }

    public float getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(float couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getAmountOfCouponsLeft() {
        return amountOfCouponsLeft;
    }

    public void setAmountOfCouponsLeft(int amountOfCouponsLeft) {
        this.amountOfCouponsLeft = amountOfCouponsLeft;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Objects.equals(startDate, coupon.startDate) && Objects.equals(endDate, coupon.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponName='" + couponName + '\'' +
                ", id=" + id +
                ", couponDescription='" + couponDescription + '\'' +
                ", couponPrice=" + couponPrice +
                ", amountOfCouponsLeft=" + amountOfCouponsLeft +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", companyId=" + companyId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
}
}
