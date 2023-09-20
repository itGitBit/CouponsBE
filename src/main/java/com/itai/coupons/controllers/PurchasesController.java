package com.itai.coupons.controllers;

import com.itai.coupons.dto.ExtendedPurchase;
import com.itai.coupons.dto.Purchase;
import com.itai.coupons.dto.SuccessfulLoginDetails;
import com.itai.coupons.entities.PurchaseEntity;
import com.itai.coupons.enums.ErrorType;
import com.itai.coupons.exceptions.ApplicationException;
import com.itai.coupons.logic.PurchasesLogic;
import com.itai.coupons.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController {
    private PurchasesLogic purchaseLogic;

    @Autowired
    public PurchasesController(PurchasesLogic purchaseLogic) {
        this.purchaseLogic = purchaseLogic;
    }

    @PostMapping
    public void addPurchase(@RequestHeader("Authorization")String token, @RequestBody Purchase purchase) throws ApplicationException {
        try {

            SuccessfulLoginDetails successfulLoginDetails = JWTUtils.decodeJWT(token);
            purchase.setUserId(successfulLoginDetails.getId());
            purchase.setDateOfPurchase(Date.from(Instant.now()));
            purchase.setCompanyId(purchaseLogic.getCompanyIdByCouponId(purchase.getCouponId()));
        } catch (Exception e) {
            throw new ApplicationException(ErrorType.GENERAL_ERROR,"Couldn't confirm purchase", e);
        }
        purchaseLogic.addPurchase(purchase);
    }

    @PutMapping
    public void updatePurchase( @RequestBody Purchase purchase) throws ApplicationException {
        purchaseLogic.updatePurchase(purchase);
    }

    @GetMapping
    public List<ExtendedPurchase> getAllPurchases() throws ApplicationException {
       return purchaseLogic.getAllPurchases();
    }

    @GetMapping("/{purchaseId}")
    public ExtendedPurchase getPurchase(@PathVariable("purchaseId") int purchaseId) throws ApplicationException {
       return purchaseLogic.getPurchase(purchaseId);
    }

    @DeleteMapping("/{purchaseId}")
    public void removePurchase(@PathVariable("purchaseId") int purchaseId) throws ApplicationException {
        purchaseLogic.removePurchase(purchaseId);
    }

    @GetMapping("/byCompanyId")
    public List<ExtendedPurchase> getAllPurchasesByCompanyId(@RequestParam("companyId") int companyId) throws ApplicationException {
        return purchaseLogic.getAllPurchasesByCompanyId(companyId);
    }

    @GetMapping("/byCategoryId")
    public List<ExtendedPurchase> getAllPurchasesByCategoryId(@RequestParam("categoryId") int categoryId) throws ApplicationException {
        return purchaseLogic.getAllPurchasesByCategoryId(categoryId);
    }

    @GetMapping("/byUserId")
    public List<ExtendedPurchase> getAllPurchasesByUserId(@RequestParam("userId") String userId) throws ApplicationException {
        int userIdInt = Integer.parseInt(userId);
        List<ExtendedPurchase> purchaseList = purchaseLogic.getAllPurchasesByUserId(userIdInt);
        return purchaseList;
    }

//
//    //http://localhost:8080/purchases/byDateRange?startDate=2022-05-04&endDate=2023-05-04
    @GetMapping("/byDateRange")
    public List<ExtendedPurchase> getAllPurchasesByEndDateRange(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws ApplicationException {
        return purchaseLogic.getAllPurchasesByEndDateRange(startDate, endDate);
    }
}
