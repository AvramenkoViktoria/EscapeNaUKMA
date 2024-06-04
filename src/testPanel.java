import Enums.SpeakerType;
import Pechkurova.Pechkurova;
import SceneObjects.*;
import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class testPanel extends ImageBackground implements ActionListener {
    private Timer timer;
    private LinkedList<Pechkurova> pechkurovas = new LinkedList<>();
    private Decoration[] decorations = new Decoration[13];
    private int collisionCounter = 0;
    private final Random random = new Random();
    private MainCharacter mainCharacter;

    public testPanel(String imagePath) {
        super(imagePath);
        setLayout(null);
        timer = new Timer(18, this);
        Pechkurova pechkurova = new Pechkurova("Images\\Pechkurova.jpg", 1100, 100);
        pechkurovas.add(pechkurova);
        pechkurova.setxVelocity(3);
        pechkurova.setyVelocity(3);

        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 40, 750, 100, 100);

        decorations[0] = new Desk(-15, -15, 25, 1015, null);
        decorations[1] = new Desk(-15, -15, 1315, 25, null);
        decorations[2] = new Desk(-15, 990, 1315, 25, null);
        decorations[3] = new Desk(1290, -15, 25, 1015, null);
        //Walls
        decorations[4] = new Door(945, 10, 213, 12);
        //Door
        decorations[5] = new Desk(170, 122, 221, 301, "Litachok have seen his better days...");
        decorations[6] = new PortalDesk(170, 577, 221, 303, "Be sure to check everything, because the moment you seat next task will start! Press E to seat."); //Needs to be PortalDesk
        decorations[7] = new Desk(575, 123, 216, 300, "Are they going to create an altar for Karel?");
        decorations[8] = new Desk(575, 577, 218, 301, "Java book? One day I will finally sit and learn how to code...");
        //Desks
        decorations[9] = new Desk(954, 456, 160, 423, "Hmm, I see a big cup of coffee. But pani Olena just went out for one...");
        //Pechkurova's table
        decorations[10] = new Desk(1228, 57, 70, 354, "I am surprised that flowers are still alive in ecosystem of FI..");
        //Cabinet
        decorations[11] = new Desk(1158, 10, 62, 48, null);
        //Additional decoration
        decorations[12] = new Desk(1118, 612, 20, 100, "I don't think that it's a place for me...");
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
                            System.out.println("Going out");
                        } else if (interaction.getMessage() != null) {
                            addDialogWindow(interaction);
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


        timer.start();
    }

    private void addDialogWindow(InteractiveObject interaction) {
        int x = tempTest.testPanel.getX() + tempTest.testPanel.getWidth() - DialogWindow.WIDTH;
        int y = tempTest.testPanel.getY() + tempTest.testPanel.getHeight() - DialogWindow.HEIGHT;
        DialogWindow dialogWindow;
        if (interaction.getSpeakerType().equals(SpeakerType.FRIEND)) {
            dialogWindow = new DialogWindow(x, y, interaction.getMessage(), SpeakerType.FRIEND);
        } else {
            dialogWindow = new DialogWindow(x, y, interaction.getMessage(), SpeakerType.USER);
        }
        add(dialogWindow);
    }


    private void handleMousePress(MouseEvent e) {
        if (pechkurovas.size() <= 1) {
            return;
        }

        for (int i = 1; i < pechkurovas.size(); i++) {
            Pechkurova pechkurova = pechkurovas.get(i);
            if (pechkurova.getBounds().contains(e.getPoint())) {
                pechkurovas.remove(i);
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

        for (int i = 0; i < pechkurovas.size(); i++) {
            Pechkurova pechkurova = pechkurovas.get(i);
            pechkurova.move(decorations);


            if (i == 0 && pechkurova.collides) {
                firstPechkurovaCollided = true;
                collisionCounter++;
                pechkurova.collides = false;
            }

            pechkurova.setBounds(pechkurova.getXForMove(), pechkurova.getYForMove(), Pechkurova.SIZE, Pechkurova.SIZE);
        }

        if (firstPechkurovaCollided && collisionCounter >= 3) {
            collisionCounter = 0;
            Pechkurova firstPechkurova = pechkurovas.get(0);
            Pechkurova newPechkurova = new Pechkurova("Images\\Pechkurova.jpg", firstPechkurova.getXForMove(), firstPechkurova.getYForMove());
            newPechkurova.setxVelocity(random.nextInt(4) + 1);
            newPechkurova.setyVelocity(random.nextInt(4) + 1);
            newPechkurovas.add(newPechkurova);
            add(newPechkurova);
        }

        pechkurovas.addAll(newPechkurovas);

        for (Pechkurova pechkurova : pechkurovas) {
            if (collide(mainCharacter, pechkurova)) {
                handleCollision(mainCharacter, pechkurova);
            }
        }
        repaint();
    }

    private boolean collide(MainCharacter mainCharacter, Pechkurova pechkurova) {
        return mainCharacter.getBounds().intersects(pechkurova.getBounds());
    }

    private void handleCollision(MainCharacter mainCharacter, Pechkurova pechkurova) {
        System.out.println("OH NO!");
        remove(mainCharacter);
        mainCharacter.setBounds(-500, -500, 10, 10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
