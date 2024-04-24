
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        DataStorageServices.getInstance();
        DataStorageServices.getInstance().getUsers();

        for( Users x:   DataStorageServices.getInstance().getUsers() )
            System.out.println(x.toString());

    }

}
