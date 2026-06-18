import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends Frame implements ActionListener {

    Label userLabel, passLabel, msg;
    TextField userField, passField;
    Button loginBtn, registerBtn;

    public LoginPage() {
        setTitle("Login");
        setSize(300, 220);
        setLayout(new FlowLayout());

        userLabel = new Label("Username:");
        passLabel = new Label("Password:");

        userField = new TextField(20);
        passField = new TextField(20);
        passField.setEchoChar('*');

        loginBtn = new Button("Login");
        registerBtn = new Button("Register");

        msg = new Label("");

        add(userLabel); add(userField);
        add(passLabel); add(passField);
        add(loginBtn);
        add(registerBtn);
        add(msg);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {
            try {
                String user = userField.getText();
                String pass = passField.getText();

                Connection con = DBConnection.getConnection();

                PreparedStatement ps = con.prepareStatement(
                    "SELECT role FROM users WHERE username=? AND password=?"
                );

                ps.setString(1, user);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");

                    msg.setText("Login Success");

                    new GradeManager(role);
                    dispose();

                } else {
                    msg.setText("Invalid Login");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                msg.setText("Error: " + ex.getMessage());
            }
        }

        if (e.getSource() == registerBtn) {
            new RegistrationPage();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}