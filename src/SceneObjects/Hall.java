package SceneObjects;

import Data.Test;
import Enums.SpeakerType;
import Pechkurova.MainCharacter;
import SuperSwing.ImageBackground;
import Vozniuk.VozniukRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hall extends ImageBackground {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private MainCharacter mainCharacter;
    public int thoughtCounter = 0;
    private Decoration[] decorations;
    public JFrame vozniukRoomFrame;
    public VozniukRoom vozniukRoom;

    public Hall(String imagePath) {
        super(imagePath);
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 100, 100, 90, 90);
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
                            System.out.println("Going out");
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
                        }
                    }
                }
                repaint();
            }
        });
    }

    private void initialiazeDecorationsList() {
        decorations = new Decoration[8];
        decorations[0] = new Desk(-30, -10, 47, 500, null);
        decorations[1] = new Desk(-10, -30, 820, 49, null);
        decorations[2] = new Desk(-10, 382, 820, 50, null);
        decorations[3] = new Desk(783, -10, 30, 500, null);
        //walls
        decorations[4] = new Desk(17, 133, 14, 140, "Oh, a door for escape from NaUKMA!");
        //exit
        decorations[5] = new Desk(160, 19, 134, 16, "Glibovets door? Coming soon...");
        //Glibovets room
        decorations[6] = new Desk(770, 126, 14, 140, "Oh, a door for escape from NaUKMA!");
        //Pechkurova's room
        decorations[7] = new Door(460, 19, 134, 16, false);
        //Vozniuk's room
    }

    private void addThought() {
        if (thoughtCounter == 0) {
            thoughtCounter++;
            Thought thought = new Thought(0, HEIGHT - DialogWindow.HEIGHT, "Hooh.. That was rough. Now i need to come in pan Andrii's office. But it's closed and i don't remember the code he told me..", SpeakerType.USER, 16);
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
}
