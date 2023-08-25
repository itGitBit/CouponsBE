package com.itai.coupons.exceptions;

import com.itai.coupons.dto.ErrorBean;
import com.itai.coupons.enums.ErrorType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public ErrorBean toResponse(Throwable throwable, HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(500);
        if (throwable instanceof ApplicationException) {
            ApplicationException appException = (ApplicationException) throwable;
            if (appException.getErrorType().isStackTrace()) {
                appException.printStackTrace();
            }
            ErrorType errorType = appException.getErrorType();
            int errorNumber = errorType.getErrorNumber();
            String errorMessage = errorType.getErrorMessage();
            return new ErrorBean(errorType, errorNumber, errorMessage);
        }

        throwable.printStackTrace();
        String errorMessage = throwable.getMessage();
        return new ErrorBean(ErrorType.GENERAL_ERROR,601, "General error");
    }

}
