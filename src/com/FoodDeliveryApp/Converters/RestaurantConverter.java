package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantConverter implements DataConverter<Restaurant> {

    public Restaurant convertFromCsv(String csvLine) {
        // Split the CSV line using a regex that ignores commas inside quotes
        String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (values.length < 7) {
            throw new IllegalArgumentException("CSV line does not contain enough data for Restaurant.");
        }

        String name = values[0].trim();
        String restaurantID = values[1].trim();
        String location = values[2].trim();

        // Convert menu IDs to List of FoodItem objects, parsing each ID to int before fetching
        List<FoodItem> menu = Arrays.stream(values[3].split(";"))
                .map(id -> {
                    try {
                        return DataStorageServices.getInstance().getFoodItemById(Integer.parseInt(id.trim()));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to fetch FoodItem: " + e.getMessage(), e);
                    }
                })
                .collect(Collectors.toList());

        double rating = Double.parseDouble(values[4].trim());
        String priceRange = values[5].trim();

        // Convert review IDs to List of Review objects, parsing each ID to int before fetching
        List<Review> reviews = Arrays.stream(values[6].split(";"))
                .map(id -> {
                    try {
                        return DataStorageServices.getInstance().getReviewById((id.trim()));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to fetch Review: " + e.getMessage(), e);
                    }
                })
                .collect(Collectors.toList());

        return new Restaurant(name, restaurantID, location, menu, rating, priceRange, reviews);
    }

    public String convertToCsv(Restaurant restaurant) {
        // Convert menu and reviews to semicolon-separated IDs
        String menuIds = restaurant.getMenu().stream()
                .map(FoodItem::getFoodID)
                .map(String::valueOf)  // Convert each int ID to a String
                .collect(Collectors.joining(";"));

        String reviewIds = restaurant.getReviews().stream()
                .map(Review::getReviewID)
                .map(String::valueOf)  // Assuming Review::getReviewID returns int, convert it to String
                .collect(Collectors.joining(";"));

        return String.join(",",
                restaurant.getName(),
                restaurant.getRestaurantID(),
                restaurant.getLocation(),
                "\"" + menuIds + "\"",
                String.valueOf(restaurant.getRating()),
                restaurant.getPriceRange(),
                "\"" + reviewIds + "\"");
    }
}
