package com.itai.coupons.logic;

import com.itai.coupons.dal.IPurchasesDal;
import com.itai.coupons.dto.Coupon;
import com.itai.coupons.dto.ExtendedPurchase;
import com.itai.coupons.dto.Purchase;
import com.itai.coupons.entities.PurchaseEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchasesLogic {
    private IPurchasesDal purchasesDal;

    private UsersLogic usersLogic;

    private CouponsLogic couponsLogic;

    @Autowired
    public PurchasesLogic(IPurchasesDal purchasesDal, UsersLogic usersLogic, CouponsLogic couponsLogic) {
        this.purchasesDal = purchasesDal;
        this.usersLogic = usersLogic;
        this.couponsLogic = couponsLogic;
    }

    @Transactional(rollbackOn = Exception.class)
    public void addPurchase(Purchase purchase) throws ApplicationException {
        validatePurchase(purchase);
        PurchaseEntity purchaseEntity = new PurchaseEntity(purchase);
        this.purchasesDal.save(purchaseEntity);
        Coupon coupon = this.couponsLogic.getCoupon(purchase.getCouponId());
        int newAmountOfCouponsLeft = coupon.getAmountOfCouponsLeft() - purchaseEntity.getAmountOfProducts();
        coupon.setAmountOfCouponsLeft(newAmountOfCouponsLeft);
        couponsLogic.updateCoupon(coupon);
        StatisticsUtils.sendStatistics("Purchase added, purchase: " + purchase.getId());
    }

    public void removePurchase(int purchaseId) throws ApplicationException {
        this.purchasesDal.deleteById(purchaseId);
        StatisticsUtils.sendStatistics("Purchase removed, purchase: " + purchaseId);
    }

    public ExtendedPurchase getPurchase(int purchaseId) throws ApplicationException {
       return this.purchasesDal.getPurchaseById(purchaseId);
    }

    public List<ExtendedPurchase> getAllPurchases() throws ApplicationException {
        return this.purchasesDal.getAllPurchases();
    }


    @Transactional (rollbackOn = ApplicationException.class)
    public void updatePurchase(Purchase purchase) throws ApplicationException {
        validatePurchase(purchase);
        Coupon coupon = this.couponsLogic.getCoupon(purchase.getCouponId());
        Purchase oldPurchase = this.purchasesDal.getPurchaseById(purchase.getId());
        int newAmountOfCouponsLeft = coupon.getAmountOfCouponsLeft()+oldPurchase.getAmountOfProducts()-purchase.getAmountOfProducts();
        coupon.setAmountOfCouponsLeft(newAmountOfCouponsLeft);
        this.couponsLogic.updateCoupon(coupon);
        PurchaseEntity purchaseEntity = new PurchaseEntity(purchase);
        this.purchasesDal.save(purchaseEntity);
        StatisticsUtils.sendStatistics("Purchase updated, purchase: " + purchase.getId());
    }

    private void validatePurchase(Purchase purchase) throws ApplicationException {
        validateAmountOfProducts(purchase.getAmountOfProducts());
        validatePurchaseDate(purchase.getDateOfPurchase());
        validateCouponIdInPurchase(purchase.getCouponId());
        validateUserIdInPurchase(purchase.getUserId());
    }

    private void validateAmountOfProducts(int amountOfProducts) throws ApplicationException{
        if (amountOfProducts<0){
            throw new ApplicationException(ErrorType.INVALID_AMOUNT_OF_PRODUCTS,"There was a problem with the amount of products you tried to purchase. "+amountOfProducts);
        }
    }

    private void validateUserIdInPurchase(int id) throws ApplicationException {
        if (!isUserIdExist(id)) {
            throw new ApplicationException(ErrorType.USER_ID_WAS_NOT_FOUND, "Couldn't locate user ID: " + id);
        }
    }

    private boolean isUserIdExist(int userId) throws ApplicationException {
        return usersLogic.isUserIdExist(userId);
    }

    private void validateCouponIdInPurchase(int couponId) throws ApplicationException {
        if (!isCouponIdExist(couponId)) {
            throw new ApplicationException(ErrorType.COUPON_ID_DOES_NOT_EXIST, "Couldn't locate Coupon ID: " + couponId);
        }
    }

    private boolean isCouponIdExist(int couponId) throws ApplicationException {
        return couponsLogic.isCouponExist(couponId);
    }

    private void validatePurchaseDate(Date purchaseDate) throws ApplicationException {
        if (purchaseDate.after(Date.from(Instant.now()))) {
            throw new ApplicationException(ErrorType.INVALID_PURCHASE_DATE);
        }
    }

    public List<ExtendedPurchase> getAllPurchasesByCompanyId(int companyId) throws ApplicationException {
        return purchasesDal.getAllPurchasesByCompanyId(companyId);
    }

    public List<ExtendedPurchase> getAllPurchasesByUserId(int userId) throws ApplicationException {
        return purchasesDal.getAllPurchasesByUserId(userId);
    }

    public List<ExtendedPurchase> getAllPurchasesByCategoryId(int categoryId) throws ApplicationException {
        return purchasesDal.getAllPurchasesByCategoryId(categoryId);
    }

    public List<ExtendedPurchase> getAllPurchasesByEndDateRange(Date startDate, Date endDate) throws ApplicationException {
        return purchasesDal.getAllPurchasesByEndDateRange(startDate, endDate);
    }

    private void validateId(int id) throws ApplicationException {
        if (id <= 0) {
            throw new ApplicationException(ErrorType.INVALID_ID);
        }
    }

    public int getCompanyIdByCouponId(int couponId) throws ApplicationException {
        return this.couponsLogic.getCoupon(couponId).getCompanyId();
           }
//    public List<ExtendedPurchase> getAllPurchasesByFilter(int id, String filterType) throws ApplicationException {
//        validateId(id);
//        validateFilterType(filterType);
//        return purchasesDal.getAllPurchasesByFilter(id, filterType);
//    }
//
//    private void validateFilterType(String filterType) throws ApplicationException {
//        if (filterType.equals("Company")) {
//            return;
//        }
//        if (filterType.equals("Category")) {
//            return;
//        }
//        if (filterType.equals("User")) {
//            return;
//        } else {
//            throw new ApplicationException(ErrorType.FILTER_INVALID);
//        }
//    }


}
