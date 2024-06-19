package Glybovets;

import Data.Test;
import Enums.RuleOption;
import Enums.SpeakerType;
import Enums.Status;
import Pechkurova.MainCharacter;
import SceneObjects.*;
import SuperSwing.ImageBackground;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GlybovetsRoom extends ImageBackground implements ActionListener {
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private Decoration[] decorations;
    private MainCharacter mainCharacter;
    public Rule rule;
    Clip backgroundMusicClip;

    public GlybovetsRoom(String imagePath) {
        super(imagePath);
        Test.mainMenu.levelMenu.roomMenu.hall.stopBackgroundMusic();
        playBackgroundMusic("Audio\\Elevator.wav");
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 800, 50, 80, 80);
        initialiazeDecorationsList();
        add(mainCharacter);
        revalidate();
        repaint();
        addGlybovets();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        if (mainCharacter.canMoveForward(decorations).isAbleToMove()) {
                            mainCharacter.moveForward();
                        }
                    }
                    case KeyEvent.VK_S -> {
                        if (mainCharacter.canMoveBackward(decorations).isAbleToMove()) {
                            mainCharacter.moveBackward();
                        }
                    }
                    case KeyEvent.VK_D -> mainCharacter.turnRight();
                    case KeyEvent.VK_A -> mainCharacter.turnLeft();
                    case KeyEvent.VK_E -> {
                        mainCharacter.setEKeyPressed(true);
                        Door interaction = mainCharacter.touchTheDoor(decorations);
                        if (interaction != null && !interaction.isBlocked()) {
                            Test.mainMenu.levelMenu.roomMenu.hall.glybovetsFrame.setVisible(false);
                            stopBackgroundMusic();
                            Test.mainMenu.levelMenu.roomMenu.addRooms(new Status[]{Status.PECHKUROVA, Status.VOZNIUK, Status.GLYBOVETS});
                            Test.mainMenu.levelMenu.roomMenu.hallFrame.setVisible(true);
                            Test.mainMenu.levelMenu.roomMenu.hall.startBackgroundMusic();
                            Test.mainMenu.levelMenu.roomMenu.hall.changeObjectsForExitScene();
                        }
                        PortalDesk portalDesk = mainCharacter.touchPortalDesk(decorations);
                        if (portalDesk != null) {
                            stopBackgroundMusic();
                            rule = new Rule(0, HEIGHT - DialogWindow.HEIGHT-30, "Exam tasks seem to be hard. Try to use ChatGPT and get 100 points. But don`t get caught!", RuleOption.ChatGPT, 20);
                            add(rule);
                            rule.bringToFront();
                            revalidate();
                            repaint();
                        }
                    }
                }
            }
        });
    }

    private void initialiazeDecorationsList() {
        decorations = new Decoration[11];
        decorations[0] = new Desk(-30, -50, 45, 900, null);
        decorations[1] = new Desk(-50, -30, 1200, 47, null);
        decorations[2] = new Desk(-30, 747, 1000, 50, null);
        decorations[3] = new Desk(963, -30, 50, 900, null);
        //walls
        decorations[4] = new Desk(953, 66, 50, 318, "I hope, we won't write code on shits of paper...");
        //whiteboard;
        decorations[5] = new Door(748, 15, 142, 21, true);
        //door
        decorations[6] = new PortalDesk(510, 15, 133, 174, null, null);
        //comp desk
        decorations[7] = new Desk(197, 15, 133, 174, null);
        //upper desk
        decorations[8] = new Desk(160, 575, 130, 174, null);
        decorations[9] = new Desk(430, 575, 130, 174, null);
        //lower desks
        decorations[10] = new Desk(707, 540, 118, 220, "I believe, tickets were already given to us ");
        //professor desk
    }

    public void addGlybovetsCongratulations() {
        Hint hint = new Hint(0, HEIGHT - DialogWindow.HEIGHT-30, "Congratulations. You have zarah and free to leave... for now", SpeakerType.GLYBOVETS, 20);
        add(hint);
        revalidate();
        repaint();
        Door door = (Door) decorations[5];
        door.setBlocked(false);
    }

    private void addGlybovets() {
        ImageIcon glybovets = resizeImage("Images\\Glybovets.jpg", 90, 90);
        JLabel glybovetsLabel = new JLabel(glybovets);
        glybovetsLabel.setBounds(860, 610, 90, 90);
        add(glybovetsLabel);
        revalidate();
        repaint();
    }


    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }

    public void playBackgroundMusic(String filePath) {
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
