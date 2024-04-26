
import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {

        DataStorageServices.getInstance().initData();

        DataStorageServices.getInstance().getClients().add(new Client("Tudori","AUDI","tas","1231","C-100123","40178066688","Cluj-Napoca","vasile.nicolae@example.com"));
        DataStorageServices.getInstance().writeCsv("res/CSV/Client_Data.csv",(DataConverter)new ClientConverter(),(List)DataStorageServices.getInstance().getClients());
    }

}
