import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener, MouseListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    private final ImageIcon loginImage;

    Login() {
        setTitle("Hotel Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout());

        // Load the image from the classpath
        loginImage = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        if (loginImage.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.err.println("Failed to load image: icons/second.jpg");
            // Fallback for missing image, though the NullPointerException is usually caught by ClassLoader.getSystemResource returning null
            // You might want to display a placeholder or log more severely.
        }

        // --- Image Panel (Left Side) ---
        // A custom JPanel that draws the image as its background, scaling it to fit.
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Call JPanel's paintComponent to clear the background
                if (loginImage != null && loginImage.getImage() != null) {
                    g.drawImage(loginImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    // Draw a placeholder if image fails to load
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.DARK_GRAY);
                    g.drawString("Image Not Found", getWidth() / 2 - 50, getHeight() / 2);
                }
            }
        };
        imagePanel.setPreferredSize(new Dimension(400, 500)); // Fixed width for the image panel
        add(imagePanel, BorderLayout.WEST);

        // --- Login Form Panel (Right Side) ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(248, 248, 248)); // Light off-white background
        formPanel.setBorder(new EmptyBorder(50, 50, 50, 50)); // Padding around the form
        add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12); // Padding for components
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Title spans two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center components horizontally

        // Title Label
        JLabel titleLabel = new JLabel("Admin Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Modern font, larger size
        titleLabel.setForeground(new Color(50, 50, 50)); // Darker text for contrast
        formPanel.add(titleLabel, gbc);

        // Username Label and Field
        gbc.gridy++; // Move to the next row
        gbc.gridwidth = 1; // Labels and fields occupy one column
        gbc.anchor = GridBagConstraints.LINE_END; // Align label to the end of its cell
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userLabel.setForeground(new Color(70, 70, 70));
        formPanel.add(userLabel, gbc);

        gbc.gridx = 1; // Move to the second column
        gbc.anchor = GridBagConstraints.LINE_START; // Align field to the start of its cell
        usernameField = new JTextField(18); // Wider text field
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light gray border
                BorderFactory.createEmptyBorder(8, 10, 8, 10))); // Internal padding
        formPanel.add(usernameField, gbc);

        // Password Label and Field
        gbc.gridy++; // Next row
        gbc.gridx = 0; // First column
        gbc.anchor = GridBagConstraints.LINE_END;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        passLabel.setForeground(new Color(70, 70, 70));
        formPanel.add(passLabel, gbc);

        gbc.gridx = 1; // Second column
        gbc.anchor = GridBagConstraints.LINE_START;
        passwordField = new JPasswordField(18);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        formPanel.add(passwordField, gbc);

        // Buttons Panel
        gbc.gridy++; // Next row
        gbc.gridx = 0; // Start from first column
        gbc.gridwidth = 2; // Buttons span both columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 20)); // Spacing between buttons
        buttonPanel.setBackground(new Color(248, 248, 248)); // Match form panel background

        loginButton = new JButton("Login");
        styleButton(loginButton, Color.GREEN); // Changed to black
        buttonPanel.add(loginButton);

        cancelButton = new JButton("Cancel");
        styleButton(cancelButton, Color.RED); // Changed to black
        buttonPanel.add(cancelButton);

        formPanel.add(buttonPanel, gbc);

        setVisible(true); // Make the frame visible
    }

    // Helper method to style buttons consistently
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Remove border around text when focused
        button.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30)); // Padding inside button
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        button.addActionListener(this); // Register for action events
        button.addMouseListener(this); // Register for mouse events (for hover)
    }

    // --- ActionListener Methods ---
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                conn c = new conn(); // Establish database connection
                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query); // Execute the query

                if (rs.next()) {
                    // Login successful: hide this window, open the dashboard
                    setVisible(false);
                    new Dashboard();
                } else {
                    // Login failed: show error message
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            }
        } else if (ae.getSource() == cancelButton) {
            // Cancel button clicked: exit the application
            System.exit(0);
        }
    }

    // --- MouseListener Methods for Button Hover Effects ---
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == loginButton) {
            button.setBackground(new Color(0, 90, 200)); // Darker blue on hover
        } else if (button == cancelButton) {
            button.setBackground(new Color(80, 80, 80)); // Darker gray on hover
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == loginButton) {
            button.setBackground(new Color(0, 123, 255)); // Original primary blue
        } else if (button == cancelButton) {
            button.setBackground(new Color(108, 117, 125)); // Original muted gray
        }
    }

    // Main method: Entry point of the application
    public static void main(String[] args) {
        // Set the system's native look and feel for a better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login(); // Create and display the login window
    }
}