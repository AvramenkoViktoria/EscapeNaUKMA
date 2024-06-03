package Pechkurova;

import SceneObjects.Decoration;
import SuperSwing.ImageBackground;


import java.util.Random;
import java.util.Timer;

public class Pechkurova extends ImageBackground {
    public static final int SIZE = 90;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private boolean wasXVelocityNeg = false;
    private boolean wasYVelocityNeg = false;
    private boolean xVelocityNeg = false;
    private boolean yVelocityNeg = false;
    private final Random random = new Random();
    public boolean collides = false;
    private Timer timer;

    public Pechkurova(String imagePath, int x, int y) {
        super(imagePath);
        this.x = x;
        this.y = y;
        setBounds(x, y, SIZE, SIZE);
    }

    public void move(Decoration[] decorations) {
        collides = false;
        for (Decoration decoration : decorations) {
            if (touch(decoration)) {
                collides = true;
                bounce();
                break;
            }
        }
        x += xVelocity;
        //System.out.println("x+: " + xVelocity);
        y += yVelocity;
        //System.out.println("y+^ " + yVelocity);
        //System.out.println(wasYVelocityNeg);
    }

    public boolean touch(Decoration decoration) {
        return upSideTouch(decoration) || leftSideTouch(decoration) || downSideTouch(decoration) || rightSideTouch(decoration);
    }

    private boolean leftSideTouch(Decoration decoration) {
        if (x <= decoration.getX() + decoration.getWidth() && x >= decoration.getX() + decoration.getWidth() - 3 &&
                ((y + 1 >= decoration.getY() && y + 1 <= decoration.getY() + decoration.getHeight()) ||
                        (y + SIZE - 1 >= decoration.getY() && y + SIZE - 1 <= decoration.getY() + decoration.getHeight()))) {
            x = decoration.getX() + decoration.getWidth() + 1;
            xVelocityNeg = true;
            yVelocityNeg = false;
            //System.out.println("Left touch");
            return true;
        }
        return false;
    }

    private boolean rightSideTouch(Decoration decoration) {
        if (x + SIZE >= decoration.getX() && x + SIZE <= decoration.getX() + 3 &&
                ((y + 1 >= decoration.getY() && y + 1 <= decoration.getY() + decoration.getHeight()) ||
                        (y + SIZE - 1 >= decoration.getY() && y + SIZE - 1 <= decoration.getY() + decoration.getHeight()))) {
            x = decoration.getX() - SIZE - 1;
            xVelocityNeg = true;
            yVelocityNeg = false;
            //System.out.println("Right touch");
            return true;
        }
        return false;
    }

    private boolean upSideTouch(Decoration decoration) {
        if (y <= decoration.getY() + decoration.getHeight() && y >= decoration.getY() + decoration.getHeight() - 3 &&
                ((x + 1 >= decoration.getX() && x + 1 <= decoration.getX() + decoration.getWidth()) ||
                        (x + SIZE - 1 >= decoration.getX() && x + SIZE - 1 <= decoration.getX() + decoration.getWidth()))) {
            y = decoration.getY() + decoration.getHeight() + 1;
            xVelocityNeg = false;
            yVelocityNeg = true;
            //System.out.println("Up touch");
            return true;
        }
        return false;
    }

    private boolean downSideTouch(Decoration decoration) {
        if (y + SIZE >= decoration.getY() && y + SIZE <= decoration.getY() + 3 &&
                ((x + 1 >= decoration.getX() && x + 1 <= decoration.getX() + decoration.getWidth()) ||
                        (x + SIZE - 1 >= decoration.getX() && x + SIZE - 1 <= decoration.getX() + decoration.getWidth()))) {
            y = decoration.getY() - SIZE - 1;
            xVelocityNeg = false;
            yVelocityNeg = true;
            //System.out.println("Down touch");
            return true;
        }
        return false;
    }

    public void bounce() {
        xVelocity = random.nextInt(3, 5);
        yVelocity = random.nextInt(3, 5);

        if (wasXVelocityNeg)
            xVelocity = xVelocity * -1;
        if (wasYVelocityNeg)
            yVelocity = yVelocity * -1;
        if (xVelocityNeg) {
            xVelocity = xVelocity * -1;
        }
        if (yVelocityNeg) {
            yVelocity = yVelocity * -1;
        }
        wasXVelocityNeg = xVelocity < 0;
        wasYVelocityNeg = yVelocity < 0;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getXForMove() {
        return x;
    }

    public int getYForMove() {
        return y;
    }
}
