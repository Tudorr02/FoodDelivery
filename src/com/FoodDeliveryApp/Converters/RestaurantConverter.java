package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.RestaurantDataConverterException;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantConverter implements DataConverter<Restaurant> {

    @Override
    public Restaurant convertFromCsv(String csvLine) throws RestaurantDataConverterException {
        try {
            String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (values.length < 7) {
                throw new RestaurantDataConverterException("CSV line does not contain enough data for Restaurant.");
            }

            String name = values[0].trim();
            String restaurantID = values[1].trim();
            String location = values[2].trim();

            List<FoodItem> menu = Arrays.stream(values[3].replace("\"", "").split(";"))
                    .map(id -> {
                        try {
                            return DataStorageServices.getInstance().getFoodItemById(Integer.parseInt(id.trim()));
                        } catch (Exception e) {
                            try {
                                throw new RestaurantDataConverterException("Failed to fetch FoodItem: " + e.getMessage(), e);
                            } catch (RestaurantDataConverterException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    })
                    .collect(Collectors.toList());

            double rating = Double.parseDouble(values[4].trim());
            String priceRange = values[5].trim();

            List<Review> reviews = Arrays.stream(values[6].replace("\"", "").split(";"))
                    .map(id -> {
                        try {
                            return DataStorageServices.getInstance().getReviewById((id.trim()));
                        } catch (Exception e) {
                            try {
                                throw new RestaurantDataConverterException("Failed to fetch Review: " + e.getMessage(), e);
                            } catch (RestaurantDataConverterException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    })
                    .collect(Collectors.toList());

            return new Restaurant(name, restaurantID, location, menu, rating, priceRange, reviews);
        } catch (Exception e) {
            throw new RestaurantDataConverterException("Error converting CSV to Restaurant", e);
        }
    }

    @Override
    public String convertToCsv(Restaurant restaurant) throws RestaurantDataConverterException {
        try {
            String menuIds = restaurant.getMenu().stream()
                    .map(FoodItem::getFoodID)
                    .map(String::valueOf)
                    .collect(Collectors.joining(";"));

            String reviewIds = restaurant.getReviews().stream()
                    .map(Review::getReviewID)
                    .map(String::valueOf)
                    .collect(Collectors.joining(";"));

            return String.join(",",
                    restaurant.getName(),
                    restaurant.getRestaurantID(),
                    restaurant.getLocation(),
                    "\"" + menuIds + "\"",
                    String.valueOf(restaurant.getRating()),
                    restaurant.getPriceRange(),
                    "\"" + reviewIds + "\"");
        } catch (Exception e) {
            throw new RestaurantDataConverterException("Error converting Restaurant to CSV", e);
        }
    }

     public String getFilePath(){
        return "res/CSV/Restaurants_Data.csv";
    }
}
