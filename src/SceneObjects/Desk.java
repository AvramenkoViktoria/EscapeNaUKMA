package SceneObjects;

public class Desk extends Decoration {
    private int x;
    private int y;
    private int width;
    private int height;
    private String thought;

    public Desk(int x, int y, int width, int height, String thought) {
        super(x, y, width, height);
        this.thought = thought;
    }

    public String getThought() {
        return thought;
    }
}
