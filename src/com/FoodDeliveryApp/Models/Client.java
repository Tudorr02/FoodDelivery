package com.FoodDeliveryApp.Models;
import java.util.Arrays;
import java.util.List;

public class Client extends Users {

    private String deliveryAddress;
    private String email;


    public Client(String lastName, String firstName, String userName, String password, String userID, String phoneNumber, String deliveryAddress, String email) {
        super(lastName, firstName, userName, password, userID, phoneNumber);
        this.deliveryAddress = deliveryAddress;
        this.email = email;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString()  +
                "deliveryAddress='" + deliveryAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}


