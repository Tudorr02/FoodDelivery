    import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Testclass {

    public static void main(String[] args) {
        JButton orderButton = new JButton("Show Order Dialog");
        JFrame frame = new JFrame("Order Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);
        frame.add(orderButton);
        frame.setVisible(true);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
                JDialog dialog = new JDialog(frame, "Order Type", true);
                dialog.setSize(400, 200);
                dialog.setLayout(new BorderLayout());

                // Create a panel to hold radio buttons and text field
                JPanel panel = new JPanel(new GridLayout(3, 1));

                // Create radio buttons
                JRadioButton option1 = new JRadioButton("Delivery (payment methods: card) + 10%");
                JRadioButton option2 = new JRadioButton("Pick up (available payment methods: card, cash)");

                // Group the radio buttons to ensure only one can be selected
                ButtonGroup group = new ButtonGroup();
                group.add(option1);
                group.add(option2);

                // Create the JTextField with a placeholder
                JTextField deliveryDiscountField = new JTextField(8);
                deliveryDiscountField.setMaximumSize(new Dimension(100, 20));
                String placeholder = "Delivey Discount Code";
                deliveryDiscountField.setText(placeholder);
                deliveryDiscountField.setForeground(Color.GRAY); // Set text color to gray
                deliveryDiscountField.addFocusListener(new java.awt.event.FocusAdapter() {
                    @Override
                    public void focusGained(java.awt.event.FocusEvent e) {
                        // Remove placeholder text when field gains focus
                        if (deliveryDiscountField.getText().equals(placeholder)) {
                            deliveryDiscountField.setText("");
                            deliveryDiscountField.setForeground(Color.BLACK); // Set text color to black
                        }
                    }

                    @Override
                    public void focusLost(java.awt.event.FocusEvent e) {
                        // Restore placeholder text if the field is empty when focus is lost
                        if (deliveryDiscountField.getText().isEmpty()) {
                            deliveryDiscountField.setForeground(Color.GRAY); // Set text color back to gray
                            deliveryDiscountField.setText(placeholder);
                        }
                    }
                });

                // Add ActionListeners to radio buttons to enable or disable the promo code field
                option1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryDiscountField.setEnabled(true);
                    }
                });

                option2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        deliveryDiscountField.setEnabled(false);
                    }
                });

                // Add components to the panel
                panel.add(option1);
                panel.add(deliveryDiscountField);
                panel.add(option2);

                // Create the "Order" button with a green background
                JButton orderBtn = new JButton("Order");
                orderBtn.setBackground(Color.GREEN);
                orderBtn.setOpaque(true);
                orderBtn.setBorderPainted(false);
                orderBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Handle order button click
                        if (option1.isSelected()) {
                            System.out.println("Option 1 selected");
                        } else if (option2.isSelected()) {
                            System.out.println("Option 2 selected");
                        } else {
                            System.out.println("No option selected");
                        }
                        String discount = deliveryDiscountField.getText();
                        System.out.println("Discount: " + discount);
                        dialog.dispose();
                    }
                });

                // Create the "Cancel" button
                JButton cancelBtn = new JButton("Cancel");
                cancelBtn.addActionListener(ev -> dialog.dispose());

                // Add the buttons to a separate panel
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                buttonPanel.add(orderBtn);
                buttonPanel.add(cancelBtn);

                // Add components to the dialog
                dialog.add(panel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);

                // Show the dialog
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
            }
        });

    }
}
