package Menu;

import Data.FileManager;
import Enums.Level;
import Enums.Status;
import SuperSwing.ImageBackground;
import SuperSwing.HoverButton;
import SuperSwing.Warning;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private Clip backgroundMusicClip;
    public LevelMenu levelMenu;

    public MainMenu() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        addBackground();
        playBackgroundMusic("Audio\\Menu.wav"); // Play background music
        setVisible(true);
    }

    private void addBackground() {
        ImageBackground background = new ImageBackground("Images\\NaUKMA.png");
        background.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        background.setLayout(null);
        addNewGameButton(background);
        addContinueButton(background);
        addExitButton(background);
        revalidate();
        repaint();
        add(background);
    }

    private void addNewGameButton(ImageBackground background) {
        HoverButton button = new HoverButton("New Game", new Color(171, 43, 44));
        button.setBounds(890, 25, 245, 50);
        Font buttonFont = new Font("Chiller", Font.PLAIN, 70);
        button.setFont(buttonFont);
        button.addActionListener(e -> {
            FileManager.user.setStatus(Status.GLYBOVETS);
            setVisible(false);
            levelMenu = new LevelMenu();
        });
        background.add(button);
    }

    private void addContinueButton(ImageBackground background) {
        HoverButton button = new HoverButton("Continue", new Color(171, 43, 44));
        button.setBounds(890, 95, 245, 50);
        Font buttonFont = new Font("Chiller", Font.PLAIN, 70);
        button.setFont(buttonFont);
        button.addActionListener(e -> {
            setVisible(false);
            if (!FileManager.user.getLevel().equals(Level.NONE)) {
                RoomMenu roomMenu = new RoomMenu();
            } else {
                Warning warning = new Warning("There is no progress to continue!", 270, this);
            }
        });
        background.add(button);
    }

    private void addExitButton(ImageBackground background) {
        HoverButton button = new HoverButton("Exit", new Color(171, 43, 44));
        button.setBounds(890, 165, 245, 50);
        Font buttonFont = new Font("Chiller", Font.PLAIN, 70);
        button.setFont(buttonFont);
        button.addActionListener(e -> {
            FileManager.saveRoomData();
            System.exit(0);
        });
        background.add(button);
    }

    // Method to play background music
    private void playBackgroundMusic(String filePath) {
        try {
            // Open the audio file as a stream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get the clip resource
            backgroundMusicClip = AudioSystem.getClip();

            // Open the clip and load the audio data from the audio input stream
            backgroundMusicClip.open(audioStream);

            // Loop the clip continuously
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    // Method to stop the background music
    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }

}
