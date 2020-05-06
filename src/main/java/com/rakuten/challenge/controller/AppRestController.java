package com.rakuten.challenge.controller;

import com.rakuten.challenge.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class AppRestController {
    @Autowired
    MessageSource messageSource;

    public BusinessException generateExceptionDetails(BusinessException ex, String messageKey, int httpStatus) {
        String message = messageSource.getMessage(messageKey, null, null);
        return ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
    }

    public BusinessException generateExceptionDetailsWithParam(BusinessException ex, String messageKey, int httpStatus, Object[] param) {
        String message = messageSource.getMessage(messageKey, param, null);
        return ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
    }
}
