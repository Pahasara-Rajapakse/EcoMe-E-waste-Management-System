package FrontPage;

import Database.DatabaseConnection;
import Manager.WindwUtils.WindowUtils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Registration.RegisterEwaste;


public class Loging {

    private final JLabel message;
    final JTextField user;
    final JPasswordField password;
    final DatabaseConnection dbConnection;
    private final LoggingPage fPage;

    public Loging(JLabel msg, JTextField user, JPasswordField password, DatabaseConnection dbConnection, LoggingPage fPage) {
        this.message = msg;
        this.user = user;
        this.password = password;
        this.dbConnection = dbConnection;
        this.fPage = fPage;
    }

    public void loging() {
        try {
            String um = this.user.getText();
            String ps = new String(this.password.getPassword());

            if (dbConnection == null) {
                System.out.println("Database not connected");
                return;
            }

            String query = "SELECT username, password, role FROM employee WHERE username = ? AND password = ?";
            try (PreparedStatement pst = dbConnection.prepareStatement(query)) {
                pst.setString(1, um);
                pst.setString(2, ps);

                try (ResultSet rs = pst.executeQuery()) {
                    if (!rs.isBeforeFirst()) {
                        this.message.setText("Incorrect username or password");
                        this.user.setText("");
                        this.password.setText("");
                    } else {
                        while (rs.next()) {
                            String role = rs.getString("role");
                            switch (role) {
                                case "manager":
                                    WindowUtils.close(fPage);
                                    ManagerPin managerpin = new ManagerPin();
                                    managerpin.setVisible(true);
                                    break;
                                case "cashier":
                                    WindowUtils.close(fPage);
                                    RegisterEwaste cashier = new RegisterEwaste();
                                    cashier.setVisible(true);
                                    break;
                                default:
                                    // Handle other roles or unexpected values
                                    break;
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
