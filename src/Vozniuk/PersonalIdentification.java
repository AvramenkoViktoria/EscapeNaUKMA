package Vozniuk;

import Data.Test;
import SuperSwing.ImageButton;

import javax.swing.*;
import java.awt.*;

public class PersonalIdentification extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    public PersonalIdentification(VozniukAccount vozniukAccount) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(new Color(10, 44, 86));
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setTitle("Verify your account");
        ImageIcon icon = new ImageIcon("Images\\VozniukA.jpg");
        Image image = icon.getImage();
        setIconImage(image);
        addMainLabel();
        addAppleButton();
        addAndroidButton();
        setVisible(true);
    }

    private void addMainLabel() {
        JLabel label = new JLabel("CHOOSE OPERATION SYSTEM:");
        label.setFont(new Font("Baskerville Old Face", Font.BOLD, 30)); // Example font settings
        label.setForeground(Color.WHITE);
        label.setBounds(315, 100, 470, 50);
        //label.setFont();
        add(label);
    }

    private void addAppleButton() {
        ImageButton apple = new ImageButton("Images\\Apple.png");
        apple.setBounds(300, 300, 200, 200);
        apple.addActionListener(e -> {
            setVisible(false);
            Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(true);
            Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.startIndiansScene();
        });
        add(apple);
    }

    private void addAndroidButton() {
        ImageButton android = new ImageButton("Images\\Android.jpg");
        android.setBounds(600, 300, 200, 200);
        android.addActionListener(e -> {
            System.exit(0);
        });
        add(android);
    }
}
