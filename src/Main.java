
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Models.FoodItem;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DataStorageServices.getInstance();
        System.out.println(DataStorageServices.getInstance().getUsers().size()); ;



        for( Review x:   DataStorageServices.getInstance().getReviews() )
            System.out.println(x.toString());





    }

}
