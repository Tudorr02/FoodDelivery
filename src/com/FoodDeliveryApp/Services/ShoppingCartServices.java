package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.ShoppingCart;

import java.util.Map;

public class ShoppingCartServices {

    private ShoppingCart cart;

    public ShoppingCartServices(ShoppingCart cart) {
        this.cart = cart;
    }

    public void addFoodItem(FoodItem foodItem, int quantity){

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

    public int getCartSize() {
        return cart.getItems().size();
    }

    public Map<FoodItem, Integer> getCartItems() {
        return cart.getItems();
    }

    public double getTotalItemPrice (Map.Entry<FoodItem, Integer> entry){
            return entry.getKey().getPrice() * entry.getValue();
    }

    public void modifyQuantity(FoodItem foodItem, int quantity){
      cart.updateQuantity(foodItem,quantity);
    }

    public double calculateTotalPrice (){
        cart.updateTotal();
        return cart.getTotal();
    }


}
