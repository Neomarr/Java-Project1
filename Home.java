package furniture;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Home {
	private static JTextField discountedPriceField;
	private static JTextField subtotalField;
	private static JTextField totalField;

    private static List<Purchase> purchases = new ArrayList<>();

    public static void main(String[] args) {
    	
    	        JFrame frame = new JFrame("Air Supplies");
    	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	        frame.setSize(900, 600);
    	        frame.setLocationRelativeTo(null);

    	        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    	        mainPanel.setBackground(new Color(0xFFF5E4));
    	        frame.setContentPane(mainPanel);

    	        // TOP PANEL
    	        JPanel topPanel = new JPanel();
    	        topPanel.setBackground(new Color(0x6A9C89));
    	        topPanel.setPreferredSize(new Dimension(100, 70));
    	        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
    	        JLabel titleLabel = new JLabel("Air Supplies");
    	        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    	        titleLabel.setForeground(Color.WHITE);
    	        topPanel.add(titleLabel);
    	        mainPanel.add(topPanel, BorderLayout.NORTH);

    	        // RIGHT PANEL (Receipt/Bill)
    	        JPanel rightPanel = new JPanel();
    	        rightPanel.setPreferredSize(new Dimension(250, 100));
    	        rightPanel.setBackground(new Color(0x6A9C89));
    	        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

    	        JLabel receiptTitle = new JLabel("Receipt");
    	        receiptTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
    	        receiptTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    	        receiptTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
    	        rightPanel.add(receiptTitle);

    	        JTextArea receiptArea = new JTextArea(10, 20);
    	        receiptArea.setEditable(false);
    	        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
    	        JScrollPane scroll1 = new JScrollPane(receiptArea);
    	        scroll1.setBorder(BorderFactory.createTitledBorder("Receipt"));
    	        scroll1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	        rightPanel.add(scroll1);

    	        JTextArea receiptArea2 = new JTextArea(10, 20);
    	        receiptArea2.setEditable(false);
    	        receiptArea2.setFont(new Font("Monospaced", Font.PLAIN, 12));
    	        JScrollPane scroll2 = new JScrollPane(receiptArea2);
    	        scroll2.setBorder(BorderFactory.createTitledBorder("Bill"));
    	        scroll2.setAlignmentX(Component.CENTER_ALIGNMENT);
    	        rightPanel.add(scroll2);


    	        mainPanel.add(rightPanel, BorderLayout.EAST);

    	        // CENTER PANEL
    	        JPanel centerPanel = new JPanel();
    	        centerPanel.setOpaque(false);
    	        centerPanel.setLayout(new BorderLayout());

    	        JPanel scrollContent = new JPanel();
    	        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
    	        scrollContent.setOpaque(false);

    	        JLabel sectionLabel = new JLabel("Paper");
    	        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
    	        sectionLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
    	        sectionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    	        scrollContent.add(sectionLabel);

    	        // Load and scale images
    	        ImageIcon shortBondImg = new ImageIcon(Home.class.getResource("/furniture/short bond.jpg"));
    	        ImageIcon PastelImg = new ImageIcon(Home.class.getResource("/furniture/Pastel.jpg"));
    	        ImageIcon Kraft2Img = new ImageIcon(new ImageIcon(Home.class.getResource("/furniture/Kraft2.jpg")).getImage().getScaledInstance(210, 200, Image.SCALE_SMOOTH));
    	        ImageIcon Art2Img = new ImageIcon(new ImageIcon(Home.class.getResource("/furniture/Art2.jpg")).getImage().getScaledInstance(210, 200, Image.SCALE_SMOOTH));
    	        ImageIcon CrepeImg = new ImageIcon(new ImageIcon(Home.class.getResource("/furniture/Crepe.jpg")).getImage().getScaledInstance(210, 200, Image.SCALE_SMOOTH));

    	        JPanel productGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
    	        productGrid.setOpaque(false);
    	        productGrid.add(createProductCard("Pastel Paper", "10 pcs", "Stocks 20", receiptArea, PastelImg, 60.00));
    	        productGrid.add(createProductCard("Short Bond Paper", "Hardcopy / 100 pcs", "Stocks 20", receiptArea, shortBondImg, 250.00));
    	        productGrid.add(createProductCard("Crêpe paper", "10 pcs", "Stocks 20", receiptArea, CrepeImg, 35.00));
    	        productGrid.add(createProductCard("Kraft paper", "10 pcs", "Stocks 20", receiptArea, Kraft2Img, 75.00));
    	        productGrid.add(createProductCard("Art paper", "10 pcs", "Stocks 20", receiptArea, Art2Img, 80.00));

    	        scrollContent.add(productGrid);
    	        JScrollPane scrollPane = new JScrollPane(scrollContent);
    	        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    	        centerPanel.add(scrollPane, BorderLayout.CENTER);

    	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    	        buttonPanel.setBackground(new Color(0xFFF5E4));
    	        buttonPanel.add(createButton("Total", receiptArea, receiptArea2));
    	        buttonPanel.add(createButton("Receipt", receiptArea, receiptArea2));
    	        buttonPanel.add(createButton("Reset", receiptArea, receiptArea2));

    	        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
    	        mainPanel.add(centerPanel, BorderLayout.CENTER);

    	        frame.setVisible(true);
    	    }

    private static JPanel createProductCard(String title, String description, String stockStr, JTextArea receiptArea, ImageIcon image, double price) {
        int initialStock = Integer.parseInt(stockStr.replaceAll("[^0-9]", ""));
        Product product = new Product(title, description, initialStock);

        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(230, 280));
        card.setBackground(new Color(0xD3E4CD));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
        ));

        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(125, 125));
        card.add(imageLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0xD3E4CD));

        JLabel priceLabel = new JLabel(String.format("₱ %.2f", price));
        JLabel titleLabel = new JLabel(title);
        JLabel descLabel = new JLabel(description);
        JLabel stockLabel = new JLabel("Stocks " + product.stock);
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 90, 1));
        quantitySpinner.setMaximumSize(new Dimension(50, 25));

        JButton addButton = new JButton("Buy");

        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quantitySpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        addButton.setBackground(new Color(0xFFA725));
        addButton.setForeground(Color.BLACK);
        addButton.setFocusPainted(false);

        // Action for adding items to the cart
        addButton.addActionListener(e -> {
            int quantity = (Integer) quantitySpinner.getValue();
            Purchase.addPurchase(new Purchase(title, quantity, price, "NONE"));
            receiptArea.append(title + " x" + quantity + " | ₱ " + String.format("%.2f", price * quantity) + "\n");
        });

        infoPanel.add(Box.createVerticalStrut(3));
        infoPanel.add(priceLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(descLabel);
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(quantitySpinner);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(addButton);

        card.add(infoPanel, BorderLayout.CENTER);
        return card;
    }


    	    private static void addTextFieldRow(JPanel container, String labelText) {
    	        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
    	        row.setOpaque(false);
    	        JLabel label = new JLabel(labelText);
    	        JTextField textField = new JTextField(10);
    	        row.add(label);
    	        row.add(textField);
    	        container.add(row);

    	        switch (labelText) {
    	            case "Discounted Price":
    	                discountedPriceField = textField;
    	                break;
    	            case "Subtotal":
    	                subtotalField = textField;
    	                break;
    	            case "Total":
    	                totalField = textField;
    	                break;
    	        }
    	    }

    	    private static JButton createButton(String text, JTextArea receiptArea, JTextArea receiptArea2) {
    	        JButton button = new JButton(text);
    	        button.setBackground(new Color(0xFFA725));
    	        button.setForeground(Color.BLACK);
    	        button.setFocusPainted(false);

    	        if (text.equals("Reset")) {
    	            button.addActionListener(e -> {
    	                receiptArea.setText("");
    	                receiptArea2.setText("");
    	                Purchase.clearPurchases();
    	                subtotalField.setText("");
    	                discountedPriceField.setText("");
    	                totalField.setText("");
    	            });
    	        } else if (text.equals("Total")) {
    	            button.addActionListener(e -> {
    	                double subtotal = 0;
    	                double total = 0;
    	                double totalDiscounted = 0;

    	                receiptArea2.setText("");
    	                receiptArea2.append("****** Art Supplies ********\n");

    	                // Ask once for discount type
    	                String[] responses = {"PWD", "SENIOR", "STUDENT", "NONE"};
    	                String selected = (String) JOptionPane.showInputDialog(
    	                        null,
    	                        "Choose Discount Type for all items:",
    	                        "Discount Options",
    	                        JOptionPane.QUESTION_MESSAGE,
    	                        null,
    	                        responses,
    	                        responses[0]);

    	                if (selected == null) return; // Cancelled

    	                double discountRate;
    	                switch (selected.toUpperCase()) {
    	                    case "PWD":
    	                        discountRate = 0.20;
    	                        break;
    	                    case "SENIOR":
    	                        discountRate = 0.15;
    	                        break;
    	                    case "STUDENT":
    	                        discountRate = 0.10;
    	                        break;
    	                    default:
    	                        discountRate = 0.0;
    	                        break;
    	                }


    	                for (Purchase p : Purchase.getPurchases()) {
    	                    double itemSubtotal = p.price * p.quantity;
    	                    double discountAmount = itemSubtotal * discountRate;
    	                    double itemTotal = itemSubtotal - discountAmount;

    	                    subtotal += itemSubtotal;
    	                    total += itemTotal;
    	                    totalDiscounted += discountAmount;

    	                    receiptArea2.append(p.title + " x" + p.quantity +
    	                            " | ₱" + String.format("%.2f", itemTotal) + "\n");
    	                }

    	                receiptArea2.append("\nTOTAL: ₱" + String.format("%.2f", total) + "\n");
    	                receiptArea2.append("*************************************");

    	                subtotalField.setText(String.format("₱ %.2f", subtotal));
    	                discountedPriceField.setText(String.format("₱ %.2f", totalDiscounted));
    	                totalField.setText(String.format("₱ %.2f", total));
    	            });
    	        }

    	        return button;
    	    }

    	}


