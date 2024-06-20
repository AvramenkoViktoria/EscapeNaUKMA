package SceneObjects;

import Data.Test;
import Enums.SpeakerType;
import Glybovets.GlybovetsRoom;
import Pechkurova.InteractiveObject;
import Pechkurova.MainCharacter;
import SuperSwing.ImageBackground;
import SuperSwing.Win;
import Vozniuk.VozniukRoom;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Hall extends ImageBackground {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private MainCharacter mainCharacter;
    public int thoughtCounter = 1;
    private Decoration[] decorations;
    public JFrame vozniukRoomFrame;
    public VozniukRoom vozniukRoom;
    private Clip backgroundMusicClip;
    private Thought thought;
    private boolean glybovetsScene = false;
    private boolean exitScene = false;
    public JFrame glybovetsFrame;
    public GlybovetsRoom glybovetsRoom;

    public Hall(String imagePath) {
        super(imagePath);
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        if (!glybovetsScene) {
            mainCharacter = new MainCharacter("Images\\MainCharUp.png", 680, 150, 90, 90);
        } else if (!exitScene) {
            mainCharacter = new MainCharacter("Images\\MainCharUp.png", 100, 100, 90, 90);
        } else {
            mainCharacter = new MainCharacter("Images\\MainCharUp.png", 100, 100, 90, 90);
        }
        initialiazeDecorationsList();
        add(mainCharacter);
        revalidate();
        repaint();
        addThought();


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
                            if (!glybovetsScene) {
                                stopBackgroundMusic();
                                Test.mainMenu.levelMenu.roomMenu.hallFrame.setVisible(false);
                                vozniukRoomFrame = new JFrame();
                                vozniukRoomFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                vozniukRoomFrame.setResizable(false);
                                vozniukRoomFrame.setSize(1000, 800);
                                vozniukRoomFrame.setLocationRelativeTo(null);
                                vozniukRoom = new VozniukRoom("Images\\Vozniuk.png");
                                vozniukRoom.setBounds(0, 0, 1000, 800);
                                vozniukRoomFrame.add(vozniukRoom);
                                vozniukRoomFrame.setVisible(true);
                            } else if (!exitScene) {
                                stopBackgroundMusic();
                                Test.mainMenu.levelMenu.roomMenu.hallFrame.setVisible(false);
                                glybovetsFrame = new JFrame();
                                glybovetsFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                glybovetsFrame.setResizable(false);
                                glybovetsFrame.setSize(1000, 800);
                                glybovetsFrame.setLocationRelativeTo(null);
                                glybovetsRoom = new GlybovetsRoom("Images\\noComp.png");
                                glybovetsFrame.add(glybovetsRoom);
                                glybovetsFrame.setVisible(true);
                            } else {
                                stopBackgroundMusic();
                                Test.mainMenu.levelMenu.roomMenu.hallFrame.setVisible(false);
                                Win win = new Win();
                            }
                        }
                        InteractiveObject object = mainCharacter.canMoveForward(decorations);
                        if (object.getMessage() != null && !object.getSpeakerType().equals(SpeakerType.FRIEND)) {
                            if (thought != null) {
                                remove(thought);
                            }
                            thought = new Thought(0, HEIGHT - DialogWindow.HEIGHT, object.getMessage(), SpeakerType.USER, 20);
                            add(thought);
                            revalidate();
                            repaint();
                        }
                    }
                }
                repaint();
            }
        });
    }

    public void startBackgroundMusic() {
        System.out.println("Starting background music."); // Debug statement
        playBackgroundMusic("Audio\\Bob.wav");
    }

    private void initialiazeDecorationsList() {
        decorations = new Decoration[8];
        decorations[0] = new Desk(-30, -10, 47, 500, null);
        decorations[1] = new Desk(-10, -30, 820, 49, null);
        decorations[2] = new Desk(-10, 382, 820, 50, null);
        decorations[3] = new Desk(783, -10, 30, 500, null);
        //walls
        decorations[4] = new Desk(17, 133, 14, 140, "The exit. Will pass through this door again.. i hope.");
        //exit
        decorations[5] = new Desk(160, 19, 134, 16, "I need to get there but can't remember the code");
        //Glibovets room
        decorations[6] = new Desk(770, 126, 14, 140, "I'm to scared to go back there");
        //Pechkurova's room
        decorations[7] = new Door(460, 19, 134, 16, false);
        //Vozniuk's room
    }

    private void addThought() {
        if (!glybovetsScene && !exitScene)
            thoughtCounter = 0;
        if (thoughtCounter == 0) {
            thoughtCounter++;
            thought = new Thought(0, HEIGHT - DialogWindow.HEIGHT, "Hooh.. That was rough. Now i need to come in pan Andrii's office. But it's closed and i don't remember the code he told me..", SpeakerType.USER, 16);
            add(thought);
            thought.bringToFront();
            revalidate();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }

    public Door getVozniukDoor() {
        return (Door) decorations[7];
    }

    public void changeObjectsForVozniukScene() {
        System.out.println("changeObjectsForVozniukScene");
        glybovetsScene = false;
        exitScene = false;
        decorations[4] = new Desk(17, 133, 14, 140, "The exit. Will pass through this door again.. i hope.");
        decorations[5] = new Desk(160, 19, 134, 16, "I need to get there but can't remember the code");
        getVozniukDoor().setBlocked(false);
    }


    public void changeObjectsForGlybovetsScene() {
        System.out.println("changeObjectsForGlybovetsScene");
        decorations[5] = new Door(160, 19, 134, 16, false);
        glybovetsScene = true;
    }

    public void changeObjectsForExitScene() {
        Door door = (Door) decorations[5];
        door.setBlocked(true);
        decorations[4] = new Door(17, 133, 14, 140, false);
        exitScene = true;
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

    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null) {
            backgroundMusicClip.stop();
            backgroundMusicClip.close();
        }
    }


}
