package com.FoodDeliveryApp.Exceptions;

public class DataConverterException extends Exception {
    public DataConverterException(String message) {
        super(message);
    }

    public DataConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
