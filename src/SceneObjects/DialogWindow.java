package SceneObjects;

import Enums.SpeakerType;
import SuperSwing.RoundedButton;
import SuperSwing.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogWindow extends RoundedPanel {
    private SpeakerType type;
    private int x;
    private int y;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 150;
    private JTextArea textArea;
    private String textToType;
    private int currentIndex;
    private final String friendImagePath = "Images/friend.png";
    private final String userImagePath = "Images/MainCharDown.png";
    private final String pechkurovaImagePath = "Images/PechkurovaMess.png";
    public RoundedButton OKButton;
    private Timer typingTimer;
    public Timer disappearanceTimer;

    public DialogWindow(int x, int y, String textToType, SpeakerType type) {
        super(20); // Set the corner radius to 20
        this.x = x;
        this.y = y;
        this.type = type;
        this.textToType = (textToType != null) ? textToType : "Let's continue our journey!";
        this.currentIndex = 0;

        setLayout(null); // Using null layout to manually place components
        setBounds(x, y, WIDTH, HEIGHT);


        addTextArea();
        add(textArea);


        addImageLabel();

        addOKButton();


        typingTimer = new Timer(50, new ActionListener() { // Faster typing
            @Override
            public void actionPerformed(ActionEvent e) {
                typeText(e);
            }
        });
        typingTimer.start();

        setVisible(true);
    }

    public void addOKButton() {
        if (type == SpeakerType.FRIEND) {
            OKButton = new RoundedButton("OK");
            int buttonWidth = 60;
            int buttonHeight = 30;
            int buttonX = textArea.getX() + (textArea.getWidth() - buttonWidth) / 2;
            int buttonY = textArea.getY() + textArea.getHeight(); // 10 pixels below the text area
            OKButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
            add(OKButton);
        }
    }

    private void addTextArea() {
        textArea = new JTextArea();
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        textArea.setFont(font);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(new Color(238, 238, 238)); // Set background color
        textArea.setForeground(Color.BLACK); // Set text color

        int textX = 160;
        int textY = 10;
        int textWidth = WIDTH - 170; // Adjusted width for better placement
        int textHeight = HEIGHT - 65; // Adjusted height for better placement

        textArea.setBounds(textX, textY, textWidth, textHeight);
    }

    private void addImageLabel() {
        JLabel imageLabel = new JLabel();
        ImageIcon icon;
        int imageX = 0;
        int imageY = 0;
        int imageWidth;
        int imageHeight;

        switch (type) {
            case FRIEND:
                icon = new ImageIcon(friendImagePath);
                imageWidth = 150;
                imageHeight = 150;
                break;
            case USER:
                icon = new ImageIcon(userImagePath);
                imageWidth = 150;
                imageHeight = 150;
                break;
            case PECHKUROVA:
                icon = new ImageIcon(pechkurovaImagePath);
                imageWidth = 140;
                imageHeight = 140;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        Image image = icon.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setBounds(imageX, imageY, imageWidth, imageHeight); // Adjust bounds for better placement
        add(imageLabel);
    }

    private void typeText(ActionEvent e) {
        if (currentIndex < textToType.length()) {
            textArea.append(String.valueOf(textToType.charAt(currentIndex)));
            currentIndex++;
        } else {
            typingTimer.stop();
            startDisappearanceTimer();
        }
    }

    public void startDisappearanceTimer() {
        disappearanceTimer = new Timer(10000, new ActionListener() { // Window disappears after 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(DialogWindow.this);
                    parent.repaint();
                    parent.revalidate();
                }
                disappearanceTimer.stop();
            }
        });
        disappearanceTimer.setRepeats(false);
        disappearanceTimer.start();
    }

    @Override
    public void setVisible(boolean isVisible) {
        super.setVisible(isVisible);
        if (isVisible) {
            bringToFront();
        }
    }

    private void bringToFront() {
        Container parent = getParent();
        if (parent != null) {
            parent.setComponentZOrder(this, 0); // Bring this DialogWindow to the front
            parent.repaint();
            parent.revalidate();
        }
    }
}
