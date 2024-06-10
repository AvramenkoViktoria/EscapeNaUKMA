package SceneObjects;

import Enums.Level;
import SuperSwing.ImageBackground;

public class Hearts extends ImageBackground {
    private static final int FULL_WIDTH = 150;
    private static final int TWO_HEARTS_WIDTH = 110;
    private static final int ONE_HEART_WIDTH = 60;
    private static final int HEIGHT = 35;
    private String imagePath;
    public Hearts(String imagePath, Level level, int x, int y) {
        super(imagePath);
        this.imagePath = imagePath;
        switch (level) {
            case CONTRACT -> setBounds(x, y, FULL_WIDTH, HEIGHT);
            case BUDGET -> setBounds(x, y, TWO_HEARTS_WIDTH, HEIGHT);
            case GRANT -> setBounds(x, y, ONE_HEART_WIDTH, HEIGHT);
        }
    }

    public String getPath() {
        return imagePath;
    }
}
