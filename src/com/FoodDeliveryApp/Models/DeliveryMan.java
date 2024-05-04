package com.FoodDeliveryApp.Models;
import java.util.Arrays;
import java.util.List;

public class DeliveryMan extends Users {
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

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

    @Override
    public String toString() {
        return super.toString()  +
                "nationality='" + nationality + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", rating=" + rating +
                '}';
    }
}
