package com.FoodDeliveryApp.Services;


import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryManConverter;

import java.util.List;

public class DeliveryManServices {

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
                    break;
                }
            }

            if (found) {
                writeDeliveryMenToCsv(deliveryMen);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void writeDeliveryMenToCsv(List<DeliveryMan> deliveryMen) throws Exception {
        DataConverter<DeliveryMan> converter = new DeliveryManConverter();
        DataStorageServices.getInstance().writeCsv(converter, deliveryMen);
        DataStorageServices.getInstance().initData();
    }
}
