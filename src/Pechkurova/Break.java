package Pechkurova;

import SuperSwing.ImageButton;

public class Break extends ImageButton {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 100;
    private int x;
    private int y;
    public Break (String path, int x, int y) {
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
