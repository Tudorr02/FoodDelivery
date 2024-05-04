package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.ClientDataConverterException;
import com.FoodDeliveryApp.Models.*;

public class ClientConverter implements DataConverter<Client> {
    @Override
    public Client convertFromCsv(String csvLine) throws ClientDataConverterException {
        try {
            String[] values = csvLine.split(",");
            if (values.length < 8) {
                throw new ClientDataConverterException("CSV line does not contain enough data for Client.");
            }
            // Example assumes CSV is ordered as firstName, lastName, userName, password, userID, phoneNumber, deliveryAddress, email
            return new Client(values[0].trim(), values[1].trim(), values[2].trim(), values[3].trim(),
                    values[4].trim(), values[5].trim(), values[6].trim(), values[7].trim());
        } catch (Exception e) {
            throw new ClientDataConverterException("Error converting CSV to Client", e);
        }
    }

    @Override
    public String convertToCsv(Client client) throws ClientDataConverterException {
        try {
            return String.join(",",
                    client.getLastName(),
                    client.getFirstName(),
                    client.getUserName(),
                    client.getPassword(),
                    client.getUserID(),
                    client.getPhoneNumber(),
                    client.getDeliveryAddress(),
                    client.getEmail());
        } catch (Exception e) {
            throw new ClientDataConverterException("Error converting Client to CSV", e);
        }
    }

    public String getFilePath(){
        return "res/CSV/Client_Data.csv";
    }

}