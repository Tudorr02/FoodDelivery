package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class ClientInterface extends javax.swing.JFrame {
    private JList restaurantJList;
    private JPanel MainPanel;
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private ClientInterface() throws Exception {

        frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setResizable(false);

        listModel = new DefaultListModel<>();
        for( Restaurant res : DataStorageServices.getInstance().getRestaurants())
            listModel.addElement(res.getName()+" "+res.getLocation());

        restaurantJList.setModel(listModel);





        frame.add(MainPanel);



        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();
        new ClientInterface();
    }

}
