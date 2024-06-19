package SceneObjects;

import Enums.Level;
import javax.swing.*;
import java.awt.*;

public class Hearts extends JLabel {
    private static final int FULL_WIDTH = 150;
    private static final int TWO_HEARTS_WIDTH = 110;
    private static final int ONE_HEART_WIDTH = 60;
    private static final int HEIGHT = 35;
    private String imagePath;

    public Hearts(String imagePath, Level level, int x, int y, int width, int height) {
        this.imagePath = imagePath;
        setIcon(new ImageIcon(imagePath)); // Set the image icon directly to the JLabel
        setIcon(resizeImage(imagePath, width, height));
        setBounds(x, y, width, height);
        setOpaque(false); // Ensure the JLabel is transparent
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        // Set bounds based on the level and desired size
        switch (level) {
            case CONTRACT -> setBounds(x, y, FULL_WIDTH, HEIGHT);
            case BUDGET -> setBounds(x, y, TWO_HEARTS_WIDTH, HEIGHT);
            case GRANT -> setBounds(x, y, ONE_HEART_WIDTH, HEIGHT);
        }
    }

    public String getPath() {
        return imagePath;
    }
    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
