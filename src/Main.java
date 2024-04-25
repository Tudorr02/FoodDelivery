
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import org.w3c.dom.ls.LSOutput;


public class Main {
    public static void main(String[] args) {

        DataStorageServices.getInstance();
        System.out.println(DataStorageServices.getInstance().getShoppingCarts().size());


        for(  Delivery x:   DataStorageServices.getInstance().getDeliveries() )
            System.out.println(x.toString());


    }

}
