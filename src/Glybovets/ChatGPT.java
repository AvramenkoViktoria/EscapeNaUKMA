package Glybovets;

import SuperSwing.ImageBackground;
import SuperSwing.ImageRectButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Random;

public class ChatGPT extends ImageBackground implements ActionListener {
    private static final int WIDTH = 950;
    private static final int HEIGHT = 670;
    public boolean chat;
    private int points = 0;
    private Timer timer;
    private ImageRectButton generateButton;
    private final Random random = new Random();
    private JLabel pointsLabel;
    private int stepCounter = 0;
    private int delay = 20;

    public ChatGPT(String imagePath) {
        super(imagePath);
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        timer = new Timer(delay, this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (x >= WIDTH - 60 && x <= WIDTH && y >= 0 && y <= 40) {
                    if (chat) {
                        changeImage("Images\\IDE.png");
                        generateButton.setVisible(false);
                        pointsLabel.setVisible(false);
                        chat = false;
                    } else {
                        changeImage("Images\\ChatGPT.png");
                        generateButton.setVisible(true);
                        pointsLabel.setVisible(true);
                        chat = true;
                    }
                }
            }
        });
        addGenerateButton();
        addPointsLabel();
        timer.start();
    }

    private void addGenerateButton() {
        generateButton = new ImageRectButton("Images\\Generate.png", new Dimension(120, 50));
        generateButton.setBounds(random.nextInt(290, WIDTH - 100), random.nextInt(190, HEIGHT - 40 - 100), 120, 50);
        generateButton.addActionListener(e -> {
            generateButton.setLocation(random.nextInt(290, WIDTH - 150), random.nextInt(190, HEIGHT - 140));
            points += 5;
            pointsLabel.setText("Points: " + points);
            repaint();
            revalidate();
        });
        add(generateButton);
    }

    private void addPointsLabel() {
        pointsLabel = new JLabel("Points: " + points);
        pointsLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        pointsLabel.setBounds(80, HEIGHT - 100, 150, 40);
        pointsLabel.setForeground(Color.WHITE);
        pointsLabel.setFont(pointsLabel.getFont().deriveFont(25.0f));
        add(pointsLabel);
        revalidate();
        repaint();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isChat() {
        return chat;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GPTTest.quest.room.moveGlybovets();
        stepCounter++;
        if (stepCounter == 190) {
            stepCounter = 0;
            GPTTest.quest.room.step++;
        }
    }
}
