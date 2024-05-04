package com.FoodDeliveryApp.Exceptions;

public class DeliveryManDataConverterException extends DataConverterException {
    public DeliveryManDataConverterException(String message) {
        super(message);
    }

    public DeliveryManDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
