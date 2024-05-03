package com.FoodDeliveryApp.Models;

import com.FoodDeliveryApp.Models.AsignedType;
import java.time.LocalDateTime;
import java.util.Date;

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
