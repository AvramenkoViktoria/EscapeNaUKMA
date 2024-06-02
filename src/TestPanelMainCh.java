

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestPanelMainCh extends JPanel {
    private MainCharacter mainCharacter;
    private Decoration[] decorations = new Decoration[8];

    public TestPanelMainCh() {
        setLayout(null);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 50, 50);
        decorations[0] = new Decoration(850, 5, 5, 890);
        decorations[1] = new Decoration(5, 5, 10, 890);
        decorations[2] = new Decoration(5, 10, 890, 5);
        decorations[3] = new Decoration(5, 800, 890, 5);
        decorations[4] = new Decoration(200, 200, 100, 100);
        decorations[5] = new Decoration(600, 200, 100, 100);
        decorations[6] = new Decoration(600, 450, 100, 100);
        decorations[7] = new Decoration(200, 450, 100, 100);

        add(mainCharacter);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        if (mainCharacter.canMoveForward(decorations)) {
                            mainCharacter.moveForward();
                        }
                    }
                    case KeyEvent.VK_S -> {
                        if (mainCharacter.canMoveBackward(decorations)) {
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
        JFrame frame = new JFrame("Main Character Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.add(new TestPanelMainCh());
        frame.setVisible(true);
    }
}
