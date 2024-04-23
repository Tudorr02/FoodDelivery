package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class PickUpOrder extends Order{

    private int pickUpTime;

    public PickUpOrder(String orderID, String customerID, String restaurantID, String shoppingCart, String paymentMethod, LocalDateTime orderDate, int pickUpTime) {
        super(orderID, customerID, restaurantID, shoppingCart, paymentMethod, orderDate);
        this.pickUpTime = pickUpTime;
    }

    public int getPickUpTime() {
        return pickUpTime;
    }

    @Override
    public String toString() {
        return "PickUpOrder{" +
                "pickUpTime=" + pickUpTime +
                '}';
    }
}
