package Pechkurova;

import SuperSwing.ImageButton;
import SuperSwing.ImageButtonSimple;

public class Comment extends ImageButtonSimple {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 100;
    private int x;
    private int y;

    public Comment(String path, int x, int y) {
        super(path);
    }

    public void move(int step) {
        y += step;
        setBounds(x, y, WIDTH, HEIGHT);
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
