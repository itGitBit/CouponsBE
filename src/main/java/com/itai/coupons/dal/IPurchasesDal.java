package com.itai.coupons.dal;

import com.itai.coupons.dto.Coupon;
import com.itai.coupons.dto.ExtendedPurchase;
import com.itai.coupons.entities.PurchaseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IPurchasesDal extends CrudRepository<PurchaseEntity,Integer> {
//TODO DELETE USERID from extended purchase becasue userId is a security breach
    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p")
    List<ExtendedPurchase> getAllPurchases();


    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p JOIN p.user u join p.coupon co Join co.category cat where p.coupon.company.id=:companyId")
    List<ExtendedPurchase> getAllPurchasesByCompanyId(int companyId);

    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p where p.coupon.category.id=:categoryId")
    List<ExtendedPurchase> getAllPurchasesByCategoryId(int categoryId);

    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p  where p.dateOfPurchase between :startDate and :endDate")
    List<ExtendedPurchase> getAllPurchasesByEndDateRange(Date startDate, Date endDate);

    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p where p.user.id=:userId")
    List<ExtendedPurchase> getAllPurchasesByUserId(int userId);

    @Query("SELECT new com.itai.coupons.dto.ExtendedPurchase(p.id,p.user.id, p.coupon.id, p.amountOfProducts, p.dateOfPurchase, p.coupon.couponName, p.coupon.couponDescription, p.coupon.couponPrice, p.coupon.startDate, p.coupon.endDate, p.coupon.company.id, p.coupon.company.companyName, p.user.userName,  p.coupon.category.id) " +
            "FROM PurchaseEntity p where p.id=:purchaseId")
    ExtendedPurchase getPurchaseById(int purchaseId);
}
