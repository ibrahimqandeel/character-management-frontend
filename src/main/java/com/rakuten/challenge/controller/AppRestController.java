package com.rakuten.challenge.controller;

import com.rakuten.challenge.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class AppRestController {
    @Autowired
    MessageSource messageSource;

    public BusinessException generateExceptionDetails(BusinessException ex, String messageKey, int httpStatus) {
        String message = messageSource.getMessage(messageKey, null, Locale.ENGLISH);
        return ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
    }

    public BusinessException generateExceptionDetailsWithParam(BusinessException ex, String messageKey, int httpStatus, Object[] param) {
        String message = messageSource.getMessage(messageKey, param, Locale.ENGLISH);
        return ex.setMessageKey(messageKey)
                .setMessage(message)
                .setHttpStatus(httpStatus);
    }
}
