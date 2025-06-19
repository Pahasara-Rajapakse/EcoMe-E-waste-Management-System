/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager.Products;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Pasin
 */
public class ProductTable {
    
    final DatabaseConnection dbConnection;
    final JTable table;
    final JTextField productID;
    final JTextField productName;
    final JTextField productBrand;
    final JTextField productType;
    final JTextField productPrice;
    final JTextField productQuan;
    final JTextField productCondition;
 //   final JLabel productIcon;
    
    public ProductTable(DatabaseConnection dbConnection, JTable table, JTextField productID, JTextField productName, JTextField productBrand, JTextField productType, JTextField productPrice, JTextField productQuan, JTextField productCondition, JLabel productIcon) {
        this.dbConnection = dbConnection;
        this.table = table;
        this.productID = productID;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productQuan = productQuan;
        this.productCondition = productCondition;
       // this.productIcon = productIcon;
    }



    public void ProductTableLoad() {
     
        try{ 
         
            if(dbConnection == null){
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
            
            String query = "select product_id as Product_ID, name as Product_name, brand as Brand, type as Type, price as Price, quantity as Quantity, condition_ as Condition_ from product ";
            
            PreparedStatement pst = dbConnection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            this.table.setModel(DbUtils.resultSetToTableModel(rs));

            /*            //int imageColumnIndex = 8;
            while (rs.next()) {
            byte[] imageBytes = rs.getBytes(imageColumnIndex);
            if (imageBytes != null) {
            ImageIcon imageIcon = new ImageIcon(imageBytes);
            productIcon.setIcon(imageIcon);
            } else {
            productIcon.setIcon(null);
            }
            }*/
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
            
        
    }
       
        
    
    
    public final void ProductTableData() {
        int row = table.getSelectedRow();

             if (row >= 0) { 

                String productid = table.getValueAt(row, 0).toString();
                String name = table.getValueAt(row, 1).toString();
                String brand = table.getValueAt(row, 2).toString();
                String type = table.getValueAt(row, 3).toString();
                String price = table.getValueAt(row, 4).toString();
                String quantity = table.getValueAt(row, 5).toString();
                String condition = table.getValueAt(row, 6).toString();
               // String ob_image= table.getValueAt(row, 7).toString() ;

                productID.setText(productid);
                productName.setText(name);
                productBrand.setText(brand);
                productType.setText(type);
                productPrice.setText(price);
                productQuan.setText(quantity);
                productCondition.setText(condition);
            //    productIcon.setText(ob_image);
                
            } else {
       
                productID.setText("");
                productName.setText("");
                productBrand.setText("");
                productType.setText("");
                productPrice.setText("");
                productQuan.setText("");
                productCondition.setText("");
               // productIcon.setIcon(null);
            }
    }
    

}
