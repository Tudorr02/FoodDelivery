package com.FoodDeliveryApp.Converters;

import com.FoodDeliveryApp.Exceptions.DataConverterException;

public interface DataConverter<T> {
    T convertFromCsv(String csvLine) throws Exception;
    String convertToCsv(T object) throws DataConverterException;
    String getFilePath();
}
