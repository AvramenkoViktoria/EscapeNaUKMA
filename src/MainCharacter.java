import Enums.Direction;
import SceneObjects.Decoration;
import SceneObjects.Desk;
import SceneObjects.Door;
import SceneObjects.PortalDesk;
import SuperSwing.ImageBackground;

import java.awt.*;

public class MainCharacter extends ImageBackground {
    private int x, y;
    private Direction direction;
    private boolean isEKeyPressed = false;

    public MainCharacter(String imagePath, int x, int y) {
        super(imagePath);
        this.x = x;
        this.y = y;
        this.direction = Direction.UP; // Initial direction is UP
        setBounds(x, y, 100, 100);
    }

    public void moveForward() {
        switch (direction) {
            case UP -> y -= 5;
            case RIGHT -> x += 5;
            case DOWN -> y += 5;
            case LEFT -> x -= 5;
        }
        setBounds(x, y, getWidth(), getHeight());
    }

    public void moveBackward() {
        switch (direction) {
            case UP -> y += 5;
            case RIGHT -> x -= 5;
            case DOWN -> y -= 5;
            case LEFT -> x += 5;
        }
        setBounds(x, y, getWidth(), getHeight());
    }

    public void turnRight() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
        updateImage();
    }

    public void turnLeft() {
        direction = switch (direction) {
            case UP -> Direction.LEFT;
            case LEFT -> Direction.DOWN;
            case DOWN -> Direction.RIGHT;
            case RIGHT -> Direction.UP;
        };
        updateImage();
    }

    private void updateImage() {
        String imagePath = switch (direction) {
            case UP -> "Images\\MainCharUp.png";
            case RIGHT -> "Images\\MainCharRight.png";
            case DOWN -> "Images\\MainCharDown.png";
            case LEFT -> "Images\\MainCharLeft.png";
        };
        loadImage(imagePath);
    }

    public void setEKeyPressed(boolean isEKeyPressed) {
        this.isEKeyPressed = isEKeyPressed;
    }

    public boolean canMoveForward(Decoration[] decorations) {
        Rectangle nextPosition = getNextPosition(5);
        return interactWithDecoration(decorations, nextPosition);
    }

    public boolean canMoveBackward(Decoration[] decorations) {
        Rectangle nextPosition = getNextPosition(-5);
        return interactWithDecoration(decorations, nextPosition);
    }
    private boolean interactWithDecoration(Decoration[] decorations, Rectangle nextPosition) {
        for (Decoration decoration : decorations) {
            if (nextPosition.intersects(decoration.getBounds())) {
                switch (decoration) {
                    case Door door when isEKeyPressed -> System.out.println("Going out");
                    case Desk desk when isEKeyPressed -> {
                        String message = desk.getThought();
                        if (message != null) {
                            System.out.println(message);
                        }
                    }
                    case PortalDesk portalDesk when isEKeyPressed -> System.out.println("U sure?");
                    default -> {
                    }
                }
                return false;
            }
        }
        return true;
    }


    private Rectangle getNextPosition(int step) {
        switch (direction) {
            case UP -> {
                return new Rectangle(x, y - step, getWidth(), getHeight());
            }
            case RIGHT -> {
                return new Rectangle(x + step, y, getWidth(), getHeight());
            }
            case DOWN -> {
                return new Rectangle(x, y + step, getWidth(), getHeight());
            }
            case LEFT -> {
                return new Rectangle(x - step, y, getWidth(), getHeight());
            }
        }
        return getBounds();
    }
}
