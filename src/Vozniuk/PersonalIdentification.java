package Vozniuk;

import Data.Test;
import SuperSwing.ImageButton;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PersonalIdentification extends JFrame {
    private static final int WIDTH = 1100;
    private static final int HEIGHT = 800;
    Clip backgroundMusicClip;

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
            Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.stopBackgroundMusic();
            playBackgroundMusicForDuration("Audio\\fail.wav", 2000); // Play the sound
            Timer exitTimer = new Timer(2000, new ActionListener() { // Set a delay of 2000 milliseconds
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.exit(0); // Exit the program after the delay
                }
            });
            exitTimer.setRepeats(false); // Ensure the timer only runs once
            exitTimer.start(); // Start the timer
        });

        add(android);
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
    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }
}
