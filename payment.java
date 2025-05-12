
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PurchasingSystem extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    
    double totalAmount = 0;
    String selectedPayment = "";
    String paymentDetails = "";

    JTextArea receiptArea;

    public PurchasingSystem() {
        setTitle("Multi-Step Purchasing System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(productPanel(), "product");
        mainPanel.add(paymentMethodPanel(), "paymentMethod");
        mainPanel.add(onlineFormPanel(), "onlineForm");
        mainPanel.add(confirmationPanel(), "confirmation");
        mainPanel.add(receiptPanel(), "receipt");

        add(mainPanel);
        setVisible(true);
    }

    //Product selection
    private JPanel productPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Select Products", SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        JTextArea cartArea = new JTextArea(10, 30);
        cartArea.setEditable(false);
        JPanel center = new JPanel();
        String[] items = {"Paper - $10", "Ink - $20", "Pen - $5"};
        int[] prices = {10, 20, 5};
        for (int i = 0; i < items.length; i++) {
            final int index = i; 
            int price = prices[index];
            JButton btn = new JButton(items[index]);
            btn.addActionListener(e -> {
                cartArea.append(items[index] + "\n");
                totalAmount += price;
            });
            center.add(btn);
        }


        panel.add(center, BorderLayout.CENTER);
        panel.add(new JScrollPane(cartArea), BorderLayout.EAST);

        JButton calcBtn = new JButton("Calculate Total & Continue");
        calcBtn.addActionListener(e -> {
            cardLayout.show(mainPanel, "paymentMethod");
        });

        panel.add(calcBtn, BorderLayout.SOUTH);
        return panel;
    }

    //payment method
    private JPanel paymentMethodPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JLabel label = new JLabel("Choose Payment Method", SwingConstants.CENTER);
        panel.add(label);

        JButton cod = new JButton("Cash on Delivery");
        JButton online = new JButton("Online Transaction");

        cod.addActionListener(e -> {
            selectedPayment = "Cash on Delivery";
            cardLayout.show(mainPanel, "confirmation");
        });

        online.addActionListener(e -> {
            selectedPayment = "Online Transaction";
            cardLayout.show(mainPanel, "onlineForm");
        });

        panel.add(cod);
        panel.add(online);
        return panel;
    }

    //Online payment form
    private JPanel onlineFormPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Select Online Payment Method", SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);

        JPanel options = new JPanel();
        JButton credit = new JButton("Credit Card");
        JButton bank = new JButton("Bank");
        JButton paymaya = new JButton("PayMaya");

        options.add(credit);
        options.add(bank);
        options.add(paymaya);
        panel.add(options, BorderLayout.CENTER);

        credit.addActionListener(e -> showForm("Credit Card"));
        bank.addActionListener(e -> showForm("Bank"));
        paymaya.addActionListener(e -> showForm("PayMaya"));

        return panel;
    }

    private void showForm(String method) {
        JFrame formFrame = new JFrame(method + " Info");
        formFrame.setSize(300, 300);
        JPanel form = new JPanel(new GridLayout(7, 1));

        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        JTextField field3 = new JTextField();

        form.add(new JLabel(method + " Payment Form"));

        switch (method) {
            case "Credit Card":
                form.add(new JLabel("Card Holder Name"));
                form.add(field1);
                form.add(new JLabel("Card Number"));
                form.add(field2);
                form.add(new JLabel("CVV"));
                form.add(field3);
                break;
            case "Bank":
                form.add(new JLabel("Bank Name"));
                form.add(field1);
                form.add(new JLabel("Account Number"));
                form.add(field2);
                form.add(new JLabel("Account Holder Name"));
                form.add(field3);
                break;
            case "PayMaya":
                form.add(new JLabel("Account Name"));
                form.add(field1);
                form.add(new JLabel("Phone Number"));
                form.add(field2);
                break;
        }

        JButton proceed = new JButton("Proceed");
        proceed.addActionListener(e -> {
            
            if ((method.equals("Credit Card") || method.equals("Bank")) &&
                (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty() || field3.getText().trim().isEmpty())) {
                JOptionPane.showMessageDialog(formFrame, "Error: Please fill all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (method.equals("PayMaya") &&
                (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty())) {
                JOptionPane.showMessageDialog(formFrame, "Error: Please fill all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            paymentDetails = method + " Info:\n" +
                    field1.getText() + "\n" +
                    field2.getText() + "\n" +
                    (field3.getText().isEmpty() ? "" : field3.getText());
            formFrame.dispose();
            cardLayout.show(mainPanel, "confirmation");
        });

        form.add(proceed);
        formFrame.add(form);
        formFrame.setVisible(true);
    }

    //Confirmation
    private JPanel confirmationPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea summary = new JTextArea();
        summary.setEditable(false);

        JButton next = new JButton("Continue to Receipt");

        next.addActionListener(e -> {
            String ref = "TXN" + new Random().nextInt(999999);
            receiptArea.setText("Reference No: " + ref + "\n");
            receiptArea.append("Payment Method: " + selectedPayment + "\n");
            if (!paymentDetails.isEmpty()) {
                receiptArea.append(paymentDetails + "\n");
            }
            receiptArea.append("Total: $" + totalAmount);
            cardLayout.show(mainPanel, "receipt");
        });

        panel.add(new JLabel("Confirm Your Payment Info", SwingConstants.CENTER), BorderLayout.NORTH);
        panel.add(summary, BorderLayout.CENTER);
        panel.add(next, BorderLayout.SOUTH);

        panel.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                summary.setText("Payment Method: " + selectedPayment + "\n");
                summary.append("Total: $" + totalAmount + "\n");
                if (!paymentDetails.isEmpty()) {
                    summary.append("Details:\n" + paymentDetails + "\n");
                }
            }
        });

        return panel;
    }

    //Receipt
    private JPanel receiptPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);

        JButton finish = new JButton("Finish Purchase");
        finish.addActionListener(e -> JOptionPane.showMessageDialog(this, "Purchase Complete!"));

        panel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        panel.add(finish, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        new PurchasingSystem();
    }
        
     
            
}





