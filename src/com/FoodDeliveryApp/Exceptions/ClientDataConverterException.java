package com.FoodDeliveryApp.Exceptions;

public class ClientDataConverterException extends DataConverterException {
    public ClientDataConverterException(String message) {
        super(message);
    }

    public ClientDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
