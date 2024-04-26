
import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        DataStorageServices.getInstance();
        System.out.println(DataStorageServices.getInstance().getShoppingCarts().size());

        DataStorageServices.getInstance().setObjects(List.of(DataStorageServices.getInstance().getUsers()));
        DataConverter<Client> writer = new ClientConverter();
        DataStorageServices.getInstance().appendCsv("res/CSV/Client_Data.csv",(DataConverter) writer);  // Correct use of generics






    }

}
