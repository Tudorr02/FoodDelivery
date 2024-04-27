package com.FoodDeliveryApp.Converters;

public interface DataConverter<T> {
    T convertFromCsv(String csvLine) throws Exception;
    String convertToCsv(T object);
    String getFilePath();
}
