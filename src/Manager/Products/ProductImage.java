package Manager.Products;

import Database.DatabaseConnection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

public class ProductImage {
    
    private byte[] ob_image;
    private final JLabel productImage;


    public ProductImage(JLabel productImage) {
        this.productImage = productImage;
      
    }

    public void setImageData(byte[] image) {
        this.ob_image = image;
        // Update the displayed image in your JLabel here if needed
    }


    public byte[] loadImage() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();

        if (f != null) {
            String filename = f.getAbsolutePath();
            System.out.println("File path: " + filename);

            try (FileInputStream fis = new FileInputStream(f);
                 ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

                byte[] buf = new byte[1024];
                for (int readnum; (readnum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readnum);
                }

                this.ob_image = bos.toByteArray();
                JOptionPane.showMessageDialog(null, "Image added successfully");

                
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage()
                 .getScaledInstance(this.productImage.getWidth(), this.productImage.getHeight(), java.awt.Image.SCALE_SMOOTH));
                this.productImage.setIcon(imageIcon);
                return bos.toByteArray(); 
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
        return null;
    }
  
  
}
