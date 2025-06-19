/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EmployeeDetails;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Pasin
 */
public class EmployeeTable {
    
    final JTextField empid;
    final JTextField empFirstName;
    final JTextField empLastName;
    final JTextField empHouseno;  
    final JTextField empcity;        
    final JTextField empStreet;        
    final JTextField empProvince;        
    final JTextField empRole;        
    final JTextField empNIC;        
    final JTextField empEmail;        
    final JTextField empMobile;
    final JTextField pincode;
    final JTextField username;
    final JTextField password;
    private final JTable emptable;
    final DatabaseConnection dbConnection;

    public EmployeeTable(JTextField empid, JTextField empFirstName, JTextField empLastName, JTextField empHouseno, JTextField empcity, JTextField empStreet, JTextField empProvince, JTextField empRole, JTextField empNIC, JTextField empEmail, JTextField empMobile, JTextField pincode, JTextField username, JTextField password, JTable emptable, DatabaseConnection dbConnection) {
        this.empid = empid;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empHouseno = empHouseno;
        this.empcity = empcity;
        this.empStreet = empStreet;
        this.empProvince = empProvince;
        this.empRole = empRole;
        this.empNIC = empNIC;
        this.empEmail = empEmail;
        this.empMobile = empMobile;
        this.pincode = pincode;
        this.username = username;
        this.password = password;
        this.emptable = emptable;
        this.dbConnection = dbConnection;
    }
    


    
    public void employeeTableLoad(){
    
                try{ 
         
            if(dbConnection == null){
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
            
            String query = "SELECT EmployeeID, FirstName, LastName, HouseNo, Street, City, Province, NIC, Role, TelNo, Email, PinCode, username, password FROM employee ";
            
            PreparedStatement pst = dbConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            this.emptable.setModel(DbUtils.resultSetToTableModel(rs));

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
     
    
    public final void EmployeeTableData() {
        int row = emptable.getSelectedRow();

             if (row >= 0) { 

                String empId = emptable.getValueAt(row, 0).toString();
                String empfirstname = emptable.getValueAt(row, 1).toString();
                String emplastname = emptable.getValueAt(row, 2).toString();
                String houseno = emptable.getValueAt(row, 3).toString();
                String street = emptable.getValueAt(row, 4).toString();
                String city = emptable.getValueAt(row, 5).toString();
                String province = emptable.getValueAt(row, 6).toString();
                String nic = emptable.getValueAt(row, 7).toString() ;
                String role = emptable.getValueAt(row, 8).toString() ;
                String mobile = emptable.getValueAt(row, 9).toString() ;                
                String email = emptable.getValueAt(row, 10).toString() ;
                String pin = emptable.getValueAt(row, 11).toString() ;
                String userName = emptable.getValueAt(row, 12).toString() ;
                String Password = emptable.getValueAt(row, 13).toString() ;

                this.empid.setText(empId);
                this.empFirstName.setText(empfirstname);
                this.empLastName.setText(emplastname);
                this.empHouseno.setText(houseno);
                this.empStreet.setText(street);
                this.empcity.setText(city);
                this.empProvince.setText(province);
                this.empRole.setText(role);
                this.empNIC.setText(nic);
                this.empEmail.setText(email);
                this.empMobile.setText(mobile);
                this.pincode.setText(pin);
                this.username.setText(userName);
                this.password.setText(Password);
               
                
            } else {
       
                this.empid.setText("");
                this.empFirstName.setText("");
                this.empLastName.setText("");
                this.empHouseno.setText("");
                this.empStreet.setText("");
                this.empcity.setText("");
                this.empProvince.setText("");
                this.empRole.setText("");
                this.empNIC.setText("");
                this.empEmail.setText("");
                this.empMobile.setText("");
                this.pincode.setText("");
                this.username.setText("");
                this.password.setText("");
            }
    }
}   
