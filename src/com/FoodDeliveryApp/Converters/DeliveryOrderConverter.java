package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.DeliveryOrderDataConverterException;
import com.FoodDeliveryApp.Models.DeliveryOrder;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Models.AsignedType;
import com.FoodDeliveryApp.Services.DataStorageServices;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeliveryOrderConverter implements DataConverter<DeliveryOrder> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    @Override
    public DeliveryOrder convertFromCsv(String csvLine) throws DeliveryOrderDataConverterException {
        try {
            String[] values = csvLine.split(",");
            if (values.length < 8) {
                throw new DeliveryOrderDataConverterException("CSV line does not contain enough data for DeliveryOrder.");
            }

            String orderID = values[0].trim();
            Users customer = DataStorageServices.getInstance().getUserById(values[1].trim());
            Restaurant restaurant = DataStorageServices.getInstance().getRestaurantById(values[2].trim());
            ShoppingCart cart = DataStorageServices.getInstance().getShoppingCartById(values[3].trim());
            String paymentMethod = values[4].trim();
            LocalDateTime orderDate;
            try {
                orderDate = LocalDateTime.parse(values[5].trim(), dateTimeFormatter);
            } catch (DateTimeParseException e) {
                throw new DeliveryOrderDataConverterException("Error parsing order date", e);
            }
            int deliveryDiscountPercent = Integer.parseInt(values[6].trim());
            AsignedType assignedType = AsignedType.valueOf(values[7].trim());

            return new DeliveryOrder(orderID, customer, restaurant, cart, paymentMethod, orderDate, deliveryDiscountPercent, assignedType);
        } catch (Exception e) {
            throw new DeliveryOrderDataConverterException("Error converting CSV to DeliveryOrder", e);
        }
    }

    @Override
    public String convertToCsv(DeliveryOrder deliveryOrder) throws DeliveryOrderDataConverterException {
        try {
            return String.join(",",
                    deliveryOrder.getOrderID(),
                    deliveryOrder.getCustomer().getUserID(),
                    deliveryOrder.getRestaurant().getRestaurantID(),
                    deliveryOrder.getShoppingCart().getShoppingCartID(),
                    deliveryOrder.getPaymentMethod(),
                    dateTimeFormatter.format(deliveryOrder.getOrderDate()),
                    String.valueOf(deliveryOrder.getDeliveryDiscountPercent()),
                    deliveryOrder.getAsigned().toString());
        } catch (Exception e) {
            throw new DeliveryOrderDataConverterException("Error converting DeliveryOrder to CSV", e);
        }
    }

    public String getFilePath(){
        return "res/CSV/DeliveryOrders_Data.csv";
    }
}
