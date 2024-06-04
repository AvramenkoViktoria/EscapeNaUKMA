package SuperSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageButtonSimple extends JButton {
    private BufferedImage image;

    public ImageButtonSimple(String imagePath) {
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

            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);

            g2.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Do not paint the default border
    }
}
