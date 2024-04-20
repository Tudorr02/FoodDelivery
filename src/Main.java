import com.FoodDeliveryApp.Converters.ClientConverter;
import com.FoodDeliveryApp.Converters.DataConverter;
import com.FoodDeliveryApp.Models.Client;
import com.FoodDeliveryApp.Models.Users;
import com.FoodDeliveryApp.Services.DataStorageServices;

import java.io.IOException;

public class Main {
    public static <IOException extends Throwable> void main(String[] args) throws java.io.IOException {
        try {
            // Instance for Clients
            DataStorageServices<Client> clientDS = DataStorageServices.getInstance();
            DataConverter<Client> clientConverter = new ClientConverter();
            clientDS.readCsv("D:\\Faculate\\ANUL 3\\sem2\\poo\\JavaProject\\Code\\res\\CSV\\Client_Data.csv", clientConverter);


            // Optionally, print out to verify loading
            for (Client client : clientDS.getObjects()) {
                System.out.println(client.toString());
            }
            }
            finally{
                System.out.printf("Exceptie tudori  vlad!");
        }
    }


}
