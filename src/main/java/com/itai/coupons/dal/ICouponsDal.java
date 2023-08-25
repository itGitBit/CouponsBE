package com.itai.coupons.dal;

import com.itai.coupons.dto.Coupon;
import com.itai.coupons.entities.CouponEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ICouponsDal extends CrudRepository<CouponEntity, Integer> {

@Query("SELECT new com.itai.coupons.dto.Coupon(c.couponName, c.id, c.couponDescription, c.couponPrice, c.startDate, c.endDate, c.company.id, c.company.companyName, c.amountOfCouponsLeft, c.category.id, c.category.categoryName,c.pictureUrl) FROM CouponEntity c ")
    List<Coupon> getAllCoupons();

    @Query("SELECT new com.itai.coupons.dto.Coupon(c.couponName, c.id, c.couponDescription, c.couponPrice, c.startDate, c.endDate, c.company.id, c.company.companyName, c.amountOfCouponsLeft, c.category.id, c.category.categoryName,c.pictureUrl) " +
            "FROM CouponEntity c where c.id=:id")
    Coupon getCoupon(int id);

    @Query("SELECT new com.itai.coupons.dto.Coupon(c.couponName, c.id, c.couponDescription, c.couponPrice, c.startDate, c.endDate, c.company.id, c.company.companyName, c.amountOfCouponsLeft, c.category.id, c.category.categoryName,c.pictureUrl) FROM CouponEntity c where c.category.id=:categoryId ")
    List<Coupon> getAllCouponsByCategoryId(int categoryId);

    @Query("SELECT new com.itai.coupons.dto.Coupon(c.couponName, c.id, c.couponDescription, c.couponPrice, c.startDate, c.endDate, c.company.id, c.company.companyName, c.amountOfCouponsLeft, c.category.id, c.category.categoryName,c.pictureUrl) FROM CouponEntity c where c.couponPrice<:maxPrice ")
    List<Coupon> getAllCouponsByMaxPrice(float maxPrice);


    @Query("SELECT new com.itai.coupons.dto.Coupon(c.couponName, c.id, c.couponDescription, c.couponPrice, c.startDate, c.endDate, c.company.id, c.company.companyName, c.amountOfCouponsLeft, c.category.id, c.category.categoryName,c.pictureUrl) FROM CouponEntity c where c.endDate between :startRange and :endRange ")
    List<Coupon> getAllCouponsByDateRange(Date startRange, Date endRange);
}
