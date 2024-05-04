package com.FoodDeliveryApp.Exceptions;

public class CsvWriteException extends DataStorageException {
    public CsvWriteException(String message) {
        super(message);
    }

    public CsvWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
