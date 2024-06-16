package SuperSwing;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JFrame {

    public GameOver() {
        setSize(700, 400);  // Example size, adjust as needed
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        ImagePanel panel = new ImagePanel();
        add(panel);
        setVisible(true);
    }

    class ImagePanel extends JPanel {
        private Image backgroundImage;
        private HoverButton button;

        public ImagePanel() {
            backgroundImage = new ImageIcon("Images\\GAMEOVER.png").getImage();
            setLayout(null);
            button = new HoverButton("MENU", new Color(95, 10, 10));
            button.setBounds(265, 220, 170, 60);
            Font buttonFont = new Font("Chiller", Font.PLAIN, 70);
            button.setFont(buttonFont);
            add(button);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}