package SceneObjects;

import Enums.SpeakerType;
import javax.swing.*;


public class DialogWindow extends JFrame {
    private SpeakerType type;
    private int x;
    private int y;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private final String friendImagePath = "";
    private final String userImagePath = "Images\\MainCharDown.png";
    private final String pechkurovaImaghePath = "Images\\Pechkurova.jpg";
    public DialogWindow(int x, int y, String message, SpeakerType type) {
        this.x = x;
        this.y = y;
        this.type = type;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBounds(x, y, WIDTH, HEIGHT);

        setVisible(true);
    }

    private void writeMessage(String message) {

    }

}
