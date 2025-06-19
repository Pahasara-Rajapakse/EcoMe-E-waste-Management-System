/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reciption_RegisterEwastes;

import Database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Pasin
 */
public class Ewaste {
   
    public String eName;
    public String eType;
    public int eQuantity;
    public String eDescrition;
    private DatabaseConnection dbConnection;
    
    
    public Ewaste(){
    dbConnection = new DatabaseConnection();

    }
    
    public void RegisterEwaste(String ename, int equantity, String description, String etype ){
    
        try {
            if(dbConnection == null){
                System.out.println("Database Connecton Error");
            }
            
            String query = "INSERT INTO e_waste values ewaste_name, quantity, description, type WHERE seller_id = ? ";
            try (PreparedStatement pst = dbConnection.prepareStatement(query)){
               pst.setString(1, eName);
                pst.setInt(2, eQuantity);
                 pst.setString(3, eDescrition);
                  pst.setString(4, eType);
               
                  try (ResultSet rs = pst.executeQuery()){
                      if(!rs.isBeforeFirst()){
                                
                      }
                    
                } catch (Exception e) {
                }
                
            } catch (Exception e) {
            }
        } catch (Exception e) {
        }    
        
    }
     
    
}
