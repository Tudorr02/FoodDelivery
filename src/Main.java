import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Converters.DeliveryManConverter;
import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Models.DeliveryMan;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.io.IOException;

public class Main {
    public static <IOException extends Throwable> void main(String[] args) throws java.io.IOException {
        try {
            // Instance for Clients

            DataStorageServices<DeliveryMan> deliveryManDs = DataStorageServices.getInstance();
            DataConverter<DeliveryMan> deliveryManDataConverter = new DeliveryManConverter();
            deliveryManDs.readCsv("res/CSV/DeliveryMen_Data.csv",deliveryManDataConverter);

            DataStorageServices<Client> clientDS = DataStorageServices.getInstance();
            DataConverter<Client> clientConverter = new ClientConverter();
            clientDS.readCsv("res/CSV/Client_Data.csv", clientConverter);



            for (DeliveryMan deliveryMan : deliveryManDs.getObjects()) {
                System.out.println(deliveryMan.toString());
            }
            }
            finally{
                System.out.printf("Exceptie tudori  vlad!");
        }
    }


}
