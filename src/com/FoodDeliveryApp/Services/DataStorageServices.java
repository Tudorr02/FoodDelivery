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

    // List to hold multiple objects of type T
    private List<T> objects;

    // users
    private List<Users> users;
    private List<Client> cUsers;
    private List<DeliveryMan> dmUsers;

    private List<FoodItem> foodItems;
    private List<Restaurant> restaurants;
    private List<Review> reviews;
    private List<Delivery> deliveries;

    // orders
    private List<PickUpOrder> puOrders;
    private List<DeliveryOrder> dOrders;


    // Private constructor to prevent external instantiation
    private DataStorageServices() {
        List<Client> cUsers=new ArrayList<>();
        List<DeliveryMan> dmUsers = new ArrayList<>();
        List<FoodItem> foodItems= new ArrayList<>();
        List<Restaurant> restaurants= new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        List<PickUpOrder> puOrders = new ArrayList<>();
        List<DeliveryOrder> dOrders = new ArrayList<>();
        List<Delivery> deliveries = new ArrayList<>();

        objects = new ArrayList<>();
    }

    // Generic method to return the single instance with appropriate type
    public static synchronized <U> DataStorageServices<U> getInstance() {
        if (instance == null) {
            instance = new DataStorageServices<U>();

            instance.initializeData();
        }
        return (DataStorageServices<U>) instance;
    }

    private void initializeData(){
         DataConverter<Client> clientConverter = new ClientConverter();
         DataConverter<DeliveryMan> dmConverter = new DeliveryManConverter();
         DataConverter<FoodItem> fiConverter = new FoodItemConverter();
         DataConverter<Review> reviewConverter = new ReviewConverter();
         DataConverter<Restaurant> restaurantConverter = new RestaurantConverter();



        try {
//            this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);
//            this.cUsers= (List<Client>) new ArrayList<>(this.objects);
//            this.objects.clear();
//
//            this.readCsv("res/CSV/DeliveryMen_Data.csv",(DataConverter<T>) dmConverter);
//            this.dmUsers= (List<DeliveryMan>) new ArrayList<>(this.getObjects());
//            objects.clear();

            this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);
            this.readCsv("res/CSV/DeliveryMen_Data.csv", (DataConverter<T>) dmConverter);
            this.users = (List<Users>) new ArrayList<>(this.objects);
            this.objects.clear();

            this.readCsv("res/CSV/FoodItems_Data.csv",(DataConverter<T>) fiConverter);
            this.foodItems = (List<FoodItem>) new ArrayList<>(this.getObjects());
            objects.clear();

            this.readCsv("res/CSV/Reviews_Data.csv",(DataConverter<T>) reviewConverter);
            this.reviews = (List<Review>) new ArrayList<>(this.getObjects());
            objects.clear();

            this.readCsv("res/CSV/Restaurants_Data.csv",(DataConverter<T>) restaurantConverter);
            this.restaurants = (List<Restaurant>) new ArrayList<>(this.getObjects());



//
//            this.readCsv("res/CSV/FoodItems_Data.csv" , (DataConverter<T>) foodItems);
//            this.foodItems= (List<FoodItem>) this.getObjects(); objects.clear();
//
//            this.readCsv("res/CSV/Restaurants_Data.csv", (DataConverter<T>) restaurants);
//            this.restaurants= (List<Restaurant>) this.getObjects(); objects.clear();

        } catch (IOException e) {
            System.out.println("Error reading CSV data: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Optionally, print out to verify loading

//        System.out.println();
//
//        for( T DeliveryMan : this.getObjects()) {
//            System.out.println(DeliveryMan.toString());
//        }

    }

    // Method to get the stored objects
    public List<T> getObjects() {
        return objects;
    }

    public List<Users> getUsers()
    {
        return users;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public List<PickUpOrder> getPUOrders() {
        return puOrders;
    }

    public List<DeliveryOrder> getDOrders() {
        return dOrders;
    }

    public void setObjects(List<T> objects) {

        this.objects = new ArrayList<T>(objects);
    }

    // Method to read data from a CSV file
    public void readCsv(String filePath, DataConverter<T> converter ) throws Exception {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
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
    }

    // Method to write data to a CSV file
    public void writeCsv(String filePath, DataConverter<T> converter) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
            for (T object : objects) {
                String csvLine = converter.convertToCsv(object);
                bw.write(csvLine);
                bw.newLine();
            }
        }
    }


    public Users getUserById (String userId) throws Exception {

        if(this.users.isEmpty())
            throw new IOException("Custom Exception - getUserById : Empty Users List !");

        for (Users user : this.users)
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
        throw new Exception("getFoodItemById : Food item not found !");
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


}