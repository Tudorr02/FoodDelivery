package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewConverter implements DataConverter<Review>{

    public Review convertFromCsv(String csvLine) {
        // Split the CSV line using a regex that ignores commas inside quotes
        String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (values.length < 4) {
            throw new IllegalArgumentException("CSV line does not contain enough data for Review.");
        }

        String reviewID = values[0].trim();
        String userId = values[1].trim();
        double givenRating = Double.parseDouble(values[2].trim());

        // Remove possible surrounding quotes from the message
        String message = values[3].trim();
        if (message.startsWith("\"") && message.endsWith("\"")) {
            message = message.substring(1, message.length() - 1);
        }

        return new Review(reviewID, userId, givenRating, message);
    }

    public String convertToCsv(Review review) {
        // Ensure messages with commas are quoted
        return String.join(",",
                review.getReviewID(),
                review.getUserId(),
                String.valueOf(review.getGivenRating()),
                "\"" + review.getMessage() + "\"");
    }
}
