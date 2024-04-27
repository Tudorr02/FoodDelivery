package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.ShoppingCart;
public class ShoppingCartServices {

    private ShoppingCart cart;

    public void addFoodItem(FoodItem foodItem,int quantity){

        cart.addItem(foodItem,quantity);
        System.out.println("Added " + quantity + " " + foodItem.getName() + "(s) to the cart.");
    }


    public void increaseItemQuantity(FoodItem foodItem) {
        cart.incrementItemQuantity(foodItem);
    }

    public void decreaseItemQuantity(FoodItem foodItem) {
        cart.decrementItemQuantity(foodItem);
    }

    public void removeFoodItem(FoodItem foodItem) {
        cart.removeItem(foodItem);
        System.out.println("Removed " + foodItem.getName() + " from the cart.");
    }
}
