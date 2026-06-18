// import java.awt.*;
// import java.awt.event.*;
// import java.sql.*;

// public class GradeManager extends Frame implements ActionListener {

//     Label nameLabel, subjectLabel, marksLabel, msgLabel;
//     TextField nameField, subjectField, marksField;
//     Button addBtn, viewBtn, updateBtn, deleteBtn;
//     TextArea area;

//     String role;

//     public GradeManager(String role) {
//         this.role = role;

//         setTitle("Grade Manager System");
//         setSize(500, 500);
//         setLayout(new BorderLayout());

//         Panel formPanel = new Panel(new GridLayout(3, 2, 10, 10));

//         nameLabel = new Label("Name:");
//         subjectLabel = new Label("Subject:");
//         marksLabel = new Label("Marks:");

//         nameField = new TextField(20);
//         subjectField = new TextField(20);
//         marksField = new TextField(20);

//         formPanel.add(nameLabel);
//         formPanel.add(nameField);

//         formPanel.add(subjectLabel);
//         formPanel.add(subjectField);

//         formPanel.add(marksLabel);
//         formPanel.add(marksField);

//         Panel buttonPanel = new Panel(new GridLayout(1, 4, 10, 10));

//         addBtn = new Button("Add");
//         viewBtn = new Button("View");
//         updateBtn = new Button("Update");
//         deleteBtn = new Button("Delete");

//         buttonPanel.add(addBtn);
//         buttonPanel.add(viewBtn);
//         buttonPanel.add(updateBtn);
//         buttonPanel.add(deleteBtn);

//         msgLabel = new Label("");
//         area = new TextArea();

//         add(formPanel, BorderLayout.NORTH);
//         add(buttonPanel, BorderLayout.CENTER);
//         add(area, BorderLayout.SOUTH);
//         add(msgLabel, BorderLayout.WEST);

//         addBtn.addActionListener(this);
//         viewBtn.addActionListener(this);
//         updateBtn.addActionListener(this);
//         deleteBtn.addActionListener(this);

//         if (role.equals("student") || role.equals("parent")) {
//             addBtn.setEnabled(false);
//             updateBtn.setEnabled(false);
//             deleteBtn.setEnabled(false);
//         }

//         addWindowListener(new WindowAdapter() {
//             public void windowClosing(WindowEvent e) {
//                 dispose();
//             }
//         });

//         setVisible(true);
//     }

//     public void actionPerformed(ActionEvent e) {
//         try {
//             Connection con = DBConnection.getConnection();

//             String name = nameField.getText();
//             String subject = subjectField.getText();
//             int marks = Integer.parseInt(marksField.getText());

//             Student s = new Student(name, marks);
//             String grade = s.getGrade();

//             if (e.getSource() == addBtn) {
//                 PreparedStatement ps = con.prepareStatement(
//                     "INSERT INTO students(name, subject, marks, grade) VALUES (?, ?, ?, ?)"
//                 );

//                 ps.setString(1, name);
//                 ps.setString(2, subject);
//                 ps.setInt(3, marks);
//                 ps.setString(4, grade);

//                 ps.executeUpdate();
//                 msgLabel.setText("Added!");
//             }

//             if (e.getSource() == viewBtn) {
//                 area.setText("");

//                 Statement st = con.createStatement();
//                 ResultSet rs = st.executeQuery("SELECT * FROM students");

//                 while (rs.next()) {
//                     area.append(
//                         rs.getString("name") + " - " +
//                         rs.getString("subject") + " - " +
//                         rs.getInt("marks") + " - " +
//                         rs.getString("grade") + "\n"
//                     );
//                 }
//             }

//             if (e.getSource() == updateBtn) {
//                 PreparedStatement ps = con.prepareStatement(
//                     "UPDATE students SET marks=?, grade=? WHERE name=? AND subject=?"
//                 );

//                 ps.setInt(1, marks);
//                 ps.setString(2, grade);
//                 ps.setString(3, name);
//                 ps.setString(4, subject);

//                 ps.executeUpdate();
//                 msgLabel.setText("Updated!");
//             }

