package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.Delivery;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Models.DeliveryOrder;
import com.FoodDeliveryApp.Models.Order;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class DeliveryConverter implements DataConverter<Delivery> {

    @Override
    public Delivery convertFromCsv(String csvLine) {
        String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (values.length < 4) {
            throw new IllegalArgumentException("CSV line does not contain enough data for Delivery.");
        }

        String deliveryID = values[0].trim();
        String deliveryManID = values[1].trim().replace("\"","");
        String orderID = values[2].trim().replace("\"","");
        LocalDateTime expectedDate = LocalDateTime.parse(values[3].trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

        // Assuming getDeliveryManById and getOrderById methods return DeliveryMan and Order objects
        DeliveryMan deliveryMan;
        DeliveryOrder order;
        try {
            deliveryMan = DataStorageServices.getInstance().getDeliveryManById(deliveryManID);
            order = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(orderID);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch DeliveryMan or Order: " + e.getMessage(), e);
        }

        return new Delivery(deliveryID, deliveryMan, order, expectedDate);
    }

    @Override
    public String convertToCsv(Delivery delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return String.join(",",
                delivery.getDeliveryID(),
                delivery.getDeliveryMan().getUserID()  ,
                delivery.getOrder().getOrderID()  ,
                delivery.getExpectedDate().format(formatter));
    }

    public String getFilePath(){
        return "res/CSV/Delivery_Data.csv";
    }
}
