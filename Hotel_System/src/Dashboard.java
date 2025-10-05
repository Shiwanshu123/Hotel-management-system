import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard extends JFrame implements ActionListener {

    // Changed 'receptionButton' to 'addCustomerButton'
    private JButton addCustomerButton, addEmployeeButton, addRoomButton, addDriverButton, logoutButton;

    Dashboard() {
        // --- Frame Setup ---
        setTitle("Hotel Management System - Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window fullscreen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- Navigation Sidebar (West) ---
        JPanel navPanel = new JPanel();
        navPanel.setLayout(null);
        navPanel.setPreferredSize(new Dimension(250, 0));
        navPanel.setBackground(new Color(24, 29, 49)); // Dark blue background
        add(navPanel, BorderLayout.WEST);

        // Sidebar Title
        JLabel navTitle = new JLabel("HMS Dashboard");
        navTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        navTitle.setForeground(Color.WHITE);
        navTitle.setBounds(30, 40, 200, 30);
        navPanel.add(navTitle);

        // --- Navigation Buttons ---
        // Changed "Reception" to "Add Customer"
        addCustomerButton = createNavButton("Add Customer", 120);
        navPanel.add(addCustomerButton);

        addEmployeeButton = createNavButton("Add Employee", 170);
        navPanel.add(addEmployeeButton);

        addRoomButton = createNavButton("Add Rooms", 220);
        navPanel.add(addRoomButton);

        addDriverButton = createNavButton("Add Drivers", 270);
        navPanel.add(addDriverButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(30, 900, 190, 40);
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        logoutButton.setBackground(new Color(220, 53, 69)); // Red color for logout
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(new EmptyBorder(10, 25, 10, 25));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(this);
        navPanel.add(logoutButton);


        // --- Main Content Area (Center) ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        // Header Panel (Top of Main Area)
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerPanel.setBackground(new Color(248, 249, 250));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        timeLabel.setForeground(new Color(50, 50, 50));
        headerPanel.add(timeLabel);

        // Timer to update the clock every second
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy  |  hh:mm:ss a");
            timeLabel.setText(sdf.format(new Date()));
        });
        timer.start();

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Background Image in the center
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        mainPanel.add(image, BorderLayout.CENTER);


        setVisible(true);
    }

    // Helper method to create styled navigation buttons
    private JButton createNavButton(String text, int yPosition) {
        JButton button = new JButton(text);
        button.setBounds(0, yPosition, 250, 50);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        button.setForeground(new Color(200, 200, 200)); // Light gray text
        button.setBackground(new Color(24, 29, 49)); // Match sidebar background
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 40, 10, 25)); // Left padding for alignment
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(this);

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(44, 52, 79));
                button.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(24, 29, 49));
                button.setForeground(new Color(200, 200, 200));
            }
        });

        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        // --- Updated logic for the buttons ---
        if (ae.getSource() == addCustomerButton) {
            this.setVisible(false);
            new AddCustomer();
        } else if (ae.getSource() == addEmployeeButton) {
            new AddEmployee();
        } else if (ae.getSource() == addRoomButton) {
            new AddRooms();
        } else if (ae.getSource() == addDriverButton) {
            new AddDriver();
        } else if (ae.getSource() == logoutButton) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}