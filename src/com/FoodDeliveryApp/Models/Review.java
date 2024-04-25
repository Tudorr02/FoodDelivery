package com.FoodDeliveryApp.Models;

import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.xml.crypto.Data;

public class Review {
    private String reviewID;
    private Users user;
    private double givenRating;
    private String message;

    public Review(String reviewID, Users user, double givenRating, String message) {
        this.reviewID = reviewID;
        this.user = user;
        this.givenRating = givenRating;
        this.message = message;
    }

    public String getReviewID() {
        return reviewID;
    }

    public Users getUser() {
        return user;
    }

    public double getGivenRating() {
        return givenRating;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID='" + reviewID + '\'' +
                ", userId='" + user.getUserID() + '\'' + " last name : "+ user.getLastName()+
                ", givenRating=" + givenRating +
                ", message='" + message + '\'' +
                '}';
    }
}
