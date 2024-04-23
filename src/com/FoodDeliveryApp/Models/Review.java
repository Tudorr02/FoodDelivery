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

    public String getReviewID() {
        return reviewID;
    }

    public String getUserId() {
        return userId;
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
                ", userId='" + userId + '\'' +
                ", givenRating=" + givenRating +
                ", message='" + message + '\'' +
                '}';
    }
}
