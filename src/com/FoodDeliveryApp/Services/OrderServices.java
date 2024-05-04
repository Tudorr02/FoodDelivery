package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Converters.*;
import com.FoodDeliveryApp.Models.*;

import javax.xml.crypto.Data;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;


public class OrderServices {

    Order order;

    public void generateDeliveryOrder(String userID, Restaurant restaurant, ShoppingCart shoppingCart,PaymentMethod pMethod,int deliveryDiscount) throws Exception {

        String prefix="DO-";
        int orderNumber;
        String method;


        if(pMethod.equals(PaymentMethod.CARD))
            method="card";
        else
            method="cash";

        orderNumber=getMaxOrderNumberId(OrderType.DELIVERY_ORDER);

        String orderID = prefix + orderNumber;
        shoppingCart.generateCartID();
        List<ShoppingCart> carts= DataStorageServices.getInstance().getShoppingCarts();
        carts.add(shoppingCart);
        DataStorageServices.getInstance().writeCsv((DataConverter)new ShoppingCartConverter(),carts);

        Users user = DataStorageServices.getInstance().getUserById(userID);
        LocalDateTime orderDate = LocalDateTime.now();
        List<DeliveryOrder> deliveryOrders =DataStorageServices.getInstance().getdOrders();
        deliveryOrders.add(new DeliveryOrder(orderID,user,restaurant,shoppingCart,method,orderDate,deliveryDiscount,AsignedType.NEPRELUAT));
        DataStorageServices.getInstance().writeCsv((DataConverter)new DeliveryOrderConverter(),deliveryOrders );

    }

    public void generatePickUpOrder(String userID, Restaurant restaurant, ShoppingCart shoppingCart,PaymentMethod pMethod) throws Exception {

        String prefix="P-";
        int orderNumber;
        String method;


        if(pMethod.equals(PaymentMethod.CARD))
            method="card";
        else
            method="cash";

        orderNumber=getMaxOrderNumberId(OrderType.PICKUP_ORDER);

        String orderID = prefix + orderNumber;
        shoppingCart.generateCartID();
        List<ShoppingCart> carts= DataStorageServices.getInstance().getShoppingCarts();
        carts.add(shoppingCart);
        DataStorageServices.getInstance().writeCsv((DataConverter)new ShoppingCartConverter(),carts);

        Users user = DataStorageServices.getInstance().getUserById(userID);
        LocalDateTime orderDate = LocalDateTime.now();

        int randomNumber = new Random().nextInt(60)+20;

        LocalDateTime pickUpDate = orderDate.plusMinutes(randomNumber);
        List<PickUpOrder> puOrders =DataStorageServices.getInstance().getPuOrders();
        puOrders.add(new PickUpOrder(orderID,user,restaurant,shoppingCart,method,orderDate,pickUpDate));
        DataStorageServices.getInstance().writeCsv((DataConverter)new PickUpOrdersConverter(),puOrders );

    }

    public ArrayList<?> getClientOrders(String clientID) throws Exception {

        DataStorageServices.getInstance().initData();
        List<DeliveryOrder> duOrders = (List)DataStorageServices.getInstance().getdOrders();

        List<PickUpOrder> puOrders = (List)DataStorageServices.getInstance().getPuOrders();

        List<Order> orders=new ArrayList<>();

        orders.addAll(duOrders);
        orders.addAll(puOrders);

        return (ArrayList<?>) orders.stream()
                .filter(order->clientID.equals(order.getCustomer().getUserID()))
                .collect(Collectors.toList());

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

    public String getOrderLabel ( String orderID) throws Exception {

        String prefix = orderID.split("-")[0];
        Order order = DataStorageServices.getInstance().getOrderById(orderID);
        ShoppingCart sc = order.getShoppingCart();
        Restaurant res = order.getRestaurant();
        String paymentMethod = order.getPaymentMethod();
        LocalDateTime orderDate = order.getOrderDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDate = orderDate.format(formatter);

        String orderLabel = MessageFormat.format("Restaurant: {0}<br>" +
                                                "Order Date: {1}<br>" +
                                                "Payment Method: {2}<br>" +
                                                "Total: {3}",
                                        res.getName(),
                                        formattedDate,
                                        paymentMethod,
                                        sc.getTotal());

        return orderLabel;

    }

    public OrderStatus getOrderStatus (Order order) throws Exception {
        if (order instanceof PickUpOrder){
             LocalDateTime now = LocalDateTime.now();
             LocalDateTime pickupTime = ((PickUpOrder) order).getPickUpTime();

            if (now.isAfter(pickupTime)) {
                return OrderStatus.PickUp_READY;
            } else {
                return OrderStatus.PickUp_InProgress;
            }
        }else if (order instanceof DeliveryOrder){

            DeliveryOrder deliveryOrder = new DeliveryServices().getDeliveryOrderByOrderID(order.getOrderID()) ;

            return switch (deliveryOrder.getAsigned()) {
                case AsignedType.NEPRELUAT -> OrderStatus.Delivery_InProgress;
                case AsignedType.PRELUAT -> OrderStatus.Delivery_InDelivery;
                case AsignedType.PREDAT -> OrderStatus.Delivery_Completed;
            };


        }
        return OrderStatus.UNKNOWN;
    }

    public void markDeliveryOrderAsCompleted(DeliveryOrder order) throws Exception {
        DeliveryOrder dOrder = (DeliveryOrder) DataStorageServices.getInstance().getOrderById(order.getOrderID());

        dOrder.setStatus(AsignedType.PREDAT);

        List<DeliveryOrder>dOrders = DataStorageServices.getInstance().getdOrders();
        DataStorageServices.getInstance().writeCsv((DataConverter)new DeliveryOrderConverter(),dOrders);


    }
    public static void main(String[] args) throws Exception {
        //System.out.println(new OrderServices().getMaxOrderNumberId(OrderType.DELIVERY_ORDER));
        System.out.println(new OrderServices().getMaxOrderNumberId(OrderType.DELIVERY_ORDER));

    }
}
