package com.FoodDeliveryApp.Exceptions;

public class DeliveryOrderDataConverterException extends DataConverterException {
    public DeliveryOrderDataConverterException(String message) {
        super(message);
    }

    public DeliveryOrderDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
