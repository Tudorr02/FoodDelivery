package com.FoodDeliveryApp.Services;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.ShoppingCart;
import com.FoodDeliveryApp.Services.AuditServices.AuditingService;

import java.util.Map;

public class ShoppingCartServices extends AuditingService {

    private ShoppingCart cart;

    public ShoppingCartServices(ShoppingCart cart) {
        this.cart = cart;
    }

    public void addFoodItem(FoodItem foodItem, int quantity){
        logAction("addFoodItem: " + foodItem.getName() + ", quantity: " + quantity);
        cart.addItem(foodItem,quantity);
        System.out.println("Added " + quantity + " " + foodItem.getName() + "(s) to the cart.");
    }


    public void increaseItemQuantity(FoodItem foodItem) {
        logAction("increaseItemQuantity: " + foodItem.getName());
        cart.incrementItemQuantity(foodItem);
    }

    public void decreaseItemQuantity(FoodItem foodItem) {
        logAction("decreaseItemQuantity: " + foodItem.getName());
        cart.decrementItemQuantity(foodItem);
    }

    public void removeFoodItem(FoodItem foodItem) {
        logAction("removeFoodItem: " + foodItem.getName());
        cart.removeItem(foodItem);
        System.out.println("Removed " + foodItem.getName() + " from the cart.");
    }

    public int getCartSize() {
        logAction("getCartSize");
        return cart.getItems().size();
    }

    public Map<FoodItem, Integer> getCartItems() {
        logAction("getCartItems");
        return cart.getItems();
    }

    public double getTotalItemPrice (Map.Entry<FoodItem, Integer> entry){
        logAction("getTotalItemPrice: " + entry.getKey().getName());
        return entry.getKey().getPrice() * entry.getValue();
    }

    public void modifyQuantity(FoodItem foodItem, int quantity){
        logAction("modifyQuantity: " + foodItem.getName() + ", new quantity: " + quantity);
        cart.updateQuantity(foodItem,quantity);
    }

    public double calculateTotalPrice (){
        logAction("calculateTotalPrice");
        cart.updateTotal();
        return cart.getTotal();
    }


}
