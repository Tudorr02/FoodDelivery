package com.FoodDeliveryApp.Models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private String shoppingCartID;
    private Map<String, Integer> items; // Holds FoodItems and their quantities
    private double total;

    // Constructor
    public ShoppingCart() {
        this.items = new HashMap<>();
        this.total = 0.0;
    }

    public ShoppingCart(String shoppingCartID, Map<String, Integer> items, double total) {
        this.shoppingCartID = shoppingCartID;
        this.items = items;
        this.total = total;
    }

    // Method to add an item to the cart
    public void addItem(String itemId, int quantity) {
        if (items.containsKey(itemId)) {
            items.put(itemId, items.get(itemId) + quantity); // Increment quantity if already exists
        } else {
            items.put(itemId, quantity); // Add new item with quantity
        }
        updateTotal();
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    // Method to remove an item from the cart
    public void removeItem(String itemId) {
        if (items.containsKey(itemId)) {
            items.remove(itemId); // Remove the item completely
            updateTotal();
        }
    }

    // Method to update the total cost of the cart
    private void updateTotal() {
        total = 0.0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            FoodItem item = FoodItem.getFoodItemById(entry.getKey());
            total += item.getPrice() * entry.getValue(); // Multiply item price by its quantity
        }
    }

    // Getters
    public Map<FoodItem, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
