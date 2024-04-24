
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        DataStorageServices.getInstance();
        DataStorageServices.getInstance().getCUsers();

        for(DeliveryMan x :   DataStorageServices.getInstance().getDMUsers() )
            System.out.println(x.toString());



    }


}
