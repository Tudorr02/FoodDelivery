package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.DeliveryManDataConverterException;
import com.FoodDeliveryApp.Models.*;

public class DeliveryManConverter implements DataConverter<DeliveryMan> {
    @Override
    public DeliveryMan convertFromCsv(String csvLine) throws DeliveryManDataConverterException {
        try {
            String[] values = csvLine.split(",");
            if (values.length < 9) {
                throw new DeliveryManDataConverterException("CSV line does not contain enough data for DeliveryMan.");
            }
            // Example assumes CSV is ordered as firstName, lastName, userName, password, userID, phoneNumber, nationality, vehicle, rating
            return new DeliveryMan(values[1].trim(), values[0].trim(), values[2].trim(), values[3].trim(),
                    values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim(), Double.parseDouble(values[8].trim()));
        } catch (Exception e) {
            throw new DeliveryManDataConverterException("Error converting CSV to DeliveryMan", e);
        }
    }

    @Override
    public String convertToCsv(DeliveryMan deliveryMan) throws DeliveryManDataConverterException {
        try {
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
        } catch (Exception e) {
            throw new DeliveryManDataConverterException("Error converting DeliveryMan to CSV", e);
        }
    }

     public String getFilePath(){
        return "res/CSV/DeliveryMen_Data.csv";
    }
}
