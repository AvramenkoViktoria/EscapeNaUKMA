package SceneObjects;

import Enums.Type;

public class PortalDesk extends Decoration {
    private int x;
    private int y;
    private int width;
    private int height;
    private String message;
    private Type type;

    public PortalDesk(int x, int y, int width, int height, String message, Type type) {
        super(x, y, width, height);
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }
}
