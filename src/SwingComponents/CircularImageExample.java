package SwingComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class CircularImageExample extends JFrame {

    public String path;
    
    public CircularImageExample() {
        initComponents();
    }

    public CircularImageExample(String path) {
        this.path = path;
    }
    
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Circular Image Example");

        // Use absolute path to the image
        JLabel circularImageLabel = new CircularImageLabel(this.path);
        circularImageLabel.setHorizontalAlignment(JLabel.CENTER);
        circularImageLabel.setVerticalAlignment(JLabel.CENTER);

        add(circularImageLabel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    public void setHorizontalAlignment(int CENTER) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setVerticalAlignment(int CENTER) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Custom JLabel for circular image
    private static class CircularImageLabel extends JLabel {
       // private BufferedImage originalImage;
        private ImageIcon originalImage;

    public CircularImageLabel(String imagePath) {
        try {
            URL imageUrl = getClass().getClassLoader().getResource(imagePath);
            originalImage = new ImageIcon(imageUrl);
            setPreferredSize(new Dimension(originalImage.getIconWidth(), originalImage.getIconHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Create a buffered image with a TYPE_INT_ARGB type
        BufferedImage circularImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = circularImage.createGraphics();

        // Create a circular shape
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);

        // Set the clip to the circular shape
        g2d.setClip(circle);

        // Draw the original image onto the circular image
        originalImage.paintIcon(this, g2d, 0, 0);

        // Dispose of the graphics object
        g2d.dispose();

        // Draw the circular image onto the label
        g.drawImage(circularImage, 0, 0, this);
    }
}
}
