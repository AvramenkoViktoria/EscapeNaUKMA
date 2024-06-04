package SceneObjects;

import Enums.SpeakerType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DialogWindow extends JFrame {
    private SpeakerType type;
    private int x;
    private int y;
    private static final int WIDTH = 450; // Adjusted for better space
    private static final int HEIGHT = 188; // Adjusted for better space
    private JPanel panel;
    private JTextArea textArea;
    private String textToType;
    private int currentIndex;
    private final String friendImagePath = "Images/friend.png"; // Ensure this path is correct
    private final String userImagePath = "Images/MainCharDown.png"; // Ensure this path is correct
    private final String pechkurovaImagePath = "Images/PechkurovaMess.png"; // Ensure this path is correct

    public DialogWindow(int x, int y, String textToType, SpeakerType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        if (textToType!=null) {
            this.textToType = textToType;
        }
        else{
            this.textToType = "Let's continue our journey!";
        }
        currentIndex = 0;

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setBounds(x, y, WIDTH, HEIGHT);

        panel = new JPanel();
        panel.setLayout(null); // Using null layout to manually place components
        panel.setBounds(0, 0, WIDTH, HEIGHT);
        add(panel);

        // Set up the text area
        addTextArea();
        panel.add(textArea);

        // Add image label
        addImageLabel();

        // Set up a timer to simulate typing
        Timer timer = new Timer(50, new ActionListener() { // Faster typing
            @Override
            public void actionPerformed(ActionEvent e) {
                typeText(e);
            }
        });
        timer.start();

        setVisible(true);
    }

    private void addTextArea() {
        textArea = new JTextArea();
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        int x = 150;
        int y = 5;
        switch (type) {
            case FRIEND:
                break;
            case USER:
                break;
            case PECHKUROVA:
                break;
            default:
        }
        textArea.setBackground(new Color(238, 238, 238)); // Set background color to black
        textArea.setForeground(Color.BLACK); // Set text color to green
        textArea.setBounds(x, y, WIDTH - 160, HEIGHT - 20); // Adjust bounds for better placement
    }

    private void addImageLabel() {
        JLabel imageLabel = new JLabel();
        ImageIcon icon;
        int x = 0;
        int y = 0;
        int width;
        int height;

        switch (type) {
            case FRIEND:
                icon = new ImageIcon(friendImagePath);
                x = -55;
                y = 5;
                width = 200;
                height = 150;
                break;
            case USER:
                icon = new ImageIcon(userImagePath);
                y = 0;
                width = 150;
                height = 150;
                break;
            case PECHKUROVA:
                icon = new ImageIcon(pechkurovaImagePath);
                width = 140;
                height = 140;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setBounds(x, y, width, height); // Adjust bounds for better placement
        panel.add(imageLabel);
    }

    private void typeText(ActionEvent e) {
        if (currentIndex < textToType.length()) {
            textArea.append(String.valueOf(textToType.charAt(currentIndex)));
            currentIndex++;
        } else {
            ((Timer) e.getSource()).stop();
        }
    }
}

