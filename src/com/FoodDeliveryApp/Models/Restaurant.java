package com.FoodDeliveryApp.Models;

import java.util.List;
import java.util.stream.Collectors;

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


    public String getName() {
        return name;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getLocation() {
        return location;
    }

    public List<FoodItem> getMenu() {
        return menu;
    }

    public double getRating() {
        return rating;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Restaurant{");
        builder.append("name='").append(name).append('\'');
        builder.append(", restaurantID='").append(restaurantID).append('\'');
        builder.append(", location='").append(location).append('\'');

        // Adding menu items' string representations
        builder.append(", menu=[");
        if (menu != null) {
            builder.append(menu.stream()
                    .map(FoodItem::toString)  // Use FoodItem's toString
                    .collect(Collectors.joining(", ")));
        }
        builder.append("]");

        // Adding rating and price range
        builder.append(", rating=").append(rating);
        builder.append(", priceRange='").append(priceRange).append('\'');

        // Adding reviews' string representations
        builder.append(", reviews=[");
        if (reviews != null) {
            builder.append(reviews.stream()
                    .map(Review::toString)  // Use Review's toString
                    .collect(Collectors.joining(", ")));
        }
        builder.append("]");

        builder.append('}');
        return builder.toString();
    }

}
