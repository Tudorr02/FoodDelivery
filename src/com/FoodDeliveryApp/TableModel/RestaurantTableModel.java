package com.FoodDeliveryApp.TableModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import com.FoodDeliveryApp.Models.Restaurant;
import java.awt.*;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RestaurantTableModel extends AbstractTableModel {
    private final List<Restaurant> restaurants;
    private final String[] columnNames = {"Name", "ID", "Location", "Rating", "Price Range"};

    public RestaurantTableModel(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public int getRowCount() {
        return restaurants.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Restaurant restaurant = restaurants.get(rowIndex);
        switch (columnIndex) {
            case 0: return restaurant.getName();
            case 1: return restaurant.getRestaurantID();
            case 2: return restaurant.getLocation();
            case 3: return restaurant.getRating();
            case 4: return restaurant.getPriceRange();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }
}
