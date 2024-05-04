package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.DeliveryDataConverterException;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class DeliveryConverter implements DataConverter<Delivery> {

    @Override
    public Delivery convertFromCsv(String csvLine) throws DeliveryDataConverterException {
        try {
            String[] values = csvLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            if (values.length < 5) {
                throw new DeliveryDataConverterException("CSV line does not contain enough data for Delivery.");
            }

            String deliveryID = values[0].trim();
            String deliveryManID = values[1].trim();
            String orderID = values[2].trim();
            LocalDateTime expectedDate = LocalDateTime.parse(values[3].trim(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            DeliveryStatus status = DeliveryStatus.valueOf(values[4].trim().toUpperCase());

            DeliveryMan deliveryMan = DataStorageServices.getInstance().getDeliveryManById(deliveryManID);
            DeliveryOrder order = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(orderID);

            return new Delivery(deliveryID, deliveryMan, order, expectedDate, status);
        } catch (Exception e) {
            throw new DeliveryDataConverterException("Error converting CSV to Delivery", e);
        }
    }
    @Override
    public String convertToCsv(Delivery delivery) throws DeliveryDataConverterException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            return String.join(",",
                    delivery.getDeliveryID(),
                    delivery.getDeliveryMan().getUserID(),
                    delivery.getOrder().getOrderID(),
                    delivery.getExpectedDate().format(formatter),
                    delivery.getStatus().name());
        } catch (Exception e) {
            throw new DeliveryDataConverterException("Error converting Delivery to CSV", e);
        }
    }

    public String getFilePath() {
        return "res/CSV/Delivery_Data.csv";
    }
}
