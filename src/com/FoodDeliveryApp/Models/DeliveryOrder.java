package com.FoodDeliveryApp.Models;

import java.time.LocalDateTime;

public class DeliveryOrder extends Order{
    private int deliveryDiscountPercent;
    private AsignedType asigned;


    public DeliveryOrder(String orderID, Users customerID, Restaurant restaurantID, ShoppingCart shoppingCartID, String paymentMethod, LocalDateTime orderDate, int deliveryDiscountPercent,AsignedType asigned) {
        super(orderID, customerID, restaurantID, shoppingCartID, paymentMethod, orderDate);
        this.deliveryDiscountPercent = deliveryDiscountPercent;
        this.asigned = (asigned == null) ? AsignedType.NEPRELUAT : asigned;

    }

    public int getDeliveryDiscountPercent() {
        return deliveryDiscountPercent;
    }


    public AsignedType getAsigned() {
        return asigned;
    }
    public void setStatus(AsignedType status){
        this.asigned = status;
    }


    @Override
    public String toString() {
        return super.toString()+ "DeliveryOrder{" +
                "deliveryDiscountPercent=" + deliveryDiscountPercent +
                ", asigned=" + asigned +
                '}';
    }

    public void setDeliveryDiscountPercent(int deliveryDiscountPercent) {
        this.deliveryDiscountPercent = deliveryDiscountPercent;
    }

    public void setAsigned(AsignedType asigned) {
        this.asigned = asigned;
    }
}
