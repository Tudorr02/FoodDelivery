package com.FoodDeliveryApp.Exceptions;

public class RestaurantDataConverterException extends DataConverterException {
    public RestaurantDataConverterException(String message) {
        super(message);
    }

    public RestaurantDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
