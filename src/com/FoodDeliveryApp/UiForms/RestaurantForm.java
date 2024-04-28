package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.TableModel.RestaurantTableModel;
import com.FoodDeliveryApp.Models.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class RestaurantForm extends JFrame {
    private JTable RestaurantsTabel;
    private JPanel RestaurantPanel;
    private RestaurantTableModel tableModel;

    public RestaurantForm(List<Restaurant> restaurants) throws Exception {

        JFrame frame = new JFrame("Restaurant Selection");
        tableModel = new RestaurantTableModel(DataStorageServices.getInstance().getRestaurants());
        RestaurantsTabel.setModel(tableModel);
        frame.add(RestaurantPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setVisible(true);

        // Initialize the table model

        // Add a mouse listener to handle click events on the table
        RestaurantsTabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = RestaurantsTabel.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    int modelRow = RestaurantsTabel.convertRowIndexToModel(row);
                    String id = tableModel.getValueAt(modelRow, 1).toString();  // Assuming ID is at column index 1
                    RestaurantsTabel.setVisible(false);
                }
            }
        });
    }
}
