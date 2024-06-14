package SceneObjects;

import Pechkurova.MainCharacter;
import SuperSwing.ImageBackground;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hall extends ImageBackground {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private MainCharacter mainCharacter;
    private Decoration[] decorations;

    public Hall(String imagePath) {
        super(imagePath);
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 100, 100, 90, 90);
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
        decorations[1]= new Desk(-10, -30, 820, 49, null);
        decorations[2] = new Desk(-10, 382, 820, 50, null);
        decorations[3] = new Desk(783, -10, 30, 500, null);
        //walls
        decorations[4] = new Desk(17, 133, 14, 140, "Oh, a door for escape from NaUKMA!");
        //exit
        decorations[5] = new Desk(160, 19, 134,16, "Glibovets door? Coming soon...");
        //Glibovets room
        decorations[6] = new Desk(770, 126, 14, 140, "Oh, a door for escape from NaUKMA!");
        //Pechkurova's room
        decorations[7] = new PortalDesk(460, 19, 134,16, "Oh, Cisco room..");
        //Vozniuk's room

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
