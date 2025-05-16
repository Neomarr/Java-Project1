package furniture;

import javax.swing.*;
import java.awt.*;

public class PaymentPageDialog extends JDialog {
    private JComboBox<String> paymentMethodCombo;
    private JPanel paymentDetailsPanel;
    private JTextField cardNumberField, expiryField, cvvField;
    private JTextField bankNameField, accountNumberField;
    private JTextField paymayaNumberField;
    private PaymentResult paymentResult = null;

    public PaymentPageDialog(JFrame parent, double totalAmount) {
        super(parent, "Select Payment Method", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(0xFFF5E4));
        setContentPane(mainPanel);

        JPanel paymentSelectorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        paymentMethodCombo = new JComboBox<>(new String[]{"Cash on Delivery", "Credit Card", "Bank Transfer", "PayMaya"});
        paymentMethodCombo.addActionListener(e -> updatePaymentDetails());
        paymentSelectorPanel.add(new JLabel("Select Payment Method:"));
        paymentSelectorPanel.add(paymentMethodCombo);
        mainPanel.add(paymentSelectorPanel, BorderLayout.NORTH);

        paymentDetailsPanel = new JPanel(new CardLayout());
        paymentDetailsPanel.add(getCashPanel(), "Cash on Delivery");
        paymentDetailsPanel.add(getCardPanel(), "Credit Card");
        paymentDetailsPanel.add(getBankPanel(), "Bank Transfer");
        paymentDetailsPanel.add(getPayMayaPanel(), "PayMaya");
        mainPanel.add(paymentDetailsPanel, BorderLayout.CENTER);

        JButton confirmBtn = new JButton("Confirm Payment");
        confirmBtn.addActionListener(e -> confirmPayment(totalAmount));
        JPanel btnPanel = new JPanel();
        btnPanel.add(confirmBtn);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        updatePaymentDetails();
    }

    private void updatePaymentDetails() {
        String selected = (String) paymentMethodCombo.getSelectedItem();
        CardLayout cl = (CardLayout) paymentDetailsPanel.getLayout();
        cl.show(paymentDetailsPanel, selected);
    }

    private JPanel getCashPanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Cash on Delivery selected. Please prepare cash."));
        return panel;
    }
    private JPanel getCardPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(new JLabel("Card Number:"));
        cardNumberField = new JTextField(15);
        panel.add(cardNumberField);
        panel.add(new JLabel("Expiry Date:"));
        expiryField = new JTextField(7);
        panel.add(expiryField);
        panel.add(new JLabel("CVV:"));
        cvvField = new JTextField(4);
        panel.add(cvvField);
        return panel;
    }
    private JPanel getBankPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.add(new JLabel("Bank Name:"));
        bankNameField = new JTextField(15);
        panel.add(bankNameField);
        panel.add(new JLabel("Account Number:"));
        accountNumberField = new JTextField(15);
        panel.add(accountNumberField);
        return panel;
    }
    private JPanel getPayMayaPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
        panel.add(new JLabel("PayMaya Number:"));
        paymayaNumberField = new JTextField(15);
        panel.add(paymayaNumberField);
        return panel;
    }

    private void confirmPayment(double totalAmount) {
        String method = (String) paymentMethodCombo.getSelectedItem();
        String paymentInfo = "";
        switch (method) {
            case "Cash on Delivery":
                paymentInfo = "";
                break;
            case "Credit Card":
                if (cardNumberField.getText().trim().isEmpty() || expiryField.getText().trim().isEmpty() || cvvField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All credit card details required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                paymentInfo = String.format("Card: ****%s, Exp: %s", 
                    cardNumberField.getText().length() > 4 ? cardNumberField.getText().substring(cardNumberField.getText().length()-4) : cardNumberField.getText(), 
                    expiryField.getText());
                break;
            case "Bank Transfer":
                if (bankNameField.getText().trim().isEmpty() || accountNumberField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All bank details required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                paymentInfo = String.format("Bank: %s, Acc#: %s", bankNameField.getText(), accountNumberField.getText());
                break;
            case "PayMaya":
                if (paymayaNumberField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "PayMaya number required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                paymentInfo = String.format("PayMaya #: %s", paymayaNumberField.getText());
                break;
        }
        paymentResult = new PaymentResult(method, paymentInfo);
        dispose();
    }

    public PaymentResult showDialog() {
        setVisible(true); // modal
        return paymentResult;
    }
}