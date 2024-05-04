package com.FoodDeliveryApp.Exceptions;

public class DeliveryDataConverterException extends DataConverterException {
    public DeliveryDataConverterException(String message) {
        super(message);
    }

    public DeliveryDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
