package Glybovets;

import Data.Test;
import Enums.SpeakerType;
import Pechkurova.MainCharacter;
import SceneObjects.*;
import SuperSwing.ImageBackground;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GlybovetsRoom  extends ImageBackground implements ActionListener {
    private final static int WIDTH = 800;
    private final static int HEIGHT = 1000;
    private Decoration[] decorations;
    private MainCharacter mainCharacter;
    public  GlybovetsRoom (String imagePath){
        super(imagePath);
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 150, 450, 80, 80);
        initialiazeDecorationsList();
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
                            System.out.println("Going out");
                            Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoomFrame.setVisible(false);
                            Test.mainMenu.levelMenu.roomMenu.hallFrame.setVisible(true);
                            Test.mainMenu.levelMenu.roomMenu.hall.getVozniukDoor().setBlocked(true);
                        }
                        if (interaction != null && interaction.isBlocked()) {
                            Thought thought = new Thought(getWidth() - DialogWindow.WIDTH, getHeight() - DialogWindow.HEIGHT, "The door is blocked. I need to find the code.", SpeakerType.USER, 20);
                            add(thought);
                            thought.bringToFront();
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
        decorations[4] = new Desk(953, 66, 50, 318, null);
        //whiteboard;
        decorations[5] = new PortalDesk( 748, 15, 142, 21,null,null);
        //door
        decorations[6] = new PortalDesk( 510, 15, 133, 174,null,null);
        //comp desk
        decorations[7] = new Desk( 197, 15, 133, 174,null);
        //upper desk
        decorations[8] = new Desk( 160, 575, 130, 174,null);
        decorations[9] = new Desk( 430, 575, 130, 174,null);
        //lower desks
        decorations[10] = new Desk( 707, 540, 118, 220,null);
        //professor desk
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
}
