package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DeliveryManInterface  extends javax.swing.JFrame {

    private JFrame frame;
    private JPanel MainPanel;
    private JButton TakeOrderbtn;
    private JButton OrderHistorybtn;

    private JPanel ButtonPanel;
    private JPanel TakeOrderPanel;
    private JScrollPane ScrollPanel;
    private JPanel ParentPanel;

    public DeliveryManInterface() throws Exception{

        frame = new JFrame("DeliveryMan Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        TakeOrderPanel.setVisible(true);
        TakeOrderPanel.setLayout(new BoxLayout(TakeOrderPanel, BoxLayout.Y_AXIS));
        ArrayList<DeliveryOrder> deliveries = new ArrayList<>(DataStorageServices.getInstance().getdOrders());

        for (DeliveryOrder delivery : deliveries) {
            if(delivery.getAsigned().equals(AsignedType.NEPRELUAT)){
                Restaurant res = delivery.getRestaurant();
                Client c = (Client) delivery.getCustomer();
                String buttonLabel = String.format("Restaurant: %s - Location: %s DeliveryAdress: %s - PaymentMethod: %s",  res.getName(),res.getLocation(),c.getDeliveryAddress(),delivery.getPaymentMethod());
                JButton deliveryButton = new JButton(buttonLabel);
                Dimension buttonSize = new Dimension(800, 40);
                deliveryButton.setPreferredSize(buttonSize);
                deliveryButton.setMinimumSize(buttonSize);
                deliveryButton.setMaximumSize(buttonSize);
                deliveryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                TakeOrderPanel.add(deliveryButton);
                TakeOrderPanel.add(Box.createVerticalStrut(10));

            }
        }
        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();
        new DeliveryManInterface();

    }
}
