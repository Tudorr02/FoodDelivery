package com.FoodDeliveryApp.Exceptions;

public class ShoppingCartDataConverterException extends DataConverterException {
    public ShoppingCartDataConverterException(String message) {
        super(message);
    }

    public ShoppingCartDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
