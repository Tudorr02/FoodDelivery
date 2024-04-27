package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.*;

public class DeliveryManConverter implements DataConverter<DeliveryMan> {
    @Override
    public DeliveryMan convertFromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length < 9) throw new IllegalArgumentException("CSV line does not contain enough data for DeliveryMan.");
        // Example assumes CSV is ordered as firstName, lastName, userName, password, userID, phoneNumber, nationality, vehicle, rating
        return new DeliveryMan(values[1].trim(), values[0].trim(), values[2].trim(), values[3].trim(), values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim(), Double.parseDouble(values[8].trim()));
    }

    @Override
    public String convertToCsv(DeliveryMan deliveryMan) {
        return String.join(",",
                deliveryMan.getFirstName(),
                deliveryMan.getLastName(),
                deliveryMan.getUserName(),
                deliveryMan.getPassword(),
                deliveryMan.getUserID(),
                deliveryMan.getPhoneNumber(),
                deliveryMan.getNationality(),
                deliveryMan.getVehicle(),
                String.valueOf(deliveryMan.getRating()));
    }

     public String getFilePath(){
        return "res/CSV/DeliveryMen_Data.csv";
    }
}
