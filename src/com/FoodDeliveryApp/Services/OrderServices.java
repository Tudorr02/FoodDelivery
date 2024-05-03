package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Models.Order;


public class OrderServices {

    Order order;

    public String generateOrder() throws Exception {

        return DataStorageServices.getInstance().getdOrders().getLast().getOrderID();
    }
}
