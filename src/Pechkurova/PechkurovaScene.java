package Pechkurova;

import Enums.SpeakerType;
import SceneObjects.*;
import SuperSwing.ImageBackground;

import java.awt.*;
import java.awt.event.*;

public class PechkurovaScene extends ImageBackground {
    private final Decoration[] decorations = new Decoration[13];
    private final MainCharacter mainCharacter;
    private DialogWindow dialogWindow;

    public PechkurovaScene(String imagePath) {
        super(imagePath);
        setLayout(null);

        mainCharacter = new MainCharacter("Images\\MainCharUp.png", 990, 250, 80, 80);

        decorations[0] = new Desk(-30, -30, 40, 885, null);
        decorations[1] = new Desk(-30, -30, 1300, 40, null);
        decorations[2] = new Desk(-30, 845, 1300, 50, null);
        decorations[3] = new Desk(1191, -30, 100, 1015, null);
        //Walls
        decorations[4] = new Door(870, 10, 203, 12, false);
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
                        if (interaction.getMessage() != null) {
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
    }

    private void addDialogWindow(InteractiveObject interaction) {
        if (dialogWindow != null) {
            remove(dialogWindow);
            dialogWindow = null;
        }
        int x = 0;
        int y = getHeight() - DialogWindow.HEIGHT;
        if (interaction.getSpeakerType().equals(SpeakerType.FRIEND)) {
            dialogWindow = new Hint(x, y, interaction.getMessage());
        } else {
            dialogWindow = new Thought(x, y, interaction.getMessage(), SpeakerType.USER);
        }
        add(dialogWindow);
        dialogWindow.bringToFront();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Decoration decoration : decorations) {
            decoration.draw(g);
        }
    }
}
