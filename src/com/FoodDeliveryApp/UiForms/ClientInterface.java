package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.FoodItem;
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
    private JScrollPane ReviewsScrollPanel;
    private JScrollPane FoodItemsScrollPanel;
    private JButton BackToRestaurantsBtn;
    private DefaultListModel ReviewsModel;

    ClientInterface() throws Exception {

        frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setMaximumSize(new Dimension(800, 600));

        ParentPanel.setMinimumSize(new Dimension(250, 400));
        ParentPanel.setMaximumSize(new Dimension(250, 400));
        ParentPanel.setPreferredSize(new Dimension(250, 400));

        frame.setResizable(false);

        showRestaurants();
        BackToRestaurantsBtn.setVisible(false);


        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public JButton addCustomRestaurantButton(Restaurant restaurantOption){
        JButton restaurantOptionButton = new JButton(restaurantOption.getName());
        Dimension buttonSize = new Dimension(200,40);
        restaurantOptionButton.setPreferredSize(buttonSize);
        restaurantOptionButton.setMinimumSize(buttonSize);
        restaurantOptionButton.setMaximumSize(buttonSize);
        restaurantOptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restaurantOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ParentPanel.removeAll();
                ParentPanel.add(FoodItemsScrollPanel);
                ParentPanel.repaint();
                ParentPanel.revalidate();

                showReviewsPanel(restaurantOption);
                showMenu(restaurantOption);
                BackToRestaurantsBtn.setVisible(true);



            }
        });

        return restaurantOptionButton;

    }

    public void showRestaurants() throws Exception {

        RestaurantsCard.setLayout(new BoxLayout(RestaurantsCard, BoxLayout.Y_AXIS));
        for(Restaurant restaurantOption : DataStorageServices.getInstance().getRestaurants()){
            RestaurantsCard.add(addCustomRestaurantButton(restaurantOption));
            RestaurantsCard.add(Box.createVerticalStrut(5));

        }
    }

    public void showMenu(Restaurant restaurant){
        BackToRestaurantsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(ScrollCard);
                ParentPanel.repaint();
                ParentPanel.revalidate();
            }
        });
        FoodItemsCard.setLayout(new BoxLayout(FoodItemsCard, BoxLayout.Y_AXIS));

        for(FoodItem foodItem : restaurant.getMenu()){
            JPanel foodItemPanel = new JPanel();
            foodItemPanel.setBackground(Color.BLUE);

            foodItemPanel.setLayout(new BorderLayout());

            //FoodItemsCard.add(addFoodItemButton(foodItem));

           foodItemPanel.setLayout(new BoxLayout(foodItemPanel, BoxLayout.Y_AXIS));
           foodItemPanel.setPreferredSize(new Dimension(400, 100));
           foodItemPanel.setMaximumSize(new Dimension(400, 100));
           foodItemPanel.setMinimumSize(new Dimension(400, 100));


           foodItemPanel.add(addFoodItemButton(foodItem));

           JTextArea itemDescriptionArea = new JTextArea(" "+foodItem.getDescription()+"\n Weight : "+ foodItem.getWeight()+"\n Price : "+foodItem.getPrice());

           foodItemPanel.add(itemDescriptionArea);

           FoodItemsCard.add(foodItemPanel);
           FoodItemsCard.add(Box.createVerticalStrut(5));

        }



    }
    
    public JButton addFoodItemButton(FoodItem foodItem) {
        String buttonLabel = foodItem.getName();
        JButton foodItemBtn = new JButton(buttonLabel);
        Dimension buttonSize = new Dimension(400, 40);
        foodItemBtn.setPreferredSize(buttonSize);
        foodItemBtn.setMinimumSize(buttonSize);
        foodItemBtn.setMaximumSize(buttonSize);
        foodItemBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        return foodItemBtn;
    }

    public void showReviewsPanel(Restaurant restaurantOption) {
        String DescriptionMsg="Location : "+ restaurantOption.getLocation() + "\nPrice Range : " + restaurantOption.getPriceRange() +"\nRating : " +restaurantOption.getRating();
        DescriptionArea.setText(DescriptionMsg);
        ReviewsModel =new DefaultListModel<>();
        ReviewsModel.clear();
        for(Review review : restaurantOption.getReviews()){
            ReviewsModel.addElement("Rating :"+ review.getGivenRating()+"  \n Review : "+ review.getMessage());
        }

        Reviews.setModel(ReviewsModel);
    }

    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();
        new ClientInterface();
    }

}
