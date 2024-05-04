package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.ReviewDataConverterException;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class ReviewConverter implements DataConverter<Review>{

    @Override
    public Review convertFromCsv(String csvLine) throws ReviewDataConverterException {
        try {
            String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (values.length < 4) {
                throw new ReviewDataConverterException("CSV line does not contain enough data for Review.");
            }

            String reviewID = values[0].trim();
            Users user = DataStorageServices.getInstance().getUserById(values[1].trim());
            double givenRating = Double.parseDouble(values[2].trim());

            String message = values[3].trim();
            if (message.startsWith("\"") && message.endsWith("\"")) {
                message = message.substring(1, message.length() - 1);
            }

            return new Review(reviewID, user, givenRating, message);
        } catch (Exception e) {
            throw new ReviewDataConverterException("Error converting CSV to Review", e);
        }
    }

    @Override
    public String convertToCsv(Review review) throws ReviewDataConverterException {
        try {
            return String.join(",",
                    review.getReviewID(),
                    review.getUser().getUserID(),
                    String.valueOf(review.getGivenRating()),
                    "\"" + review.getMessage() + "\"");
        } catch (Exception e) {
            throw new ReviewDataConverterException("Error converting Review to CSV", e);
        }
    }

     public String getFilePath(){
        return "res/CSV/Reviews_Data.csv";
    }


}
