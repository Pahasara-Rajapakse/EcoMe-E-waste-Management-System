package Manager.Products;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Product extends ProductTable {

    public Product(DatabaseConnection dbConnection, JTable table, JTextField productID, JTextField productName, JTextField productBrand, JTextField productType, JTextField productPrice, JTextField productQuan, JTextField productCondition, JLabel productIcon) {
        super(dbConnection, table, productID, productName, productBrand, productType, productPrice, productQuan, productCondition, productIcon);
    }

    
    
    public void insertProduct() {
        String productid = this.productID.getText();
        String productname = this.productName.getText();
        String productbrand = this.productBrand.getText();
        String producttype = this.productType.getText();
        String pric = this.productPrice.getText();
        String quan = this.productQuan.getText();
        String condition = this.productCondition.getText();

        // Validation checks
        if (productid.isEmpty() || productname.isEmpty() || productbrand.isEmpty() || producttype.isEmpty() || pric.isEmpty() || quan.isEmpty() || condition.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required. Please fill in all the fields.");
            return;
        }

        try {
            double price = Double.parseDouble(pric);
            int quantity = Integer.parseInt(quan);

            if (price <= 0 || quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Price and quantity must be positive values.");
                return;
            }

            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }

            // Database insertion
            String query = "INSERT INTO product (product_id, name, brand, type, price, quantity, condition_) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement pst = dbConnection.prepareStatement(query);
                pst.setString(1, productid);
                pst.setString(2, productname);
                pst.setString(3, productbrand);
                pst.setString(4, producttype);
                pst.setDouble(5, price);
                pst.setInt(6, quantity);
                pst.setString(7, condition);
               
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Product added successfully.");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric values for price or quantity.");
        }
    }    
    

    public void UpdateProduct() {
        
        String productid = this.productID.getText();
        String productname = this.productName.getText();
        String productbrand = this.productBrand.getText();
        String producttype = this.productType.getText();
        String pric = this.productPrice.getText();
        String quan = this.productQuan.getText();
        String condition = this.productCondition.getText();

        try {
            
            if (productid.isEmpty() || productname.isEmpty() || productbrand.isEmpty() || producttype.isEmpty() || pric.isEmpty() || quan.isEmpty() || condition.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required. Please fill in all the fields.");
                return;
            }
            
            if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
            }
            
            double price = Double.parseDouble(pric);
            int quantity = Integer.parseInt(quan);

            if (price <= 0 || quantity <= 0) {
                JOptionPane.showMessageDialog(null, "Price and quantity must be positive values.");
                return;
            }

            String query = "UPDATE product SET name = ?, brand = ?, type = ?, price = ?, quantity = ?, condition_ = ? WHERE product_id = ?";
            PreparedStatement pst = dbConnection.prepareStatement(query);
            pst.setString(1, productname);
            pst.setString(2, productbrand);
            pst.setString(3, producttype);
            pst.setDouble(4, price);
            pst.setInt(5, quantity);
            pst.setString(6, condition);
            pst.setString(7, productid);
          
            
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record UPDATED");
            } else {
                JOptionPane.showMessageDialog(null, "No records updated. Product ID not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid numeric values for price or quantity.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    
    public void deleteProduct(){
    
        try {
            
             if (dbConnection == null) {
                JOptionPane.showMessageDialog(null, "Database connection is null");
                return;
             }

             String productid = this.productID.getText(); 
             String query = "DELETE FROM product WHERE product_id = ?";
        
        PreparedStatement pst = dbConnection.prepareStatement(query);
        pst.setString(1, productid);
        
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
