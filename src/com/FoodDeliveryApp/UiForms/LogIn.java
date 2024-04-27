package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Services.ClientService;

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
    private JFrame frame;

    public LogIn() throws HeadlessException {

        frame = new JFrame("Food Delivery !");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setResizable(false);



        frame.add(LogInPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        LogInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if ( new ClientService().LogInAsClient(UserNameField.getText(),PasswordField.getText())){
                        System.out.println("Successfully logged in");
                        frame.setVisible(false);
                    }

                    else
                        System.out.println("Failed to log in");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
