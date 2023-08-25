//package com.itai.coupons.unittests;
//
//import com.itai.coupons.dto.*;
//import com.itai.coupons.enums.UserType;
//import com.itai.coupons.exceptions.ApplicationException;
//import com.itai.coupons.logic.*;
//
//import java.sql.Date;
//import java.util.List;
//
//public class UnitTests {
//
//
//    public static void testAddCouponSuccess(String couponName, String couponDescription, float couponPrice, Date startDate, Date endDate, int companyId, int amountOfCouponsLeft, int categoryId) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            Coupon coupon = new Coupon(couponName, couponDescription, couponPrice, startDate, endDate, companyId, amountOfCouponsLeft, categoryId);
//            couponsLogic.addCoupon(coupon);
//            System.out.println("testAddCouponSuccess - success ");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testAddCompanySuccess(String companyName) {
//        CompaniesLogic companiesLogic = new CompaniesLogic();
//        try {
//            Company company = new Company(companyName);
//            companiesLogic.createCompany(company);
//            System.out.println("testAddCompanySuccess-success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testAddPurchaseSuccess(int userId, int couponId, int amountOfProducts, Date dateOfPurchase) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            Purchase purchase = new Purchase(userId, couponId, amountOfProducts, dateOfPurchase);
//            purchaseLogic.addPurchase(purchase);
//            System.out.println("testAddPurchaseSuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void testAddUserSuccess(String userName, String userPassword, UserType userType){
//        testAddUserSuccess(userName,userPassword,userType,null);
//    }
//    public static void testAddUserSuccess(String userName, String userPassword, UserType userType, Integer companyId) {
//        UsersLogic usersLogic = new UsersLogic();
//        try {
//            User user = new User(userName, userPassword, userType, companyId);
//            usersLogic.addUser(user);
//            System.out.println("testAddUserSuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    public static void testAddCategorySuccess(String categoryName) {
//        CategoriesLogic categoriesLogic = new CategoriesLogic();
//        try {
//            Category category = new Category(categoryName);
//            categoriesLogic.addCategory(category);
//            System.out.println("testAddCategorySuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void testGetCouponSuccess(int couponId) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            Coupon coupon = couponsLogic.getCoupon(couponId);
//            System.out.println("testGetCouponSuccess - success: " + coupon.toString());
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetCompanySuccess(int companyId) {
//        CompaniesLogic companiesLogic = new CompaniesLogic();
//        try {
//            Company company = companiesLogic.getCompany(companyId);
//            System.out.println("testGetCompanySuccess - success: " + company.toString());
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetUserSuccess(int userId) {
//        UsersLogic usersLogic = new UsersLogic();
//        try {
//            User user = usersLogic.getUser(userId);
//            System.out.println(user.toString());
//            System.out.println("testGetUserSuccess - success: " + user.toString());
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetPurchase(int purchaseId) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            ExtendedPurchase extendedPurchase = purchaseLogic.getPurchase(purchaseId);
//            System.out.println("testGetPurchaseSuccess - success: " +extendedPurchase.toString());
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetCategory(int categoryId) {
//        CategoriesLogic categoriesLogic = new CategoriesLogic();
//        try {
//           Category category = categoriesLogic.getCategory(categoryId);
//            System.out.println("testGetCategorySuccess - success: " +category.toString());
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testRemoveCouponSuccess(int couponId) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            couponsLogic.removeCoupon(couponId);
//            System.out.println("testRemoveCouponSuccess-success"+couponId);
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testRemoveUserSuccess(int userId) {
//        UsersLogic usersLogic = new UsersLogic();
//        try {
//            usersLogic.removeUser(userId);
//            System.out.println("testRemoveUserSuccess-success"+userId);
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testRemoveCompanySuccess(int companyId) {
//        CompaniesLogic companiesLogic = new CompaniesLogic();
//        try {
//            companiesLogic.removeCompany(companyId);
//            System.out.println("testRemoveCompanySuccess- success"+companyId);
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testRemovePurchaseSuccess(int purchaseId) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            purchaseLogic.removePurchase(purchaseId);
//            System.out.println("testRemovePurchaseSuccess- success"+purchaseId);
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetAllCouponsSuccess() {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            couponsLogic.getAllCoupons();
//            System.out.println("testGetAllCouponsSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void testGetAllCouponsByMaxPriceSuccess(float maxPrice) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            List<Coupon> coupons = couponsLogic.getAllCouponsByMaxPrice(maxPrice);
//            System.out.println("testGetAllCouponsByMaxPriceSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void testGetAllCouponsByCategoryId(int categoryId) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            List<Coupon> coupons = couponsLogic.getAllCouponsByCategoryId(categoryId);
//            System.out.println("testGetAllCouponsByMaxPriceSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void testGetAllCompaniesSuccess() {
//        CompaniesLogic companiesLogic = new CompaniesLogic();
//        try {
//            companiesLogic.getAllCompanies();
//            System.out.println("testGetAllCompanies Success success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void testGetAllUsersSuccess() {
//        UsersLogic usersLogic = new UsersLogic();
//        try {
//            usersLogic.getAllUsers();
//            System.out.println("testGetAllUsersSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetAllPurchasesSuccess() {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            purchaseLogic.getAllPurchases();
//            System.out.println("testGetAllPurchasesSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void testUpdateCouponSuccess(int couponId, String couponName, String couponDescription, float couponPrice, Date startDate, Date endDat, int companyId, int amountOfCouponsLeft, int categoryId) {
//        CouponsLogic couponsLogic = new CouponsLogic();
//        try {
//            Coupon coupon = new Coupon(couponName, couponId, couponDescription, couponPrice, startDate, endDat, companyId, amountOfCouponsLeft, categoryId);
//            couponsLogic.updateCoupon(coupon);
//            System.out.println("testUpdateCouponSuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void testUpdateUserSuccess(String userName, String userPassword, UserType userType, int userId) {
//        testUpdateUserSuccess(userName,userPassword,userType,userId,null);
//    }
//    public static void testUpdateUserSuccess(String userName, String userPassword, UserType userType, int userId, Integer companyId) {
//        UsersLogic usersLogic = new UsersLogic();
//        User user;
//        if (userType == UserType.COMPANY) {
//            user = new User(userName, userPassword, userType, userId, companyId);
//        } else {
//            user = new User(userName, userPassword, userType, userId);
//        }
//        try {
//            usersLogic.updateUser(user);
//            System.out.println("testUpdateUserSuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testUpdateCompanySuccess(String companyName, int companyId) {
//        CompaniesLogic companiesLogic = new CompaniesLogic();
//        try {
//            Company company = new Company(companyName, companyId);
//            companiesLogic.updateCompany(company);
//            System.out.println("testUpdateCompanySuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testUpdatePurchaseSuccess(int id, int userId, int couponId, int amountOfProducts, Date dateOfPurchase) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            Purchase purchase = new Purchase(id, userId, couponId, amountOfProducts, dateOfPurchase);
//            purchaseLogic.updatePurchase(purchase);
//            System.out.println("testUpdatePurchaseSuccess - success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void testGetAllPurchasesByCompanyIdSuccess(int companyId) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            purchaseLogic.getAllPurchasesByFilter(companyId,"Company");
//            System.out.println("testGetAllPurchasesByCompanyIdSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void testGetAllPurchasesByUserIdSuccess(int userId) {
//        PurchasesLogic purchaseLogic = new PurchasesLogic();
//        try {
//            purchaseLogic.getAllPurchasesByFilter(userId,"User");
//            System.out.println("testGetAllPurchasesByUserIdSuccess success");
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//}
