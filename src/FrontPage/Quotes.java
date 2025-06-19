/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrontPage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import javax.swing.JLabel;

/**
 *
 * @author Pasin
 */
public class Quotes {
    
    private JLabel quoteLabel;
    private Random random;
    
    public Quotes(JLabel qLabel){
    
        this.quoteLabel = qLabel;
        this.random = new Random(System.currentTimeMillis());
       
    }
   
    public void displayQuotes(String folderPath){
        try {
            List<String> quotes = Files.readAllLines(Paths.get(folderPath));
            
            if(!quotes.isEmpty()){
                
                
                int ranIndex = random.nextInt(quotes.size());
                String ranQuotes = quotes.get(ranIndex);
                
                String[] lines = ranQuotes.split("\\n");
                StringBuilder quote = new StringBuilder();
                
                for (int i = 0; i < Math.min(4, lines.length); i++) {
                    quote.append(lines[i]).append("<br>");
                }
                this.quoteLabel.setText("<html><div style='text-align: center;'>" + quote + "</div></html>");
              
            }else{
                this.quoteLabel.setText("No Quotes Found.");
            
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.quoteLabel.setText("Error reading quotes.");
        }
        
    
    }
}
