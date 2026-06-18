import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationPage extends Frame implements ActionListener {

    Label userLabel, passLabel, roleLabel, msg;
    TextField userField, passField;
    Choice roleChoice;
    Button registerBtn;

    public RegistrationPage() {
        setTitle("Register");
        setSize(300, 250);
        setLayout(new FlowLayout());

        userLabel = new Label("Username:");
        passLabel = new Label("Password:");
        roleLabel = new Label("Role:");

        userField = new TextField(20);
        passField = new TextField(20);
        passField.setEchoChar('*');

        roleChoice = new Choice();
        roleChoice.add("admin");
        roleChoice.add("teacher");
        roleChoice.add("student");
        roleChoice.add("parent");

        registerBtn = new Button("Register");
        msg = new Label("");

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(roleLabel); add(roleChoice);
        add(registerBtn);
        add(msg);

        registerBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            String username = userField.getText();
            String password = passField.getText();
            String role = roleChoice.getSelectedItem();

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username, password, role) VALUES (?, ?, ?)"
            );

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ps.executeUpdate();

            msg.setText("Registration Successful!");

        } catch (Exception ex) {
            ex.printStackTrace();
            msg.setText("Error: " + ex.getMessage());
        }
    }
}