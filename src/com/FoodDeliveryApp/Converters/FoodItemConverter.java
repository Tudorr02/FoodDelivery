package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.FoodItem;

public class FoodItemConverter implements DataConverter<FoodItem> {

    @Override
    public FoodItem convertFromCsv(String csvLine) {
        // Split the CSV line using a regex that ignores commas inside quotes
        String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (values.length < 5) {
            throw new IllegalArgumentException("CSV line does not contain enough data for FoodItem.");
        }

        // Remove possible surrounding quotes from the description
        String description = values[2].startsWith("\"") && values[2].endsWith("\"") ?
                values[2].substring(1, values[2].length() - 1) : values[2];

        // Parse the other fields
        int foodItemID = Integer.parseInt(values[0].trim());
        String name = values[1].trim();
        double weight = Double.parseDouble(values[3].trim());
        double price = Double.parseDouble(values[4].trim());

        return new FoodItem(name, description,foodItemID ,weight, price);
    }

    @Override
    public String convertToCsv(FoodItem foodItem) {
        return String.join(",",
                String.valueOf(foodItem.getFoodID()),
                foodItem.getName(),
                "\"" + foodItem.getDescription() + "\"", // Ensure descriptions with commas are quoted
                String.valueOf(foodItem.getWeight()),
                String.valueOf(foodItem.getPrice()));
    }
}
