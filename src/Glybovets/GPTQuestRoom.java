package Glybovets;

import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.*;

public class GPTQuestRoom extends ImageBackground {
    private static final int WIDTH = 483;
    private static final int HEIGHT = 650;
    private JLabel glybovetsLabel;
    public boolean canSee;
    private boolean movingForward = true;
    public int step;

    public GPTQuestRoom(String imagePath) {
        super(imagePath);
        setBounds(1000, 0, WIDTH, HEIGHT);
        setLayout(null);
        addGlybovets();
        addMainCharacter();
        step = 1;
    }

    private void addGlybovets() {
        ImageIcon glybovets = resizeImage("Images\\Glybovets.jpg", 60, 60);
        glybovetsLabel = new JLabel(glybovets);
        glybovetsLabel.setBounds(220, 27, 60, 60);
        add(glybovetsLabel);
        revalidate();
        repaint();
    }

    private void addMainCharacter() {
        ImageIcon mainCharacter = resizeImage("Images\\MainCharDown.png", 70, 70);
        JLabel charLabel = new JLabel(mainCharacter);
        charLabel.setBounds(30, 300, 70, 70);
        add(charLabel);
        revalidate();
        repaint();
    }

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void moveGlybovets() {
        if (movingForward) {
            glybovetsLabel.setLocation(glybovetsLabel.getX(), glybovetsLabel.getY() + step);
            if (glybovetsLabel.getY() + glybovetsLabel.getHeight() >= HEIGHT - 15) {
                movingForward = false;
            }
        } else {
            glybovetsLabel.setLocation(glybovetsLabel.getX(), glybovetsLabel.getY() - step);
            if (glybovetsLabel.getY() <= 27) {
                glybovetsLabel.setLocation(glybovetsLabel.getX(), 27);
                movingForward = true;
            }
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //drawCenterLine(g);
    }

    private void drawCenterLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        int centerY = HEIGHT / 2;
        g2d.drawLine(0, centerY - 24, WIDTH, centerY - 24);
    }
}
