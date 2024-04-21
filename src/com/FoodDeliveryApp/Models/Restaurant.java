package com.FoodDeliveryApp.Models;

import java.util.List;

public class Restaurant {

    private String name;
    private String restaurantID;
    private String location;
    private List<FoodItem> menu; // List of FoodItems for the menu
    private double rating;
    private String priceRange;
    private List<Review> reviews;

    public Restaurant(String name, String restaurantID, String location, List<FoodItem> menu, double rating, String priceRange, List<Review> reviews) {
        this.name = name;
        this.restaurantID = restaurantID;
        this.location = location;
        this.menu = menu;
        this.rating = rating;
        this.priceRange = priceRange;
        this.reviews = reviews;
    }
}
