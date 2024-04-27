package com.FoodDeliveryApp.Models;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private String shoppingCartID;
    private Map<FoodItem, Integer> items; // Holds FoodItems and their quantities
    private double total;

    // Constructor
    public ShoppingCart() {
        this.items = new HashMap<>();
        this.total = 0.0;
    }

    // Method to add an item to the cart
    public void addItem(FoodItem item, int quantity) {
        // Check if the item already exists in the cart
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity); // Increment quantity if already exists
        } else {
            items.put(item, quantity); // Add new item with quantity
        }
        updateTotal();
    }

    public void incrementItemQuantity(FoodItem item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
        updateTotal();
        System.out.println("Increased " + item.getName() + " by 1. Total now: " + items.get(item));
    }

    public void decrementItemQuantity(FoodItem item) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity > 1) {
                items.put(item, currentQuantity - 1);
                System.out.println("Decreased " + item.getName() + " by 1. Total now: " + items.get(item));
            } else {
                items.remove(item);
                System.out.println(item.getName() + " removed from the cart.");
            }
            updateTotal();
        } else {
            System.out.println("Error: No " + item.getName() + " in the cart to decrease.");
        }
    }

    public ShoppingCart(String shoppingCartID, Map<FoodItem, Integer> items, double total) {
        this.shoppingCartID = shoppingCartID;
        this.items = items;
        this.total = total;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    // Method to remove an item from the cart
    public void removeItem(FoodItem item) {
        if (items.containsKey(item)) {
            items.remove(item); // Remove the item completely
            updateTotal();
        }
    }

    // Method to update the total cost of the cart
    private void updateTotal() {
        total = 0.0;
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue(); // Multiply item price by its quantity
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
                "items=" + items.keySet()+ " quantity=" + items.values() +
                ", total=" + total +
                '}';
    }
}
