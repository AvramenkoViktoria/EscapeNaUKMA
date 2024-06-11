package SceneObjects;

public class Door extends Decoration {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean blocked;

    public Door(int x, int y, int width, int height, boolean blocked) {
        super(x, y, width, height);
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
