
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;



public class Main {
    public static void main(String[] args) {

        DataStorageServices.getInstance();
        System.out.println(DataStorageServices.getInstance().getShoppingCarts().size());



        for( ShoppingCart x:   DataStorageServices.getInstance().getShoppingCarts() )
            System.out.println(x.toString());

    }

}
