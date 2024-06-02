import java.awt.*;

public class Decoration {
    private int x;
    private int y;
    private int width;
    private int height;

    public Decoration(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED); // или любой другой цвет
        g.fillRect(x, y, width, height);
    }
}
