package com.FoodDeliveryApp.Models;

public class FoodItem {
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodID() {
        return foodItemID;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }


    private String foodItemID;
    private String name;
    private String description;
    private double weight; // assuming weight is measured in grams
    private double price; // assuming price is in a standard currency unit

    public FoodItem(String name, String description, String foodItemID, double weight, double price) {
        this.name = name;
        this.description = description;
        this.foodItemID = foodItemID;
        this.weight = weight;
        this.price = price;
    }
}
