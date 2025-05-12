package furniture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class Home {
    private static JTextField discountedPriceField;
    private static JTextField subtotalField;
    private static JTextField totalField;

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

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout());

        JPanel scrollContent = new JPanel();
        scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.Y_AXIS));
        scrollContent.setOpaque(false);
        
        // Using a placeholder image for backup
        ImageIcon placeholderIcon = createPlaceholderIcon(210, 200);
        
        //Drawing Materials
        JLabel drawingMaterialsLabel = new JLabel("Drawing Materials");
        drawingMaterialsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        drawingMaterialsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        drawingMaterialsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollContent.add(drawingMaterialsLabel);

        JPanel drawingMaterialsGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        drawingMaterialsGrid.setOpaque(false);
        
        ImageIcon archesDPImg = loadAndResizeResourceImage("/furniture/Arches DP.jpg", 210, 200);
        ImageIcon bruynzeelCPImg = loadAndResizeResourceImage("/furniture/Bruynzeel CP.jpg", 210, 200);
        ImageIcon derwentInktenseCPImg = loadAndResizeResourceImage("/furniture/Derwent_Inktense CP.jpg", 210, 200);
        ImageIcon prismacolorPremierCPImg = loadAndResizeResourceImage("/furniture/Prismacolor_Premier CP.jpg", 210, 200);
        ImageIcon sennelierPastelImg = loadAndResizeResourceImage("/furniture/Sennelier_Pastel.jpg", 210, 200);
        ImageIcon staedtlerMarsImg = loadAndResizeResourceImage("/furniture/Staedtler_Mars GP.jpg", 210, 200);
        ImageIcon strathmoreDPImg = loadAndResizeResourceImage("/furniture/Strathmore_DP.jpg", 210, 200);
        ImageIcon carandAcheLuminanceCPImg = loadAndResizeResourceImage("/furniture/Caran d’Ache_Luminance CP.jpg", 210, 200);
        ImageIcon cretacolorGnCPImg = loadAndResizeResourceImage("/furniture/Cretacolor_GnCP.jpg", 210, 200);
        ImageIcon faberCastelPolychromosCPImg = loadAndResizeResourceImage("/furniture/FaberCastel_Polychromos CP.jpg", 210, 200);
        
        // Fall back to placeholders if needed
        if (archesDPImg == null) archesDPImg = createPlaceholderIcon(210, 200);
        if (bruynzeelCPImg == null) bruynzeelCPImg = createPlaceholderIcon(210, 200);
        if (derwentInktenseCPImg == null) derwentInktenseCPImg = createPlaceholderIcon(210, 200);
        if (prismacolorPremierCPImg == null) prismacolorPremierCPImg = createPlaceholderIcon(210, 200);
        if (sennelierPastelImg == null) sennelierPastelImg = createPlaceholderIcon(210, 200);
        if (staedtlerMarsImg == null) staedtlerMarsImg = createPlaceholderIcon(210, 200);
        if (strathmoreDPImg == null) strathmoreDPImg = createPlaceholderIcon(210, 200);
        if (carandAcheLuminanceCPImg == null) carandAcheLuminanceCPImg = createPlaceholderIcon(210, 200);
        if (cretacolorGnCPImg == null) cretacolorGnCPImg = createPlaceholderIcon(210, 200);
        if (faberCastelPolychromosCPImg == null) faberCastelPolychromosCPImg = createPlaceholderIcon(210, 200);
        
        
        drawingMaterialsGrid.add(createProductCard("Arches Drawing Paper", "10 pcs", "Stocks 20", receiptArea, archesDPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Bruynzeel Colored Pencils", "10 pcs", "Stocks 20", receiptArea, bruynzeelCPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Derwent Pencils Inktense Color Pencil", "10 pcs", "Stocks 20", receiptArea, derwentInktenseCPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Prismacolor Premier Colored Pencils", "10 pcs", "Stocks 20", receiptArea, prismacolorPremierCPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Sennelier Pastel", "10 pcs", "Stocks 20", receiptArea, sennelierPastelImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Staedtler Mars Graphite Pencils", "10 pcs", "Stocks 20", receiptArea, staedtlerMarsImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Strathmore Drawing Paper", "100 pcs", "Stocks 20", receiptArea, strathmoreDPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Caran d'Ache Luminance Colored Pencils", "10 pcs", "Stocks 20", receiptArea, carandAcheLuminanceCPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Cretacolor Graphite and Charcoal Pencils", "10 pcs", "Stocks 20", receiptArea, cretacolorGnCPImg, 80.00));
        drawingMaterialsGrid.add(createProductCard("Faber-Castell Polychromos Colored Pencils", "10 pcs", "Stocks 20", receiptArea, faberCastelPolychromosCPImg, 80.00));

        scrollContent.add(drawingMaterialsGrid);
        
        //DrawingTools
        JLabel drawingToolsLabel = new JLabel("Drawing Tools");
        drawingToolsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        drawingToolsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        drawingToolsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollContent.add(drawingToolsLabel);

        JPanel drawingToolsGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        drawingToolsGrid.setOpaque(false);
        
        ImageIcon Cretacolor_CharBlenderImg = loadAndResizeResourceImage("/furniture/Cretacolor_CharBlender.jpg", 210, 200);
        ImageIcon Derwent_BStumpsImg = loadAndResizeResourceImage("/furniture/Derwent_BStumps.jpg", 210, 200);
        ImageIcon Prismacolor_CBlenderImg = loadAndResizeResourceImage("/furniture/Prismacolor_CBlender.jpg", 210, 200);
        ImageIcon Sakura_EraserImg = loadAndResizeResourceImage("/furniture/Sakura_Eraser.jpg", 210, 200);
        
        // Fall back to placeholders if needed
        if (Cretacolor_CharBlenderImg == null) Cretacolor_CharBlenderImg = createPlaceholderIcon(210, 200);
        if (Derwent_BStumpsImg == null) Derwent_BStumpsImg = createPlaceholderIcon(210, 200);
        if (Prismacolor_CBlenderImg == null) Prismacolor_CBlenderImg = createPlaceholderIcon(210, 200);
        if (Sakura_EraserImg == null) Sakura_EraserImg = createPlaceholderIcon(210, 200);
        
        drawingToolsGrid.add(createProductCard("Cretacolor Charcoal Blender", "10 pcs", "Stocks 20", receiptArea, Cretacolor_CharBlenderImg, 80.00));
        drawingToolsGrid.add(createProductCard("Derwent Blender Stumps", "100 pcs", "Stocks 20", receiptArea, Derwent_BStumpsImg, 80.00));
        drawingToolsGrid.add(createProductCard("Prisma color Color Blender", "10 pcs", "Stocks 20", receiptArea, Prismacolor_CBlenderImg, 80.00));
        drawingToolsGrid.add(createProductCard("Sakura Eraser", "10 pcs", "Stocks 20", receiptArea, Sakura_EraserImg, 80.00));

        scrollContent.add(drawingToolsGrid);
        
        //Painting Materials
        JLabel paintingMaterialsLabel = new JLabel("Painting Materials");
        paintingMaterialsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        paintingMaterialsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        paintingMaterialsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollContent.add(paintingMaterialsLabel);

        JPanel paintingMaterialsGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        paintingMaterialsGrid.setOpaque(false);
        
        ImageIcon Arteza_APImg = loadAndResizeResourceImage("/furniture/Arteza_AP.jpg", 210, 200);
        ImageIcon DanielSmith_WCImg = loadAndResizeResourceImage("/furniture/Daniel Smith_WC.jpg", 210, 200);
        ImageIcon Golden_mediumImg = loadAndResizeResourceImage("/furniture/Golden_medium.jpg", 210, 200);
        ImageIcon Liquitex_APImg = loadAndResizeResourceImage("/furniture/Liquitex_AP.jpg", 210, 200);
        ImageIcon Maimeri_ClassicoOPImg = loadAndResizeResourceImage("/furniture/Maimeri_Classico OP.jpg", 210, 200);
        ImageIcon Mijello_MissionGoldWCImg = loadAndResizeResourceImage("/furniture/Mijello_MissionGold WC.jpg", 210, 200);
        ImageIcon Pebeo_OPImg = loadAndResizeResourceImage("/furniture/Pebeo_OP.jpg", 210, 200);
        ImageIcon RoyalTalens_RembrandtOPImg = loadAndResizeResourceImage("/furniture/RoyalTalens_Rembrandt OP.jpg", 210, 200);
        ImageIcon Sennelier_WCImg = loadAndResizeResourceImage("/furniture/Sennelier_WC.jpg", 210, 200);
        ImageIcon WinsorandNewton_OPImg = loadAndResizeResourceImage("/furniture/Winsor&Newton_OP.jpg", 210, 200);
        
        // Fall back to placeholders if needed
        if (Arteza_APImg == null) Arteza_APImg = createPlaceholderIcon(210, 200);
        if (DanielSmith_WCImg == null) DanielSmith_WCImg = createPlaceholderIcon(210, 200);
        if (Golden_mediumImg == null) Golden_mediumImg = createPlaceholderIcon(210, 200);
        if (Liquitex_APImg == null) Liquitex_APImg = createPlaceholderIcon(210, 200);
        if (Maimeri_ClassicoOPImg == null) Maimeri_ClassicoOPImg = createPlaceholderIcon(210, 200);
        if (Mijello_MissionGoldWCImg == null) Mijello_MissionGoldWCImg = createPlaceholderIcon(210, 200);
        if (Pebeo_OPImg == null) Pebeo_OPImg = createPlaceholderIcon(210, 200);
        if (RoyalTalens_RembrandtOPImg == null) RoyalTalens_RembrandtOPImg = createPlaceholderIcon(210, 200);
        if (Sennelier_WCImg == null) Sennelier_WCImg = createPlaceholderIcon(210, 200);
        if ( WinsorandNewton_OPImg == null)  WinsorandNewton_OPImg = createPlaceholderIcon(210, 200);
        
        paintingMaterialsGrid.add(createProductCard("Arteza Acrylic Paint", "10 pcs", "Stocks 20", receiptArea, Arteza_APImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Daniel Smith Watercolor", "100 pcs", "Stocks 20", receiptArea, DanielSmith_WCImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Golden Medium", "10 pcs", "Stocks 20", receiptArea, Golden_mediumImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Liquitex Acrylic Paint", "10 pcs", "Stocks 20", receiptArea, Liquitex_APImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Maimeri Classico Oilpaint", "10 pcs", "Stocks 20", receiptArea, Maimeri_ClassicoOPImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Mijello Mission Gold Watercolor", "10 pcs", "Stocks 20", receiptArea, Mijello_MissionGoldWCImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Pebeo Oilpaint", "10 pcs", "Stocks 20", receiptArea, Pebeo_OPImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Royal Talents Rembrandt Oilpaint", "10 pcs", "Stocks 20", receiptArea, RoyalTalens_RembrandtOPImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Sennelier Watercolor", "10 pcs", "Stocks 20", receiptArea, Sennelier_WCImg, 80.00));
        paintingMaterialsGrid.add(createProductCard("Winson & Newton Oilpaint", "10 pcs", "Stocks 20", receiptArea, WinsorandNewton_OPImg, 80.00));

        scrollContent.add(paintingMaterialsGrid);
        
        //Painting Tools
        JLabel paintingToolsLabel = new JLabel("Painting Tools");
        paintingToolsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        paintingToolsLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        paintingToolsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scrollContent.add(paintingToolsLabel);

        JPanel paintingToolsGrid = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        paintingToolsGrid.setOpaque(false);
        
        ImageIcon GamblinGamvarVarnishBrushImg = loadAndResizeResourceImage("/furniture/Gamblin Gamvar_Varnish Brush.jpg", 210, 200);
        ImageIcon Liquitex_PKImg = loadAndResizeResourceImage("/furniture/Liquitex_PK.jpg", 210, 200);
        ImageIcon Mabef_easelImg = loadAndResizeResourceImage("/furniture/Mabef_easel.jpg", 210, 200);
        ImageIcon Mimik_BrushImg = loadAndResizeResourceImage("/furniture/Mimik_Brush.jpg", 210, 200);
        ImageIcon Paasche_AirbrushImg = loadAndResizeResourceImage("/furniture/Paasche_Airbrush.jpg", 210, 200);
        ImageIcon Princeton_VelvetouchBrushImg = loadAndResizeResourceImage("/furniture/Princeton_Velvetouch Brush.jpg", 210, 200);
        ImageIcon RGM_WoodenPKImg = loadAndResizeResourceImage("/furniture/RGM_Wooden PK.jpg", 210, 200);
        ImageIcon SilverBrushGrandPrix_BristleBrushImg = loadAndResizeResourceImage("/furniture/SilverBrushGrandPrix_BristleBrush.jpg", 210, 200);

        // Fall back to placeholders if needed
        if (GamblinGamvarVarnishBrushImg == null) GamblinGamvarVarnishBrushImg = createPlaceholderIcon(210, 200);
        if (Liquitex_PKImg == null) Liquitex_PKImg = createPlaceholderIcon(210, 200);
        if (Mabef_easelImg == null) Mabef_easelImg = createPlaceholderIcon(210, 200);
        if (Mimik_BrushImg == null) Mimik_BrushImg = createPlaceholderIcon(210, 200);
        if (Paasche_AirbrushImg == null) Paasche_AirbrushImg = createPlaceholderIcon(210, 200);
        if (Princeton_VelvetouchBrushImg == null) Princeton_VelvetouchBrushImg = createPlaceholderIcon(210, 200);
        if (RGM_WoodenPKImg == null) RGM_WoodenPKImg = createPlaceholderIcon(210, 200);
        if (SilverBrushGrandPrix_BristleBrushImg == null) SilverBrushGrandPrix_BristleBrushImg = createPlaceholderIcon(210, 200);
        
        paintingToolsGrid.add(createProductCard("Gamblin Gamvar Varnish Brush", "10 pcs", "Stocks 20", receiptArea, GamblinGamvarVarnishBrushImg, 80.00));
        paintingToolsGrid.add(createProductCard("Liquitex Pallete Knives", "10 pcs", "Stocks 20", receiptArea, Liquitex_PKImg, 80.00));
        paintingToolsGrid.add(createProductCard("Mabef Easel", "Hardcopy / 100 pcs", "Stocks 20", receiptArea, Mabef_easelImg, 80.00));
        paintingToolsGrid.add(createProductCard("Mimik Brush", "10 pcs", "Stocks 20", receiptArea, Mimik_BrushImg, 80.00));
        paintingToolsGrid.add(createProductCard("Paasche Airbrush", "10 pcs", "Stocks 20", receiptArea, Paasche_AirbrushImg, 80.00));
        paintingToolsGrid.add(createProductCard("Princeton Velvetouch Brush", "10 pcs", "Stocks 20", receiptArea, Princeton_VelvetouchBrushImg, 80.00));
        paintingToolsGrid.add(createProductCard("RGM Wooden Pallete Knives", "10 pcs", "Stocks 20", receiptArea, RGM_WoodenPKImg, 80.00));
        paintingToolsGrid.add(createProductCard("Silver Brush Grand Prix Bristle Brush", "10 pcs", "Stocks 20", receiptArea, SilverBrushGrandPrix_BristleBrushImg, 80.00));

        scrollContent.add(paintingToolsGrid);
        
        JScrollPane scrollPane = new JScrollPane(scrollContent);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Create summary panel for discounted price, subtotal, and total
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(0xFFF5E4));
        addTextFieldRow(summaryPanel, "Subtotal");
        addTextFieldRow(summaryPanel, "Discounted Price");
        addTextFieldRow(summaryPanel, "Total");
        
        centerPanel.add(summaryPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(0xFFF5E4));
        buttonPanel.add(createButton("Total", receiptArea, receiptArea2));
        buttonPanel.add(createButton("Receipt", receiptArea, receiptArea2));
        buttonPanel.add(createButton("Reset", receiptArea, receiptArea2));

        centerPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Implemented method to load and resize resource images
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

    // Implemented method to load resource images
    private static ImageIcon loadResourceImage(String path) {
        try {
            // Get input stream for resource
            InputStream is = Home.class.getResourceAsStream(path);
            if (is == null) {
                System.err.println("Resource not found: " + path);
                return null;
            }
            
            // Read image from input stream
            BufferedImage img = ImageIO.read(is);
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

    // Method to create placeholder icons
    private static ImageIcon createPlaceholderIcon(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Draw background
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillRect(0, 0, width, height);
        
        // Draw border
        g2d.setColor(Color.GRAY);
        g2d.drawRect(0, 0, width-1, height-1);
        
        // Draw "No Image" text
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2d.getFontMetrics();
        String text = "No Image";
        int textWidth = fm.stringWidth(text);
        g2d.drawString(text, (width - textWidth) / 2, height / 2);
        
        g2d.dispose();
        return new ImageIcon(image);
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
        textField.setEditable(false); // Make it read-only
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
                if (Purchase.getPurchases().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No purchases to calculate total.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                double subtotal = 0;
                double total = 0;
                double totalDiscounted = 0;

                // Clear receipt area2 and add header
                receiptArea2.setText("");
                receiptArea2.append("****** Art Supplies Bill Summary ********\n");
                receiptArea2.append("---------------------------------------\n");

                // Ask for discount type
                String[] responses = {"NONE", "PWD", "SENIOR", "STUDENTS"};
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

                // Add each purchase to the bill with pricing details
                for (Purchase p : Purchase.getPurchases()) {
                    double itemSubtotal = p.price * p.quantity;
                    double discountAmount = itemSubtotal * discountRate;
                    double itemTotal = itemSubtotal - discountAmount;

                    subtotal += itemSubtotal;
                    total += itemTotal;
                    totalDiscounted += discountAmount;

                    receiptArea2.append(String.format("%s x%d | ₱%.2f | Discount: ₱%.2f | Total: ₱%.2f\n", 
                        p.title, p.quantity, itemSubtotal, discountAmount, itemTotal));
                }

                // Add summary information to the bill
                receiptArea2.append("\n---------------------------------------\n");
                receiptArea2.append(String.format("Discount Type: %s (%.0f%%)\n", selected, discountRate * 100));
                receiptArea2.append(String.format("Subtotal: ₱%.2f\n", subtotal));
                receiptArea2.append(String.format("Total Discount: ₱%.2f\n", totalDiscounted));
                receiptArea2.append(String.format("TOTAL: ₱%.2f\n", total));
                receiptArea2.append("*****************************************");

                // Update the text fields
                subtotalField.setText(String.format("₱ %.2f", subtotal));
                discountedPriceField.setText(String.format("₱ %.2f", totalDiscounted));
                totalField.setText(String.format("₱ %.2f", total));
            });
        } else if (text.equals("Receipt")) {
            button.addActionListener(e -> {
                if (Purchase.getPurchases().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No purchases to print a receipt.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                // Check if totals have been calculated
                if (subtotalField.getText().isEmpty() || totalField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please calculate totals first using the 'Total' button.", 
                                                "Calculate Total", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Get customer information
                String name = JOptionPane.showInputDialog(null, "Enter your full name:");
                if (name == null || name.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Name is required to proceed.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String contact = JOptionPane.showInputDialog(null, "Enter your contact number:");
                if (contact == null || contact.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Contact number is required to proceed.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String address = JOptionPane.showInputDialog(null, "Enter your address:");
                if (address == null || address.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Address is required to proceed.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create receipt content
                StringBuilder receiptContent = new StringBuilder();
                receiptContent.append("****************** AIR SUPPLIES RECEIPT ******************\n");
                receiptContent.append("----------------------------------\n");
                receiptContent.append("Date: " + java.time.LocalDate.now() + "\n");
                receiptContent.append("Time: " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) + "\n\n");
                
                // Add customer information to receipt
                receiptContent.append("Customer: " + name + "\n");
                receiptContent.append("Contact: " + contact + "\n");
                receiptContent.append("Address: " + address + "\n\n");
                
                // Add purchased items
                receiptContent.append("PURCHASES:\n");
                for (Purchase p : Purchase.getPurchases()) {
                    receiptContent.append(String.format("%s x%d  ₱%.2f\n", 
                        p.title, p.quantity, p.price * p.quantity));
                }

                // Add totals from the calculated fields
                receiptContent.append("----------------------------------\n");
                receiptContent.append("Subtotal: " + subtotalField.getText() + "\n");
                receiptContent.append("Discount: " + discountedPriceField.getText() + "\n");
                receiptContent.append("TOTAL: " + totalField.getText() + "\n\n");
                receiptContent.append("Thank you for shopping with Air Supplies!\n");
                receiptContent.append("**********************************");
                
                // Create and display the receipt dialog
                JTextArea receiptDisplayArea = new JTextArea(receiptContent.toString());
                receiptDisplayArea.setEditable(false);
                receiptDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                
                JScrollPane scrollPane = new JScrollPane(receiptDisplayArea);
                scrollPane.setPreferredSize(new Dimension(400, 500));
                
                JOptionPane.showMessageDialog(null, scrollPane, "Air Supplies Receipt", JOptionPane.PLAIN_MESSAGE);
                
                // Also update the receipt area for reference
                receiptArea.setText(receiptContent.toString());
            });
        }

        return button;
    }
}