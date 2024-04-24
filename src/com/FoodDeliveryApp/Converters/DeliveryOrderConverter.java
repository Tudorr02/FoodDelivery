package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.DeliveryOrder;
//import com.FoodDeliveryApp.Models.ShoppingCart;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DeliveryOrderConverter implements DataConverter<DeliveryOrder> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public DeliveryOrder convertFromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length < 7) {
            throw new IllegalArgumentException("CSV line does not contain enough data for DeliveryOrder.");
        }
        // Assumes CSV is ordered as orderID, customerID, restaurantID, shoppingCart, paymentMethod, orderDate, deliveryDiscountPercent
        String orderID = values[0].trim();
        String customerID = values[1].trim();
        String restaurantID = values[2].trim();
        String shoppingCartID = values[3].trim();
        String paymentMethod = values[4].trim();
        LocalDateTime orderDate = null;
        try {
            orderDate = LocalDateTime.parse(values[5].trim(),dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Error parsing order date", e);
        }
        int deliveryDiscountPercent = Integer.parseInt(values[6].trim());

        return new DeliveryOrder(orderID, customerID, restaurantID, shoppingCartID, paymentMethod, orderDate, deliveryDiscountPercent);
    }

    @Override
    public String convertToCsv(DeliveryOrder deliveryOrder) {
        return String.join(",",
                deliveryOrder.getOrderID(),
                deliveryOrder.getCustomerID(),
                deliveryOrder.getRestaurantID(),
                deliveryOrder.getShoppingCartID(),
                deliveryOrder.getPaymentMethod(),
                dateTimeFormatter.format(deliveryOrder.getOrderDate()),
                String.valueOf(deliveryOrder.getDeliveryDiscountPercent()));
    }


}
