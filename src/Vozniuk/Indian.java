package Vozniuk;

import javax.swing.*;
import java.awt.*;

public class Indian extends JLabel {
    public static final int SIZE = 60;
    private static final int STEP = 2;
    private int x;
    private int y;
    private int currentPoint;
    private final Point[] points;
    private boolean movingForward = true;

    public Indian(String imagePath, int x, int y, Point[] points) {
        this.x = x;
        this.y = y;
        this.points = points;
        currentPoint = 0;
        setIcon(resizeImage(imagePath));
        setBounds(x, y, SIZE, SIZE);
    }

    public void move() {
        if (movingForward) {
            moveTowards(points[currentPoint + 1]);
            if (reachedPoint(points[currentPoint + 1])) {
                currentPoint++;
                if (currentPoint == points.length - 1) {
                    movingForward = false;
                }
            }
        } else {
            moveTowards(points[currentPoint - 1]);
            if (reachedPoint(points[currentPoint - 1])) {
                currentPoint--;
                if (currentPoint == 0) {
                    movingForward = true;
                }
            }
        }
    }

    private void moveTowards(Point point) {
        if (x < point.x()) {
            x += STEP;
        } else if (x > point.x()) {
            x -= STEP;
        }
        if (y < point.y()) {
            y += STEP;
        } else if (y > point.y()) {
            y -= STEP;
        }
        setBounds(x, y, SIZE, SIZE);
    }

    private boolean reachedPoint(Point point) {
        return Math.abs(x - point.x()) <= 5 && Math.abs(y - point.y()) <= 5;
    }

    private ImageIcon resizeImage(String imagePath) {
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(Indian.SIZE, Indian.SIZE, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
