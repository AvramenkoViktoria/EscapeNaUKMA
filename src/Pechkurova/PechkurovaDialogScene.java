package Pechkurova;

import javax.swing.*;

public class PechkurovaDialogScene extends JFrame {
    public PechkurovaDialogScene() {
        loadIDE();
    }

    private void loadIDE() {
        setVisible(false);
        IDE ide = new IDE();
    }
}
