package com.FoodDeliveryApp.Models;
import java.util.Arrays;
import java.util.List;

public class Client extends Users {

    private String deliveryAddress;
    private String email;


    public Client(String lastName, String firstName, String userName, String password, String userID, String phoneNumber, String deliveryAddress, String email) {
        super(lastName, firstName, userName, password, userID, phoneNumber);
        this.deliveryAddress = deliveryAddress;
        this.email = email;
    }

    @Override
    public Users convertFromCsv(String csvLine) {
        Users user = super.convertFromCsv(csvLine); // Call the general part
        List<String> values = Arrays.asList(csvLine.split(","));
        if (values.size() < 8) {
            throw new IllegalArgumentException("Invalid CSV line: does not contain enough data elements for client.");
        }
        // Set additional fields specific to Client
        this.deliveryAddress = values.get(6).trim();
        this.email = values.get(7).trim();
        return this;
    }
    @Override
    public String convertToCsv(Object object) {
        if (!(object instanceof Client)) {
            throw new IllegalArgumentException("Object must be an instance of Client");
        }
        Client client = (Client) object;
        // Call the superclass method to get the CSV string for common fields
        String baseCsv = super.convertToCsv(client);
        // Append the Client-specific fields to the base CSV
        return String.join(",", baseCsv, client.getDeliveryAddress(), client.getEmail());
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString()  +
                "deliveryAddress='" + deliveryAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


