/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JLabel;


/**
 *
 * @author Pasin
 */
public class Manager {
    
     public DatabaseConnection dbConnection;
     private final JLabel pcountLabel;
     private final JLabel purchaseLabel;
     private final JLabel revenueLabel;
     
     public Manager(JLabel pcount, JLabel purchase, JLabel revenue, DatabaseConnection dbConnection){
     
         this.pcountLabel = pcount;
         this.purchaseLabel = purchase;
         this.revenueLabel = revenue;
         this.dbConnection = dbConnection;
     
     }
    
    public int getProductCount() {
    int count = 0;
    
    try {
        if (dbConnection == null) {
            System.out.println( "Database connection is null");
            return count; 
        }
        
        String query = "SELECT COUNT(*) FROM  product";
        
        PreparedStatement pst = dbConnection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            count = rs.getInt(1); 
        }

        rs.close();
        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return count; 
}
    

    public int getPurchaseCount() {
    int count = 0;

    try {
        if (dbConnection == null) {
            System.out.println("Database connection is null");
            return count;
        }

        String query = "SELECT COUNT(*) FROM purchase";

        PreparedStatement pst = dbConnection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        pst.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
   
   
}


public double getRevenue() {
    double result = 0.0;

    try {
        if (dbConnection == null) {
            System.out.println("Database connection is null");
            return result;
        }

        String query = "SELECT SUM(amount) FROM purchase ";
       

        try (PreparedStatement pst = dbConnection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                result = rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

  
    return result;
}



    
}
