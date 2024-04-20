package com.FoodDeliveryApp.Services;

import com.FoodDeliveryApp.Converters.DataConverter;

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

    // Private constructor to prevent external instantiation
    private DataStorageServices() {
        objects = new ArrayList<>();
    }

    // Generic method to return the single instance with appropriate type
    public static synchronized <U> DataStorageServices<U> getInstance() {
        if (instance == null) {
            instance = new DataStorageServices<U>();
        }
        return (DataStorageServices<U>) instance;
    }

    // Method to get the stored objects
    public List<T> getObjects() {
        return objects;
    }

    // Method to set the stored objects
    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

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