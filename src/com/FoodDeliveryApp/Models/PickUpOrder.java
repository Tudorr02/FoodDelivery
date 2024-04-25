package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class PickUpOrder extends Order {
    private LocalDateTime pickUpTime;

    public PickUpOrder(String orderID, Users customerID, Restaurant restaurantID, ShoppingCart shoppingCartID, String paymentMethod, LocalDateTime orderDate, LocalDateTime pickUpTime) {
        super(orderID, customerID, restaurantID, shoppingCartID, paymentMethod, orderDate);
        this.pickUpTime = pickUpTime;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    @Override
    public String toString() {
        return super.toString() + ", pickUpTime=" + pickUpTime;
    }
}
