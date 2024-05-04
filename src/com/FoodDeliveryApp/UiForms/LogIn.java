package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Exceptions.InvalidCredentialsException;
import com.FoodDeliveryApp.Exceptions.UserTypeNotFoundException;
import com.FoodDeliveryApp.Models.UserType;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.UserServices;
import com.FoodDeliveryApp.UiForms.FrameUtils.FrameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame {
    private JPanel LogInPanel;
    private JButton LogInBtn;
    private JLabel UserNameLabel;
    private JLabel PasswordLabel;
    private JTextField UserNameField;
    private JPasswordField PasswordField;
    private JLabel TitleLabel;
    private JButton SignUpbtn;
    private JFrame frame;
    private UserType userType;

    public LogIn() {

        frame = new JFrame("Food Delivery !");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setResizable(false);
        FrameUtils.setGlobalIcon(frame, "res/img/app_icon.jpg");

        ImageIcon originalIcon = new ImageIcon("res/img/log_in.png"); // Update with the correct path
        Image resizedImage = originalIcon.getImage().getScaledInstance(120, 120, Image.SCALE_AREA_AVERAGING); // Adjust dimensions
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        TitleLabel.setIcon(resizedIcon);
        TitleLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Adjust text position relative to the icon
        TitleLabel.setVerticalTextPosition(SwingConstants.TOP);


        LogInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String userName = UserNameField.getText();
                    String password = new String(PasswordField.getPassword());

                    UserServices userService = new UserServices();
                    Users user = userService.LogInUser(userName, password);

                    if (user == null) {
                        throw new InvalidCredentialsException("Invalid username or password.");
                    }

                    userType = userService.getUserType(user);
                    frame.dispose();
                    frame.setVisible(false);
                    switch (userType) {
                        case CLIENT:
                            new ClientInterface(user.getUserID());
                            System.out.println("Client logged in");
                            break;
                        case ADMIN:
                            // Open admin panel
                            System.out.println("Admin logged in");
                            break;
                        case DELIVERYMAN:
                            // Open deliveryman panel
                            new DeliveryManInterface(user.getUserID());
                            System.out.println("Deliveryman logged in");
                            break;
                        default:
                            throw new UserTypeNotFoundException("User not found.");
                    }

                } catch (InvalidCredentialsException | UserTypeNotFoundException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.add(LogInPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        SignUpbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
                new SignUp();
            }
        });
    }
}
