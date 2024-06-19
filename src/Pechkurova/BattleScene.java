package Pechkurova;

import Data.FileManager;
import Data.Test;
import Enums.Level;
import Enums.RuleOption;
import Enums.Status;
import SceneObjects.*;
import SuperSwing.GameOver;
import SuperSwing.ImageBackground;
import SuperSwing.Warning;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class BattleScene extends ImageBackground implements ActionListener {
    private final Timer timer;
    private final LinkedList<Pechkurova> pechkurovas = new LinkedList<>();
    private final Decoration[] decorations = new Decoration[13];
    private int collisionCounter = 0;
    private final Random random = new Random();
    private final MainCharacter mainCharacter;
    private int mitosisNumber = 0;
    private boolean collided;
    private Hearts hearts;
    private Clip backgroundMusicClip;

    public BattleScene(String imagePath) {
        super(imagePath);
        setLayout(null);
        int delay = 0;
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                delay = 20;
                mitosisNumber = 3;
                addHeartsPanel(new Hearts("Images\\Contract\\fullHearts.png", Level.CONTRACT, 200, 600, 130,30));
                break;
            case BUDGET:
                delay = 16;
                mitosisNumber = 2;
                addHeartsPanel(new Hearts("Images\\Budget\\fullHearts.png", Level.BUDGET, 220, 600,100,30));
                break;
            case GRANT:
                delay = 16;
                mitosisNumber = 1;
                addHeartsPanel(new Hearts("Images\\Grant\\fullHearts.png", Level.GRANT, 243, 600,70,40));
        }
        timer = new Timer(delay, this);
        Pechkurova pechkurova = new Pechkurova("Images\\Pechkurova.jpg", 960, 20);
        pechkurovas.add(pechkurova);
        pechkurova.setxVelocity(3);
        pechkurova.setyVelocity(3);

        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 80, 530, 80, 80);

        decorations[0] = new Desk(-30, -30, 40, 885, null);
        decorations[1] = new Desk(-30, -30, 1300, 40, null);
        decorations[2] = new Desk(-30, 845, 1300, 50, null);
        decorations[3] = new Desk(1191, -30, 100, 1015, null);
        //Walls
        decorations[4] = new Door(870, 10, 203, 12, true);
        //Door
        decorations[5] = new Desk(160, 103, 202, 260, "Litachok have seen his better days... Now next students will have to deal with him");
        decorations[6] = new PortalDesk(160, 490, 202, 260, "Be sure to check everything, because the moment you seat next task will start! Press OK to start", null); //Needs to be PortalDesk
        decorations[7] = new Desk(530, 103, 202, 260, "Are they going to create an altar for Karel? I hope it will be a pyramid");
        decorations[8] = new Desk(530, 490, 202, 260, "Java book? One day I will finally sit and learn how to code... But not today of course, not today");
        //Desks
        decorations[9] = new Desk(880, 388, 152, 363, "Hmm, I see a big cup of coffee. But pani Olena just went out for one...");
        //Pechkurova's table
        decorations[10] = new Desk(1140, 50, 70, 301, "I am surprised that flowers are still alive in ecosystem of FI..");
        //Cabinet
        decorations[11] = new Desk(1073, 10, 200, 40, null);
        //Additional decoration
        decorations[12] = new Desk(1035, 520, 20, 90, "I don't think that it's a place for me...");
        //Pechkurova's chair


        add(pechkurova);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePress(e);
            }
        });
        add(mainCharacter);
        setFocusable(true);
    }

    public void addRuleWindow() {
        DialogWindow window = new Rule(0, 853 - DialogWindow.HEIGHT, "On no! You failed. Now try to run away!", RuleOption.PECHKUROVA, 20);
        add(window);
        window.bringToFront();
        revalidate();
        repaint();
    }

    public void addKeyListeners() {
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
                        InteractiveObject interaction = mainCharacter.canMoveForward(decorations);
                        if (interaction.IsDoor()) {
                            timer.stop();
                            IDE.battleFrame.setVisible(false);
                            FileManager.user.setStatus(Status.VOZNIUK);
                            switch (FileManager.user.getLevel()) {
                                case CONTRACT -> FileManager.user.setHeartsNum(3);
                                case BUDGET -> FileManager.user.setHeartsNum(2);
                                case GRANT -> FileManager.user.setHeartsNum(1);
                            }
                            stopBackgroundMusic();
                            Test.mainMenu.levelMenu.roomMenu.setVisible(true);
                            Test.mainMenu.levelMenu.roomMenu.addRooms(new Status[]{Status.PECHKUROVA, Status.CURRENT, Status.BLOCKED});
                        }
                    }
                }
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_E) {
                    mainCharacter.setEKeyPressed(false);
                }
            }
        });
    }

    public void startTimer() {
        timer.start();
    }

    private void handleMousePress(MouseEvent e) {
        if (pechkurovas.size() <= 1) {
            return;
        }

        Iterator<Pechkurova> iterator = pechkurovas.iterator();
        while (iterator.hasNext()) {
            Pechkurova pechkurova = iterator.next();
            if (pechkurova.getBounds().contains(e.getPoint())) {
                iterator.remove();
                remove(pechkurova);
                repaint();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        LinkedList<Pechkurova> newPechkurovas = new LinkedList<>();
        boolean firstPechkurovaCollided = false;

        for (Pechkurova pechkurova : pechkurovas) {
            pechkurova.move(decorations);

            if (pechkurovas.indexOf(pechkurova) == 0 && pechkurova.collides) {
                firstPechkurovaCollided = true;
                collisionCounter++;
                pechkurova.collides = false;
            }

            pechkurova.setBounds(pechkurova.getXForMove(), pechkurova.getYForMove(), Pechkurova.SIZE, Pechkurova.SIZE);
        }

        if (firstPechkurovaCollided && collisionCounter >= mitosisNumber) {
            collisionCounter = 0;
            Pechkurova firstPechkurova = pechkurovas.getFirst();
            Pechkurova newPechkurova = new Pechkurova("Images\\Pechkurova.jpg", firstPechkurova.getXForMove(), firstPechkurova.getYForMove());
            newPechkurova.setxVelocity(random.nextInt(4) + 1);
            newPechkurova.setyVelocity(random.nextInt(4) + 1);
            newPechkurovas.add(newPechkurova);
            add(newPechkurova);
        }

        pechkurovas.addAll(newPechkurovas);

        Iterator<Pechkurova> iterator = pechkurovas.iterator();
        while (iterator.hasNext()) {
            Pechkurova pechkurova = iterator.next();
            if (collide(mainCharacter, pechkurova)) {
                handleCollision(mainCharacter);
                break;
            }
        }
        repaint();
    }

    private boolean collide(MainCharacter mainCharacter, Pechkurova pechkurova) {
        return mainCharacter.getBounds().intersects(pechkurova.getBounds());
    }

    private void handleCollision(MainCharacter mainCharacter) {
        if (!collided) {
            if (lost()) {
                remove(mainCharacter);
                mainCharacter.setBounds(-500, -500, 10, 10);
                stopBackgroundMusic();
                //Warning fail = new Warning("YOU LOST!", 200, IDE.battleFrame);
                IDE.battleFrame.setVisible(false);
                playBackgroundMusicForDuration("Audio\\fail.wav", 2000);
                GameOver gameOver = new GameOver();
            } else {
                Iterator<Pechkurova> iterator = pechkurovas.iterator();
                while (iterator.hasNext()) {
                    Pechkurova pechkurova = iterator.next();
                    if (pechkurovas.indexOf(pechkurova) != 0) {
                        iterator.remove();
                        remove(pechkurova);
                    }
                }
                mainCharacter.setBounds(40, 650, 90, 90);
                mainCharacter.setX(40);
                mainCharacter.setY(650);
                collided = false;
            }
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

    private boolean lost() {
        collided = true;
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                switch (FileManager.user.getHeartsNum()) {
                    case 3:
                        FileManager.user.setHeartsNum(2);
                        addHeartsPanel(new Hearts("Images\\Contract\\twoHearts.png", Level.CONTRACT, 200, 600,130,30));
                        break;
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Contract\\oneHeart.png", Level.CONTRACT, 200, 600,130,30));
                        break;
                    case 1:
                        return true;
                }
                break;
            case BUDGET:
                switch (FileManager.user.getHeartsNum()) {
                    case 2:
                        FileManager.user.setHeartsNum(1);
                        addHeartsPanel(new Hearts("Images\\Budget\\oneHeart.png", Level.BUDGET, 200, 600,100,30));
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