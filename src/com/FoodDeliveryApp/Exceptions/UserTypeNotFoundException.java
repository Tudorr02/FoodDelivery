package com.FoodDeliveryApp.Exceptions;

public class UserTypeNotFoundException extends Exception{
    public UserTypeNotFoundException(String message) {
        super(message);
    }

    public UserTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
