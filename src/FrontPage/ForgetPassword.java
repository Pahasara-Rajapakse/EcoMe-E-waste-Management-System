package FrontPage;

import Database.DatabaseConnection;
import FrontPage.LoggingPage;
import FrontPage.Loging;
import Manager.WindwUtils.WindowUtils;
import Registration.RegisterEwaste;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.SwingUtilities;



public class ForgetPassword  {

    final JTextField conPassword;
    final JTextField newPassword;
    final JTextField username;
    final DatabaseConnection dbConnection;
    private final JLabel message;
    private final ForgetPasswordPage fpPage;

    public ForgetPassword(JTextField conPassword, JTextField newPassword, JTextField username, DatabaseConnection dbConnection, JLabel message, ForgetPasswordPage fpage) {
        this.conPassword = conPassword;
        this.newPassword = newPassword;
        this.username = username;
        this.dbConnection = dbConnection;
        this.message = message;
        this.fpPage = fpage;
    }
   

public void forgetPassword() {
    try {
        String us = this.username.getText();
        String ps = this.newPassword.getText();
        String cps = this.conPassword.getText();

        if (dbConnection == null) {
            JOptionPane.showMessageDialog(null, "Database not connected");
            return;
        }

        if (!ps.equals(cps)) {
            this.message.setText("Passwords do not match");
            return;
        }

        // Check the role before updating the password
        String selectRoleQuery = "SELECT role FROM employee WHERE username = ?";
        try (PreparedStatement rolePst = dbConnection.prepareStatement(selectRoleQuery)) {
            rolePst.setString(1, us);

            try (ResultSet roleRs = rolePst.executeQuery()) {
                if (roleRs.next()) {
                    String role = roleRs.getString("role");

                  if ("manager".equals(role)) {
                // Show the ManagerPin frame
                
                    SwingUtilities.invokeLater(() -> {
                    ManagerPin managerpin = new ManagerPin();
                    managerpin.setVisible(true);
                    
                    
                });
                    
                    } else if ("cashier".equals(role)) {
                        
                        // Continue with the password update for cashier
                        String updateQuery = "UPDATE employee SET password = ? WHERE username = ? AND role = ?";
                        try (PreparedStatement updatePst = dbConnection.prepareStatement(updateQuery)) {
                            updatePst.setString(1, ps);
                            updatePst.setString(2, us);
                            updatePst.setString(3, "cashier");

                            int rowsAffected = updatePst.executeUpdate();

                            if (rowsAffected > 0) {
                                JOptionPane.showMessageDialog(null, "Password successfully updated");
                            } else {
                                JOptionPane.showMessageDialog(null, "Username not found or password update failed");
                                this.username.setText("");
                                this.newPassword.setText("");
                                this.conPassword.setText("");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Unsupported role for password update");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Role not found for the username");
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
