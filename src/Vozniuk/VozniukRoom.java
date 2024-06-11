package Vozniuk;

import SceneObjects.Decoration;
import SuperSwing.ImageBackground;

public class VozniukRoom extends ImageBackground {
    private final static int WIDTH = 1000;
    private final static int HEIGHT = 800;
    private final Decoration[] decorations = new Decoration[0];
    public VozniukRoom(String imagePath) {
        super(imagePath);
        setLayout(null);
        setBounds(0, 0, WIDTH, HEIGHT);
        fillDecorations();
    }

    private void fillDecorations() {

    }
}
