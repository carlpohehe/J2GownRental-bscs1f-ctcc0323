import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class J2GownRental extends JFrame {
    private DefaultListModel<GownItem> gownListModel;
    private JList<GownItem> gownList;
    private JComboBox<String> sizeComboBox;
    private JPanel colorPanel;
    private JSpinner quantitySpinner;
    private JButton addToCartButton;
    private JButton checkoutButton;
    private JSpinner rentalDaysSpinner;

    private List<GownItem> cartItems;

    public J2GownRental() {
        setTitle("Gown Rental");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        cartItems = new ArrayList<>();

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#ff6b6b"));
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(getWidth(), 90));

        JLabel headerLabel = new JLabel("Gown Rental - Book Yours!");
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(headerLabel, BorderLayout.NORTH);

        // Add price range label
        JLabel priceRangeLabel = new JLabel("<html><div style='text-align: center;'>Price Range: <br><span style='font-size: 16pt; font-weight: bold;'>PHP 1000 - PHP 4000</span></div></html>");
        priceRangeLabel.setForeground(Color.WHITE);
        priceRangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(priceRangeLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        gownListModel = new DefaultListModel<>();

        List<String> sizes = new ArrayList<>();
        sizes.add("Small");
        sizes.add("Medium");
        sizes.add("Large");
        sizes.add("Extra Large");

        gownListModel.addElement(new GownItem("Cassie Beige", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie White", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Maroon", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Rust", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Rosegold", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Navy", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie SageGreen", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Black", sizes, new int[]{1000, 1500, 2000, 4000}));
        gownListModel.addElement(new GownItem("Cassie Red", sizes, new int[]{1000, 1500, 2000, 4000}));

        gownList = new JList<>(gownListModel);
        gownList.setCellRenderer(new GownListCellRenderer());
        gownList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gownList.addListSelectionListener(e -> updateColorAndQuantity());
        JScrollPane scrollPane = new JScrollPane(gownList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(Color.WHITE);
        southPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel colorAndQuantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        colorAndQuantityPanel.setBackground(Color.WHITE);

        colorPanel = new JPanel();
        colorPanel.setPreferredSize(new Dimension(100, 100));
        colorAndQuantityPanel.add(colorPanel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setBackground(Color.WHITE);

        JLabel colorLabel = new JLabel("Color and Style:");
        optionsPanel.add(colorLabel);

        sizeComboBox = new JComboBox<>();
        sizeComboBox.addItem("Small");
        sizeComboBox.addItem("Medium");
        sizeComboBox.addItem("Large");
        sizeComboBox.addItem("Extra Large");
        sizeComboBox.addActionListener(e -> updateColorAndQuantity());
        optionsPanel.add(sizeComboBox);

        JLabel quantityLabel = new JLabel("Quantity:");
        optionsPanel.add(quantityLabel);

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        optionsPanel.add(quantitySpinner);

        colorAndQuantityPanel.add(optionsPanel);
        southPanel.add(colorAndQuantityPanel, BorderLayout.WEST);

        
        SpinnerModel rentalDaysModel = new SpinnerNumberModel(1, 1, 30, 1); 
        rentalDaysSpinner = new JSpinner(rentalDaysModel); 
        JLabel rentalDaysLabel = new JLabel("Rental Days:");
        JPanel rentalDaysPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        rentalDaysPanel.setBackground(Color.WHITE);
        rentalDaysPanel.add(rentalDaysLabel);
        rentalDaysPanel.add(rentalDaysSpinner);
        southPanel.add(rentalDaysPanel, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setBackground(Color.decode("#ff6b6b"));
        addToCartButton.setForeground(Color.WHITE);
        addToCartButton.setFocusPainted(false);
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToCart();
            }
        });
        buttonPanel.add(addToCartButton);

        checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(Color.decode("#ffa07a"));
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setFocusPainted(false);
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReceipt();
            }
        });
        buttonPanel.add(checkoutButton);

        southPanel.add(buttonPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        gownList.setSelectedIndex(0);
        updateColorAndQuantity();

        setVisible(true);

        
        JFrame chooseGownFrame = new JFrame();
        chooseGownFrame.setTitle("J2 GOWN RENTAL");
        chooseGownFrame.setSize(900, 600);
        chooseGownFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseGownFrame.setLayout(new BorderLayout());
        chooseGownFrame.getContentPane().setBackground(Color.RED);

        JPanel chooseGownPanel = new JPanel();
        chooseGownPanel.setLayout(new BorderLayout());
        chooseGownPanel.setBackground(Color.RED);

        JLabel chooseGownLabel = new JLabel("Welcome to J2 GOWN RENTAL");
        chooseGownLabel.setForeground(Color.WHITE);
        chooseGownLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        chooseGownLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chooseGownLabel.setVerticalAlignment(SwingConstants.CENTER);
        chooseGownPanel.add(chooseGownLabel, BorderLayout.CENTER);

        JButton chooseGownButton = new JButton("Tap to Choose Your Gown");
        chooseGownButton.setBackground(Color.RED);
        chooseGownButton.setForeground(Color.WHITE);
        chooseGownButton.setFocusPainted(false);
        chooseGownButton.addActionListener(e -> {
            chooseGownFrame.dispose();  
            setVisible(true);  
        });
        chooseGownPanel.add(chooseGownButton, BorderLayout.SOUTH);

        chooseGownFrame.add(chooseGownPanel, BorderLayout.CENTER);
        chooseGownFrame.setVisible(true);
    }

    private class GownItem {
        private String name;
        private List<String> sizes;
        private int[] prices;

        public GownItem(String name, List<String> sizes, int[] prices) {
            this.name = name;
            this.sizes = sizes;
            this.prices = prices;
        }

        public String getName() {
            return name;
        }

        public List<String> getSizes() {
            return sizes;
        }

        public int getPrice(String size) {
            int index = sizes.indexOf(size);
            if (index != -1 && index < prices.length) {
                return prices[index];
            }
            return 0;
        }

        public int computeTotalCost(String size, int quantity, int rentalDays) {
            int price = getPrice(size);
            return price * quantity * rentalDays;
        }
    }

    private class GownListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof GownItem) {
                GownItem gownItem = (GownItem) value;
                setText(gownItem.getName());
                setPreferredSize(new Dimension(250, 30));
                setBackground(isSelected ? Color.decode("#f0f0f0") : Color.WHITE);
            }
            return this;
        }
    }

    private void updateColorPanel(String gownName) {
        switch (gownName) {
            case "Cassie Beige":
                colorPanel.setBackground(Color.decode("#f5f5dc"));
                break;
            case "Cassie White":
                colorPanel.setBackground(Color.WHITE);
                break;
            case "Cassie Maroon":
                colorPanel.setBackground(Color.decode("#800000"));
                break;
            case "Cassie Rust":
                colorPanel.setBackground(Color.decode("#b7410e"));
                break;
            case "Cassie Rosegold":
                colorPanel.setBackground(Color.decode("#b76e79"));
                break;
            case "Cassie Navy":
                colorPanel.setBackground(Color.decode("#000080"));
                break;
            case "Cassie SageGreen":
                colorPanel.setBackground(Color.decode("#bcebcf"));
                break;
            case "Cassie Black":
                colorPanel.setBackground(Color.BLACK);
                break;
            case "Cassie Red":
                colorPanel.setBackground(Color.RED);
                break;
            default:
                colorPanel.setBackground(Color.WHITE);
                break;
        }
    }

    private void updateColorAndQuantity() {
        GownItem selectedGown = gownList.getSelectedValue();
        if (selectedGown != null) {
            updateColorPanel(selectedGown.getName());

            SpinnerNumberModel spinnerModel = (SpinnerNumberModel) quantitySpinner.getModel();
            spinnerModel.setMinimum(1);
            spinnerModel.setMaximum(10);
            spinnerModel.setValue(1);
        }
    }

    private void addToCart() {
        GownItem selectedGown = gownList.getSelectedValue();
        if (selectedGown != null) {
            String size = (String) sizeComboBox.getSelectedItem();
            int quantity = (int) quantitySpinner.getValue();
            int rentalDays = (int) rentalDaysSpinner.getValue(); 
            int totalCost = selectedGown.computeTotalCost(size, quantity, rentalDays);

            String message = "Added to Cart:\n";
            message += "Gown: " + selectedGown.getName() + "\n";
            message += "Size: " + size + "\n";
            message += "Quantity: " + quantity + "\n";
            message += "Rental Days: " + rentalDays + "\n";
            message += "Total Price: PHP " + totalCost;

            JOptionPane.showMessageDialog(this, message);

            cartItems.add(selectedGown);
        }
    }

    private void showReceipt() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Your cart is empty.");
        } else {
            StringBuilder receipt = new StringBuilder();
            receipt.append("Receipt:\n");

            int totalCost = 0;
            for (GownItem item : cartItems) {
                String size = (String) sizeComboBox.getSelectedItem();
                int quantity = (int) quantitySpinner.getValue();
                int rentalDays = (int) rentalDaysSpinner.getValue(); 
                int total = item.computeTotalCost(size, quantity, rentalDays);
                totalCost += total;

                receipt.append("Gown: ").append(item.getName()).append("\n");
                receipt.append("Size: ").append(size).append("\n");
                receipt.append("Quantity: ").append(quantity).append("\n");
                receipt.append("Rental Days: ").append(rentalDays).append("\n");
                receipt.append("Total Price: PHP ").append(total).append("\n\n");
            }

            receipt.append("Total Cost: PHP ").append(totalCost).append("\n\n");
            receipt.append("Thank you so much!");

            JOptionPane.showMessageDialog(this, receipt.toString());

            cartItems.clear();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new J2GownRental());
    }
}