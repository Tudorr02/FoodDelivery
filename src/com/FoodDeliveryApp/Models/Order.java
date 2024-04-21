package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Order {
    private String orderID;
    private String customerID;
    private String restaurantID;
    private ShoppingCart shoppingCart; // Assuming ShoppingCart is a defined class
    private String paymentMethod;
    private LocalDateTime orderDate;
}
