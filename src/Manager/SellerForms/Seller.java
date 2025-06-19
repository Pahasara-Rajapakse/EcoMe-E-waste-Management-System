/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.SellerForms;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Pasin
 */
public class Seller {
    
    private final DatabaseConnection dbConnection;
    private final JTable table;
    private final JTextField sellerID;
    private final JTextField sellerName;
    private final JTextField sellerAddress;
    private final JTextField sellerCity;
    private final JTextField sellerProvince;
    private final JTextField sellerEmail;
    private final JTextField sellerMobile;
    private final JTextArea sellerEwaste;
    private final JTextArea sellerQuantity;
    private final JTextField appRaise;

    public Seller(DatabaseConnection dbConnection, JTable table, JTextField sellerID, JTextField sellerName, JTextField sellerAddress, JTextField sellerCity, JTextField sellerProvince, JTextField sellerEmail, JTextField sellerMobile, JTextArea sellerEwaste, JTextArea sellerQuantity, JTextField appRaise) {
        this.dbConnection = dbConnection;
        this.table = table;
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.sellerAddress = sellerAddress;
        this.sellerCity = sellerCity;
        this.sellerProvince = sellerProvince;
        this.sellerEmail = sellerEmail;
        this.sellerMobile = sellerMobile;
        this.sellerEwaste = sellerEwaste;
        this.sellerQuantity = sellerQuantity;
        this.appRaise = appRaise;
    }


  
    
    public void sellerTableLoad(){
               
        try {
         
            if(dbConnection == null){
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
            
            String query = "select form_id as Form_ID, seller_id as Seller_ID, date as Date, stutus as Stutus from seller_forms order by date ASC ";
            
            PreparedStatement pst = dbConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            this.table.setModel(DbUtils.resultSetToTableModel(rs));

            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        
    
    }
    
    private void sellerTableSelection() {
    this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                   
                }
            }
        }
    });
}
    
    
    public void sellerTableData() {
        
    int selectedRow = table.getSelectedRow();
    try {
        String query = "select s.seller_id, s.First_name, s.address, s.city, s.province, e.ewaste_name, e.quantity, s.email, s.mobile_number from  seller s right join e_waste e on s.seller_id = e.seller_id where e.seller_id = ?";
        
       
        PreparedStatement pst = dbConnection.prepareStatement(query);
                        if (selectedRow >= 0) {
                        String sellerId = table.getValueAt(selectedRow, 1).toString();
                        
                        try {
                            
                            pst.setString(1, sellerId);
                            
                            
                            ResultSet rs = pst.executeQuery();
                 
                StringBuilder eWasteNames = new StringBuilder();
                StringBuilder quantities = new StringBuilder();
                            
                while (rs.next()) {
                    String seller_id = rs.getString("seller_id");
                    String Name = rs.getString("first_name");
                    String addressStr = rs.getString("address");
                    String city = rs.getString("city");
                    String province = rs.getString("province");
                    String ewasteName = rs.getString("ewaste_name");
                    String qty = rs.getString("quantity");                    
                    String email = rs.getString("email");
                    String mobileNumber = rs.getString("mobile_number");


                    this.sellerID.setText(seller_id);
                    this.sellerName.setText(Name);
                    this.sellerAddress.setText(addressStr);
                    this.sellerCity.setText(city);
                    this.sellerProvince.setText(province);
                    this.sellerEmail.setText(email);
                    this.sellerMobile.setText(mobileNumber);

                    eWasteNames.append(ewasteName).append("\n");
                    quantities.append(qty).append("\n");
                }

                this.sellerEwaste.setText(eWasteNames.toString().replaceAll(", $", ""));
                this.sellerQuantity.setText(quantities.toString());
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                           
                        }
                    }
                }
                catch (Exception e){
                   e.printStackTrace();
                }
            }
  
    public void appRaise(){
    
        String appRaise = this.appRaise.getText();
        
        int selectedRow = this.table.getSelectedRow();
        String query = "UPDATE seller_forms SET amount = ? WHERE form_id = ?";
   
        if (selectedRow >= 0) {
        String formid = this.table.getValueAt(selectedRow, 0).toString();
    
        try {
            PreparedStatement pst = dbConnection.prepareStatement(query);
            
            pst.setString(1,appRaise);
            pst.setString(2, formid);
            int rowsUpdated = pst.executeUpdate();
    
                if(rowsUpdated > 0){
                    JOptionPane.showMessageDialog(null, "Added");
                    this.table.setValueAt(appRaise, selectedRow, 3);
                }else {
                JOptionPane.showMessageDialog(null, "Failed to Added.");
                }

        } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating seller's price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
    JOptionPane.showMessageDialog(null, "Please select a row to appraise.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
