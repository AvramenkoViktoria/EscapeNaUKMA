package SuperSwing;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageButton extends JButton {
    private BufferedImage image;
    public ImageButton(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false);
    }

    @Override
    public Dimension getPreferredSize() {
        if (image != null) {
            return new Dimension(image.getWidth(), image.getHeight());
        } else {
            return super.getPreferredSize();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            // Enable antialiasing for smoother edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the round button
            Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
            g2.setClip(circle);
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            // Draw the thick white border
            g2.setClip(null);
            g2.setStroke(new BasicStroke(5)); // Adjust thickness here
            g2.setColor(Color.WHITE);
            g2.draw(circle);

            g2.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the default border
    }

    @Override
    public boolean contains(int x, int y) {
        // Check if the point is within the round shape
        int radius = getWidth() / 2;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        return (x - centerX) * (x - centerX) + (y - centerY) * (y - centerY) <= radius * radius;
    }
}