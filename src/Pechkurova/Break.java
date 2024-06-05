package Pechkurova;

import SuperSwing.ImageButton;
import SuperSwing.ImageButtonSimple;

public class Break extends ImageButtonSimple {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 16;
    private int x;
    private int y;
    public Break (String path, int x, int y) {
        super(path);
        setBounds(x, y, WIDTH, HEIGHT);
        this.x = x;
        this.y = y;
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
