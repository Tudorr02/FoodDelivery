package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.ClientConverter;

import java.util.List;
import java.util.Comparator;

public class ClientServices {

    public boolean addNewClient(String firstName, String lastName, String userName, String password, String phoneNumber, String deliveryAddress, String email) {
        try {
            DataStorageServices.getInstance().initData();
            List<Client> clients = DataStorageServices.getInstance().getClients();

            // Generate a new UserID by incrementing the highest existing ID
            String newUserId = generateNewClientId(clients);

            // Create new Client object
            Client newClient = new Client(lastName, firstName, userName, password, newUserId, phoneNumber, deliveryAddress, email);

            // Add to the list and write to CSV
            clients.add(newClient);
            writeClientsToCsv(clients);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateNewClientId(List<Client> clients) {
        // Get the highest existing ID and increment it
        String maxId = clients.stream()
                .map(Client::getUserID)
                .max(Comparator.naturalOrder())
                .orElse("C-100000");
        int newIdNumber = Integer.parseInt(maxId.substring(2)) + 1;
        return "C-" + String.format("%06d", newIdNumber);
    }

    private void writeClientsToCsv(List<Client> clients) throws Exception {
        DataConverter<Client> converter = new ClientConverter();
        DataStorageServices.getInstance().writeCsv(converter, clients);
        DataStorageServices.getInstance().initData();
    }
}
