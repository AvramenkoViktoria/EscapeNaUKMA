package Vozniuk;

import SuperSwing.ImageButton;

import javax.swing.*;
import java.awt.*;

public class PersonalIdentification extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    public PersonalIdentification(VozniukAccount vozniukAccount) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Verify your account");
        ImageIcon icon = new ImageIcon("Images\\VozniukA.jpg");
        Image image = icon.getImage();
        setIconImage(image);
        addMainLabel();
        addAppleButton();
        addAndroidButton(vozniukAccount);
        setVisible(true);
    }

    private void addMainLabel() {
        JLabel label = new JLabel("What operation system is your phone?");
        label.setBounds(300, 100, 500, 50);
        //label.setFont();
        add(label);
    }

    private void addAppleButton() {
        ImageButton apple = new ImageButton("Images\\VozniukA.jpg");
        apple.setBounds(100, 300, 200, 200);
        apple.addActionListener(e -> {
            // Wiping all the data and restarting the game
        });
        add(apple);
    }

    private void addAndroidButton(VozniukAccount vozniukAccount) {
        ImageButton android = new ImageButton("Images\\Glybovets.jpg");
        android.setBounds(600, 300, 200, 200);
        android.addActionListener(e -> {
            setVisible(false);
            CiscoBinaryGame binaryGame = new CiscoBinaryGame(vozniukAccount);
        });
        add(android);
    }
}
