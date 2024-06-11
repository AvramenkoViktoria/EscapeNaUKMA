import Pechkurova.MainCharacter;
import SceneObjects.Decoration;
import SceneObjects.Desk;
import SceneObjects.Door;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestPanelMainCh extends JPanel {
    private MainCharacter mainCharacter;
    private Decoration[] decorations = new Decoration[9];

    public TestPanelMainCh() {
        setLayout(null);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 400, 200,100,100);

        decorations[0] = new Desk(850, 5, 5, 890, null);
        decorations[1] = new Desk(5, 5, 10, 890, null);
        decorations[2] = new Desk(5, 10, 890, 5, null);
        decorations[3] = new Desk(5, 800, 890, 5, null);
        decorations[4] = new Desk(200, 200, 100, 100, null);
        decorations[5] = new Desk(600, 200, 100, 100, null);
        decorations[6] = new Desk(600, 450, 100, 100, null);
        decorations[7] = new Desk(200, 450, 100, 100, null);
        decorations[8] = new Door(20, 30, 10, 90, true);

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
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Character Data.Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.add(new TestPanelMainCh());
        frame.setVisible(true);
    }
}
