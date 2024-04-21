import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DataStorageServices.getInstance();
    }
}
