package com.FoodDeliveryApp.Exceptions;

public class FoodItemDataConverterException extends DataConverterException {
    public FoodItemDataConverterException(String message) {
        super(message);
    }

    public FoodItemDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
