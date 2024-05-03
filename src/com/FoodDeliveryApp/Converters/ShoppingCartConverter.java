package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Services.DataStorageServices;


import java.util.*;

public class ShoppingCartConverter implements DataConverter<ShoppingCart> {

    @Override
    public String convertToCsv(ShoppingCart cart) {

        StringBuilder ShoppingCartBuilder = new StringBuilder();

        int index=0;

        for ( Map.Entry<FoodItem, Integer> entry : cart.getItems().entrySet()){
            ShoppingCartBuilder.append('(')
                    .append(entry.getKey().getFoodID())
                    .append(',')
                    .append(entry.getValue())
                    .append(')');

            if(index != cart.getItems().size() - 1)
                ShoppingCartBuilder.append(';');

            index++;
        }


        return String.join(",",
            cart.getShoppingCartID(),
            "\""+ ShoppingCartBuilder +"\"",
               String.valueOf(cart.getTotal() ));

    }

    @Override
    public ShoppingCart convertFromCsv(String csvLine) throws Exception {
        String[] parts = csvLine.split(",\"|\",");
        String shoppingCartID = parts[0];

        Map<FoodItem, Integer> items = new HashMap<>();
        List<String> foodItemsPair = List.of(parts[1].split(";"));

        for (String s : foodItemsPair) {
            String[] shoppingCartItems = s.replace("(", "").replace(")", "").split(",");
            int foodItemField = Integer.parseInt(shoppingCartItems[0]);
            int quantity = Integer.parseInt(shoppingCartItems[1]);
            items.put(DataStorageServices.getInstance().getFoodItemById(foodItemField), quantity);

        }

        double total = Double.parseDouble(parts[2]);


        return new ShoppingCart(shoppingCartID, items, total);
    }

     public String getFilePath(){
        return "res/CSV/ShoppingCart_Data.csv";
    }
}
