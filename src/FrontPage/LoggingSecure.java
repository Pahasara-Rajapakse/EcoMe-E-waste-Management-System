package FrontPage;

import Database.DatabaseConnection;
import Manager.ManagerDashboard;
import Manager.WindwUtils.WindowUtils;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.sql.ResultSet;
import javax.swing.JLabel;
import java.sql.SQLException;

public class LoggingSecure {

    private final JPasswordField pinCode;
    final DatabaseConnection dbConnection;
    private final JLabel message;
    private final ManagerPin managerpin;
    public final String sourcePage;


    public LoggingSecure(JPasswordField pinCode, DatabaseConnection dbConnection, JLabel message, ManagerPin managerpin, String sourcePage) {
        this.pinCode = pinCode;
        this.dbConnection = dbConnection;
        this.message = message;
        this.managerpin = managerpin;
        this.sourcePage = sourcePage;
    }

    public void Security() {
        try {
            char[] passwordChars = pinCode.getPassword();
            String pin = new String(passwordChars);

            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database not connected");
                return;
            }

            String query = "SELECT pincode FROM employee WHERE role = 'manager'";
            try (PreparedStatement pst = dbConnection.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String managerPin = rs.getString("pincode");

                        if (pin.equals(managerPin)) {
                            System.out.println("pin correct");
                            if ("LoggingPage".equals(sourcePage)) {
                                System.out.println("access11111");

                                WindowUtils.close(managerpin);
                                ManagerDashboard dash = new ManagerDashboard();
                                dash.setVisible(true);

                            } 
                        } else {
                            // Incorrect PIN
                            this.message.setText("Incorrect PIN!!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Manager not found in the database");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
