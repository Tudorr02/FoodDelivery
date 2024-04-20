package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Models.*;

public class ClientConverter implements DataConverter<Client> {
    @Override
    public Client convertFromCsv(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length < 8) throw new IllegalArgumentException("CSV line does not contain enough data for Client.");
        // Example assumes CSV is ordered as firstName, lastName, userName, password, userID, phoneNumber, deliveryAddress, email
        return new Client(values[0].trim(),values[1].trim(), values[2].trim(), values[3].trim(), values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim());
    }

    @Override
    public String convertToCsv(Client client) {
        return String.join(",",
                client.getFirstName(),
                client.getLastName(),
                client.getUserName(),
                client.getPassword(),
                client.getUserID(),
                client.getPhoneNumber(),
                client.getDeliveryAddress(),
                client.getEmail());
    }
}