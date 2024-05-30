import Enums.Level;
import SuperSwing.ImageBackground;
import SuperSwing.RoundedButton;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private static final int FRAME_WIDTH = 1300;
    private static final int FRAME_HEIGHT = 900;

    public MainMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addBackground();
        setVisible(true);
    }

    private void addBackground() {
        ImageBackground background = new ImageBackground("C:\\Users\\ACER\\IdeaProjects\\EscapeNaUKMA\\Images\\NaUKMA_background.jpg");
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addGameName(background);
        addNewGameButton(background);
        addContinueButton(background);
        addExitButton(background);
        revalidate();
        repaint();
        add(background);
    }

    private void addGameName(ImageBackground background) {
        JPanel nameBackground = new JPanel();
        nameBackground.setLayout(null);
        nameBackground.setBounds(30, 30, 400, 100);
        //nameBackground.setBackground();
        JLabel name = new JLabel("Escape NaUKMA");
        //name.setFont();
        //name.setForeground();
        name.setBounds(30, 20, 250, 80);
        nameBackground.add(name);
        nameBackground.revalidate();
        nameBackground.repaint();
        background.add(nameBackground);
    }

    private void addNewGameButton(ImageBackground background) {
        RoundedButton button = new RoundedButton("New Game");
        button.setBounds(30, 150, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> {
            setVisible(false);
            LevelMenu levelMenu = new LevelMenu();
        });
        background.add(button);
    }

    private void addContinueButton(ImageBackground background) {
        RoundedButton button = new RoundedButton("Continue");
        button.setBounds(30, 220, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> {
            setVisible(false);
            if (!FileManager.user.getLevel().equals(Level.NONE)) {
                RoomMenu roomMenu = new RoomMenu();
            } else {
                setVisible(false);
                Warning warning = new Warning("Warning text", this);
            }
        });
        background.add(button);
    }

    private void addExitButton(ImageBackground background) {
        RoundedButton button = new RoundedButton("Exit");
        button.setBounds(30, 300, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.BLACK);
        button.addActionListener(e -> {
            FileManager.saveRoomData();
            System.exit(0);
        });
        background.add(button);
    }
}