//             if (e.getSource() == deleteBtn) {
//                 PreparedStatement ps = con.prepareStatement(
//                     "DELETE FROM students WHERE name=? AND subject=?"
//                 );

//                 ps.setString(1, name);
//                 ps.setString(2, subject);

//                 ps.executeUpdate();
//                 msgLabel.setText("Deleted!");
//             }

//         } catch (Exception ex) {
//             ex.printStackTrace();
//             msgLabel.setText("Error");
//         }
//     }
// }






import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GradeManager extends Frame implements ActionListener {

    Label nameLabel, subjectLabel, marksLabel, msgLabel;
    TextField nameField, subjectField, marksField;
    Button addBtn, viewBtn, updateBtn, deleteBtn;
    TextArea area;

    String role;

    public GradeManager(String role) {
        this.role = role;

        setTitle("Grade Manager System");
        setSize(600, 500);
        setLayout(new BorderLayout());

        Panel formPanel = new Panel(new GridLayout(3, 2, 10, 10));

        nameLabel = new Label("Name:");
        subjectLabel = new Label("Subject:");
        marksLabel = new Label("Marks:");

        nameField = new TextField(20);
        subjectField = new TextField(20);
        marksField = new TextField(20);

        formPanel.add(nameLabel);
        formPanel.add(nameField);

        formPanel.add(subjectLabel);
        formPanel.add(subjectField);

        formPanel.add(marksLabel);
        formPanel.add(marksField);

        Panel buttonPanel = new Panel(new GridLayout(1, 4, 10, 10));

        addBtn = new Button("Add");
        viewBtn = new Button("View");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        msgLabel = new Label("Ready");
        area = new TextArea();

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(area, BorderLayout.SOUTH);
        add(msgLabel, BorderLayout.WEST);

        addBtn.addActionListener(this);
        viewBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        if (role.equalsIgnoreCase("student")
                || role.equalsIgnoreCase("parent")) {

            addBtn.setEnabled(false);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            Connection con = DBConnection.getConnection();

            String name = nameField.getText().trim();
            String subject = subjectField.getText().trim();

            // ADD
            if (e.getSource() == addBtn) {

                int marks = Integer.parseInt(marksField.getText().trim());

                Student s = new Student(name, marks);
                String grade = s.getGrade();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO students(name, subject, marks, grade) VALUES (?, ?, ?, ?)");

                ps.setString(1, name);
                ps.setString(2, subject);
                ps.setInt(3, marks);
                ps.setString(4, grade);

                ps.executeUpdate();

                msgLabel.setText("Record Added Successfully");
            }

            // VIEW
            else if (e.getSource() == viewBtn) {

                area.setText("");

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM students");

                while (rs.next()) {

                    area.append(
                            rs.getString("name") + " | "
                                    + rs.getString("subject") + " | "
                                    + rs.getInt("marks") + " | "
                                    + rs.getString("grade") + "\n");
                }

                msgLabel.setText("Records Displayed");
            }

            // UPDATE
            else if (e.getSource() == updateBtn) {

                int marks = Integer.parseInt(marksField.getText().trim());

                Student s = new Student(name, marks);
                String grade = s.getGrade();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE students SET marks=?, grade=? WHERE name=? AND subject=?");

                ps.setInt(1, marks);
                ps.setString(2, grade);
                ps.setString(3, name);
                ps.setString(4, subject);

                int rows = ps.executeUpdate();

                if (rows > 0)
                    msgLabel.setText("Record Updated");
                else
                    msgLabel.setText("Record Not Found");
            }

            // DELETE
            else if (e.getSource() == deleteBtn) {

                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM students WHERE name=? AND subject=?");

                ps.setString(1, name);
                ps.setString(2, subject);

                int rows = ps.executeUpdate();

                if (rows > 0)
                    msgLabel.setText("Record Deleted");
                else
                    msgLabel.setText("Record Not Found");
            }

            con.close();

        } catch (NumberFormatException ex) {
            msgLabel.setText("Enter valid marks");
        } catch (Exception ex) {
            ex.printStackTrace();
            msgLabel.setText("Database Error");
        }
    }
}