package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryOrderConverter;
import com.FoodDeliveryApp.Models.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;


public class OrderServices {

    Order order;

    public void generateDeliveryOrder(String userID, OrderType oType, Restaurant restaurant, ShoppingCart shoppingCart,PaymentMethod pMethod,int deliveryDiscount) throws Exception {

        String prefix;
        int orderNumber;
        String method;

        if(oType.equals(OrderType.PICKUP_ORDER)){
            prefix = "P-";
        }
        else{
            prefix = "DO-";
        }

        if(pMethod.equals(PaymentMethod.CARD))
            method="card";
        else
            method="cash";

        orderNumber=getMaxOrderNumberId(oType);

        String orderID = prefix + orderNumber;

        Users user = DataStorageServices.getInstance().getUserById(userID);
        //String orderDateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
        LocalDateTime orderDate = LocalDateTime.now();
        List<DeliveryOrder> deliveryOrders =DataStorageServices.getInstance().getdOrders();
        deliveryOrders.add(new DeliveryOrder(orderID,user,restaurant,shoppingCart,method,orderDate,deliveryDiscount,AsignedType.NEPRELUAT));
        DataStorageServices.getInstance().writeCsv((DataConverter)new DeliveryOrderConverter(),deliveryOrders );
    }


    public int getMaxOrderNumberId(OrderType orderType) throws Exception {

        int maxNumber =0;
        DataStorageServices.getInstance().initData();

        List<Order> orders = new ArrayList<>();

        if(orderType.equals(OrderType.PICKUP_ORDER)){
            orders= (List<Order>) (List<?>) DataStorageServices.getInstance().getPuOrders();
        }
        else
            orders= (List<Order>) (List<?>) DataStorageServices.getInstance().getdOrders();


        for(Order order : orders){
            String orderID = order.getOrderID().split("-")[1];
            int orderNumber = Integer.parseInt(orderID);
            if(orderNumber>maxNumber){
                maxNumber=orderNumber;
            }

        }

        return maxNumber+1;
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(new OrderServices().getMaxOrderNumberId(OrderType.DELIVERY_ORDER));
        System.out.println(new OrderServices().getMaxOrderNumberId(OrderType.DELIVERY_ORDER));

    }
}
