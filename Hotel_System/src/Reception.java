import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener {

    // Declare all buttons as class members
    JButton newCustomerButton, roomsButton, departmentButton, allEmployeeButton, customerInfoButton, managerInfoButton,
            checkoutButton, updateButton, roomStatusButton, pickupButton, searchRoomButton, logoutButton;

    Reception() {
        // --- Frame Setup ---
        setTitle("Reception Control Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(240, 240, 240)); // A light, neutral background

        // Use GridLayout for a clean, organized grid of buttons
        setLayout(new GridLayout(4, 3, 15, 15)); // 4 rows, 3 columns, with 15px gaps

        // Add padding around the entire grid
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));

        // --- Create and Add Buttons to the Grid ---
        // Each button is created using a helper method for consistent styling
        newCustomerButton = createActionButton("New Customer", "icons/new_customer.png");
        add(newCustomerButton);

        roomsButton = createActionButton("View Rooms", "icons/rooms.png");
        add(roomsButton);

        departmentButton = createActionButton("Department Info", "icons/department.png");
        add(departmentButton);

        allEmployeeButton = createActionButton("Employee Info", "icons/employees.png");
        add(allEmployeeButton);

        customerInfoButton = createActionButton("Customer Info", "icons/customer_info.png");
        add(customerInfoButton);

        managerInfoButton = createActionButton("Manager Info", "icons/manager.png");
        add(managerInfoButton);

        checkoutButton = createActionButton("Checkout", "icons/checkout.png");
        add(checkoutButton);

        updateButton = createActionButton("Update Check Status", "icons/update.png");
        add(updateButton);

        roomStatusButton = createActionButton("Update Room Status", "icons/update_room.png");
        add(roomStatusButton);

        pickupButton = createActionButton("Pickup Service", "icons/pickup.png");
        add(pickupButton);

        searchRoomButton = createActionButton("Search Rooms", "icons/search.png");
        add(searchRoomButton);

        logoutButton = createActionButton("Back to Dashboard", "icons/back.png");
        add(logoutButton);

        setVisible(true);
    }

    /**
     * A helper method to create and style each navigation button consistently.
     * @param text The text to display on the button.
     * @param iconPath The path to the button's icon within the classpath.
     * @return A styled JButton.
     */
    private JButton createActionButton(String text, String iconPath) {
        JButton button = new JButton(text);
        try {
            // Load and scale the icon
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
            Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Icon not found for " + text + " at path: " + iconPath);
        }

        // Styling
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(24, 29, 49)); // Dark blue text
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Position text below the icon
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);

        // Add the action listener to make the button functional
        button.addActionListener(this);

        return button;
    }

    // Handles all button clicks for this frame
    public void actionPerformed(ActionEvent ae) {
        // The setVisible(false) call hides the current Reception window
        // before opening a new one.
        if (ae.getSource() == newCustomerButton) {
            setVisible(false);
            new NewCustomer();
        } else if (ae.getSource() == logoutButton) {
            setVisible(false);
            new Dashboard();
        }
        // Add logic for other buttons here as you create their respective classes...
    }

    // Main method for testing this screen independently
    public static void main(String[] args) {
        new Reception();
    }
}