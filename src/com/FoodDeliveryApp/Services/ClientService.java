package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Client;

import java.util.List;
import java.util.Optional;

public class ClientService {
    List<Client> clients ;

    public ClientService() throws Exception {
         clients = DataStorageServices.getInstance().getClients();
    }


    public boolean LogInAsClient(String UserName, String Password){
        return clients.stream()
            .anyMatch(client -> client.getUserName().equals(UserName) && client.getPassword().equals(Password));
    }




}
