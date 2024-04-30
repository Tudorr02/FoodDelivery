package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Delivery;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryConverter;
import com.FoodDeliveryApp.Models.DeliveryOrder;
import com.FoodDeliveryApp.Models.DeliveryStatus;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DeliveryServices {


    public boolean generateAndRecordDelivery(String deliveryManId, String orderId, LocalDateTime orderDate) {
        try {
            List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();
            String lastDeliveryId = deliveries.isEmpty() ? "DEL-1000000" : deliveries.get(deliveries.size() - 1).getDeliveryID();
            String newDeliveryId = incrementDeliveryId(lastDeliveryId);

            DeliveryMan deliveryMan = DataStorageServices.getInstance().getDeliveryManById(deliveryManId);
            DeliveryOrder order = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(orderId);

            Random rand = new Random();
            int randomHour = 1 + rand.nextInt(2); // Generate a random number between 1 and 2
            LocalDateTime expectedDate = orderDate.plusHours(randomHour);

            Delivery newDelivery = new Delivery(newDeliveryId, deliveryMan, order, expectedDate, DeliveryStatus.UNFINISHED);
            System.out.println("IMI INTRA AICICIAAAAAAAAAAAAAAAAAAAAAAAAAA");
            deliveries.add(newDelivery);
            writeDeliveriesToCsv(deliveries);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Helper method to increment the last delivery ID
    private String incrementDeliveryId(String lastId) {
        int num = Integer.parseInt(lastId.substring(4)) + 1;
        return "DEL-" + num;
    }


    public boolean updateDeliveryMan(String deliveryId, String deliveryManId) {

        try {
            List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();

            boolean found = false;
            for (Delivery delivery : deliveries) {
                if (delivery.getOrder().getOrderID().equals(deliveryId)) {
                    delivery.setDeliveryMan(DataStorageServices.getInstance().getDeliveryManById(deliveryManId)); // Assume constructor exists
                    found = true;
                    break;
                }
            }
            if (found) {
                writeDeliveriesToCsv(deliveries);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateDeliveryStatus(Delivery delivery) throws Exception {
        List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();
        boolean isUpdated = false; // Flag to check if any delivery was updated

        for (Delivery del : deliveries) {
            if (del.getDeliveryID().equals(delivery.getDeliveryID())) {
                del.setStatus(delivery.getStatus()); // Assuming setStatus sets the status
                isUpdated = true; // Set flag as true since we've made an update
                break; // Exit loop once update is made
            }
        }

        // Only write to CSV if there was an update
        if (isUpdated) {
            writeDeliveriesToCsv(deliveries);
        }
    }



    private void writeDeliveriesToCsv(List<Delivery> deliveries) throws Exception {
        DataConverter<Delivery> converter = new DeliveryConverter();
        DataStorageServices.getInstance().writeCsv(converter, deliveries);
        System.out.println("imi scrie in csv!!!!!!!!!!!!!!!!!!");
        DataStorageServices.getInstance().initData();
    }
}
