package com.FoodDeliveryApp.UiForms.FrameUtils;

import javax.swing.*;
import java.awt.*;

public class FrameUtils {
    public static void setGlobalIcon(JFrame frame, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        frame.setIconImage(icon.getImage());
    }
}
