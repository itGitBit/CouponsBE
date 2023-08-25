package com.itai.coupons.exceptions;

import com.itai.coupons.enums.ErrorType;

public class ApplicationException extends Exception {
    private ErrorType errorType;

    // Only for user input validations
    public ApplicationException(ErrorType errorType) {
        this.errorType = errorType;
    }

    //Ctor for programmer
    public ApplicationException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    // Ctor used when wrapping another exception of a 3rd party
    public ApplicationException(ErrorType errorType, String message, Exception innerException) {
        super(message, innerException);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String toString() {
        return "ApplicationException{" +
                "errorType=" + errorType +
                super.toString()+
                '}';
    }
}

