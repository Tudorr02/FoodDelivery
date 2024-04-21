package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryManConverter;
import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Models.Users;

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
    private List<Users> users;


    // Private constructor to prevent external instantiation
    private DataStorageServices() {
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

        try {
            this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);
            this.readCsv("res/CSV/DeliveryMen_Data.csv",(DataConverter<T>) dmConverter);
           // this.readCsv("res/CSV/Client_Data.csv", (DataConverter<T>) clientConverter);


            // Assume the instance is specifically for Client
        } catch (IOException e) {
            System.out.println("Error reading CSV data: " + e.getMessage());
        }

        // Optionally, print out to verify loading
        for (T client : this.getObjects()) {
            System.out.println(client.toString());
        }

        System.out.println();

        for( T DeliveryMan : this.getObjects()) {
            System.out.println(DeliveryMan.toString());
        }

    }
    // Method to get the stored objects
    public List<T> getObjects() {
        return objects;
    }
    public List<Users> getUsers(){ return users;}

    // Method to set the stored objects
//    public void setObjects(List<T> objects) {
//        this.objects = objects;
//    }

    // Method to read data from a CSV file
    public void readCsv(String filePath, DataConverter<T> converter) throws IOException {
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