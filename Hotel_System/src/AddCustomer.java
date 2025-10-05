import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {

    JComboBox<String> comboId;
    JTextField tfnumber, tfname, tfcountry, tfdeposit;
    JRadioButton rbmale, rbfemale;
    Choice croom;
    JLabel checkintime;
    JButton addButton, backButton;

    AddCustomer() {
        // Frame Setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(350, 200, 800, 550);

        // Title
        JLabel text = new JLabel("New Customer Form");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(text);

        // --- Form Fields ---

        JLabel lblid = new JLabel("ID");
        lblid.setBounds(35, 80, 100, 20);
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblid);

        String[] options = {"Passport", "Aadhar Card", "Voter ID", "Driving License"};
        comboId = new JComboBox<>(options);
        comboId.setBounds(200, 80, 150, 25);
        comboId.setBackground(Color.WHITE);
        add(comboId);

        JLabel lblnumber = new JLabel("Number");
        lblnumber.setBounds(35, 120, 100, 20);
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblnumber);

        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 160, 100, 20);
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(35, 200, 100, 20);
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBackground(Color.WHITE);
        rbmale.setBounds(200, 200, 60, 25);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBackground(Color.WHITE);
        rbfemale.setBounds(270, 200, 100, 25);
        add(rbfemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);

        JLabel lblcountry = new JLabel("Country");
        lblcountry.setBounds(35, 240, 100, 20);
        lblcountry.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblcountry);

        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(35, 280, 150, 20);
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lblroom);

        croom = new Choice();
        try {
            conn c = new conn();
            String query = "SELECT * FROM room WHERE availability = 'Available'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                croom.add(rs.getString("roomnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        croom.setBounds(200, 280, 150, 25);
        add(croom);

        JLabel lbltime = new JLabel("Check-in time");
        lbltime.setBounds(35, 320, 150, 20);
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbltime);

        Date date = new Date();
        checkintime = new JLabel("" + date);
        checkintime.setBounds(200, 320, 250, 25);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        add(checkintime);

        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setBounds(35, 360, 100, 20);
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
        add(lbldeposit);

        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);

        // --- Buttons ---
        addButton = new JButton("Add Customer");
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(50, 410, 140, 30);
        addButton.addActionListener(this);
        add(addButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(220, 410, 120, 30);
        backButton.addActionListener(this);
        add(backButton);

        // --- Image ---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            String id = (String) comboId.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = rbmale.isSelected() ? "Male" : "Female";
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            String deposit = tfdeposit.getText();

            try {
                conn c = new conn();
                // Query to insert customer details
                String query1 = "INSERT INTO customer VALUES('" + id + "', '" + number + "', '" + name + "', '" + gender + "', '" + country + "', '" + room + "', '" + time + "', '" + deposit + "')";
                // Query to update the room's availability to 'Occupied'
                String query2 = "UPDATE room SET availability = 'Occupied' WHERE roomnumber = '" + room + "'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                setVisible(false);
                new Dashboard(); // Go back to the main dashboard

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Dashboard(); // Go back to the main dashboard
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}