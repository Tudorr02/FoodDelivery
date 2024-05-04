package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.PickUpOrderDataConverterException;
import com.FoodDeliveryApp.Models.PickUpOrder;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PickUpOrdersConverter implements DataConverter<PickUpOrder> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");




    @Override
    public PickUpOrder convertFromCsv(String csvLine) throws PickUpOrderDataConverterException {
        try {
            String[] values = csvLine.split(",");
            if (values.length != 7) {
                throw new PickUpOrderDataConverterException("CSV line does not contain the correct number of data fields for PickUpOrder.");
            }

            String orderID = values[0].trim();
            Users customer = DataStorageServices.getInstance().getUserById(values[1].trim());
            Restaurant restaurant = DataStorageServices.getInstance().getRestaurantById(values[2].trim());
            ShoppingCart cart = DataStorageServices.getInstance().getShoppingCartById(values[3].trim());
            String paymentMethod = values[4].trim();
            LocalDateTime orderDate;
            LocalDateTime pickUpTime;
            try {
                orderDate = LocalDateTime.parse(values[5].trim(), formatter);
                pickUpTime = LocalDateTime.parse(values[6].trim(), formatter);
            } catch (DateTimeParseException e) {
                throw new PickUpOrderDataConverterException("Error parsing date/time fields", e);
            }

            return new PickUpOrder(orderID, customer, restaurant, cart, paymentMethod, orderDate, pickUpTime);
        } catch (Exception e) {
            throw new PickUpOrderDataConverterException("Error converting CSV to PickUpOrder", e);
        }
    }

    @Override
    public String convertToCsv(PickUpOrder pickUpOrder) throws PickUpOrderDataConverterException {
        try {
            return String.join(",",
                    pickUpOrder.getOrderID(),
                    pickUpOrder.getCustomer().getUserID(),
                    pickUpOrder.getRestaurant().getRestaurantID(),
                    pickUpOrder.getShoppingCart().getShoppingCartID(),
                    pickUpOrder.getPaymentMethod(),
                    pickUpOrder.getOrderDate().format(formatter),
                    pickUpOrder.getPickUpTime().format(formatter));
        } catch (Exception e) {
            throw new PickUpOrderDataConverterException("Error converting PickUpOrder to CSV", e);
        }
    }

     public String getFilePath(){
        return "res/CSV/PickUpOrders_Data.csv";
    }

}
