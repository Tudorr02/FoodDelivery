package com.FoodDeliveryApp.Models;

public class Delivery {

    private String deliveryID;
    private String deliveryManID;
    private String orderID;
    private int expectedTime;

    public Delivery(String deliveryID, String deliveryManID, String orderID, int expectedTime) {
        this.deliveryID = deliveryID;
        this.deliveryManID = deliveryManID;
        this.orderID = orderID;
        this.expectedTime = expectedTime;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryID='" + deliveryID + '\'' +
                ", deliveryManID='" + deliveryManID + '\'' +
                ", orderID='" + orderID + '\'' +
                ", expectedTime=" + expectedTime +
                '}';
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public String getDeliveryManID() {
        return deliveryManID;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getExpectedTime() {
        return expectedTime;
    }
}
