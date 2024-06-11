package SceneObjects;

import Pechkurova.MainCharacter;
import SuperSwing.ImageBackground;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Hall extends ImageBackground {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private MainCharacter mainCharacter;
    private final Decoration[] decorations = new Decoration[0];

    public Hall(String imagePath) {
        super(imagePath);
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 100, 100, 90, 90);
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
}
