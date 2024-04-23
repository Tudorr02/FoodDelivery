package com.FoodDeliveryApp.Models;

import java.util.Date;
import java.time.LocalDateTime;

public abstract class Order {
    private String orderID;
    private String customerID;
    private String restaurantID;
    private String shoppingCartID; // Assuming ShoppingCart is a defined class
    private String paymentMethod;
    private LocalDateTime orderDate;

    public Order(String orderID, String customerID, String restaurantID, String shoppingCart, String paymentMethod, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.shoppingCartID = shoppingCart;
        this.paymentMethod = paymentMethod;
        this.orderDate=orderDate;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", restaurantID='" + restaurantID + '\'' +
                ", shoppingCart=" + shoppingCartID +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
