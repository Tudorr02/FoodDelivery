package com.FoodDeliveryApp.Models;
import java.util.Arrays;
import java.util.List;

public class DeliveryMan extends Users {
    private String nationality;
    private String vehicle;
    private double rating;


    public String getNationality() {
        return nationality;
    }

    public String getVehicle() {
        return vehicle;
    }

    public double getRating() {
        return rating;
    }

    public DeliveryMan(String lastName, String firstName, String userName, String password, String userID, String phoneNumber, String nationality, String vehicle, double rating) {
        super(lastName, firstName, userName, password, userID, phoneNumber);
        this.nationality = nationality;
        this.vehicle = vehicle;
        this.rating = rating;
    }

}
