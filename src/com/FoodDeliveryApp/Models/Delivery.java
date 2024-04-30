package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;

public class Delivery {

    private String deliveryID;
    private DeliveryMan deliveryMan;
    private DeliveryOrder order;
    private LocalDateTime expectedDate;
    private DeliveryStatus status;

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryID='" + deliveryID + '\'' +
                ", deliveryMan=" + deliveryMan +
                ", order=" + order +
                ", expectedDate=" + expectedDate +
                ", status=" + status +
                '}';
    }

    public Delivery(String deliveryID, DeliveryMan deliveryMan, DeliveryOrder order, LocalDateTime expectedDate, DeliveryStatus status) {
        this.deliveryID = deliveryID;
        this.deliveryMan = deliveryMan;
        this.order = order;
        this.expectedDate = expectedDate;
        this.status = status;
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




    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public void setOrder(DeliveryOrder order) {
        this.order = order;
    }

    public void setExpectedDate(LocalDateTime expectedDate) {
        this.expectedDate = expectedDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
