package com.FoodDeliveryApp.UiForms;

import com.FoodDeliveryApp.Models.*;
import com.FoodDeliveryApp.Services.DataStorageServices;
import com.FoodDeliveryApp.Services.DeliveryServices;
import com.FoodDeliveryApp.Services.OrderServices;
import com.FoodDeliveryApp.Services.ShoppingCartServices;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.MessageFormat;
import java.util.Map;
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
    private JPanel ShoppingPanel;
    private JScrollPane ShoppingCartScroll;
    private JScrollPane ReviewsScrollPanel;
    private JScrollPane FoodItemsScrollPanel;
    private JButton BackToRestaurantsBtn;
    private JButton orderButton;
    private JPanel ShoppingCartPanel;
    private JPanel UserPanel;
    private DefaultListModel ReviewsModel;
    private ShoppingCartServices shoppingCart;
    boolean restaurantLock;
    Restaurant orderRestaurant;

    ClientInterface(String clientID) throws Exception {

        frame = new JFrame("Client Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 700));
        frame.setMinimumSize(new Dimension(800, 700));
        frame.setMaximumSize(new Dimension(800, 700));

        ParentPanel.setMinimumSize(new Dimension(400, 400));
        ParentPanel.setMaximumSize(new Dimension(400, 400));
        ParentPanel.setPreferredSize(new Dimension(400, 400));

        ShoppingPanel.setMinimumSize(new Dimension(300, 400));
        ShoppingPanel.setMaximumSize(new Dimension(300, 400));
        ShoppingPanel.setPreferredSize(new Dimension(300, 400));



        // other settings

        ReviewsPanel.setVisible(false);
        shoppingCart = new ShoppingCartServices(new ShoppingCart());
        restaurantLock=false;

        showRestaurants();

        frame.setResizable(false);
        frame.add(MainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//        orderButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JDialog dialog = new JDialog(frame, "Order Type", true);
//                dialog.setSize(400, 200);
//                dialog.setLayout(new BorderLayout());
//                dialog.setResizable(false);
//
//
//                // Create a panel to hold radio buttons and text field
//                JPanel panel = new JPanel(new GridLayout(5, 2));
//
//                // Create radio buttons
//                JRadioButton optionDelivery = new JRadioButton("Delivery + 10%");
//                JRadioButton optionPickUp = new JRadioButton("Pick up ");
//
//                // Group the radio buttons to ensure only one can be selected
//                ButtonGroup group = new ButtonGroup();
//                group.add(optionDelivery);
//                group.add(optionPickUp);
//
//                JLabel paymentLabel = new JLabel("Select Payment Method");
//                JRadioButton paymentCard = new JRadioButton("Card");
//                JRadioButton paymentCash = new JRadioButton("Cash");
//
//                ButtonGroup group2 = new ButtonGroup();
//                group2.add(paymentCard);
//                group2.add(paymentCash);
//
//                // Create the JTextField with a placeholder
//                JTextField deliveryDiscountField = new JTextField(8);
//                JButton checkDiscountBtn = new JButton("OK");
//
//                deliveryDiscountField.setEnabled(false);
//                checkDiscountBtn.setEnabled(false);
//
//                deliveryDiscountField.setMaximumSize(new Dimension(100, 40));
//                deliveryDiscountField.setPreferredSize(new Dimension(100, 40));
//                deliveryDiscountField.setPreferredSize(new Dimension(100, 40));
//
//                String placeholder = "Delivey Discount Code";
//                deliveryDiscountField.setText(placeholder);
//                deliveryDiscountField.setForeground(Color.GRAY); // Set text color to gray
//
//                final double[] price = {shoppingCart.calculateTotalPrice()};
//                String orderBtnLabel= "Order | Total : ";
//                JButton orderBtn = new JButton(orderBtnLabel+ price[0]);
//                orderBtn.setEnabled(false);
//                final OrderType[] oType = {null};
//                final PaymentMethod[] pMethod={null};
//
//
//                orderBtn.setBackground(Color.GREEN);
//                orderBtn.setForeground(Color.WHITE);
//
//                optionDelivery.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                        if(!deliveryDiscountField.isEnabled()){
//                            price[0] += price[0] *0.1;
//                            orderBtn.setText(orderBtnLabel+ price[0]);
//                        }
//                        deliveryDiscountField.setEnabled(true);
//                        checkDiscountBtn.setEnabled(true);
//                        oType[0] =OrderType.DELIVERY_ORDER;
//                        if(group2.getSelection()!=null)
//                            orderBtn.setEnabled(true);
//
//                    }
//                });
//
//                optionPickUp.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                        deliveryDiscountField.setEnabled(false);
//                        checkDiscountBtn.setEnabled(false);
//                        price[0]=shoppingCart.calculateTotalPrice();
//                        orderBtn.setText(orderBtnLabel+ price[0]);
//                        oType[0]=OrderType.PICKUP_ORDER;
//
//                        if(group2.getSelection()!=null)
//                            orderBtn.setEnabled(true);
//
//                    }
//                });
//
//                paymentCash.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        pMethod[0]=PaymentMethod.CASH;
//                        if(group.getSelection()!=null)
//                            orderBtn.setEnabled(true);
//                    }
//                });
//
//                paymentCard.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        pMethod[0] = PaymentMethod.CARD;
//                        if(group.getSelection()!=null)
//                            orderBtn.setEnabled(true);
//                    }
//                });
//
//
//                deliveryDiscountField.addFocusListener(new java.awt.event.FocusAdapter() {
//                    @Override
//                    public void focusGained(java.awt.event.FocusEvent e) {
//                        // Remove placeholder text when field gains focus
//                        if (deliveryDiscountField.getText().equals(placeholder)) {
//                            deliveryDiscountField.setText("");
//                            deliveryDiscountField.setForeground(Color.BLACK); // Set text color to black
//
//                        }
//
//                    }
//
//                    @Override
//                    public void focusLost(java.awt.event.FocusEvent e) {
//                        // Restore placeholder text if the field is empty when focus is lost
//                        if (deliveryDiscountField.getText().isEmpty()) {
//                            deliveryDiscountField.setForeground(Color.GRAY); // Set text color back to gray
//                            deliveryDiscountField.setText(placeholder);
//                        }
//
//
//                    }
//                });
//
//                checkDiscountBtn.addActionListener(new ActionListener() {
//                    public void actionPerformed(ActionEvent e) {
//                        if(!deliveryDiscountField.getText().isEmpty()){
//                            int discountPercentage = new DeliveryServices().getPromoCodePercentage(deliveryDiscountField.getText());
//                            price[0] -= price[0] * ((double)discountPercentage/100);
//                            orderBtn.setText(orderBtnLabel+ price[0]);
//                        }else{
//                            deliveryDiscountField.setForeground(Color.RED);
//                        }
//                    }
//                });
//
//                // Add components to the panel
//                panel.add(optionDelivery);
//                panel.add(new JLabel());
//                panel.add(deliveryDiscountField);
//                panel.add(checkDiscountBtn);
//                panel.add(optionPickUp);
//                panel.add(new JLabel());
//                panel.add(paymentLabel);
//                panel.add(new JLabel());
//                panel.add(paymentCash);
//                panel.add(paymentCard);
//
//
//                orderBtn.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        // Handle order button click
//                        if (optionDelivery.isSelected()) {
//
//                            int deliveryDiscountP =new DeliveryServices().getPromoCodePercentage(deliveryDiscountField.getText());
//                            try {
//                                new OrderServices().generateDeliveryOrder(clientID,orderRestaurant,shoppingCart.getCart(),pMethod[0],deliveryDiscountP);
//                            } catch (Exception ex) {
//                                throw new RuntimeException(ex);
//                            }
//
//                        } else if (optionPickUp.isSelected()) {
//                            try {
//                                new OrderServices().generatePickUpOrder(clientID,orderRestaurant,shoppingCart.getCart(),pMethod[0]);
//                            } catch (Exception ex) {
//                                throw new RuntimeException(ex);
//                            }
//                        }
//
//                        String discount = deliveryDiscountField.getText();
//                        System.out.println("Discount: " + discount);
//                        dialog.dispose();
//                        shoppingCart=new ShoppingCartServices(new ShoppingCart());
//                        updateShoppingCart();
//                        try {
//                            showRestaurants();
//                            ReviewsPanel.setVisible(false);
//                        } catch (Exception ex) {
//                            throw new RuntimeException(ex);
//                        }
//                    }
//                });
//
//                // Create the "Cancel" button
//                JButton cancelBtn = new JButton("Cancel");
//                cancelBtn.addActionListener(ev -> dialog.dispose());
//
//                // Add the buttons to a separate panel
//                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//                buttonPanel.add(orderBtn);
//                buttonPanel.add(cancelBtn);
//
//                // Add components to the dialog
//                dialog.add(panel, BorderLayout.CENTER);
//                dialog.add(buttonPanel, BorderLayout.SOUTH);
//
//                // Show the dialog
//                dialog.setLocationRelativeTo(frame);
//                dialog.setVisible(true);
//            }
//        });

        orderButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create and configure the dialog
        JDialog dialog = new JDialog(frame, "Order Type", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout());
        dialog.setResizable(false);

        // Create a panel for radio buttons and text field
        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Create and add radio buttons for order type
        JRadioButton optionDelivery = new JRadioButton("Delivery + 10%");
        JRadioButton optionPickUp = new JRadioButton("Pick up");
        ButtonGroup orderGroup = new ButtonGroup();
        orderGroup.add(optionDelivery);
        orderGroup.add(optionPickUp);

        // Create and add radio buttons for payment method
        JLabel paymentLabel = new JLabel("Select Payment Method");
        JRadioButton paymentCard = new JRadioButton("Card");
        JRadioButton paymentCash = new JRadioButton("Cash");
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(paymentCard);
        paymentGroup.add(paymentCash);

        // Create the delivery discount text field and button
        JTextField deliveryDiscountField = new JTextField("Delivery Discount Code", 8);
        deliveryDiscountField.setForeground(Color.GRAY);
        JButton checkDiscountBtn = new JButton("OK");
        deliveryDiscountField.setEnabled(false);
        checkDiscountBtn.setEnabled(false);

        // Create the order button
        double[] price = {shoppingCart.calculateTotalPrice()};
        JButton orderBtn = new JButton(updateOrderPriceLabel(price[0]));
        orderBtn.setEnabled(false);
        orderBtn.setBackground(Color.GREEN);
        orderBtn.setForeground(Color.WHITE);

        // Initialize order type and payment method
        final OrderType[] oType = {null};
        final PaymentMethod[] pMethod = {null};

        // Add action listeners for order type buttons
        optionDelivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                price[0] = shoppingCart.calculateTotalPrice() * 1.1; // Add 10% for delivery
                orderBtn.setText(updateOrderPriceLabel(price[0]));
                deliveryDiscountField.setEnabled(true);
                checkDiscountBtn.setEnabled(true);
                oType[0] = OrderType.DELIVERY_ORDER;
                if (paymentGroup.getSelection() != null) {
                    orderBtn.setEnabled(true);
                }
            }
        });

        optionPickUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deliveryDiscountField.setEnabled(false);
                checkDiscountBtn.setEnabled(false);
                price[0] = shoppingCart.calculateTotalPrice();
                orderBtn.setText(updateOrderPriceLabel(price[0]));
                oType[0] = OrderType.PICKUP_ORDER;
                if (paymentGroup.getSelection() != null) {
                    orderBtn.setEnabled(true);
                }
            }
        });

        // Add action listeners for payment method buttons
        paymentCash.addActionListener(e1 -> {
            pMethod[0] = PaymentMethod.CASH;
            if (orderGroup.getSelection() != null) {
                orderBtn.setEnabled(true);
            }
        });

        paymentCard.addActionListener(e1 -> {
            pMethod[0] = PaymentMethod.CARD;
            if (orderGroup.getSelection() != null) {
                orderBtn.setEnabled(true);
            }
        });

        // Manage placeholder text behavior for the discount field
        deliveryDiscountField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (deliveryDiscountField.getText().equals("Delivery Discount Code")) {
                    deliveryDiscountField.setText("");
                    deliveryDiscountField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (deliveryDiscountField.getText().isEmpty()) {
                    deliveryDiscountField.setForeground(Color.GRAY);
                    deliveryDiscountField.setText("Delivery Discount Code");
                }
            }
        });

        // Add action listener for the discount button
        checkDiscountBtn.addActionListener(e1 -> {
            int discountPercentage = new DeliveryServices().getPromoCodePercentage(deliveryDiscountField.getText());
            if (!deliveryDiscountField.getText().isEmpty() && discountPercentage!=0 ) {
                price[0] = shoppingCart.calculateTotalPrice() * 1.1;
                price[0] -= price[0] * ((double) discountPercentage / 100);
                orderBtn.setText(updateOrderPriceLabel(price[0]));
                deliveryDiscountField.setForeground(Color.BLACK);
            } else {
                deliveryDiscountField.setForeground(Color.RED);
            }
        });

        // Add components to the panel
        panel.add(optionDelivery);
        panel.add(new JLabel());
        panel.add(deliveryDiscountField);
        panel.add(checkDiscountBtn);
        panel.add(optionPickUp);
        panel.add(new JLabel());
        panel.add(paymentLabel);
        panel.add(new JLabel());
        panel.add(paymentCash);
        panel.add(paymentCard);

        // Add action listener for the order button
        orderBtn.addActionListener(e1 -> {
            try {
                if (optionDelivery.isSelected()) {
                    int deliveryDiscountP = new DeliveryServices().getPromoCodePercentage(deliveryDiscountField.getText());
                    new OrderServices().generateDeliveryOrder(clientID, orderRestaurant, shoppingCart.getCart(), pMethod[0], deliveryDiscountP);
                } else if (optionPickUp.isSelected()) {
                    new OrderServices().generatePickUpOrder(clientID, orderRestaurant, shoppingCart.getCart(), pMethod[0]);
                }
                shoppingCart = new ShoppingCartServices(new ShoppingCart());
                updateShoppingCart();
                showRestaurants();
                ReviewsPanel.setVisible(false);
                dialog.dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // Create and add the Cancel button
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(ev -> dialog.dispose());

        // Add the order and cancel buttons to a separate panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(orderBtn);
        buttonPanel.add(cancelBtn);

        // Add panels to the dialog
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show the dialog
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }
});


    }


    public void showRestaurants() throws Exception {


        ParentPanel.removeAll();
                ParentPanel.add(ScrollCard);
                ParentPanel.repaint();
                ParentPanel.revalidate();


        orderButton.setEnabled(shoppingCart.getCartSize() != 0);

        RestaurantsCard.setLayout(new BoxLayout(RestaurantsCard, BoxLayout.Y_AXIS));

        RestaurantsCard.removeAll();// clear the last buttons
        for(Restaurant restaurantOption : DataStorageServices.getInstance().getRestaurants()){
            JButton restaurantOptionButton = new JButton(restaurantOption.getName());
            Dimension buttonSize = new Dimension(200,40);
            restaurantOptionButton.setPreferredSize(buttonSize);
            restaurantOptionButton.setMinimumSize(buttonSize);
            restaurantOptionButton.setMaximumSize(buttonSize);
            restaurantOptionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            restaurantOptionButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if(restaurantLock && !restaurantOption.getName().equals(orderRestaurant.getName())){
                        int result = JOptionPane.showConfirmDialog(null,"You are unable to order from multiple restaurants .\nWould you like to remove the actual order ?","Restaurants",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

                        // Handle the user's response
                        if (result == JOptionPane.OK_OPTION) {
                            shoppingCart = new ShoppingCartServices(new ShoppingCart());
                            updateShoppingCart();
                            orderRestaurant=null;
                        } else if (result == JOptionPane.CANCEL_OPTION) {
                            System.out.println("User clicked Cancel");
                        } else {
                            System.out.println("Dialog was closed");
                        }
                    }
                    else {
                        showReviewsPanel(restaurantOption);
                        showMenu(restaurantOption);
                        orderRestaurant = restaurantOption;
                        ReviewsPanel.setVisible(true);
                    }

                }
            });
            RestaurantsCard.add(restaurantOptionButton);
            RestaurantsCard.add(Box.createVerticalStrut(5));

        }
    }

    public void showMenu(Restaurant restaurant){

         ParentPanel.removeAll();
                    ParentPanel.add(FoodItemsScrollPanel);
                    ParentPanel.repaint();
                    ParentPanel.revalidate();

        orderButton.setEnabled(shoppingCart.getCartSize() != 0);

        BackToRestaurantsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ReviewsPanel.setVisible(false);
                try {
                    showRestaurants();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
        FoodItemsCard.setLayout(new BoxLayout(FoodItemsCard, BoxLayout.Y_AXIS));
        FoodItemsCard.removeAll(); // clear the last generated buttons
        for(FoodItem foodItem : restaurant.getMenu()){
            JPanel foodItemPanel = new JPanel();
           foodItemPanel.setLayout(new BoxLayout(foodItemPanel, BoxLayout.Y_AXIS));
           foodItemPanel.setPreferredSize(new Dimension(400, 100));
           foodItemPanel.setMaximumSize(new Dimension(400, 100));
           foodItemPanel.setMinimumSize(new Dimension(400, 100));

           JTextArea itemDescriptionArea = new JTextArea(" "+foodItem.getDescription()+"\n Weight : "+ foodItem.getWeight()+"\n Price : "+foodItem.getPrice());
            String buttonLabel = foodItem.getName();
            JButton foodItemBtn = new JButton(buttonLabel);
            Dimension buttonSize = new Dimension(400, 40);
            foodItemBtn.setPreferredSize(buttonSize);
            foodItemBtn.setMinimumSize(buttonSize);
            foodItemBtn.setMaximumSize(buttonSize);
            foodItemBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            foodItemPanel.add(foodItemBtn);
            foodItemPanel.add(itemDescriptionArea);
            foodItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            foodItemBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    shoppingCart.addFoodItem(foodItem,1);
                    updateShoppingCart();
                }
            });

           FoodItemsCard.add(foodItemPanel);
           FoodItemsCard.add(Box.createVerticalStrut(5));

        }



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

    public void updateShoppingCart(){

        Map<FoodItem,Integer> items = shoppingCart.getCartItems();
        ShoppingCartPanel.setLayout(new BoxLayout(ShoppingCartPanel, BoxLayout.Y_AXIS));
        ShoppingCartPanel.removeAll();

        orderButton.setEnabled(!items.isEmpty());
        restaurantLock=!items.isEmpty();



        for(Map.Entry<FoodItem,Integer> entry : items.entrySet()){
            JPanel orderedItemPanel = new JPanel();
            orderedItemPanel.setLayout(new BoxLayout(orderedItemPanel, BoxLayout.X_AXIS));
            Dimension pannelSize = new Dimension(300, 50);
            orderedItemPanel.setPreferredSize(pannelSize);
            orderedItemPanel.setMaximumSize(pannelSize);
            orderedItemPanel.setMinimumSize(pannelSize);

            JButton editButton = new JButton("...");
            Dimension editBtnSize = new Dimension(60, 50);


            editButton.setPreferredSize(editBtnSize);
            editButton.setMaximumSize(editBtnSize);
            editButton.setMinimumSize(editBtnSize);

            editButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateOrderedItem(entry.getKey());
                }
            });

            double itemPrice = shoppingCart.getTotalItemPrice(entry);
            String itemLabel = MessageFormat.format("<html><b>{0}</b><br>Quantity : {1}  | Total Price : {2}</html>",entry.getKey().getName(),entry.getValue(),itemPrice);
            JButton itemBtn = new JButton(itemLabel);

            itemBtn.setEnabled(true);
            Dimension buttonSize = new Dimension(240, 50);
            itemBtn.setPreferredSize(buttonSize);
            itemBtn.setMinimumSize(buttonSize);
            itemBtn.setMaximumSize(buttonSize);


            orderedItemPanel.add(itemBtn);
            orderedItemPanel.add(editButton);
            ShoppingCartPanel.add(orderedItemPanel);
            ShoppingCartPanel.add(Box.createVerticalStrut(5));

        }


        orderButton.setText("Order | Total : "+ shoppingCart.calculateTotalPrice() );
        ShoppingPanel.repaint();
        ShoppingPanel.revalidate();

    }

    public void updateOrderedItem(FoodItem foodItem){

         JPanel panel = new JPanel(new GridLayout(3, 1));

        // Create radio buttons
        JRadioButton removeItemOption = new JRadioButton("Remove Item");
        JRadioButton modifyQuantityOption = new JRadioButton("Modify Quantity");
        JTextField quantityField = new JTextField();
        quantityField.setEnabled(false); // Initially disabled

        // Group the radio buttons so that only one can be selected
        ButtonGroup group = new ButtonGroup();
        group.add(removeItemOption);
        group.add(modifyQuantityOption);

        // Add action listener to enable the quantity input only if "Modify Quantity" is selected
        modifyQuantityOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantityField.setEnabled(true); // Enable the text field
            }
        });

        removeItemOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quantityField.setEnabled(false); // Disable the text field
            }
        });

        // Add the components to the panel
        panel.add(removeItemOption);
        panel.add(modifyQuantityOption);
        panel.add(quantityField);

        // Display the custom JOptionPane
        int result = JOptionPane.showConfirmDialog(null, panel, "Edit Item", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Handle the user's selection
        if (result == JOptionPane.OK_OPTION) {
            if (removeItemOption.isSelected()) {
                shoppingCart.removeFoodItem(foodItem);
            } else if (modifyQuantityOption.isSelected()) {
                String newQuantity = quantityField.getText();
                shoppingCart.modifyQuantity(foodItem,Integer.parseInt(newQuantity));
                if (!newQuantity.isEmpty()) {
                    System.out.println("Modify Quantity selected with new quantity: " + newQuantity);
                } else {
                    System.out.println("Invalid quantity entered");
                }
            }

            updateShoppingCart();
        } else {
            System.out.println("Edit cancelled");
        }

    }

    public String updateOrderPriceLabel(double price){

        return String.format("Order | Total : %.2f",price);
    }
    public static void main(String[] args) throws Exception {
        DataStorageServices.getInstance().initData();
        new ClientInterface("C-100001");



    }

}
