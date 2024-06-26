package com.FoodDeliveryApp.Models;

public class FoodItem {

    @Override
    public String toString() {
        return "FoodItem{" +
                "foodItemID=" + foodItemID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    private int foodItemID;
    private String name;
    private String description;
    private double weight; // assuming weight is measured in grams
    private double price; // assuming price is in a standard currency unit

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getFoodID() {
        return foodItemID;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }


    public FoodItem(String name, String description, int foodItemID, double weight, double price) {
        this.name = name;
        this.description = description;
        this.foodItemID = foodItemID;
        this.weight = weight;
        this.price = price;
    }
}
