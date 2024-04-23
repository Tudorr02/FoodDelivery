package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;

public class Delivery {

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryID='" + deliveryID + '\'' +
                ", deliveryManID='" + deliveryManID + '\'' +
                ", orderID='" + orderID + '\'' +
                ", expectedDate=" + expectedDate +
                '}';
    }

    private String deliveryID;
    private String deliveryManID;
    private String orderID;

    private LocalDateTime expectedDate;

    public Delivery(String deliveryID, String deliveryManID, String orderID, LocalDateTime expectedDate) {
        this.deliveryID = deliveryID;
        this.deliveryManID = deliveryManID;
        this.orderID = orderID;
        this.expectedDate = expectedDate;
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


}
