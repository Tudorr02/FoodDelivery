package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Models.UserType;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.AuditServices.AuditingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserServices extends AuditingService {
    List<Users> users;

    public UserServices() throws Exception {
        users= new ArrayList<Users>();
        users.addAll(DataStorageServices.getInstance().getClients());
        users.addAll(DataStorageServices.getInstance().getDeliveryMans());
    }

    public Users LogInUser(String UserName, String Password){
        logAction("LogInUser: " + UserName);
        for (Users user : users) {
             if(user.getUserName().equals(UserName) && user.getPassword().equals(Password)){
                 return user;
             }
         }
         return null;
    }

    public UserType getUserType(Users user){
        logAction("getUserType: " + user.getUserName());
        switch (user.getClass().getSimpleName()){
            case "Client": return UserType.CLIENT;
            case "DeliveryMan": return UserType.DELIVERYMAN;
            case "Admin": return UserType.ADMIN;
            default: return UserType.FAILED;
        }
    }



}
