package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Delivery;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryConverter;

import java.io.IOException;
import java.util.List;

public class DeliveryServices {


    public boolean updateDeliveryMan(String deliveryId, String deliveryManId) {
        try {
            List<Delivery> deliveries = DataStorageServices.getInstance().getDeliveries();

            boolean found = false;
            for (Delivery delivery : deliveries) {
                if (delivery.getOrder().getOrderID().equals(deliveryId)) {
                    System.out.println("IMI INTRA AICICIAAAAAAAAAAAAAAAAAAAAAAAAAA");
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

    private void writeDeliveriesToCsv(List<Delivery> deliveries) throws Exception {
        DataConverter<Delivery> converter = new DeliveryConverter();
        DataStorageServices.getInstance().writeCsv(converter, deliveries);
        DataStorageServices.getInstance().initData();
    }
}
