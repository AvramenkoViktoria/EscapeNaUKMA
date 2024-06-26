package Vozniuk;

import Data.FileManager;
import Data.Test;
import Enums.*;
import Pechkurova.MainCharacter;
import SceneObjects.*;
import SuperSwing.GameOver;
import SuperSwing.ImageBackground;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.sound.sampled.*;
import javax.swing.*;

public class VozniukRoom extends ImageBackground implements ActionListener {
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private Decoration[] decorations;
    private LinkedList<Indian> indians;
    public VozniukAccount vozniukAccount = new VozniukAccount();
    private Timer indianTimer;
    private MainCharacter mainCharacter;
    private boolean collided;
    private Timer pickTimer;
    private Hearts hearts;
    private JLabel timeLabel;
    public CiscoBinaryGame ciscoBinaryGame;
    Clip  backgroundMusicClip;

    public VozniukRoom(String imagePath) {
        super(imagePath);
        Test.mainMenu.levelMenu.roomMenu.hall.stopBackgroundMusic();
        playBackgroundMusic("Audio\\Marty.wav");
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                addHeartsPanel(new Hearts("Images\\Contract\\fullHearts.png", Level.CONTRACT, 355, 660,130,30));
                break;
            case BUDGET:
                addHeartsPanel(new Hearts("Images\\Budget\\fullHearts.png", Level.BUDGET, 378, 660,100,30));
                break;
            case GRANT:
                addHeartsPanel(new Hearts("Images\\Grant\\fullHearts.png", Level.GRANT, 400, 660,70,40));
        }
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 150, 600, 80, 80);
        initialiazeDecorationsList();
        initializeIndiansList();
        add(mainCharacter);
        revalidate();
        repaint();

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
                            switch (FileManager.user.getLevel()) {
                                case CONTRACT -> FileManager.user.setHeartsNum(3);
                                case BUDGET -> FileManager.user.setHeartsNum(2);
                                case GRANT -> FileManager.user.setHeartsNum(1);
                            }
                            FileManager.user.setStatus(Status.GLYBOVETS);
                            stopBackgroundMusic();
                            Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(false);
                            //Test.mainMenu.levelMenu.roomMenu.hall.getVozniukDoor().setBlocked(true);
                            //Test.mainMenu.levelMenu.roomMenu.hall.changeObjectsForGlybovetsScene();
                            Test.mainMenu.levelMenu.roomMenu.setVisible(true);
                            Test.mainMenu.levelMenu.roomMenu.addRooms(new Status[]{Status.PECHKUROVA, Status.VOZNIUK, Status.CURRENT});
                        }
                        if (interaction != null && interaction.isBlocked()) {
                            Thought thought = new Thought(getWidth() - DialogWindow.WIDTH, getHeight() - DialogWindow.HEIGHT, "The door is blocked. I need to find the code.", SpeakerType.USER, 20);
                            add(thought);
                            thought.bringToFront();
                            revalidate();
                            repaint();
                        }

                        PortalDesk interactionP = mainCharacter.touchPortalDesk(decorations);
                        if (interactionP != null) {
                            switch (interactionP.getType()) {
                                case PC:
                                    Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(false);
                                    vozniukAccount.setVisible(true);
                                    break;
                                case SHAFA:
                                    Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(false);
                                    ciscoBinaryGame = new CiscoBinaryGame(vozniukAccount);
                            }
                        }

                        if (mainCharacter.canMoveForward(decorations).getMessage() != null) {
                            Thought thought = new Thought(0,HEIGHT - DialogWindow.HEIGHT, mainCharacter.canMoveForward(decorations).getMessage(), SpeakerType.USER, 20);
                            add(thought);
                            thought.bringToFront();
                            revalidate();
                            repaint();
                        }
                    }
                }
                repaint();
            }
        });
    }

    private void initialiazeDecorationsList() {
        decorations = new Decoration[23];
        decorations[0] = new Desk(-30, -50, 45, 900, null);
        decorations[1] = new Desk(-50, -30, 1200, 47, null);
        decorations[2] = new Desk(-30, 747, 1000, 50, null);
        decorations[3] = new Desk(970, -30, 50, 900, null);
        //walls
        decorations[4] = new Desk(-30, 452, 60, 265, null);
        //whiteboard
        decorations[5] = new Desk(-30, 508, 175, 196, "Hm, I hope it's not our next lab");
        //machine near whiteboard
        decorations[6] = new Door(172, 728, 150, 65, true);
        //door
        decorations[7] = new Desk(905, 574, 100, 200, "What? Cisco altar???");
        //altar cisco
        decorations[8] = new PortalDesk(905, 100, 100, 380, "Cisco shafa.. Wonder how i can open it", Type.BLOCKED);
        //shafa cisco
        decorations[9] = new PortalDesk(123, 37, 138, 357, null, Type.PC);
        //Vozniuk's desk
        decorations[10] = new Desk(94, 92, 100, 128, null);
        //Vozniuk's chair
        decorations[11] = new Desk(372, 127, 121, 133, null);
        decorations[12] = new Desk(372, 150, 139, 85, null);
        //desk1
        decorations[13] = new Desk(370, 369, 123, 136, null);
        decorations[14] = new Desk(372, 393, 139, 85, null);
        //desk2
        decorations[15] = new Desk(370, 609, 125, 140, null);
        decorations[16] = new Desk(372, 634, 138, 85, null);
        //desk3
        decorations[17] = new Desk(615, 15, 126, 138, null);
        decorations[18] = new Desk(615, 45, 141, 81, null);
        //desk4
        decorations[19] = new Desk(617, 260, 123, 136, null);
        decorations[20] = new Desk(617, 284, 140, 85, null);
        //desk5
        decorations[21] = new Desk(616, 503, 123, 136, null);
        decorations[22] = new Desk(616, 527, 140, 85, null);
        //desk6
    }

    private void initializeIndiansList() {
        indians = new LinkedList<>();
        Point[] points1 = new Point[4];
        points1[0] = new Point(260, 40);
        points1[1] = new Point(540, 40);
        points1[2] = new Point(540, 270);
        points1[3] = new Point(260, 270);
        indians.add(new Indian("Images\\Indian.png", 260, 40, points1));

        Point[] points2 = new Point[2];
        points2[0] = new Point(540, 30);
        points2[1] = new Point(540, 650);
        indians.add(new Indian("Images\\Indian.png", 540, 30, points2));

        Point[] points3 = new Point[3];
        points3[0] = new Point(550, 530);
        points3[1] = new Point(300, 530);
        points3[2] = new Point(300, 330);
        indians.add(new Indian("Images\\Indian.png", 550, 530, points3));

        Point[] points4 = new Point[3];
        points4[0] = new Point(550, 670);
        points4[1] = new Point(800, 670);
        points4[2] = new Point(800, 50);
        indians.add(new Indian("Images\\Indian.png", 550, 670, points4));

        Point[] points5 = new Point[4];
        points5[0] = new Point(550, 170);
        points5[1] = new Point(770, 170);
        points5[2] = new Point(770, 36);
        points5[3] = new Point(890, 36);
        indians.add(new Indian("Images\\Indian.png", 550, 170, points5));

        Point[] points6 = new Point[4];
        points6[0] = new Point(620, 420);
        points6[1] = new Point(790, 420);
        points6[2] = new Point(790, 500);
        points6[3] = new Point(900, 500);
        indians.add(new Indian("Images\\Indian.png", 620, 420, points6));

        Point[] points7 = new Point[2];
        points7[0] = new Point(300, 600);
        points7[1] = new Point(300, 30);
        indians.add(new Indian("Images\\Indian.png", 300, 600, points7));

        Point[] points8 = new Point[2];
        points8[0] = new Point(520, 650);
        points8[1] = new Point(820, 650);
        indians.add(new Indian("Images\\Indian.png", 520, 650, points8));

        Point[] points9 = new Point[3];
        points9[0] = new Point(172, 400);
        points9[1] = new Point(172, 646);
        points9[2] = new Point(300, 646);
        indians.add(new Indian("Images\\Indian.png", 172, 400, points9));
    }

    private void addHeartsPanel(Hearts hearts) {
        if (this.hearts != null) {
            remove(this.hearts);
        }
        System.out.println(FileManager.user.getHeartsNum());
        System.out.println(hearts.getPath());
        this.hearts = hearts;
        add(hearts);
        hearts.revalidate();
        hearts.repaint();
        revalidate();
        repaint();
    }

    private void addIndiansToScene() {
        for (Indian indian : indians) {
            indian.setBounds(indian.getX(), indian.getY(), Indian.SIZE, Indian.SIZE);
            add(indian);
        }
    }

    public void startIndiansScene() {
        stopBackgroundMusic();
        addIndiansToScene();
        revalidate();
        repaint();
        PortalDesk portalDesk = (PortalDesk) decorations[8];
        portalDesk.setMessage(null);
        indianTimer = new Timer(10, this);
        addTimer();
        Rule rule = new Rule(0,getHeight() - DialogWindow.HEIGHT, "Oh no! You escaped Vozniuk`s indians. Pick them up until someone hears you and change the network password in cisco shafa later!", RuleOption.INDIANS, 15);
        add(rule);
        rule.bringToFront();
        revalidate();
        repaint();
    }

    public void startTimer() {
        indianTimer.start();
        pickTimer.start();
        playBackgroundMusic("Audio\\India.wav");
    }


    private boolean collide(MainCharacter mainCharacter, Indian indian) {
        return mainCharacter.getBounds().intersects(indian.getBounds());
    }

    private void handleCollision(MainCharacter mainCharacter) {
        boolean collisionDetected = false;
        Iterator<Indian> iterator = indians.iterator();
        while (iterator.hasNext()) {
            Indian indian = iterator.next();
            if (collide(mainCharacter, indian)) {
                collisionDetected = true;
                iterator.remove();
                remove(indian);
            }
        }
        if (collisionDetected) {
            collided = true;
        }
        if (indians.isEmpty()) {
            stopBackgroundMusic();
            System.out.println("All Indians are removed!");
            indianTimer.stop();
            PortalDesk portalDesk = (PortalDesk) decorations[8];
            portalDesk.setType(Type.SHAFA);
            vozniukAccount.removeChangePasswordButton();
            pickTimer.stop();
            remove(timeLabel);
            playBackgroundMusic("Audio\\Marty.wav");
        }
    }

    public void unlockTheDoor() {
        Door door = (Door) decorations[6];
        door.setBlocked(false);
    }
    public void removeHearts() {
        if (hearts != null)
            remove(hearts);
    }

    private int timeRemaining;

    private void addTimer() {
        timeLabel = new JLabel();
        timeLabel.setBounds(18, 8, 200, 50);
        timeLabel.setFont(timeLabel.getFont().deriveFont(37.0f));
        timeRemaining = 30;
        pickTimer = new Timer(1000, e -> {
            if (timeRemaining > 0) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            } else if (!lost()) {
                timeRemaining = 30;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timeLabel.setText(String.format("%02d:%02d", minutes, seconds));
            } else {
                pickTimer.stop();
                stopBackgroundMusic();
                Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(false);
                Test.mainMenu.levelMenu.roomMenu.hall.thoughtCounter = 0;
                playBackgroundMusicForDuration("Audio\\fail.wav", 2000);
                GameOver gameOver = new GameOver();
            }
        });

        add(timeLabel);
        revalidate();
        repaint();
        pickTimer.setInitialDelay(0);
    }

    private boolean lost() {
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                switch (FileManager.user.getHeartsNum()) {
                    case 3:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Contract\\twoHearts.png", Level.CONTRACT, 355, 660,130,30));
                        break;
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Contract\\oneHeart.png", Level.CONTRACT, 355, 660,130,30));
                        break;
                    case 1:
                        return true;
                }
                break;
            case BUDGET:
                switch (FileManager.user.getHeartsNum()) {
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Budget\\oneHeart.png", Level.BUDGET, 378, 665,100,30));
                        break;
                    case 1:
                        return true;
                }
                break;
            case GRANT:
                return true;
        }
        return false;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
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
    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Indian> iterator = indians.iterator();
        while (iterator.hasNext()) {
            Indian indian = iterator.next();
            indian.move();
            if (collide(mainCharacter, indian)) {
                handleCollision(mainCharacter);
                break;
            }
            revalidate();
            repaint();
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