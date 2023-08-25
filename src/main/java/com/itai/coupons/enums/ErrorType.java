package com.itai.coupons.enums;

public enum ErrorType {
    PRICE_TOO_HIGH("The price you have chosen for this coupon is too high", 101, false, "Coupon Price Too High"),
    PRICE_TOO_LOW("The price you have chosen for this coupon is too low", 102, false, "Coupon Price Too Low"),
    DESCRIPTION_TOO_LONG("The description for this coupon is too long", 103, false, "Coupon Description Too Long"),
    DESCRIPTION_TOO_SHORT("The description for this coupon is too short", 104, false, "Coupon Description Too Short"),
    PASSWORD_TOO_SHORT("Password is too short", 105, false, "Password too short"),
    PASSWORD_TOO_LONG("Password is too long", 106, false, "Password too long"),
    NAME_TOO_SHORT("Name is too short", 107, false, "Name too short"),
    NAME_TOO_LONG("Name is too long", 108, false, "Name too long"),
    GENERAL_ERROR("A general error has occurred", 404, true, "GENERAL ERROR"),
    START_DATE_IS_AFTER_END_DATE("The end date is before start date", 109, false, "START_DATE_IS_AFTER_END_DATE"),
    DATE_INVALID("The date is invalid.", 110, false, "DATE_INVALID"),
    USER_PASSWORD_MISSING_KEY("Password must contain an upper case letter, a lower case letter, and a number", 111, false, "Password keys missing"),
    INVALID_PURCHASE_DATE("Date of purchase cannot be in the future", 112, true, "INVALID_PURCHASE_DATE"),
    COUPON_ID_DOES_NOT_EXIST("Coupon ID could not be found", 113, false, "COUPON_ID_DOES_NOT_EXIST"),
    USER_ID_WAS_NOT_FOUND("User ID could not be found", 114, false, "USER_ID_WAS_NOT_FOUND"),
    COMPANY_ID_DOES_NOT_EXIST("Failed to fetch company", 115, false, "COMPANY_ID_DOES_NOT_EXIST"),
    CATEGORY_ID_DOES_NOT_EXIST("Failed to find category", 116, false, "CATEGORY_ID_DOES_NOT_EXIST"),
    INVALID_ID("ID is invalid", 117, false, "INVALID_ID"),

    FILTER_INVALID("Filter is mistyped", 118, false, "FILTER_INVALID"),
    LOGIN_FAILED("Log in has failed", 119, false, "LOGIN_FAILED"),
    TOKEN_WAS_NOT_CREATED("Token could not be created ", 120,true ,"TOKEN_WAS_NOT_CREATED" ), INVALID_AMOUNT_OF_PRODUCTS("Amount of products is invalid", 121, false, "INVALID_AMOUNT_OF_PRODUCTS");
    private String errorMessage;
    private int errorNumber;
    private boolean isStackTrace;
    private String errorName;

    ErrorType(String errorMessage, int errorNumber, boolean isStackTrace, String errorName) {
        this.errorMessage = errorMessage;
        this.errorNumber = errorNumber;
        this.isStackTrace = isStackTrace;
        this.errorName = errorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public boolean isStackTrace() {
        return isStackTrace;
    }

    public void setStackTrace(boolean stackTrace) {
        isStackTrace = stackTrace;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }
}
