package SuperSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageRectButton extends JButton {
    private BufferedImage image;
    private Dimension buttonSize;
    private int arcWidth = 30;  // Default arc width for rounded corners
    private int arcHeight = 30; // Default arc height for rounded corners

    public ImageRectButton(String imagePath) {
        this(imagePath, null);
    }

    public ImageRectButton(String imagePath, Dimension size) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (size != null) {
            buttonSize = size;
        } else if (image != null) {
            buttonSize = new Dimension(image.getWidth(), image.getHeight());
        } else {
            buttonSize = super.getPreferredSize();
        }

        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorderPainted(false);
    }

    public void setButtonSize(Dimension size) {
        this.buttonSize = size;
        revalidate();
        repaint();
    }

    public void setArcSize(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        revalidate();
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return buttonSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            // Enable antialiasing for smoother edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw the rounded rectangle button
            RoundRectangle2D.Double roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
            g2.setClip(roundedRect);
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            // Draw the thick white border
            g2.setClip(null);
            g2.setStroke(new BasicStroke(5)); // Adjust thickness here
            g2.setColor(Color.WHITE);
            g2.draw(roundedRect);

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
        // Check if the point is within the rounded rectangle shape
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        return roundedRect.contains(x, y);
    }
}
