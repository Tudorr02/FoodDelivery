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

            }
        });

    }
}
