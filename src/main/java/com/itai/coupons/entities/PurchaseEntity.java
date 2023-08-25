package com.itai.coupons.entities;

import com.itai.coupons.dto.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="purchases")
public class PurchaseEntity {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private CouponEntity coupon;


    @Column(name="amount_of_products", nullable = false)
    private int amountOfProducts;

    @Column(name = "purchase_date",nullable = false)
    private Date dateOfPurchase;


    public PurchaseEntity() {
    }

    public PurchaseEntity(Purchase purchase) {
        this.id = purchase.getId();
        this.dateOfPurchase = purchase.getDateOfPurchase();
        this.amountOfProducts  = purchase.getAmountOfProducts();
        this.coupon = new CouponEntity();
        this.coupon.setId(purchase.getCouponId());
        this.user = new UserEntity();
        this.user.setId(purchase.getUserId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CouponEntity getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
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

}
