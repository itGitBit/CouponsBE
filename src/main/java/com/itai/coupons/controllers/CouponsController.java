package com.itai.coupons.controllers;

import com.itai.coupons.dto.Coupon;
import com.itai.coupons.entities.CouponEntity;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.logic.CouponsLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {

    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }

    @PostMapping
    public void addCoupon(@RequestBody Coupon coupon) throws ApplicationException {
        coupon.setCompanyId(couponsLogic.getCompanyIdByCompanyName(coupon.getCompanyName()));
        coupon.setCategoryId(couponsLogic.getCategoryIdByCategoryName(coupon.getCategoryName()));
        couponsLogic.addCoupon(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException {
        couponsLogic.updateCoupon(coupon);
    }

    @GetMapping
    public List<Coupon> getAllCoupons() throws ApplicationException {
        return couponsLogic.getAllCoupons();
    }

    @GetMapping("/{couponId}")
    public Coupon getCoupon(@PathVariable("couponId") int couponId) throws ApplicationException {
        return couponsLogic.getCoupon(couponId);
    }

    @DeleteMapping("/{couponId}")
    public void removeCoupon(@PathVariable("couponId") int couponId) throws ApplicationException {
        couponsLogic.removeCoupon(couponId);
    }

    @GetMapping("/byCategoryID")
    public List<Coupon> getAllCouponsByCategoryId(@RequestParam("CategoryID") int categoryId) throws ApplicationException {
        return couponsLogic.getAllCouponsByCategoryId(categoryId);
    }
    @GetMapping("/byMaxPrice")
    public List<Coupon> getAllCouponsByMaxPrice(@RequestParam("MaxPrice") float maxPrice) throws ApplicationException {
        return couponsLogic.getAllCouponsByMaxPrice(maxPrice);
    }
    @GetMapping("/byDateRange")
    public List<Coupon> getAllCouponsByEndDateRange(@RequestParam("startRange") Date startRange, @RequestParam("endRange") Date endRange) throws ApplicationException {
        return couponsLogic.getAllCouponsByDateRange(startRange,endRange);
    }
}

