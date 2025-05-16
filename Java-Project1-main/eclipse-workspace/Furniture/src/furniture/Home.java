package furniture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.io.InputStream;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Home {
	private static JTextField discountedPriceField;
    private static JTextField subtotalField;
    private static JTextField totalField;
    
    // --- Method to load and resize resource images ---
    private static ImageIcon loadAndResizeResourceImage(String path, int width, int height) {
        try {
            ImageIcon originalIcon = loadResourceImage(path);
            if (originalIcon == null) {
                return null;
            }
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (Exception e) {
            System.err.println("Error resizing image " + path + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // --- Method to load resource image from JAR/resources ---
    private static ImageIcon loadResourceImage(String path) {
        try {
            InputStream is = Home.class.getResourceAsStream(path);
            if (is == null) {
                System.err.println("Resource not found: " + path);
                return null;
            }
            BufferedImage img = javax.imageio.ImageIO.read(is);
            if (img == null) {
                System.err.println("Failed to read image: " + path);
                return null;
            }
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Error loading image " + path + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static ImageIcon createPlaceholderIcon(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, width-1, height-1);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "No Image";
        int textWidth = fm.stringWidth(text);
        g2d.drawString(text, (width - textWidth) / 2, height / 2);
        g2d.dispose();
        return new ImageIcon(image);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Air Supplies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 800);
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
        rightPanel.setPreferredSize(new Dimension(270, 100));
        rightPanel.setBackground(new Color(0x6A9C89));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JLabel receiptTitle = new JLabel("Receipt");
        receiptTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
        receiptTitle.setForeground(Color.WHITE);
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

        // CENTER PANEL (Product Grid)
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        JPanel scrollContent = new JPanel();
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
        scrollContent.setOpaque(false);

        // --- Drawing Materials ---
        JLabel drawingMaterialsLabel = new JLabel("Drawing Materials");
        drawingMaterialsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        drawingMaterialsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        scrollContent.add(drawingMaterialsLabel);

        JPanel drawingMaterialsGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        drawingMaterialsGrid.setOpaque(false);

        // Load images (replace the paths below with your actual image resource paths)
        ImageIcon archesDPImg = loadAndResizeResourceImage("/furniture/Arches DP.jpg", 210, 120);
        ImageIcon bruynzeelCPImg = loadAndResizeResourceImage("/furniture/Bruynzeel CP.jpg", 210, 120);
        ImageIcon derwentInktenseCPImg = loadAndResizeResourceImage("/furniture/Derwent_Inktense CP.jpg", 210, 120);
        ImageIcon prismacolorPremierCPImg = loadAndResizeResourceImage("/furniture/Prismacolor_Premier CP.jpg", 210, 120);
        ImageIcon sennelierPastelImg = loadAndResizeResourceImage("/furniture/Sennelier_Pastel.jpg", 210, 120);
        ImageIcon staedtlerMarsImg = loadAndResizeResourceImage("/furniture/Staedtler_Mars GP.jpg", 210, 120);
        ImageIcon strathmoreDPImg = loadAndResizeResourceImage("/furniture/Strathmore_DP.jpg", 210, 120);
        ImageIcon carandAcheLuminanceCPImg = loadAndResizeResourceImage("/furniture/Caran d’Ache_Luminance CP.jpg", 210, 120);
        ImageIcon cretacolorGnCPImg = loadAndResizeResourceImage("/furniture/Cretacolor_GnCP.jpg", 210, 120);
        ImageIcon faberCastelPolychromosCPImg = loadAndResizeResourceImage("/furniture/FaberCastel_Polychromos CP.jpg", 210, 120);

        // Fallback to placeholder if images missing
        if (archesDPImg == null) archesDPImg = createPlaceholderIcon(210, 120);
        if (bruynzeelCPImg == null) bruynzeelCPImg = createPlaceholderIcon(210, 120);
        if (derwentInktenseCPImg == null) derwentInktenseCPImg = createPlaceholderIcon(210, 120);
        if (prismacolorPremierCPImg == null) prismacolorPremierCPImg = createPlaceholderIcon(210, 120);
        if (sennelierPastelImg == null) sennelierPastelImg = createPlaceholderIcon(210, 120);
        if (staedtlerMarsImg == null) staedtlerMarsImg = createPlaceholderIcon(210, 120);
        if (strathmoreDPImg == null) strathmoreDPImg = createPlaceholderIcon(210, 120);
        if (carandAcheLuminanceCPImg == null) carandAcheLuminanceCPImg = createPlaceholderIcon(210, 120);
        if (cretacolorGnCPImg == null) cretacolorGnCPImg = createPlaceholderIcon(210, 120);
        if (faberCastelPolychromosCPImg == null) faberCastelPolychromosCPImg = createPlaceholderIcon(210, 120);

        drawingMaterialsGrid.add(createProductCard("Arches Drawing Paper", "10 pcs of drawing paper", "Stocks 20", receiptArea, archesDPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Bruynzeel Colored Pencils", "32 pcs of colored pencils", "Stocks 20", receiptArea, bruynzeelCPImg, 120.00));
        drawingMaterialsGrid.add(createProductCard("Derwent Inktense Color Pencil", "12 pcs of colored pencils", "Stocks 20", receiptArea, derwentInktenseCPImg, 110.00));
        drawingMaterialsGrid.add(createProductCard("Prismacolor Premier Colored Pencils", "120 pcs of colored pencils", "Stocks 20", receiptArea, prismacolorPremierCPImg, 280.00));
        drawingMaterialsGrid.add(createProductCard("Sennelier Pastel", "48 pcs of pastels", "Stocks 20", receiptArea, sennelierPastelImg, 120.00));
        drawingMaterialsGrid.add(createProductCard("Staedtler Mars Graphite Pencils", "8 pcs of pencils", "Stocks 20", receiptArea, staedtlerMarsImg, 90.00));
        drawingMaterialsGrid.add(createProductCard("Strathmore Drawing Paper", "100 pcs", "Stocks 20", receiptArea, strathmoreDPImg, 220.00));
        drawingMaterialsGrid.add(createProductCard("Caran d'Ache Luminance Colored Pencils", "90 pcs of colored pencils", "Stocks 20", receiptArea, carandAcheLuminanceCPImg, 300.00));
        drawingMaterialsGrid.add(createProductCard("Cretacolor Graphite and Charcoal Pencils", "20 pcs of charcoal pencils", "Stocks 20", receiptArea, cretacolorGnCPImg, 250.00));
        drawingMaterialsGrid.add(createProductCard("Faber-Castell Polychromos Colored Pencils", "200 pcs of colored pencils", "Stocks 20", receiptArea, faberCastelPolychromosCPImg, 210.00));

        scrollContent.add(drawingMaterialsGrid);

        // --- Drawing Tools ---
        JLabel drawingToolsLabel = new JLabel("Drawing Tools");
        drawingToolsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        drawingToolsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        scrollContent.add(drawingToolsLabel);

        JPanel drawingToolsGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        drawingToolsGrid.setOpaque(false);

        ImageIcon cretacolorCharBlenderImg = loadAndResizeResourceImage("/furniture/Cretacolor_CharBlender.jpg", 210, 120);
        ImageIcon derwentBStumpsImg = loadAndResizeResourceImage("/furniture/Derwent_BStumps.jpg", 210, 120);
        ImageIcon prismacolorCBlenderImg = loadAndResizeResourceImage("/furniture/Prismacolor_CBlender.jpg", 210, 120);
        ImageIcon sakuraEraserImg = loadAndResizeResourceImage("/furniture/Sakura_Eraser.jpg", 210, 120);
        if (cretacolorCharBlenderImg == null) cretacolorCharBlenderImg = createPlaceholderIcon(210, 120);
        if (derwentBStumpsImg == null) derwentBStumpsImg = createPlaceholderIcon(210, 120);
        if (prismacolorCBlenderImg == null) prismacolorCBlenderImg = createPlaceholderIcon(210, 120);
        if (sakuraEraserImg == null) sakuraEraserImg = createPlaceholderIcon(210, 120);

        drawingToolsGrid.add(createProductCard("Cretacolor Charcoal Blender", "10 pcs of charcoal blender", "Stocks 20", receiptArea, cretacolorCharBlenderImg, 80.00));
        drawingToolsGrid.add(createProductCard("Derwent Blender Stumps", "3 pcs of blender stumps", "Stocks 20", receiptArea, derwentBStumpsImg, 90.00));
        drawingToolsGrid.add(createProductCard("Prismacolor Color Blender", "3 pcs color blender", "Stocks 20", receiptArea, prismacolorCBlenderImg, 120.00));
        drawingToolsGrid.add(createProductCard("Sakura Eraser", "2 pcs of eraser", "Stocks 20", receiptArea, sakuraEraserImg, 50.00));

        scrollContent.add(drawingToolsGrid);

        // --- Painting Materials ---
        JLabel paintingMaterialsLabel = new JLabel("Painting Materials");
        paintingMaterialsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        paintingMaterialsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        scrollContent.add(paintingMaterialsLabel);

        JPanel paintingMaterialsGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        paintingMaterialsGrid.setOpaque(false);

        ImageIcon artezaAPImg = loadAndResizeResourceImage("/furniture/Arteza_AP.jpg", 210, 120);
        ImageIcon danielSmithWCImg = loadAndResizeResourceImage("/furniture/Daniel Smith_WC.jpg", 210, 120);
        ImageIcon goldenMediumImg = loadAndResizeResourceImage("/furniture/Golden_medium.jpg", 210, 120);
        ImageIcon liquitexAPImg = loadAndResizeResourceImage("/furniture/Liquitex_AP.jpg", 210, 120);
        ImageIcon maimeriClassicoOPImg = loadAndResizeResourceImage("/furniture/Maimeri_Classico OP.jpg", 210, 120);
        ImageIcon mijelloMissionGoldWCImg = loadAndResizeResourceImage("/furniture/Mijello_MissionGold WC.jpg", 210, 120);
        ImageIcon pebeoOPImg = loadAndResizeResourceImage("/furniture/Pebeo_OP.jpg", 210, 120);
        ImageIcon royalTalensRembrandtOPImg = loadAndResizeResourceImage("/furniture/RoyalTalens_Rembrandt OP.jpg", 210, 120);
        ImageIcon sennelierWCImg = loadAndResizeResourceImage("/furniture/Sennelier_WC.jpg", 210, 120);
        ImageIcon winsorNewtonOPImg = loadAndResizeResourceImage("/furniture/Winsor&Newton_OP.jpg", 210, 120);

        if (artezaAPImg == null) artezaAPImg = createPlaceholderIcon(210, 120);
        if (danielSmithWCImg == null) danielSmithWCImg = createPlaceholderIcon(210, 120);
        if (goldenMediumImg == null) goldenMediumImg = createPlaceholderIcon(210, 120);
        if (liquitexAPImg == null) liquitexAPImg = createPlaceholderIcon(210, 120);
        if (maimeriClassicoOPImg == null) maimeriClassicoOPImg = createPlaceholderIcon(210, 120);
        if (mijelloMissionGoldWCImg == null) mijelloMissionGoldWCImg = createPlaceholderIcon(210, 120);
        if (pebeoOPImg == null) pebeoOPImg = createPlaceholderIcon(210, 120);
        if (royalTalensRembrandtOPImg == null) royalTalensRembrandtOPImg = createPlaceholderIcon(210, 120);
        if (sennelierWCImg == null) sennelierWCImg = createPlaceholderIcon(210, 120);
        if (winsorNewtonOPImg == null) winsorNewtonOPImg = createPlaceholderIcon(210, 120);

        paintingMaterialsGrid.add(createProductCard("Arteza Acrylic Paint", "2 pcs of paint", "Stocks 20", receiptArea, artezaAPImg, 100.00));
        paintingMaterialsGrid.add(createProductCard("Daniel Smith Watercolor", "5 pcs of watercolor", "Stocks 20", receiptArea, danielSmithWCImg, 90.00));
        paintingMaterialsGrid.add(createProductCard("Golden Medium", "10 pcs of medium", "Stocks 20", receiptArea, goldenMediumImg, 110.00));
        paintingMaterialsGrid.add(createProductCard("Liquitex Acrylic Paint", "10 pcs of acrylic paint", "Stocks 20", receiptArea, liquitexAPImg, 120.00));
        paintingMaterialsGrid.add(createProductCard("Maimeri Classico Oilpaint", "12 pcs of oilpaint", "Stocks 20", receiptArea, maimeriClassicoOPImg, 100.00));
        paintingMaterialsGrid.add(createProductCard("Mijello Mission Gold Watercolor", "30 pcs of watercolor", "Stocks 20", receiptArea, mijelloMissionGoldWCImg, 150.00));
        paintingMaterialsGrid.add(createProductCard("Pebeo Oilpaint", "7 pcs of oilpaint", "Stocks 20", receiptArea, pebeoOPImg, 95.00));
        paintingMaterialsGrid.add(createProductCard("Royal Talens Rembrandt Oilpaint", "10 pcs of oilpaint", "Stocks 20", receiptArea, royalTalensRembrandtOPImg, 180.00));
        paintingMaterialsGrid.add(createProductCard("Sennelier Watercolor", "5 pcs of watercolor", "Stocks 20", receiptArea, sennelierWCImg, 130.00));
        paintingMaterialsGrid.add(createProductCard("Winsor & Newton Oilpaint", "10 pcs of oilpaint", "Stocks 20", receiptArea, winsorNewtonOPImg, 105.00));

        scrollContent.add(paintingMaterialsGrid);

        // --- Painting Tools ---
        JLabel paintingToolsLabel = new JLabel("Painting Tools");
        paintingToolsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        paintingToolsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        scrollContent.add(paintingToolsLabel);

        JPanel paintingToolsGrid = new JPanel(new GridLayout(0, 5, 20, 20));
        paintingToolsGrid.setOpaque(false);

        ImageIcon gamblinGamvarVarnishBrushImg = loadAndResizeResourceImage("/furniture/Gamblin Gamvar_Varnish Brush.jpg", 210, 120);
        ImageIcon liquitexPKImg = loadAndResizeResourceImage("/furniture/Liquitex_PK.jpg", 210, 120);
        ImageIcon mabefEaselImg = loadAndResizeResourceImage("/furniture/Mabef_easel.jpg", 210, 120);
        ImageIcon mimikBrushImg = loadAndResizeResourceImage("/furniture/Mimik_Brush.jpg", 210, 120);
        ImageIcon paascheAirbrushImg = loadAndResizeResourceImage("/furniture/Paasche_Airbrush.jpg", 210, 120);
        ImageIcon princetonVelvetouchBrushImg = loadAndResizeResourceImage("/furniture/Princeton_Velvetouch Brush.jpg", 210, 120);
        ImageIcon rgmWoodenPKImg = loadAndResizeResourceImage("/furniture/RGM_Wooden PK.jpg", 210, 120);
        ImageIcon silverBrushGrandPrixBristleBrushImg = loadAndResizeResourceImage("/furniture/SilverBrushGrandPrix_BristleBrush.jpg", 210, 120);

        if (gamblinGamvarVarnishBrushImg == null) gamblinGamvarVarnishBrushImg = createPlaceholderIcon(210, 120);
        if (liquitexPKImg == null) liquitexPKImg = createPlaceholderIcon(210, 120);
        if (mabefEaselImg == null) mabefEaselImg = createPlaceholderIcon(210, 120);
        if (mimikBrushImg == null) mimikBrushImg = createPlaceholderIcon(210, 120);
        if (paascheAirbrushImg == null) paascheAirbrushImg = createPlaceholderIcon(210, 120);
        if (princetonVelvetouchBrushImg == null) princetonVelvetouchBrushImg = createPlaceholderIcon(210, 120);
        if (rgmWoodenPKImg == null) rgmWoodenPKImg = createPlaceholderIcon(210, 120);
        if (silverBrushGrandPrixBristleBrushImg == null) silverBrushGrandPrixBristleBrushImg = createPlaceholderIcon(210, 120);

        paintingToolsGrid.add(createProductCard("Gamblin Gamvar Varnish Brush", "2 pcs of brush", "Stocks 20", receiptArea, gamblinGamvarVarnishBrushImg, 100.00));
        paintingToolsGrid.add(createProductCard("Liquitex Palette Knives", "4 pcs of palette knives", "Stocks 20", receiptArea, liquitexPKImg, 130.00));
        paintingToolsGrid.add(createProductCard("Mabef Easel", "1 pcs of easel", "Stocks 20", receiptArea, mabefEaselImg, 160.00));
        paintingToolsGrid.add(createProductCard("Mimik Brush", "5 pcs of brush", "Stocks 20", receiptArea, mimikBrushImg, 120.00));
        paintingToolsGrid.add(createProductCard("Paasche Airbrush", "1 pcs of airbrush", "Stocks 20", receiptArea, paascheAirbrushImg, 200.00));
        paintingToolsGrid.add(createProductCard("Princeton Velvetouch Brush", "4 pcs of brush", "Stocks 20", receiptArea, princetonVelvetouchBrushImg, 160.00));
        paintingToolsGrid.add(createProductCard("RGM Wooden Palette Knives", "15 pcs of palette knives", "Stocks 20", receiptArea, rgmWoodenPKImg, 100.00));
        paintingToolsGrid.add(createProductCard("Silver Brush Grand Prix Bristle Brush", "13 pcs of brush", "Stocks 20", receiptArea, silverBrushGrandPrixBristleBrushImg, 145.00));

        scrollContent.add(paintingToolsGrid);

        JScrollPane scrollPane = new JScrollPane(scrollContent);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // --- BOTTOM PANEL (NEW): Contains buttons (at left) and summary (at right) ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(0xFFF5E4));

        // Button panel at bottom left
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttonPanel.setBackground(new Color(0xFFF5E4));
        buttonPanel.add(createButton("Total", receiptArea, receiptArea2, frame));
        buttonPanel.add(createButton("Receipt", receiptArea, receiptArea2, frame));
        buttonPanel.add(createButton("Reset", receiptArea, receiptArea2, frame));
        bottomPanel.add(buttonPanel, BorderLayout.WEST);

        // Summary panel at bottom right
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(0xFFF5E4));
        addTextFieldRow(summaryPanel, "Subtotal");
        addTextFieldRow(summaryPanel, "Discounted Price");
        addTextFieldRow(summaryPanel, "Total");
        bottomPanel.add(summaryPanel, BorderLayout.EAST);

        // Add bottom panel to main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private static JPanel createProductCard(String title, String description, String stockStr, JTextArea receiptArea, ImageIcon image, double price) {
        int initialStock = Integer.parseInt(stockStr.replaceAll("[^0-9]", ""));
        Product product = new Product(title, description, initialStock);

        JPanel card = new JPanel(new BorderLayout());
        card.setPreferredSize(new Dimension(210, 260));
        card.setBackground(new Color(0xD3E4CD));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)
        ));

        JLabel imageLabel = new JLabel(image == null ? createPlaceholderIcon(210, 120) : image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(125, 120));
        card.add(imageLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0xD3E4CD));

        JLabel priceLabel = new JLabel(String.format("₱ %.2f", price));
        JLabel titleLabel = new JLabel(title);
        JLabel descLabel = new JLabel(description);
        JLabel stockLabel = new JLabel("Stocks " + product.getStock());
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, product.getStock(), 1));
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

        addButton.addActionListener(e -> {
            int quantity = (Integer) quantitySpinner.getValue();
            if (quantity > product.getStock()) {
                JOptionPane.showMessageDialog(null, "Not enough stock available!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Purchase.addPurchase(new Purchase(title, quantity, price, "NONE"));
            receiptArea.append(title + " x" + quantity + " | ₱ " + String.format("%.2f", price * quantity) + "\n");
            product.setStock(product.getStock() - quantity);
            stockLabel.setText("Stocks " + product.getStock());
            quantitySpinner.setModel(new SpinnerNumberModel(1, 1, product.getStock() > 0 ? product.getStock() : 1, 1));
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
        textField.setEditable(false);
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

    private static JButton createButton(String text, JTextArea receiptArea, JTextArea receiptArea2, JFrame parentFrame) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0xFFA725));
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);

        button.addActionListener(e -> {
            if (text.equalsIgnoreCase("Total")) {
                // (same as before)
                double subtotal = 0.0;
                List<Purchase> purchases = Purchase.getPurchases();
                StringBuilder bill = new StringBuilder();
                for (Purchase p : purchases) {
                    double lineTotal = p.getQuantity() * p.getPrice();
                    subtotal += lineTotal;
                    bill.append(p.getProductName())
                            .append(" x").append(p.getQuantity())
                            .append(" | ₱ ").append(String.format("%.2f", lineTotal)).append("\n");
                }
                double discount = 0.0;
                double discountedPrice = subtotal;
                double total = discountedPrice;
                if (subtotalField != null) subtotalField.setText(String.format("%.2f", subtotal));
                if (discountedPriceField != null) discountedPriceField.setText(String.format("%.2f", discountedPrice));
                if (totalField != null) totalField.setText(String.format("%.2f", total));
                receiptArea2.setText("");
                receiptArea2.append(bill.toString());
                receiptArea2.append("\nTOTAL: ₱ " + String.format("%.2f", total));
            } else if (text.equalsIgnoreCase("Reset")) {
                Purchase.clearPurchases();
                receiptArea.setText("");
                receiptArea2.setText("");
                if (subtotalField != null) subtotalField.setText("");
                if (discountedPriceField != null) discountedPriceField.setText("");
                if (totalField != null) totalField.setText("");
            } else if (text.equalsIgnoreCase("Receipt")) {
                // Step 1: Prompt for customer info and discount
                JPanel panel = new JPanel(new GridLayout(0, 1));
                JTextField nameField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField numberField = new JTextField();

                panel.add(new JLabel("Customer Name:"));
                panel.add(nameField);

                panel.add(new JLabel("Customer Address:"));
                panel.add(addressField);

                panel.add(new JLabel("Customer Contact Number:"));
                panel.add(numberField);

                String[] discountOptions = {"None", "PWD (20%)", "Senior (15%)", "Student (10%)"};
                JComboBox<String> discountBox = new JComboBox<>(discountOptions);
                panel.add(new JLabel("Discount:"));
                panel.add(discountBox);

                int result = JOptionPane.showConfirmDialog(parentFrame, panel, "Enter Customer Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    String customerName = nameField.getText().trim();
                    String customerAddress = addressField.getText().trim();
                    String customerNumber = numberField.getText().trim();
                    int discountType = discountBox.getSelectedIndex();
                    double discountRate = 0.0;
                    String discountLabel = "None";
                    if (discountType == 1) { discountRate = 0.20; discountLabel = "PWD (20%)"; }
                    else if (discountType == 2) { discountRate = 0.15; discountLabel = "Senior (15%)"; }
                    else if (discountType == 3) { discountRate = 0.10; discountLabel = "Student (10%)"; }

                    // Step 2: After entering customer info, show payment options
                    // MODIFIED: Pass the parentFrame as second parameter
                    PaymentPage paymentPage = new PaymentPage(getCurrentTotal(subtotalField), parentFrame);
                    paymentPage.setVisible(true);
                    paymentPage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Step 3: Show receipt after payment confirmation (you may want to integrate this step further)
                    // For now, after paymentPage is closed, show the receipt dialog as before:
                    // (Optional: you may want to listen for an event from PaymentPage before showing receipt)
                    // --- Receipt Generation (same as before, but moved after paymentPage) ---
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String dateTimeString = now.format(formatter);

                    double subtotal = 0.0;
                    List<Purchase> purchases = Purchase.getPurchases();
                    StringBuilder bill = new StringBuilder();
                    bill.append("****************** AIR SUPPLIES RECEIPT ******************\n");
                    bill.append("Date/Time: ").append(dateTimeString).append("\n");
                    bill.append("Customer: ").append(customerName).append("\n");
                    bill.append("Address: ").append(customerAddress).append("\n");
                    bill.append("Contact Number: ").append(customerNumber).append("\n");
                    bill.append("Discount: ").append(discountLabel).append("\n\n");
                    bill.append("PURCHASES:\n");
                    for (Purchase p : purchases) {
                        double lineTotal = p.getQuantity() * p.getPrice();
                        subtotal += lineTotal;
                        bill.append(p.getProductName())
                                .append(" x").append(p.getQuantity())
                                .append(" | ₱ ").append(String.format("%.2f", lineTotal)).append("\n");
                    }
                    double discountAmount = subtotal * discountRate;
                    double discountedPrice = subtotal - discountAmount;
                    double total = discountedPrice;

                    if (subtotalField != null) subtotalField.setText(String.format("%.2f", subtotal));
                    if (discountedPriceField != null) discountedPriceField.setText(String.format("%.2f", discountedPrice));
                    if (totalField != null) totalField.setText(String.format("%.2f", total));

                    bill.append("\n----------------------------------\n");
                    bill.append("Subtotal: ₱ ").append(String.format("%.2f", subtotal)).append("\n");
                    bill.append("Discount: ₱ ").append(String.format("%.2f", discountAmount)).append("\n");
                    bill.append("TOTAL: ₱ ").append(String.format("%.2f", total)).append("\n");
                    bill.append("Thank you for shopping with Air Supplies!\n");
                    bill.append("*******************************************************");

                    receiptArea2.setText(bill.toString());

                    JTextArea receiptDisplayArea = new JTextArea(bill.toString());
                    receiptDisplayArea.setEditable(false);
                    receiptDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                    JScrollPane scrollPane = new JScrollPane(receiptDisplayArea);
                    scrollPane.setPreferredSize(new Dimension(400, 500));
                    JOptionPane.showMessageDialog(parentFrame, scrollPane, "Air Supplies Receipt", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        return button;
    }
    // Helper to get current total from text field
    private static double getCurrentTotal(JTextField totalField) {
        try {
            String value = totalField.getText().replace("₱", "").trim();
            return Double.parseDouble(value.isEmpty() ? "0" : value);
        } catch (Exception e) {
            return 0.0;
        }
    }
}