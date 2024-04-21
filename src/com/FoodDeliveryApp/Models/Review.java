package com.FoodDeliveryApp.Models;

public class Review {
    private String reviewID;
    private String userId;
    private double givenRating;
    private String message;

    public Review(String reviewID, String userId, double givenRating, String message) {
        this.reviewID = reviewID;
        this.userId = userId;
        this.givenRating = givenRating;
        this.message = message;
    }
}
