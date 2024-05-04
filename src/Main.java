
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.UiForms.LogIn;

import javax.swing.*;
import java.awt.*;


public class Main {


    public static void main(String[] args) throws Exception {

       DataStorageServices.getInstance().initData();
        //setUIFont(new Font("Cascadia Mono", Font.BOLD, 14));
        new LogIn();

    }


    public static void setUIFont(Font font) {
    java.util.Enumeration<Object> keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
        Object key = keys.nextElement();
        Object value = UIManager.get(key);
        if (value instanceof javax.swing.plaf.FontUIResource) {
            UIManager.put(key, new javax.swing.plaf.FontUIResource(font));
        }
    }
}


}


