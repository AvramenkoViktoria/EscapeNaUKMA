package Glybovets;

import Data.FileManager;
import Data.Test;
import Enums.Level;
import SceneObjects.Hearts;
import SuperSwing.GameOver;
import SuperSwing.ImageBackground;
import SuperSwing.ImageRectButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ChatGPT extends ImageBackground implements ActionListener {
    private static final int WIDTH = 950;
    private static final int HEIGHT = 670;
    public boolean chat = true;
    private int points = 0;
    private final Timer timer;
    private ImageRectButton generateButton;
    private final Random random = new Random();
    private JLabel pointsLabel;
    private int stepCounter = 0;
    private Hearts hearts;
    private int speedLimit;
    Clip backgroundMusicClip;

    public ChatGPT(String imagePath) {
        super(imagePath);
        setLayout(null);
        playBackgroundMusic("Audio\\Subway.wav");
        setBounds(0, 0, WIDTH, HEIGHT);
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                speedLimit = 600;
                addHeartsPanel(new Hearts("Images\\Contract\\fullHearts.png", Level.CONTRACT, 60, HEIGHT-140, 130,30));
                break;
            case BUDGET:
                speedLimit = 500;
                addHeartsPanel(new Hearts("Images\\Budget\\fullHearts.png", Level.BUDGET, 80, HEIGHT-140, 100,30));
                break;
            case GRANT:
                speedLimit = 400;
                addHeartsPanel(new Hearts("Images\\Grant\\fullHearts.png", Level.GRANT, 100, HEIGHT-140, 70,40));
        }
        int delay = 20;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.room.moveGlybovets();
        stepCounter++;
        if (stepCounter == speedLimit) {
            stepCounter = 0;
            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.room.step++;
        }
        checkWin();
        checkVisibility();
    }

    private void checkVisibility() {
        int glybovetsY = Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.room.getGlybovetsLabel().getY();
        if (glybovetsY >= HEIGHT / 2 - 24 && chat) {
            lose();
        }
    }

    private void checkWin() {
        if (points == 20) {
            timer.stop();
            stopBackgroundMusic();
            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.setVisible(false);
            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsFrame.setVisible(true);
            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.playBackgroundMusic("Audio\\Elevator.wav");
            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.addGlybovetsCongratulations();
        }
    }

    private void lose() {
        Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.room.setInitialGlybovetsCoordinates();
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                switch (FileManager.user.getHeartsNum()) {
                    case 3:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Contract\\twoHearts.png", Level.CONTRACT, 60, HEIGHT-140, 130,30));
                        stepCounter = 0;
                        break;
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Contract\\oneHeart.png", Level.CONTRACT, 60, HEIGHT-140, 130,30));
                        stepCounter = 0;
                        break;
                    case 1:
                       handleLost();
                }
                break;
            case BUDGET:
                switch (FileManager.user.getHeartsNum()) {
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Budget\\oneHeart.png", Level.BUDGET, 80, HEIGHT-140, 100,30));
                        stepCounter = 0;
                        break;
                    case 1:
                     handleLost();
                }
                break;
            case GRANT:
                handleLost();
        }
    }

    private void addHeartsPanel(Hearts hearts) {
        if (this.hearts != null) {
            remove(this.hearts);
        }
        this.hearts = hearts;
        add(hearts);
        hearts.revalidate();
        hearts.repaint();
        revalidate();
        repaint();
    }

    private void handleLost() {
        stopBackgroundMusic();
        playBackgroundMusicForDuration("Audio\\fail.wav", 2000);
        timer.stop();
        Test.mainMenu.levelMenu.roomMenu.hall.glybovetsRoom.rule.quest.setVisible(false);
        GameOver gameOver = new GameOver();
    }
    private void playBackgroundMusicForDuration(String filePath, int durationInMillis) {
        try {
            // Open the audio file as a stream
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get the clip resource
            backgroundMusicClip = AudioSystem.getClip();

            // Open the clip and load the audio data from the audio input stream
            backgroundMusicClip.open(audioStream);

            // Start playing the clip
            backgroundMusicClip.start();

            // Close the audio stream after loading the clip to free resources
            audioStream.close();

            Timer musicTimer = new Timer(durationInMillis, e -> stopBackgroundMusic());
            musicTimer.setRepeats(false); // Ensure it only runs once
            musicTimer.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
            if (backgroundMusicClip != null) {
                backgroundMusicClip.close();
            }
        }
    }
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
