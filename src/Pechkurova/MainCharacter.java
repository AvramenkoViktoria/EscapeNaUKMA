package Pechkurova;

import Enums.Direction;
import Enums.SpeakerType;
import SceneObjects.*;

import javax.swing.*;
import java.awt.*;

public class MainCharacter extends JLabel {
    private int x, y;
    private Direction direction;
    private boolean isEKeyPressed = false;
    private int width, height; // Store the desired width and height of the image

    public MainCharacter(String imagePath, int x, int y, int width, int height) {
        this.direction = Direction.UP; // Initial direction is UP
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setIcon(resizeImage(imagePath, width, height));
        setBounds(x, y, width-5, height);
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
            case UP -> "Images/MainCharUp.png";
            case RIGHT -> "Images/MainCharRight.png";
            case DOWN -> "Images/MainCharDown.png";
            case LEFT -> "Images/MainCharLeft.png";
        };
        setIcon(resizeImage(imagePath, width, height));
    }

    public void setEKeyPressed(boolean isEKeyPressed) {
        this.isEKeyPressed = isEKeyPressed;
    }

    public InteractiveObject canMoveForward(Decoration[] decorations) {
        Rectangle nextPosition = getNextPosition(5);
        return interactWithDecoration(decorations, nextPosition);
    }

    public InteractiveObject canMoveBackward(Decoration[] decorations) {
        Rectangle nextPosition = getNextPosition(-5);
        return interactWithDecoration(decorations, nextPosition);
    }

    private InteractiveObject interactWithDecoration(Decoration[] decorations, Rectangle nextPosition) {
        for (Decoration decoration : decorations) {
            if (nextPosition.intersects(decoration.getBounds())) {
                switch (decoration) {
                    case Door door when isEKeyPressed -> {
                        return new InteractiveObject(false, true, null, null);
                    }
                    case Desk desk when isEKeyPressed -> {
                        return new InteractiveObject(false, false, desk.getThought(), SpeakerType.USER);
                    }
                    case PortalDesk portalDesk when isEKeyPressed -> {
                        return new InteractiveObject(false, false, portalDesk.getMessage(), SpeakerType.FRIEND);
                    }
                    default -> {
                    }
                }
                return new InteractiveObject(false, false, null, null);
            }
        }
        return new InteractiveObject(true, false, null, null);
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

    private ImageIcon resizeImage(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
