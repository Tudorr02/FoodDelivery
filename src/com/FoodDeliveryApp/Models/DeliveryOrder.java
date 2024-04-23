package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;
import java.util.Date;

public class DeliveryOrder extends Order{
    private int deliveryDiscountPercent;

    public DeliveryOrder(String orderID, String customerID, String restaurantID, String shoppingCartID, String paymentMethod, LocalDateTime orderDate, int deliveryDiscountPercent) {
        super(orderID, customerID, restaurantID, shoppingCartID, paymentMethod, orderDate);
        this.deliveryDiscountPercent = deliveryDiscountPercent;
    }

    public int getDeliveryDiscountPercent() {
        return deliveryDiscountPercent;
    }

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "deliveryDiscountPercent=" + deliveryDiscountPercent +
                '}';
    }
}
