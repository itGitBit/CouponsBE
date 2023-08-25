package com.itai.coupons.entities;

import com.itai.coupons.dto.Coupon;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
public class CouponEntity {

    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @Id
    @GeneratedValue
    private int id;


    @Column(name = "coupon_description")
    private String couponDescription;

    @Column(name = "coupon_price", nullable = false)
    private float couponPrice;

    @Column(name="amount_of_coupons_left",nullable = false)
    private int amountOfCouponsLeft;

    @Column(name = "start_date",nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "picture_url", nullable = false)
    private String pictureUrl;


    @ManyToOne
    private CategoryEntity category;

    @ManyToOne
    private CompanyEntity company;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
    private List<PurchaseEntity> purchasesList;



    public CouponEntity() {
    }

    public CouponEntity(Coupon coupon) {
        this.id = coupon.getId();
        this.couponName=coupon.getCouponName();
        this.couponDescription = coupon.getCouponDescription();
        this.couponPrice = coupon.getCouponPrice();
        this.amountOfCouponsLeft = coupon.getAmountOfCouponsLeft();
        this.company = new CompanyEntity();
        this.company.setId(coupon.getCompanyId());
        this.startDate = coupon.getStartDate();
        this.endDate = coupon.getEndDate();
        this.category = new CategoryEntity();
        this.category.setId(coupon.getCategoryId());
this.pictureUrl = coupon.getPictureUrl();
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
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

    public int getAmountOfCouponsLeft() {
        return amountOfCouponsLeft;
    }

    public void setAmountOfCouponsLeft(int amountOfCouponsLeft) {
        this.amountOfCouponsLeft = amountOfCouponsLeft;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<PurchaseEntity> getPurchasesList() {
        return purchasesList;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setPurchasesList(List<PurchaseEntity> purchasesList) {
        this.purchasesList = purchasesList;
    }
    //    //
//    private String couponDescription;
//    //10-100 characters max
//    private float couponPrice;
//    //can't be negative, zero, or over 100
//    private int amountOfCouponsLeft;
//    private Date startDate;
//    private Date endDate;
//    //can't be before startDate
//    private int companyId;
//    private int categoryId;
//
}
