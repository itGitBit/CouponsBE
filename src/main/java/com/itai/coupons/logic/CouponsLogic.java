package com.itai.coupons.logic;

import com.itai.coupons.dal.ICouponsDal;
import com.itai.coupons.dto.Coupon;
import com.itai.coupons.entities.CouponEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class CouponsLogic {

    private ICouponsDal couponsDal;
    private CompaniesLogic companiesLogic;
    private UsersLogic usersLogic;
    private CategoriesLogic categoriesLogic;

    @Autowired
    public CouponsLogic(ICouponsDal couponsDal, CompaniesLogic companiesLogic, UsersLogic usersLogic, CategoriesLogic categoriesLogic) {
        this.couponsDal = couponsDal;
        this.companiesLogic = companiesLogic;
        this.usersLogic = usersLogic;
        this.categoriesLogic = categoriesLogic;
    }

    //CRUD
    public void addCoupon(Coupon coupon) throws ApplicationException {
        validateCoupon(coupon);
        CouponEntity couponEntity = new CouponEntity(coupon);
        this.couponsDal.save(couponEntity);
        StatisticsUtils.sendStatistics("Coupon added, coupon: " + coupon.getId());
    }

    public Coupon getCoupon(int couponId) throws ApplicationException {
        return this.couponsDal.getCoupon(couponId);
    }


    public void updateCoupon(Coupon coupon) throws ApplicationException {
        validateCoupon(coupon);
        CouponEntity couponEntity = new CouponEntity(coupon);
        couponsDal.save(couponEntity);
    }

    public void removeCoupon(int couponId) throws ApplicationException {
        couponsDal.deleteById(couponId);
    }

    public List<Coupon> getAllCoupons() throws ApplicationException {
        return couponsDal.getAllCoupons();
    }

    private void validateCoupon(Coupon coupon) throws ApplicationException {
        validateCouponName(coupon.getCouponName());
        validateCouponDescription(coupon.getCouponDescription());
        validateCouponPrice(coupon.getCouponPrice());
        validateCouponDates(coupon);
        validateCouponCompanyId(coupon.getCompanyId());
        validateCouponCategoryId(coupon.getCategoryId());
    }

    private void validateCouponName(String couponName) throws ApplicationException {
        if (couponName.length() < 4) {
            throw new ApplicationException(ErrorType.NAME_TOO_SHORT);
        }
        if (couponName.length() > 25) {
            throw new ApplicationException(ErrorType.NAME_TOO_LONG);
        }
    }

    private void validateCouponDescription(String couponDescription) throws ApplicationException {
        if (couponDescription.length() > 200) {
            throw new ApplicationException(ErrorType.DESCRIPTION_TOO_LONG);
        }
        if (couponDescription.length() < 2) {
            throw new ApplicationException(ErrorType.DESCRIPTION_TOO_SHORT);
        }
    }

    private void validateCouponPrice(float couponPrice) throws ApplicationException {
        if (couponPrice <= 0) {
            throw new ApplicationException(ErrorType.PRICE_TOO_LOW);
        }
        if (couponPrice > 10000) {
            throw new ApplicationException(ErrorType.PRICE_TOO_HIGH);
        }
    }

    private void validateCouponDates(Coupon coupon) throws ApplicationException {
        if (coupon.getEndDate().before(coupon.getStartDate())) {
            throw new ApplicationException(ErrorType.START_DATE_IS_AFTER_END_DATE);

        }
        if (coupon.getStartDate().equals(coupon.getEndDate())) {
            throw new ApplicationException(ErrorType.DATE_INVALID);
        }

    }

    private void validateCouponCompanyId(int companyId) throws ApplicationException {
        if (!this.companiesLogic.isCompanyIdExist(companyId)) {
            throw new ApplicationException(ErrorType.COMPANY_ID_DOES_NOT_EXIST);
        }
    }

    private void validateCouponCategoryId(int categoryId) throws ApplicationException {
        if (!this.categoriesLogic.isCategoryExist(categoryId)) {
            throw new ApplicationException(ErrorType.CATEGORY_ID_DOES_NOT_EXIST);
        }
    }

    boolean isCouponExist(int couponId) throws ApplicationException {
        return this.couponsDal.existsById(couponId);
    }

    public List<Coupon> getAllCouponsByMaxPrice(float maxPrice) throws ApplicationException {
        validateCouponPrice(maxPrice);
        return couponsDal.getAllCouponsByMaxPrice(maxPrice);
    }

    public List<Coupon> getAllCouponsByCategoryId(int categoryId) throws ApplicationException {
        return couponsDal.getAllCouponsByCategoryId(categoryId);
    }

    public List<Coupon> getAllCouponsByDateRange(Date startRange, Date endRange) {
        return couponsDal.getAllCouponsByDateRange(startRange, endRange);
    }

    public int getCompanyIdByCompanyName(String companyName) {
        return this.companiesLogic.getCompanyIdByCompanyName(companyName);
    }

    public int getCategoryIdByCategoryName(String categoryName) {
        return this.categoriesLogic.getCategoryIdByCategoryName(categoryName);
    }
}
