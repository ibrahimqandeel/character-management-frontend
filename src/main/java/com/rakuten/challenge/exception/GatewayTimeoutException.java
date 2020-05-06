package com.rakuten.challenge.exception;

public class GatewayTimeoutException extends BusinessException {

    public GatewayTimeoutException() {
    }

    public GatewayTimeoutException(String messageKey, String message, int httpStatus) {
        super(messageKey, message, httpStatus);
    }
}
