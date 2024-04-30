package com.FoodDeliveryApp.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    private static final String CSV_FILE = "res/CSV/audit_log_Data.csv";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String actionName) {
        String timestamp = LocalDateTime.now().format(DATE_FORMAT);
        try (FileWriter writer = new FileWriter(CSV_FILE, true)) {
            writer.append(actionName)
                    .append(',')
                    .append(timestamp)
                    .append('\n');
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
