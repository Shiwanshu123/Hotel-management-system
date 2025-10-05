import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener {

    JButton addButton, cancelButton;
    JTextField tfname, tfage, tfcompany, tfmodel, tflocation;
    JComboBox<String> availableCombo, genderCombo;

    AddDriver() {
        // Frame Setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setBounds(300, 200, 980, 470);

        // Title
        JLabel heading = new JLabel("Add Drivers");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(150, 10, 200, 20);
        add(heading);

        // --- Form Fields ---

        // Driver Name
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(60, 70, 120, 30);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 70, 150, 30);
        add(tfname);

        // Age
        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblage.setBounds(60, 110, 120, 30);
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 110, 150, 30);
        add(tfage);

        // Gender
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setBounds(60, 150, 120, 30);
        add(lblgender);

        String[] genderOptions = {"Male", "Female"};
        genderCombo = new JComboBox<>(genderOptions);
        genderCombo.setBounds(200, 150, 150, 30);
        genderCombo.setBackground(Color.WHITE);
        add(genderCombo);

        // Car Company
        JLabel lblcompany = new JLabel("Car Company");
        lblcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcompany.setBounds(60, 190, 120, 30);
        add(lblcompany);

        tfcompany = new JTextField();
        tfcompany.setBounds(200, 190, 150, 30);
        add(tfcompany);

        // Car Model/Brand
        JLabel lblmodel = new JLabel("Car Brand");
        lblmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblmodel.setBounds(60, 230, 120, 30);
        add(lblmodel);

        tfmodel = new JTextField();
        tfmodel.setBounds(200, 230, 150, 30);
        add(tfmodel);

        // Availability
        JLabel lblavailable = new JLabel("Available");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60, 270, 120, 30);
        add(lblavailable);

        String[] driverOptions = {"Available", "Busy"};
        availableCombo = new JComboBox<>(driverOptions);
        availableCombo.setBounds(200, 270, 150, 30);
        availableCombo.setBackground(Color.WHITE);
        add(availableCombo);

        // Location
        JLabel lbllocation = new JLabel("Location");
        lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbllocation.setBounds(60, 310, 120, 30);
        add(lbllocation);

        tflocation = new JTextField();
        tflocation.setBounds(200, 310, 150, 30);
        add(tflocation);

        // --- Buttons ---
        addButton = new JButton("Add Driver");
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.BLACK);
        addButton.setBounds(60, 370, 130, 30);
        addButton.addActionListener(this);
        add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setBounds(220, 370, 130, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        // --- Image ---
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            String name = tfname.getText();
            String age = tfage.getText();
            String gender = (String) genderCombo.getSelectedItem();
            String company = tfcompany.getText();
            String brand = tfmodel.getText();
            String available = (String) availableCombo.getSelectedItem();
            String location = tflocation.getText();

            // Basic validation
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name should not be empty");
                return;
            }

            try {
                conn c = new conn();
                String query = "INSERT INTO driver VALUES('" + name + "', '" + age + "', '" + gender + "', '" + company + "', '" + brand + "', '" + available + "', '" + location + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "New Driver Added Successfully");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // Cancel button
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}