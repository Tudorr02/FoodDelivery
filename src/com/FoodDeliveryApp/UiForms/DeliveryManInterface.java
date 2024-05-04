package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryConverter;
import com.FoodDeliveryApp.Converters.DeliveryOrderConverter;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.Services.DeliveryServices;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class DeliveryManInterface  extends javax.swing.JFrame {

    private JFrame frame;
    private JPanel MainPanel;
    private JButton TakeOrderbtn;
    private JButton OrderHistorybtn;

    private JPanel ButtonPanel;
    private JPanel TakeOrderPanel;
    private JScrollPane ScrollPanel;
    private JPanel ParentPanel;
    private JPanel OrdersHistory;
    private JScrollPane ScrollPanelHistory;

    public DeliveryManInterface(String deliveryManId) throws Exception{


        frame = new JFrame("DeliveryMan Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        OrdersHistory.setVisible(true);

        TakeOrderPanel.setLayout(new BoxLayout(TakeOrderPanel, BoxLayout.Y_AXIS));
        OrdersHistory.setLayout(new BoxLayout(OrdersHistory, BoxLayout.Y_AXIS));
        List<DeliveryOrder> deliveriesOrders = new ArrayList<DeliveryOrder>(DataStorageServices.getInstance().getdOrders());
        List<Delivery> deliveries = new ArrayList<Delivery>(DataStorageServices.getInstance().getDeliveries());

        for (DeliveryOrder deliveryOrder : deliveriesOrders) {
            if(deliveryOrder.getAsigned().equals(AsignedType.NEPRELUAT)){
                Restaurant res = deliveryOrder.getRestaurant();
                Client c = (Client) deliveryOrder.getCustomer();
                ShoppingCart sc = deliveryOrder.getShoppingCart();
                String buttonLabel = String.format(
                        "<html>Restaurant: %s<br>Location: %s<br>Delivery Address: %s<br>Payment Method: %s<br>Total: %.2f</html>",
                        res.getName(),
                        res.getLocation(),
                        c.getDeliveryAddress(),
                        deliveryOrder.getPaymentMethod(),
                        sc.getTotal()
                );
                JButton deliveryButton = new JButton(buttonLabel);
                Dimension buttonSize = new Dimension(800, 100);
                deliveryButton.setPreferredSize(buttonSize);
                deliveryButton.setMinimumSize(buttonSize);
                deliveryButton.setMaximumSize(buttonSize);
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                deliveryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("fabi e tare");
                        ShoppingCart sc = deliveryOrder.getShoppingCart(); // Assuming getShoppingCart() method exists
                        StringBuilder details = new StringBuilder("<html>");
                        details.append("<h3>Shopping Cart Details:</h3>");
                        for (Map.Entry<FoodItem, Integer> entry : sc.getItems().entrySet()) {
                            details.append(String.format("%s - Quantity: %d<br>", entry.getKey().getName(), entry.getValue()));
                        }
                        details.append(String.format("Total: %.2f</html>", sc.getTotal()));
                        JOptionPane.showMessageDialog(frame, details.toString(), "Shopping Cart Contents", JOptionPane.INFORMATION_MESSAGE);

                        // Show confirmation dialog
                        int response = JOptionPane.showConfirmDialog(frame, "Take this order?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {

                            deliveryOrder.setAsigned(AsignedType.PRELUAT);

                            try {
                                DataStorageServices.getInstance().writeCsv((DataConverter) new DeliveryOrderConverter(), deliveriesOrders);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            DeliveryServices d = new DeliveryServices();

                                d.generateAndRecordDelivery(deliveryManId,deliveryOrder.getOrderID(),deliveryOrder.getOrderDate());
                                deliveryButton.setVisible(false);

                                OrdersHistory.removeAll();
                            try {
                                LoadHistoryButton(deliveryManId);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }


                            // Optionally update the data storage to reflect this change
                            try {
                                DataStorageServices.getInstance().initData();
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                    }
                });

                TakeOrderPanel.add(deliveryButton);
                TakeOrderPanel.add(Box.createVerticalStrut(10));

            }
        }
        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        LoadHistoryButton(deliveryManId);

        TakeOrderbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(TakeOrderPanel);
                ParentPanel.repaint();
                ParentPanel.revalidate();
            }
        });
        OrderHistorybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(ScrollPanelHistory);
                ParentPanel.repaint();
                ParentPanel.revalidate();
                try {
                    OrdersHistory.setVisible(false);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
//                TakeOrderPanel.setVisible(false);
                OrdersHistory.setVisible(true);
            }
        });
    }

    public void LoadHistoryButton(String deliveryManId) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        List<Delivery> ordershistory = DataStorageServices.getInstance().getDeliveries();
        for (Delivery oh : ordershistory) {
            Client c = (Client) oh.getOrder().getCustomer();
            if (oh.getDeliveryMan().getUserID().equals(deliveryManId)) {
                String buttonLabel = String.format("<html><h1>Delivery ID: %s</h1>" +
                                "<br>Restaurant: %s</br>" +
                                "<br>Location: %s</br>" +
                                "<br>Expected Time: %s</br>" +
                                "<br>Delivery Address: %s<br></html>",
                        oh.getDeliveryID(),
                        oh.getOrder().getRestaurant().getName(),
                        oh.getOrder().getRestaurant().getLocation(),
                        oh.getExpectedDate().format(formatter),
                        c.getDeliveryAddress());

                JButton orderHistoryButton = new JButton(buttonLabel);
                orderHistoryButton.setPreferredSize(new Dimension(800, 150));
                orderHistoryButton.setMinimumSize(new Dimension(800, 150));
                orderHistoryButton.setMaximumSize(new Dimension(800, 150));
                orderHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                if (oh.getStatus() == DeliveryStatus.FINISHED) {
                    orderHistoryButton.setVisible(false);
                    orderHistoryButton.setBackground(Color.GREEN);
                    orderHistoryButton.setVisible(true);
                } else {
                    orderHistoryButton.setVisible(false);
                    orderHistoryButton.setBackground(Color.RED);
                    orderHistoryButton.setVisible(true);
                }

                orderHistoryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayOrderDetails(orderHistoryButton,oh);
                    }
                });

                OrdersHistory.add(orderHistoryButton);
                OrdersHistory.add(Box.createVerticalStrut(10));  // Space between buttons

            }
            ;
        }
    }

