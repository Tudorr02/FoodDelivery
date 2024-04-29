package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;

public class Delivery {

    private String deliveryID;
    private DeliveryMan deliveryMan;
    private DeliveryOrder order;
    private LocalDateTime expectedDate;

    public Delivery(String deliveryID, DeliveryMan deliveryMan, DeliveryOrder order, LocalDateTime expectedDate) {
        this.deliveryID = deliveryID;
        this.deliveryMan = deliveryMan;
        this.order = order;
        this.expectedDate = expectedDate;
    }


    public String getDeliveryID() {
        return deliveryID;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public Order getOrder() {
        return order;
    }
    public LocalDateTime getExpectedDate() { return expectedDate; }

     @Override
    public String toString() {
        return "Delivery{" +
                "deliveryID='" + deliveryID + '\'' +
                ", deliveryManID='" + deliveryMan + '\'' +
                ", orderID='" + order + '\'' +
                ", expectedDate=" + expectedDate +
                '}';
    }


    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public void setOrder(DeliveryOrder order) {
        this.order = order;
    }
}
