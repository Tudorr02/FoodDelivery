package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.UiForms.FrameUtils.FrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {

    private JFrame frame;
    private JPanel MainPanel;
    private JPanel ButtonPanel;
    private JPanel SignUpPanel;
    private JPanel DeliveryManPanel;
    private JPanel ClientPanel;
    private JTextField FirstNameText;
    private JTextField LastNameText;
    private JTextField UserNameText;
    private JTextField PasswordText;
    private JTextField PhoneNumberText;
    private JTextField NationalityText;
    private JLabel FirstNameLabel;
    private JLabel LastNameLabel;
    private JLabel UserNameLabel;
    private JLabel PasswordLabel;
    private JLabel PhoneNumberLabel;
    private JLabel NationalityLabel;
    private JTextField VehicleText;
    private JLabel VehicleLabel;
    private JRadioButton DeliveryMan;
    private JRadioButton Client;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField PasswordCText;
    private JTextField PhoneNumberCText;
    private JTextField DeliveryAdressText;
    private JLabel LastNameCLabel;
    private JLabel FirstNameCLabel;
    private JLabel UserNameCLabel;
    private JLabel PasswordCLabel;
    private JPasswordField passwordField1;
    private JLabel PhoneNumberC;
    private JLabel DeliveryAdressLabel;
    private JTextField EmailText;
    private JLabel EmailLabel;

    public SignUp(){
        frame = new JFrame("DeliveryMan Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameUtils.setGlobalIcon(frame, "res/img/app_icon.jpg");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        DeliveryMan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
