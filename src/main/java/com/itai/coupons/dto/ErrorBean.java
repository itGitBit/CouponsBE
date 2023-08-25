package com.itai.coupons.dto;

import com.itai.coupons.enums.ErrorType;

public class ErrorBean {
    private int errorNumber;
    private String errorMessage;

    private ErrorType errorType;

    public ErrorBean() {
    }

    public ErrorBean(ErrorType errorType, int errorNumber, String errorMessage) {
        this.errorNumber = errorNumber;
        this.errorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }
}
