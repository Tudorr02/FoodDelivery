package com.FoodDeliveryApp.Models;

import java.util.Date;

public class PickUpOrder extends Order{

    private int pickUpTime;

    public PickUpOrder(String orderID, String customerID, String restaurantID, ShoppingCart shoppingCart, String paymentMethod, Date orderDate) {
        super(orderID, customerID, restaurantID, shoppingCart, paymentMethod, orderDate);
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
