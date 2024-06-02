package SuperSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageBackground extends JPanel {
    protected BufferedImage image;

    public ImageBackground(String imagePath) {
        loadImage(imagePath);
    }

    public ImageBackground(Color color) {
        setBackground(color);
    }

    protected void loadImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
            repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
