package com.FoodDeliveryApp.Models;
import java.util.Arrays;
import java.util.List;

public class DeliveryMan extends Users {
    private String nationality;
    private String vehicle;
    private double rating;


    public String getNationality() {
        return nationality;
    }

    public String getVehicle() {
        return vehicle;
    }

    public double getRating() {
        return rating;
    }

    public DeliveryMan(String lastName, String firstName, String userName, String password, String userID, String phoneNumber, String nationality, String vehicle, double rating) {
        super(lastName, firstName, userName, password, userID, phoneNumber);
        this.nationality = nationality;
        this.vehicle = vehicle;
        this.rating = rating;
    }

    @Override
    public Users convertFromCsv(String csvLine) {
        // Call the general conversion method from the superclass to handle common fields
        Users user = super.convertFromCsv(csvLine);
        List<String> values = Arrays.asList(csvLine.split(","));
        if (values.size() < 9) {
            throw new IllegalArgumentException("Invalid CSV line: does not contain enough data elements for delivery man.");
        }
        // Set additional fields specific to DeliveryMan
        this.nationality = values.get(6).trim();
        this.vehicle = values.get(7).trim();
        this.rating = Double.parseDouble(values.get(8).trim());
        return this;
    }
    @Override
    public String convertToCsv(Object object) {
        if (!(object instanceof DeliveryMan)) {
            throw new IllegalArgumentException("Object must be an instance of DeliveryMan");
        }
        DeliveryMan deliveryMan = (DeliveryMan) object;
        // Call the superclass method to get the CSV string for common fields
        String baseCsv = super.convertToCsv(deliveryMan);
        // Append the DeliveryMan-specific fields to the base CSV
        return String.join(",", baseCsv,
                deliveryMan.getNationality(),
                deliveryMan.getVehicle(),
                String.valueOf(deliveryMan.getRating()));
    }
}
