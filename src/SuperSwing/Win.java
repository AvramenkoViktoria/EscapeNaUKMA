package SuperSwing;

import Data.FileManager;
import Data.Test;
import Menu.RoomMenu;
import Enums.Status;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Win extends JFrame {
    Clip backgroundMusicClip ;

    public Win() {
        //Test.mainMenu.levelMenu.roomMenu.hall.stopBackgroundMusic();
        setSize(700, 400);  // Example size, adjust as needed
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        playBackgroundMusicForDuration("Audio\\Win.wav", 2000);
        ImagePanel panel = new ImagePanel();
        add(panel);
        setVisible(true);
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

    class ImagePanel extends JPanel {
        private Image backgroundImage;
        private HoverButton buttonRoom;
        private HoverButton buttonMain;

        public ImagePanel() {
            backgroundImage = new ImageIcon("Images\\Win.png").getImage();
            setLayout(null);
            buttonRoom = new HoverButton("ROOMS", Color.BLACK);
            buttonRoom.setBounds(225, 110, 250, 60);
            Font buttonFont = new Font("Baskerville Old Face", Font.BOLD, 50);
            buttonRoom.setFont(buttonFont);
            buttonRoom.addActionListener(e -> {
                switch (FileManager.user.getLevel()) {
                    case CONTRACT -> FileManager.user.setHeartsNum(3);
                    case BUDGET -> FileManager.user.setHeartsNum(2);
                    case GRANT -> FileManager.user.setHeartsNum(1);
                }
                Test.mainMenu.levelMenu.roomMenu = new RoomMenu();
                Test.mainMenu.levelMenu.roomMenu. addRooms(new Status[]{Status.PECHKUROVA, Status.VOZNIUK, Status.GLYBOVETS});
                Test.mainMenu.levelMenu.roomMenu.setVisible(true);
                this.setVisible(false);
            });
            buttonMain = new HoverButton("MAIN", Color.BLACK);
            buttonMain.setBounds(240, 190, 200, 60);
            buttonMain.setFont(buttonFont);
            buttonMain.addActionListener(e -> {
                Test.mainMenu.setVisible(true);
            });
            add(buttonRoom);
            add(buttonMain);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

    }

}