//    private void displayOrderDetails(Delivery delivery) {
//        ShoppingCart sc = delivery.getOrder().getShoppingCart(); // Ensure Delivery has a method to access ShoppingCart
//        StringBuilder details = new StringBuilder("<html><body style='width: 200px;'>");
//        details.append(String.format("<h3>Details for Delivery ID: %s</h3>", delivery.getDeliveryID()));
//        for (Map.Entry<FoodItem, Integer> entry : sc.getItems().entrySet()) {
//            details.append(String.format("%s - Quantity: %d<br>", entry.getKey().getName(), entry.getValue()));
//        }
//        details.append(String.format("Total: %.2f", sc.getTotal()));
//        details.append("</body></html>");
//        JOptionPane.showMessageDialog(frame, details.toString(), "Order Details", JOptionPane.INFORMATION_MESSAGE);
//    }
private void displayOrderDetails(JButton button,Delivery delivery) {
    ShoppingCart sc = delivery.getOrder().getShoppingCart(); // Ensure Delivery has a method to access ShoppingCart
    StringBuilder details = new StringBuilder("<html>");
    details.append(String.format("<h3>Details for Delivery ID: %s</h3>", delivery.getDeliveryID()));
    for (Map.Entry<FoodItem, Integer> entry : sc.getItems().entrySet()) {
        details.append(String.format("%s - Quantity: %d<br>", entry.getKey().getName(), entry.getValue()));
    }
    details.append(String.format("Total: %.2f", sc.getTotal()));
    details.append("</body></html>");

    JOptionPane.showMessageDialog(frame, details.toString(), "Order Details", JOptionPane.INFORMATION_MESSAGE);
    if (delivery.getStatus() == DeliveryStatus.UNFINISHED) {
        // If the delivery is not finished, ask if they want to mark it as finished
        int response = JOptionPane.showConfirmDialog(frame,"<html><h3>Mark this order as finished?</h3></html>", "Finish Order?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            button.setVisible(false);
            button.setBackground(Color.GREEN);
            button.setVisible(true);
            markDeliveryAsFinished(delivery);
        }
    }
}

    private void markDeliveryAsFinished(Delivery delivery) {
        delivery.setStatus(DeliveryStatus.FINISHED);
        try {
            DeliveryServices d = new DeliveryServices();
            d.updateDeliveryStatus(delivery);
            JOptionPane.showMessageDialog(frame, "Order marked as finished.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to update the order status.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();

        new DeliveryManInterface("D-100001");

    }
}