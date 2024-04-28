package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.UserType;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.UserServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.Optional;


public class LogIn extends JFrame {
    private JPanel LogInPanel;
    private JButton LogInBtn;
    private JLabel UserNameLabel;
    private JLabel PasswordLabel;
    private JTextField UserNameField;
    private JPasswordField PasswordField;
    private JLabel TitleLabel;
    private JFrame frame;
    private UserType userType;

    public LogIn() throws HeadlessException {

        frame = new JFrame("Food Delivery !");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setResizable(false);

        LogInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String UserName = UserNameField.getText();
                    String Password = new String(PasswordField.getPassword());
                    Users user = new UserServices().LogInUser(UserName, Password);

                   if(user !=null) {
                       System.out.println("User logged in");

                       userType = new UserServices().getUserType(user);

                       if( userType.equals(UserType.CLIENT)){
                           // open client Panel
                           System.out.println("client logged in");
                       }else if(userType.equals(UserType.ADMIN)){
                           // open admin Panel
                           System.out.println("admin logged in");
                       } else if (userType.equals(UserType.DELIVERYMAN)) {
                           // open deliveryman Panel
                           System.out.println("deliveryman logged in");
                       }


                   }
                   else{
                       System.out.println("Did not log in");
                       userType = UserType.FAILED;
                   }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        frame.add(LogInPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }




}
