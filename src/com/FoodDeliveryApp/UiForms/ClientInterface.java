package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.Restaurant;
import com.FoodDeliveryApp.Models.Review;
import com.FoodDeliveryApp.Services.DataStorageServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;

public class ClientInterface extends javax.swing.JFrame {

    private JPanel MainPanel;
    private JPanel ParentPanel;
    private JPanel FoodItemsCard;
    private JPanel RestaurantsCard;
    private JFrame frame;
    private JScrollPane ScrollCard;
    private JPanel ReviewsPanel;
    private JTextArea DescriptionArea;
    private JList Reviews;
    private JPanel ShoppingCartPanel;
    private JScrollPane ShoppingCart;
    private DefaultListModel ReviewsModel;

    ClientInterface() throws Exception {

        frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setResizable(false);

        RestaurantsCard.setLayout(new BoxLayout(RestaurantsCard, BoxLayout.Y_AXIS));

        for(Restaurant restaurantOption : DataStorageServices.getInstance().getRestaurants()){
            RestaurantsCard.add(addCustomButton(restaurantOption));
            RestaurantsCard.add(Box.createVerticalStrut(5));

        }


        frame.add(MainPanel);



        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JButton addCustomButton(Restaurant restaurantOption){
        JButton restaurantOptionButton = new JButton(restaurantOption.getName());
        Dimension buttonSize = new Dimension(200,40);
        restaurantOptionButton.setPreferredSize(buttonSize);
        restaurantOptionButton.setMinimumSize(buttonSize);
        restaurantOptionButton.setMaximumSize(buttonSize);
        restaurantOptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restaurantOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String DescriptionMsg="Location : "+ restaurantOption.getLocation() + "\nPrice Range : " + restaurantOption.getPriceRange() +"\nRating : " +restaurantOption.getRating();
                DescriptionArea.setText(DescriptionMsg);
                ReviewsModel =new DefaultListModel<>();
                ReviewsModel.clear();
                for(Review review : restaurantOption.getReviews()){
                    ReviewsModel.addElement("Rating :"+ review.getGivenRating()+" Review : "+ review.getMessage());
                }


                Reviews.setModel(ReviewsModel);

            }
        });

        return restaurantOptionButton;

    }

    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();
        new ClientInterface();
    }

}
