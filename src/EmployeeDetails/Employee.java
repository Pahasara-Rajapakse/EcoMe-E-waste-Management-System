/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EmployeeDetails;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Pasin
 */
public class Employee extends EmployeeTable {
    
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);
    
    
    public Employee(JTextField empid, JTextField empFirstName, JTextField empLastName, JTextField empHouseno, JTextField empcity, JTextField empStreet, JTextField empProvince, JTextField empRole, JTextField empNIC, JTextField empEmail, JTextField empMobile, JTextField pincode, JTextField username, JTextField password, JTable emptable, DatabaseConnection dbConnection) {
        super(empid, empFirstName, empLastName, empHouseno, empcity, empStreet, empProvince, empRole, empNIC, empEmail, empMobile, pincode, username, password, emptable, dbConnection);
    }
    
    
    
    private boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }    
    
    
    public void insertEmployee(){
        
        String empId = this.empid.getText();
        String empfirstname = this.empFirstName.getText();
        String emplastname = this.empLastName.getText();
        String houseno = this.empHouseno.getText();
        String street = this.empStreet.getText();
        String city = this.empcity.getText();
        String province = this.empProvince.getText();
        String role = this.empRole.getText();
        String nic = this.empNIC.getText();
        String email = this.empEmail.getText();
        String mobile = this.empMobile.getText();
        String pin = this.pincode.getText();
        String userName = this.username.getText();
        String Password = this.password.getText();

        // Validation checks

        try {
            
            if (empId.isEmpty() || empfirstname.isEmpty() || emplastname.isEmpty() || houseno.isEmpty() || street.isEmpty() || city.isEmpty() || province.isEmpty() || role.isEmpty() || nic.isEmpty()
            || email.isEmpty() || mobile.isEmpty() ||  pin.isEmpty() || userName.isEmpty() || Password.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "All fields are required. Please fill in all the fields.");
            return;
            }
            
            
            int telNo = Integer.parseInt(mobile);

            if (telNo <= 0 ) {
                JOptionPane.showMessageDialog(null, "Telephone Number must be positive .");
                return;
            }

            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
                   
            if (!validateEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email.");
                return;
            }

            // Database insertion
            String query = "INSERT INTO Employee (EmployeeID, FirstName, LastName, HouseNO, Street, City, Province, Role, NIC, Email, TelNo, username, password, PinCode  ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
            try {
                PreparedStatement pst = dbConnection.prepareStatement(query);
                pst.setString(1, empId);
                pst.setString(2, empfirstname);
                pst.setString(3, emplastname);
                pst.setString(4, houseno);
                pst.setString(5, street);
                pst.setString(6, city);
                pst.setString(7, province);
                pst.setString(8, role);
                pst.setString(9, nic);
                pst.setString(10, email);
                pst.setInt(11, telNo);                
                pst.setString(12, userName);
                pst.setString(13, Password);
                pst.setString(14, pin);
               
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "EMPLOYEE added successfully.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric values for TELEPHONE NUMBER.");
        }
    }
    
    public void updateEmployee(){
        
        String empId = this.empid.getText();
        String empfirstname = this.empFirstName.getText();
        String emplastname = this.empLastName.getText();
        String houseno = this.empHouseno.getText();
        String street = this.empStreet.getText();
        String city = this.empcity.getText();
        String province = this.empProvince.getText();
        String role = this.empRole.getText();
        String nic = this.empNIC.getText();
        String email = this.empEmail.getText();
        String mobile = this.empMobile.getText();
        String pin = this.pincode.getText();
        String userName = this.username.getText();
        String Password = this.password.getText();
  
        try {
            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
            
            int telNo = Integer.parseInt(mobile);

            if (telNo <= 0 ) {
                JOptionPane.showMessageDialog(null, "Telephone Number must be positive .");
                return;
            }

            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
                   
            if (!validateEmail(email)) {
                JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email.");
                return;
            }

            String query = "UPDATE employee SET EmployeeID = ?, FirstName = ?, LastName = ?, HouseNo = ?, Street = ?, City = ?, Province = ?, NIC = ?, Email = ?, TelNo = ?, username = ?, password = ?, Role = ?, PinCode = ?  WHERE EmployeeID = ? ";
            PreparedStatement pst = dbConnection.prepareStatement(query);
            pst.setString(1, empId);
            pst.setString(2, empfirstname);
            pst.setString(3, emplastname);
            pst.setString(4, houseno);
            pst.setString(5, street);
            pst.setString(6, city);
            pst.setString(7, province);
            pst.setString(8, nic);
            pst.setString(9, email);
            pst.setInt(10, telNo);
            pst.setString(11, userName);
            pst.setString(12, Password);
            pst.setString(13, role);
            pst.setString(14, pin);
            
            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record UPDATED");
            } else {
                JOptionPane.showMessageDialog(null, "No records updated. Employee_ID not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric values for Telephone Number.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        
    
    }
    
    public void deleteEmployee(){
    
        try {
            
             if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
             }

             String empId = this.empid.getText(); 
             String query = "DELETE FROM Employee WHERE EmployeeID = ?";
        
             PreparedStatement pst = dbConnection.prepareStatement(query);
             pst.setString(1, empId);
        
             int rowCount = pst.executeUpdate();
        
             if (rowCount > 0) {
                 JOptionPane.showMessageDialog(null, "Record deleted successfully.");
            
             } else {
                 JOptionPane.showMessageDialog(null, "No record found to delete.");
             }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    
    }
 }

