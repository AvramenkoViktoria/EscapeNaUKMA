package Vozniuk;

import Data.Test;
import Enums.Type;
import Pechkurova.MainCharacter;
import Pechkurova.Pechkurova;
import SceneObjects.Decoration;
import SceneObjects.Desk;
import SceneObjects.Door;
import SceneObjects.PortalDesk;
import SuperSwing.ImageBackground;
import SuperSwing.Warning;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.*;

public class VozniukRoom extends ImageBackground implements ActionListener {
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private Decoration[] decorations;
    private LinkedList<Indian> indians;
    private VozniukAccount vozniukAccount = new VozniukAccount();
    private Timer timer;
    private MainCharacter mainCharacter;
    private boolean collided;

    public VozniukRoom(String imagePath) {
        super(imagePath);
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
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
                            System.out.println("Going out");
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
                                    CiscoBinaryGame ciscoBinaryGame = new CiscoBinaryGame(vozniukAccount);
                            }
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
        decorations[5] = new Desk(-30, 508, 175, 196, null);
        //machine near whiteboard
        decorations[6] = new Door(172, 728, 150, 65, true);
        //door
        decorations[7] = new Desk(905, 574, 100, 200, null);
        //altar cisco
        decorations[8] = new PortalDesk(905, 100, 100, 380, null, Type.BLOCKED);
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

    private void addIndiansToScene() {
        for (Indian indian : indians) {
            indian.setBounds(indian.getX(), indian.getY(), Indian.SIZE, Indian.SIZE);
            add(indian);
        }
    }

    public void startIndiansScene() {
        addIndiansToScene();
        revalidate();
        repaint();
        timer = new Timer(10, this);
        timer.start();
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
            System.out.println("All Indians are removed!");
            timer.stop();
            PortalDesk portalDesk = (PortalDesk) decorations[8];
            portalDesk.setType(Type.SHAFA);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
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
}