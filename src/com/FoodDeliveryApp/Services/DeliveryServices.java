package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Delivery;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryConverter;
import com.FoodDeliveryApp.Models.DeliveryOrder;
import com.FoodDeliveryApp.Models.DeliveryStatus;
import com.FoodDeliveryApp.Services.AuditServices.AuditingService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class DeliveryServices extends AuditingService {


    public boolean generateAndRecordDelivery(String deliveryManId, String orderId, LocalDateTime orderDate) {
        try {
            logAction(String.format("generateAndRecordDelivery: DeliveryMan ID=%s, Order ID=%s, Order Date=%s", deliveryManId, orderId, orderDate.toString()));
            List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();
            String lastDeliveryId = deliveries.isEmpty() ? "DEL-1000000" : deliveries.get(deliveries.size() - 1).getDeliveryID();
            String newDeliveryId = incrementDeliveryId(lastDeliveryId);

            DeliveryMan deliveryMan = DataStorageServices.getInstance().getDeliveryManById(deliveryManId);
            DeliveryOrder order = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(orderId);

            Random rand = new Random();
            int randomHour = 1 + rand.nextInt(2); // Generate a random number between 1 and 2
            LocalDateTime expectedDate = orderDate.plusHours(randomHour);

            Delivery newDelivery = new Delivery(newDeliveryId, deliveryMan, order, expectedDate, DeliveryStatus.UNFINISHED);
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
        logAction("incrementDeliveryId: last ID=" + lastId);
        int num = Integer.parseInt(lastId.substring(4)) + 1;
        return "DEL-" + num;
    }


    public boolean updateDeliveryMan(String deliveryId, String deliveryManId) {
        logAction(String.format("updateDeliveryMan: Delivery ID=%s, DeliveryMan ID=%s", deliveryId, deliveryManId));
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
    public DeliveryOrder getDeliveryOrderByOrderID(String orderID) throws Exception {
        logAction(String.format("getDeliveryOrderByOrderID: Order ID=%s", orderID));

        List<DeliveryOrder> orders = DataStorageServices.getInstance().getdOrders();

        return orders.stream().filter(order -> order.getOrderID().equals(orderID)).findFirst().orElseThrow(() -> new Exception("Order not found: " + orderID));
    }
    public void updateDeliveryStatus(Delivery delivery) throws Exception {
        logAction(String.format("updateDeliveryStatus: Delivery ID=%s, Status=%s", delivery.getDeliveryID(), delivery.getStatus()));
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

    public int getNumberOfCompletedOrders(String deliveryManId) {
        logAction(String.format("getNumberOfCompletedOrders: DeliveryMan ID=%s", deliveryManId));
        try {
            List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();
            int completedOrdersCount = 0;

            for (Delivery delivery : deliveries) {
                if (delivery.getDeliveryMan().getUserID().equals(deliveryManId) && delivery.getStatus() == DeliveryStatus.FINISHED) {
                    completedOrdersCount++;
                }
            }
            return completedOrdersCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0; // Return 0 if there's an error
        }
    }



    private void writeDeliveriesToCsv(List<Delivery> deliveries) throws Exception {
        logAction(String.format("writeDeliveriesToCsv: Number of deliveries=%d", deliveries.size()));
        DataConverter<Delivery> converter = new DeliveryConverter();
        DataStorageServices.getInstance().writeCsv(converter, deliveries);
        System.out.println("imi scrie in csv!!!!!!!!!!!!!!!!!!");
        DataStorageServices.getInstance().initData();
    }

    public int getPromoCodePercentage(String promoCode){
         String csvFile = "res/CSV/DeliveryDiscount_Data.csv"; // The path to your CSV file

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Split each line by comma
                String[] columns = line.split(",", 2);

                if (columns.length == 2) { // Ensure there are two columns
                    String key = columns[0].trim();
                    String value = columns[1].trim();
                    if(key.equals(promoCode))
                        return Integer.parseInt(value);
                     // Add the pair to the map
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the map to verify the contents

        return 0;
    }
}
