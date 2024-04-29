package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryConverter;
import com.FoodDeliveryApp.Converters.DeliveryOrderConverter;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.Services.DeliveryServices;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;

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
                String buttonLabel = String.format("Restaurant: %s - Location: %s DeliveryAdress: %s - PaymentMethod: %s",  res.getName(),res.getLocation(),c.getDeliveryAddress(),deliveryOrder.getPaymentMethod());
                JButton deliveryButton = new JButton(buttonLabel);
                Dimension buttonSize = new Dimension(800, 40);
                deliveryButton.setPreferredSize(buttonSize);
                deliveryButton.setMinimumSize(buttonSize);
                deliveryButton.setMaximumSize(buttonSize);
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                deliveryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Show confirmation dialog
                        int response = JOptionPane.showConfirmDialog(frame, "Take this order?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {

                            deliveryOrder.setAsigned(AsignedType.PRELUAT);
                            try {
                                DataStorageServices.getInstance().writeCsv((DataConverter) new DeliveryOrderConverter(), deliveriesOrders);
                                DeliveryServices d = new DeliveryServices();
                                d.generateAndRecordDelivery(deliveryManId,deliveryOrder.getOrderID(),deliveryOrder.getOrderDate());
                                refreshOrderPanel();

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


//        OrdersHistory.removeAll();
        DataStorageServices.getInstance().initData();

        List<Delivery> ordershistory = DataStorageServices.getInstance().getDeliveries();
        for (Delivery oh : ordershistory) {
            if (oh.getDeliveryMan().getUserID().equals(deliveryManId)) {
                String buttonLabel = String.format("Delivery ID: %s, Expected Time: %s",
                        oh.getDeliveryID(),
                        oh.getExpectedDate().toString());

                JButton deliveryButton = new JButton(buttonLabel);
                deliveryButton.setPreferredSize(new Dimension(800, 40));
                deliveryButton.setMinimumSize(new Dimension(800, 40));
                deliveryButton.setMaximumSize(new Dimension(800, 40));
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                OrdersHistory.add(deliveryButton);
                OrdersHistory.add(Box.createVerticalStrut(10));  // Space between buttons

            }
  ;

        }


//        OrdersHistory.revalidate();
//        OrdersHistory.repaint();

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
                    refreshOrderHistoryPanel(deliveryManId);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
//                TakeOrderPanel.setVisible(false);
              OrdersHistory.setVisible(true);
            }
        });
    }

    public void refreshOrderHistoryPanel(String deliveryManId) throws Exception {
       // List<Delivery> ordershistory = ;
        OrdersHistory.removeAll();
        for (Delivery oh : DataStorageServices.getInstance().getDeliveries()) {


            if (oh.getDeliveryMan().getUserID().equals(deliveryManId)) {
                String buttonLabel = String.format("Delivery ID: %s, Expected Time: %s",
                        oh.getDeliveryID(),
                        oh.getExpectedDate().toString());

                JButton deliveryButton = new JButton(buttonLabel);
                deliveryButton.setPreferredSize(new Dimension(800, 40));
                deliveryButton.setMinimumSize(new Dimension(800, 40));
                deliveryButton.setMaximumSize(new Dimension(800, 40));
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                OrdersHistory.add(deliveryButton);
                System.out.println("Button added");
                OrdersHistory.add(Box.createVerticalStrut(10));  // Space between buttons

            }
            ;

        }
    }

    public void refreshOrderPanel() throws Exception {
        // Clear existing content
        TakeOrderPanel.removeAll();

        // Get updated list of orders
        List<DeliveryOrder> deliveries = new ArrayList<>(DataStorageServices.getInstance().getdOrders());

        for (DeliveryOrder delivery : deliveries) {
            if (delivery.getAsigned().equals(AsignedType.NEPRELUAT)) {
                Restaurant res = delivery.getRestaurant();
                Client c = (Client) delivery.getCustomer();
                String buttonLabel = String.format("Restaurant: %s - Location: %s DeliveryAddress: %s - PaymentMethod: %s", res.getName(), res.getLocation(), c.getDeliveryAddress(), delivery.getPaymentMethod());
                JButton deliveryButton = new JButton(buttonLabel);
                deliveryButton.setPreferredSize(new Dimension(800, 40));
                deliveryButton.setMinimumSize(new Dimension(800, 40));
                deliveryButton.setMaximumSize(new Dimension(800, 40));
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                deliveryButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(frame, "Take this order?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            delivery.setAsigned(AsignedType.PRELUAT);
                            try {
                                DataStorageServices.getInstance().writeCsv((DataConverter) new DeliveryOrderConverter(), deliveries);
                                refreshOrderPanel();  // Refresh the panel to show updates
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(frame, "Error updating data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                });

                TakeOrderPanel.add(deliveryButton);
                TakeOrderPanel.add(Box.createVerticalStrut(10));
            }
        }
        TakeOrderPanel.revalidate();
        TakeOrderPanel.repaint();
    }



    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();

       new DeliveryManInterface("D-100001");

    }
}
