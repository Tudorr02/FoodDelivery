package com.FoodDeliveryApp.Services.AuditServices;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditingService {

    private static final String AUDIT_FILE_PATH = "res/CSV/audit_log_Data.csv";

    public AuditingService() {
        // Initialize audit log file with header
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUDIT_FILE_PATH, true))) {
            if (writer.toString().isEmpty()) {
                writer.write("name_of_action,timestamp");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error initializing audit log file: " + e.getMessage());
        }
    }

    protected void logAction(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AUDIT_FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(actionName + "," + timestamp);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to audit log file: " + e.getMessage());
        }
    }
}
