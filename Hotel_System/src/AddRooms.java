import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRooms extends JFrame implements ActionListener {

    JButton addButton, cancelButton;
    JTextField tfroom, tfprice;
    JComboBox<String> availableCombo, cleanCombo, bedCombo;

    AddRooms() {
        // Frame Setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(330, 200, 940, 470);

        // Title
        JLabel heading = new JLabel("Add Rooms");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        // --- Form Fields ---

        // Room Number
        JLabel lblroomno = new JLabel("Room Number");
        lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblroomno.setBounds(60, 80, 120, 30);
        add(lblroomno);

        tfroom = new JTextField();
        tfroom.setBounds(200, 80, 150, 30);
        add(tfroom);

        // Availability
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60, 130, 120, 30);
        add(lblavailable);

        String[] availableOptions = {"Available", "Occupied"};
        availableCombo = new JComboBox<>(availableOptions);
        availableCombo.setBounds(200, 130, 150, 30);
        availableCombo.setBackground(Color.WHITE);
        add(availableCombo);

        // Cleaning Status
        JLabel lblclean = new JLabel("Cleaning Status");
        lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblclean.setBounds(60, 180, 120, 30);
        add(lblclean);

        String[] cleanOptions = {"Cleaned", "Dirty"};
        cleanCombo = new JComboBox<>(cleanOptions);
        cleanCombo.setBounds(200, 180, 150, 30);
        cleanCombo.setBackground(Color.WHITE);
        add(cleanCombo);

        // Price
        JLabel lblprice = new JLabel("Price");
        lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblprice.setBounds(60, 230, 120, 30);
        add(lblprice);

        tfprice = new JTextField();
        tfprice.setBounds(200, 230, 150, 30);
        add(tfprice);

        // Bed Type
        JLabel lblbed = new JLabel("Bed Type");
        lblbed.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblbed.setBounds(60, 280, 120, 30);
        add(lblbed);

        String[] bedOptions = {"Single Bed", "Double Bed"};
        bedCombo = new JComboBox<>(bedOptions);
        bedCombo.setBounds(200, 280, 150, 30);
        bedCombo.setBackground(Color.WHITE);
        add(bedCombo);

        // --- Buttons ---
        addButton = new JButton("Add Room");
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        addButton.setBounds(60, 350, 130, 30);
        addButton.addActionListener(this);
        add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setBounds(220, 350, 130, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        // --- Image ---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eighth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            String roomnumber = tfroom.getText();
            String availability = (String) availableCombo.getSelectedItem();
            String status = (String) cleanCombo.getSelectedItem();
            String price = tfprice.getText();
            String type = (String) bedCombo.getSelectedItem();

            // Basic validation
            if (roomnumber.equals("") || price.equals("")) {
                JOptionPane.showMessageDialog(null, "Room Number and Price should not be empty");
                return;
            }

            try {
                conn c = new conn();
                String query = "INSERT INTO room VALUES('" + roomnumber + "', '" + availability + "', '" + status + "', '" + price + "', '" + type + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Room Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // Cancel button
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddRooms();
    }
}