package SceneObjects;

public class PortalDesk extends Decoration {
    private int x;
    private int y;
    private int width;
    private int height;
    private String message;

    public PortalDesk(int x, int y, int width, int height, String message) {
        super(x, y, width, height);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
