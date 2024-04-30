package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class DeliveryConverter implements DataConverter<Delivery> {

    @Override
    public Delivery convertFromCsv(String csvLine) throws Exception {
        String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

        if (values.length < 5) {
            throw new IllegalArgumentException("CSV line does not contain enough data for Delivery.");
        }

        String deliveryID = values[0].trim();
        String deliveryManID = values[1].trim();
        String orderID = values[2].trim();
        LocalDateTime expectedDate = LocalDateTime.parse(values[3].trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        DeliveryStatus status = DeliveryStatus.valueOf(values[4].trim().toUpperCase());

        DeliveryMan deliveryMan = DataStorageServices.getInstance().getDeliveryManById(deliveryManID);
        DeliveryOrder order = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(orderID);

        return new Delivery(deliveryID, deliveryMan, order, expectedDate, status);
    }

    @Override
    public String convertToCsv(Delivery delivery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return String.join(",",
                delivery.getDeliveryID(),
                delivery.getDeliveryMan().getUserID(),
                delivery.getOrder().getOrderID(),
                delivery.getExpectedDate().format(formatter),
                delivery.getStatus().name());
    }

    public String getFilePath() {
        return "res/CSV/Delivery_Data.csv";
    }
}
