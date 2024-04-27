
import com.FoodDeliveryApp.Converters.*;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        DataStorageServices.getInstance().initData();


        DataStorageServices.getInstance().writeCsv((DataConverter) new PickUpOrdersConverter(),(List) DataStorageServices.getInstance().getPuOrders());




//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
//
//        // Example usage with parsing and formatting
//        String dateString = "04/21/2024 20:05";
//        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
//        System.out.println("Parsed LocalDateTime: " + dateTime);
//
//        // Format the LocalDateTime object back into a string
//        String formattedDate = dateTime.format(formatter);
//        System.out.println("Formatted date string: " + formattedDate);  // This will display "04/21/2024 02:05"
//

    }

}


