package com.FoodDeliveryApp.Models;

import java.util.Date;
import java.time.LocalDateTime;

public abstract class Order {

    private String orderID;
    private Users customer;
    private Restaurant restaurant;
    private ShoppingCart shoppingCart; // Assuming ShoppingCart is a defined class
    private String paymentMethod;
    private LocalDateTime orderDate;

    public String getOrderID() {
        return orderID;
    }

    public Users getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }





    public Order(String orderID, Users customerID, Restaurant restaurantID, ShoppingCart shoppingCartID, String paymentMethod, LocalDateTime orderDate) {
        this.orderID = orderID;
        this.customer = customerID;
        this.restaurant = restaurantID;
        this.shoppingCart = shoppingCartID;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customer=" + (customer != null ? customer.toString() : "null") +
                ", restaurant=" + (restaurant != null ? restaurant.toString() : "null") +
                ", shoppingCart=" + (shoppingCart != null ? shoppingCart.toString() : "null") +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

}