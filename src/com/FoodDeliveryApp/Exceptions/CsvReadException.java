package com.FoodDeliveryApp.Exceptions;

public class CsvReadException extends DataStorageException {
    public CsvReadException(String message) {
        super(message);
    }

    public CsvReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
