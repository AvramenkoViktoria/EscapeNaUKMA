package Pechkurova;

import Data.FileManager;
import SceneObjects.*;
import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    public BattleScene(String imagePath) {
        super(imagePath);
        setLayout(null);
        int delay = 0;
        switch (FileManager.user.getLevel()) {
            case CONTRACT:
                delay = 20;
                mitosisNumber = 3;
                break;
            case BUDGET:
                delay = 16;
                mitosisNumber = 2;
                break;
            case GRANT:
                delay = 16;
                mitosisNumber = 1;
        }
        timer = new Timer(delay, this);
        Pechkurova pechkurova = new Pechkurova("Images\\Pechkurova.jpg", 960, 20);
        pechkurovas.add(pechkurova);
        pechkurova.setxVelocity(3);
        pechkurova.setyVelocity(3);

        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 40, 650, 90, 90);

        decorations[0] = new Desk(-30, -30, 40, 885, null);
        decorations[1] = new Desk(-30, -30, 1300, 40, null);
        decorations[2] = new Desk(-30, 845, 1300, 50, null);
        decorations[3] = new Desk(1191, -30, 100, 1015, null);
        //Walls
        decorations[4] = new Door(870, 10, 203, 12);
        //Door
        decorations[5] = new Desk(160, 103, 202, 260, "Litachok have seen his better days... Now next students will have to deal with him");
        decorations[6] = new PortalDesk(160, 490, 202, 260, "Be sure to check everything, because the moment you seat next task will start! Press OK to start"); //Needs to be PortalDesk
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
        DialogWindow window = new Rule(0, 853 - DialogWindow.HEIGHT , "U cunt", false);
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
                            System.out.println("Going out");
                            IDE.battleFrame.setVisible(false);
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

        for (Pechkurova pechkurova : pechkurovas) {
            if (collide(mainCharacter, pechkurova)) {
                handleCollision(mainCharacter);
            }
        }
        repaint();
    }

    private boolean collide(MainCharacter mainCharacter, Pechkurova pechkurova) {
        return mainCharacter.getBounds().intersects(pechkurova.getBounds());
    }

    private void handleCollision(MainCharacter mainCharacter) {
        System.out.println("OH NO!");
        remove(mainCharacter);
        mainCharacter.setBounds(-500, -500, 10, 10);
        IDE.battleFrame.setVisible(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
