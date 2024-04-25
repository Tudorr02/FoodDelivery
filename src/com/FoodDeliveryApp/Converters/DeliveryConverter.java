//package com.FoodDeliveryApp.Converters;
//
//import com.FoodDeliveryApp.Models.Delivery;
//
//public class DeliveryConverter implements DataConverter<Delivery> {
//
//    @Override
//    public Delivery convertFromCsv(String csvLine) {
//        String[] values = csvLine.split(",");
//        if (values.length < 4) {
//            throw new IllegalArgumentException("CSV line does not contain enough data for Delivery.");
//        }
//        // Assumes CSV is ordered as deliveryID, deliveryManID, orderID, expectedTime
//        String deliveryID = values[0].trim();
//        String deliveryManID = values[1].trim();
//
//
//        String orderID = values[2].trim();
//        int expectedTime = Integer.parseInt(values[3].trim()); // Parsing the expected time to an integer
//
//        return new Delivery(deliveryID, deliveryManID, orderID, expectedTime);
//    }
//
//    @Override
//    public String convertToCsv(Delivery delivery) {
//        return String.join(",",
//                delivery.getDeliveryID(),
//                delivery.getDeliveryManID(),
//                delivery.getOrderID(),
//                String.valueOf(delivery.getExpectedTime())); // Converting expected time back to String
//    }
//}
