
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.UiForms.LogIn;


public class Main {
    public static void main(String[] args) throws Exception {

       DataStorageServices.getInstance().initData();

        new LogIn();

    }

}


