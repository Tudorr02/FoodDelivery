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


        try {
//            this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);
//            this.cUsers= (List<Client>) new ArrayList<>(this.objects);
//            this.objects.clear();
//
//            this.readCsv("res/CSV/DeliveryMen_Data.csv",(DataConverter<T>) dmConverter);
//            this.dmUsers= (List<DeliveryMan>) new ArrayList<>(this.getObjects());
//            objects.clear();

            this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);
            this.users = (List<Users>) new ArrayList<>(this.objects);


            this.readCsv("res/CSV/DeliveryMen_Data.csv", (DataConverter<T>) dmConverter);
            this.users = (List<Users>) new ArrayList<>(this.objects);
            this.objects.clear();

            this.readCsv("res/CSV/FoodItems_Data.csv",(DataConverter<T>) fiConverter);
            this.foodItems = (List<FoodItem>) new ArrayList<>(this.getObjects());
            objects.clear();

            this.readCsv("res/CSV/Reviews_Data.csv",(DataConverter<T>) reviewConverter);
            this.reviews = (List<Review>) new ArrayList<>(this.getObjects());
            objects.clear();


//
//            this.readCsv("res/CSV/FoodItems_Data.csv" , (DataConverter<T>) foodItems);
//            this.foodItems= (List<FoodItem>) this.getObjects(); objects.clear();
//
//            this.readCsv("res/CSV/Restaurants_Data.csv", (DataConverter<T>) restaurants);
//            this.restaurants= (List<Restaurant>) this.getObjects(); objects.clear();

        } catch (IOException e) {
            System.out.println("Error reading CSV data: " + e.getMessage());
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

//     public List<Client> getCUsers() {
//        return cUsers;
//    }

//    public List<DeliveryMan> getDMUsers() {
//        return dmUsers;
//    }
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

    // Method to set the stored objects
//    public void setObjects(List<T> objects) {
//        this.objects = objects;
//    }

    // Method to read data from a CSV file
    public void readCsv(String filePath, DataConverter<T> converter ) throws IOException {
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
}