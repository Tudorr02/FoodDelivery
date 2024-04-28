
import com.FoodDeliveryApp.Converters.*;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.UiForms.LogIn;
import com.FoodDeliveryApp.UiForms.RestaurantForm;
import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

       DataStorageServices.getInstance().initData();

        new LogIn();

    }

}


