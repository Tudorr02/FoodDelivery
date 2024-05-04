package com.FoodDeliveryApp.Exceptions;

public class PickUpOrderDataConverterException extends DataConverterException {
    public PickUpOrderDataConverterException(String message) {
        super(message);
    }

    public PickUpOrderDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
