package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryOrderConverter;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.Services.DeliveryManServices;
import com.FoodDeliveryApp.Services.DeliveryServices;
import com.FoodDeliveryApp.UiForms.FrameUtils.FrameUtils;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JButton Accountbtn;
    private JPanel AcoountPanel;
    private JLabel PhoneNumberlabel;
    private JLabel UserNameLabel;
    private JTextField PhoneNumberText;
    private JTextField UserNameText;
    private JButton Modifybtn;
    private JLabel VehicleLabel;
    private JTextField VehicleText;
    private JLabel PasswordLabel;
    private JTextField PasswordText;
    private JTextField CompletedOrdersText;
    private JLabel CompletedOrdersLabel;
    private JLabel RatingLabel;
    private JProgressBar RatingProgressBar;
    private JButton SignOutbtn;

    public DeliveryManInterface(String deliveryManId) throws Exception{


        frame = new JFrame("DeliveryMan Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameUtils.setGlobalIcon(frame, "res/img/app_icon.jpg");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        OrdersHistory.setVisible(true);

        ImageIcon icon = new ImageIcon("res/img/account_icon1.png");
        DeliveryServices d = new DeliveryServices();

        Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        Accountbtn.setIcon(icon);


        ImageIcon signOutIcon = new ImageIcon("res/img/sign_out.png");
        Image signOutImg = signOutIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        signOutIcon = new ImageIcon(signOutImg);
        SignOutbtn.setIcon(signOutIcon);
        SignOutbtn.setBorderPainted(false);
        SignOutbtn.setFocusPainted(false);
        SignOutbtn.setContentAreaFilled(false);
        SignOutbtn.setMargin(new Insets(0, 0, 0, 0));
        SignOutbtn.setToolTipText("Sign Out");

        Accountbtn.setBorderPainted(false);
        Accountbtn.setFocusPainted(false);
        Accountbtn.setContentAreaFilled(false);
        Accountbtn.setMargin(new Insets(0, 0, 0, 0));
        Accountbtn.setToolTipText("Account Settings");
        Accountbtn.setText(DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getUserName());

        float rating = (float) DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getRating();
        RatingProgressBar.setMinimum(0);
        RatingProgressBar.setMaximum(5);
        RatingProgressBar.setValue((int) rating);
        RatingProgressBar.setStringPainted(true);
        RatingProgressBar.setString(String.format("%.1f", rating));
        updateProgressBarColor(RatingProgressBar, rating);

        UserNameText.setText(DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getUserName());
        PhoneNumberText.setText(DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getPhoneNumber());
        VehicleText.setText(DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getVehicle());
        PasswordText.setText(DataStorageServices.getInstance().getDeliveryManById(deliveryManId).getPassword());

        CompletedOrdersText.setText(String.valueOf(d.getNumberOfCompletedOrders(deliveryManId)));

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
                ParentPanel.add(ScrollPanel);
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

        Accountbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(AcoountPanel);
                ParentPanel.repaint();
                ParentPanel.revalidate();
            }
        });

        Modifybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedUsername = UserNameText.getText();
                String updatedPhoneNumber = PhoneNumberText.getText();
                String updatedVehicle = VehicleText.getText();
                String updatedPassword = PasswordText.getText();

                DeliveryManServices deliveryManServices = new DeliveryManServices();
                boolean isUpdated = deliveryManServices.updateDeliveryManDetails(deliveryManId, updatedUsername, updatedPhoneNumber, updatedVehicle,updatedPassword);

                if (isUpdated) {
                    JOptionPane.showMessageDialog(frame, "Information updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update information.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        SignOutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogIn();


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
            CompletedOrdersText.setText(String.valueOf(d.getNumberOfCompletedOrders(delivery.getDeliveryMan().getUserID())));
            JOptionPane.showMessageDialog(frame, "Order marked as finished.", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to update the order status.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void updateProgressBarColor(JProgressBar progressBar, float rating) {
        if (rating < 2) {
            progressBar.setForeground(Color.RED);
        } else if (rating ==1.3) {
            progressBar.setForeground(Color.YELLOW);
        } else {
            progressBar.setForeground(Color.GREEN);
        }
    }


    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();

        new DeliveryManInterface("D-100001");

    }
}