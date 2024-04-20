package com.FoodDeliveryApp.Converters;

public interface DataConverter<T> {
    T convertFromCsv(String csvLine);
    String convertToCsv(T object);
}
