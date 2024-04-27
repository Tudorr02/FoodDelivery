package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Converters.*;

import com.FoodDeliveryApp.Models.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataStorageServices<T> {
    // Static variable to hold the single instance
    private static DataStorageServices<?> instance;

    private List<Client> clients;
    private List<DeliveryMan> deliveryMans;
    private List<FoodItem> foodItems;
    private List<Restaurant> restaurants;
    private List<Review> reviews;
    private List<PickUpOrder> puOrders;
    private List<DeliveryOrder> dOrders;
    private List<Delivery> deliveries;
    private List<ShoppingCart> shoppingCarts;


    // Generic method to return the single instance with appropriate type
    public static synchronized<T>  DataStorageServices<T> getInstance() throws Exception {
        if (instance == null) {
            instance = new DataStorageServices<T>();

        }
        return (DataStorageServices) instance;
    }

    public  void initData() throws Exception {
        DataConverter<Client> clientConverter = new ClientConverter();
        DataConverter<DeliveryMan> deliveryManConverter = new DeliveryManConverter();
        DataConverter<FoodItem> foodItemConverter = new FoodItemConverter();
        DataConverter<Restaurant> restaurantConverter = new RestaurantConverter();
        DataConverter<Review> reviewConverter = new ReviewConverter();
        DataConverter<PickUpOrder> puOrderDataConverter= new PickUpOrdersConverter();
        DataConverter<DeliveryOrder> dOrderConverter = new DeliveryOrderConverter();
        DataConverter<Delivery> deliveryConverter = new DeliveryConverter();
        DataConverter<ShoppingCart> shoppingCartConverter = new ShoppingCartConverter();


        clients= readCsv((DataConverter) clientConverter);
        deliveryMans = readCsv((DataConverter) deliveryManConverter);
        foodItems=readCsv((DataConverter) foodItemConverter );
        reviews=readCsv((DataConverter)reviewConverter);
        restaurants=readCsv((DataConverter)restaurantConverter);
        shoppingCarts=readCsv((DataConverter)shoppingCartConverter);
        puOrders=readCsv((DataConverter)puOrderDataConverter);
        dOrders=readCsv((DataConverter)dOrderConverter);
        deliveries=readCsv((DataConverter)deliveryConverter);

    }

    // Method to read data from a CSV file
    public List<T> readCsv( DataConverter<T> converter ) throws Exception {
        List<T> objects = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(converter.getFilePath()))) {
            String line;
            int lineIndex = 0;
            while ((line = br.readLine()) != null) {
                if(lineIndex != 0){
                    T record = converter.convertFromCsv(line);
                    objects.add(record);
                }else
                {
                    lineIndex = 1;
                }
            }
        }

        return objects;
    }

    // Method to write data to a CSV file
    public void writeCsv(DataConverter<T> converter , List<T> items ) throws IOException {
            BufferedReader br = Files.newBufferedReader(Paths.get(converter.getFilePath()));
            String val=br.readLine();
            try(BufferedWriter bw = Files.newBufferedWriter(Paths.get(converter.getFilePath()))) {
                bw.write(val);
                bw.newLine();

                for (T object : items) {

                    String csvLine = converter.convertToCsv(object);
                    bw.write(csvLine);
                    bw.newLine();

                }
            }

    }

    public List<Client> getClients() {
        return clients;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public List<DeliveryOrder> getdOrders() {
        return dOrders;
    }

    public List<PickUpOrder> getPuOrders() {
        return puOrders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public List<DeliveryMan> getDeliveryMans() {
        return deliveryMans;
    }

    public Users getUserById (String userId) throws Exception {

        List<Users> users = new ArrayList<>();
        users.addAll(clients);
        users.addAll(deliveryMans);

        if(users.isEmpty())
            throw new IOException("Custom Exception - getUserById : Empty Users List !");

        for (Users user : users)
            if(user.getUserID().equals(userId))
                return user;

        throw new Exception("getUserById : User not found !");

    }

    public FoodItem getFoodItemById(int foodItemId) throws Exception {
        if(this.foodItems.isEmpty())
            throw new IOException("Custom Exception - getFoodItemById : Empty Food Items List !");

        // Loop through the food items list to find a matching ID
        for (FoodItem foodItem : this.foodItems) {
            if (foodItem.getFoodID() == foodItemId)  // Using '==' since ID is an int
                return foodItem;
        }

        // If no matching ID is found, throw an exception
        throw new Exception("getFoodItemById : Food Item not found !");
    }

    public Review getReviewById(String reviewId) throws Exception {
        if (this.reviews.isEmpty()) {
            throw new IOException("Custom Exception - getReviewById : Empty Reviews List !");
        }

        // Loop through the reviews list to find a matching ID
        for (Review review : this.reviews) {
            if (review.getReviewID().equals(reviewId)) {  // Using '==' since ID is an int
                return review;
            }
        }

        // If no matching ID is found, throw an exception
        throw new Exception("getReviewById : Review not found !");
    }

    public DeliveryMan getDeliveryManById(String deliveryManId) throws Exception {

        if(deliveryMans.isEmpty())
            throw new IOException("Custom Exception - getDeliveryManById : Empty DeliveryMan List !");

        for (DeliveryMan dm : deliveryMans)
            if(dm.getUserID().equals(deliveryManId))
                return  dm;

        throw new Exception("getUserById : User not found !");

    }

    public Restaurant getRestaurantById(String restaurantId) throws Exception {
        if (this.restaurants.isEmpty()) {
            throw new IOException("Custom Exception - getRestaurantById : Empty Restaurant List !");
        }

        for (Restaurant restaurant : this.restaurants) {
            if (restaurant.getRestaurantID().equals(restaurantId)) {
                return restaurant;
            }
        }

        throw new Exception("getRestaurantById: Restaurant not found !");
    }

    public ShoppingCart getShoppingCartById(String shoppingCartId) throws Exception {
        if (this.shoppingCarts.isEmpty()) {
            throw new IOException("Custom Exception - getShoppingCartById : Empty ShoppingCart List !");
        }

        for (ShoppingCart cart : this.shoppingCarts) {
            if (cart.getShoppingCartID().equals(shoppingCartId)) {
                return cart;
            }
        }

        throw new Exception("getShoppingCartById : ShoppingCart not found !");
    }

    public Order getOrderById(String orderId) throws Exception {

        List<Order> orders = new ArrayList<>();
        orders.addAll(dOrders);
        orders.addAll(puOrders);

        if (orders.isEmpty()) {
            throw new IOException("Custom Exception - getOrderById : Empty Orders List !");
        }

        for (Order order : orders) {
            if (order.getOrderID().equals(orderId)) {
                return order;
            }
        }

        throw new Exception("getOrderById: Order not found !");
    }


}