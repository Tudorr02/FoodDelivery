package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Services.ClientServices;
import com.FoodDeliveryApp.Services.DeliveryManServices;
import com.FoodDeliveryApp.UiForms.FrameUtils.FrameUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private JRadioButton DeliveryManbtn;
    private JRadioButton Clientbtn;
    private JTextField LastNameCText;
    private JTextField FirstNameCText;
    private JTextField PasswordCText;
    private JTextField PhoneNumberCText;
    private JTextField DeliveryAdressText;
    private JLabel LastNameCLabel;
    private JLabel FirstNameCLabel;
    private JLabel UserNameCLabel;
    private JLabel PasswordCLabel;
    private JTextField UserNameCText;
    private JLabel PhoneNumberC;
    private JLabel DeliveryAdressLabel;
    private JTextField EmailText;
    private JLabel EmailLabel;
    private JButton SignUpbtn;
    private JPanel SelectPanel;
    private JLabel SelectLabel;
    private JButton BackToLoginbtn;
    private ButtonGroup buttonGroup;

    public SignUp() {
        frame = new JFrame("Sign Up!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrameUtils.setGlobalIcon(frame, "res/img/app_icon.jpg");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        SelectPanel.setVisible(true);
        SelectLabel.setVisible(true);
        ClientPanel.setVisible(false);
        DeliveryManPanel.setVisible(false);
        frame.setVisible(true);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(DeliveryManbtn);
        buttonGroup.add(Clientbtn);

        ImageIcon goBackIcon = new ImageIcon("res/img/back_icon.jpg");
        Image goBackImg = goBackIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        goBackIcon = new ImageIcon(goBackImg);
        BackToLoginbtn.setIcon(goBackIcon);
        BackToLoginbtn.setBorderPainted(false);
        BackToLoginbtn.setFocusPainted(false);
        BackToLoginbtn.setContentAreaFilled(false);
        BackToLoginbtn.setMargin(new Insets(0, 0, 0, 0));
        BackToLoginbtn.setToolTipText("Go back to login");

        DeliveryManbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryManPanel.setVisible(true);
                ClientPanel.setVisible(false);
                SelectLabel.setVisible(false);
                checkFields();
            }
        });

        Clientbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeliveryManPanel.setVisible(false);
                ClientPanel.setVisible(true);
                SelectLabel.setVisible(false);
                checkFields();
            }
        });

        // Add DocumentListener to required fields
        addFieldListener(FirstNameText);
        addFieldListener(LastNameText);
        addFieldListener(UserNameText);
        addFieldListener(PasswordText);
        addFieldListener(PhoneNumberText);
        addFieldListener(NationalityText);
        addFieldListener(VehicleText);
        addFieldListener(DeliveryAdressText);
        addFieldListener(PasswordCText);
        addFieldListener(PhoneNumberCText);

        SignUpbtn.setEnabled(false); // Initially disabled

        SignUpbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DeliveryManbtn.isSelected()) {
                    // Collect data from text fields
                    String firstName = FirstNameText.getText();
                    String lastName = LastNameText.getText();
                    String userName = UserNameText.getText();
                    String password = PasswordText.getText();
                    String phoneNumber = PhoneNumberText.getText();
                    String nationality = NationalityText.getText();
                    String vehicle = VehicleText.getText();

                    // Call service to add new delivery man
                    DeliveryManServices deliveryManServices = new DeliveryManServices();
                    boolean isAdded = deliveryManServices.addNewDeliveryMan(firstName, lastName, userName, password, phoneNumber, nationality, vehicle);

                    if (isAdded) {
                        JOptionPane.showMessageDialog(frame, "Delivery man added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        resetForm();

                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to add delivery man.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if(Clientbtn.isSelected())
                {
                    String firstName = FirstNameCText.getText();
                    String lastName = LastNameCText.getText();
                    String userName = UserNameCText.getText();
                    String password = PasswordCText.getText();
                    String phoneNumber = PhoneNumberCText.getText();
                    String deliveryAddress = DeliveryAdressText.getText();
                    String email = EmailText.getText();

                    // Call service to add new client
                    ClientServices clientServices = new ClientServices();
                    boolean isAdded = clientServices.addNewClient(firstName, lastName, userName, password, phoneNumber, deliveryAddress, email);

                    if (isAdded) {
                        JOptionPane.showMessageDialog(frame, "Client added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        resetForm();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to add client.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        BackToLoginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogIn();
            }
        });
    }

    private void addFieldListener(JTextField field) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFields();
            }
        });
    }

    private void checkFields() {
        boolean enabled = false;

        if (DeliveryManbtn.isSelected()) {
            enabled = !FirstNameText.getText().isEmpty() &&
                    !LastNameText.getText().isEmpty() &&
                    !UserNameText.getText().isEmpty() &&
                    !PasswordText.getText().isEmpty() &&
                    !PhoneNumberText.getText().isEmpty() &&
                    !NationalityText.getText().isEmpty() &&
                    !VehicleText.getText().isEmpty();
        } else if (Clientbtn.isSelected()) {
            enabled = !FirstNameCLabel.getText().isEmpty() &&
                    !LastNameCLabel.getText().isEmpty() &&
                    !UserNameCLabel.getText().isEmpty() &&
                    !PasswordCLabel.getText().isEmpty() &&
                    !PhoneNumberC.getText().isEmpty() &&
                    !DeliveryAdressText.getText().isEmpty();
        }

        SignUpbtn.setEnabled(enabled);
    }

    private void resetForm() {
        // Clear text fields
        FirstNameText.setText("");
        LastNameText.setText("");
        UserNameText.setText("");
        PasswordText.setText("");
        PhoneNumberText.setText("");
        NationalityText.setText("");
        VehicleText.setText("");

        PasswordCText.setText("");
        PhoneNumberCText.setText("");
        DeliveryAdressText.setText("");

        // Reset radio buttons
        buttonGroup.clearSelection();

        buttonGroup.clearSelection();

        // Reset panels visibility
        DeliveryManPanel.setVisible(false);
        ClientPanel.setVisible(false);
        SelectLabel.setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}