package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.FoodItemDataConverterException;
import com.FoodDeliveryApp.Models.FoodItem;

public class FoodItemConverter implements DataConverter<FoodItem> {

    @Override
    public FoodItem convertFromCsv(String csvLine) throws FoodItemDataConverterException {
        try {
            String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (values.length < 5) {
                throw new FoodItemDataConverterException("CSV line does not contain enough data for FoodItem.");
            }

            String description = values[2].startsWith("\"") && values[2].endsWith("\"") ?
                    values[2].substring(1, values[2].length() - 1) : values[2];

            int foodItemID = Integer.parseInt(values[0].trim());
            String name = values[1].trim();
            double weight = Double.parseDouble(values[3].trim());
            double price = Double.parseDouble(values[4].trim());

            return new FoodItem(name, description, foodItemID, weight, price);
        } catch (Exception e) {
            throw new FoodItemDataConverterException("Error converting CSV to FoodItem", e);
        }
    }

    @Override
    public String convertToCsv(FoodItem foodItem) throws FoodItemDataConverterException {
        try {
            return String.join(",",
                    String.valueOf(foodItem.getFoodID()),
                    foodItem.getName(),
                    "\"" + foodItem.getDescription() + "\"",
                    String.valueOf(foodItem.getWeight()),
                    String.valueOf(foodItem.getPrice()));
        } catch (Exception e) {
            throw new FoodItemDataConverterException("Error converting FoodItem to CSV", e);
        }
    }

     public String getFilePath(){
        return "res/CSV/FoodItems_Data.csv";
    }
}
