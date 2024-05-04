package com.FoodDeliveryApp.Services;


import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryManConverter;
import com.FoodDeliveryApp.Services.AuditServices.AuditingService;

import java.util.Comparator;
import java.util.List;

public class DeliveryManServices extends AuditingService{

    public boolean updateDeliveryManDetails(String deliveryManId, String newUsername, String newPhoneNumber, String newVehicle, String newPasword) {
        try {
            List<DeliveryMan> deliveryMen = DataStorageServices.getInstance().getDeliveryMans();

            boolean found = false;
            for (DeliveryMan deliveryMan : deliveryMen) {
                if (deliveryMan.getUserID().equals(deliveryManId)) {
                    deliveryMan.setUserName(newUsername);
                    deliveryMan.setPhoneNumber(newPhoneNumber);
                    deliveryMan.setVehicle(newVehicle);
                    deliveryMan.setPassword(newPasword);
                    found = true;
                    logAction("Updated delivery man details for ID: " + deliveryManId);
                    break;
                }
            }

            if (found) {
                writeDeliveryMenToCsv(deliveryMen);
                return true;
            }
            logAction("Failed to find delivery man for update with ID: " + deliveryManId);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            logAction("Exception occurred while updating delivery man with ID: " + deliveryManId);
            return false;
        }
    }

    public boolean addNewDeliveryMan(String firstName, String lastName, String userName, String password, String phoneNumber, String nationality, String vehicle) {
        try {
            DataStorageServices.getInstance().initData();
            List<DeliveryMan> deliveryMen = DataStorageServices.getInstance().getDeliveryMans();

            // Generate a new UserID by incrementing the highest existing ID
            String newUserId = generateNewDeliveryManId(deliveryMen);
            double defaultRating = 4.0;


            DeliveryMan newDeliveryMan = new DeliveryMan(lastName, firstName, userName, password, newUserId, phoneNumber, nationality, vehicle, defaultRating);

            // Add to the list and write to CSV
            deliveryMen.add(newDeliveryMan);
            writeDeliveryMenToCsv(deliveryMen);
            logAction("Added new delivery man with ID: " + newUserId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logAction("Exception occurred while adding new delivery man");
            return false;
        }
    }

    private String generateNewDeliveryManId(List<DeliveryMan> deliveryMen) {
        // Get the highest existing ID and increment it
        String maxId = deliveryMen.stream()
                .map(DeliveryMan::getUserID)
                .max(Comparator.naturalOrder())
                .orElse("D-100000");
        int newIdNumber = Integer.parseInt(maxId.substring(2)) + 1;
        return "D-" + String.format("%06d", newIdNumber);
    }


    private void writeDeliveryMenToCsv(List<DeliveryMan> deliveryMen) throws Exception {
        DataConverter<DeliveryMan> converter = new DeliveryManConverter();
        DataStorageServices.getInstance().writeCsv(converter, deliveryMen);
        DataStorageServices.getInstance().initData();
    }
}
