package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.PickUpOrder;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PickUpOrdersConverter implements DataConverter<PickUpOrder> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:m");

    @Override
    public PickUpOrder convertFromCsv(String csvLine) throws Exception {
        String[] values = csvLine.split(",");
        if (values.length != 7) {
            throw new IllegalArgumentException("CSV line does not contain correct number of data fields for PickUpOrder.");
        }

        String orderID = values[0].trim();
        Users customer = DataStorageServices.getInstance().getUserById(values[1].trim()); // Fetch user by ID
        Restaurant restaurant = DataStorageServices.getInstance().getRestaurantById(values[2].trim()); // Fetch restaurant by ID
        ShoppingCart cart = DataStorageServices.getInstance().getShoppingCartById(values[3].trim()); // Fetch shopping cart by ID
        String paymentMethod = values[4].trim();
        LocalDateTime orderDate = LocalDateTime.parse(values[5].trim(), formatter);
        LocalDateTime pickUpTime = LocalDateTime.parse(values[6].trim(), formatter);

        // Passing actual object references instead of IDs
        return new PickUpOrder(orderID, customer, restaurant, cart, paymentMethod, orderDate, pickUpTime);
    }

    @Override
    public String convertToCsv(PickUpOrder pickUpOrder) {
        return String.join(",",
                pickUpOrder.getOrderID(),
                pickUpOrder.getCustomer().getUserID(), // Assuming Users has getUserID method
                pickUpOrder.getRestaurant().getRestaurantID(), // Assuming Restaurant has getRestaurantID method
                pickUpOrder.getShoppingCart().getShoppingCartID(), // Assuming ShoppingCart has getShoppingCartId method
                pickUpOrder.getPaymentMethod(),
                pickUpOrder.getOrderDate().format(formatter),
                pickUpOrder.getPickUpTime().format(formatter));
    }
}
