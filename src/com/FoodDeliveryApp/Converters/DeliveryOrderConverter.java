package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.DeliveryOrder;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Services.DataStorageServices;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeliveryOrderConverter implements DataConverter<DeliveryOrder> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy H:m");

    @Override
    public DeliveryOrder convertFromCsv(String csvLine) throws Exception {
        String[] values = csvLine.split(",");
        if (values.length < 7) {
            throw new IllegalArgumentException("CSV line does not contain enough data for DeliveryOrder.");
        }

        String orderID = values[0].trim();
        Users customer = DataStorageServices.getInstance().getUserById(values[1].trim()); // Fetch user by ID
        Restaurant restaurant = DataStorageServices.getInstance().getRestaurantById(values[2].trim()); // Fetch restaurant by ID
        ShoppingCart cart = DataStorageServices.getInstance().getShoppingCartById(values[3].trim()); // Fetch shopping cart by ID
        String paymentMethod = values[4].trim();
        LocalDateTime orderDate;
        try {
            orderDate = LocalDateTime.parse(values[5].trim(), dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error parsing order date", e);
        }
        int deliveryDiscountPercent = Integer.parseInt(values[6].trim());

        return new DeliveryOrder(orderID, customer, restaurant, cart, paymentMethod, orderDate, deliveryDiscountPercent);
    }

    @Override
    public String convertToCsv(DeliveryOrder deliveryOrder) {
        return String.join(",",
                deliveryOrder.getOrderID(),
                deliveryOrder.getCustomer().getUserID(),
                deliveryOrder.getRestaurant().getRestaurantID(),
                deliveryOrder.getShoppingCart().getShoppingCartID(),
                deliveryOrder.getPaymentMethod(),
                dateTimeFormatter.format(deliveryOrder.getOrderDate()),
                String.valueOf(deliveryOrder.getDeliveryDiscountPercent()));
    }
}
