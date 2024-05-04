package com.FoodDeliveryApp.Exceptions;

public class ReviewDataConverterException extends DataConverterException {
    public ReviewDataConverterException(String message) {
        super(message);
    }

    public ReviewDataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
