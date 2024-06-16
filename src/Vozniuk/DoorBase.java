package Vozniuk;

import Data.Test;
import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DoorBase extends JFrame {
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 750;
    public DoorBase() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageBackground codes = new ImageBackground("Images\\NaUKMA_DOORS.png");
        codes.setBounds(0, 0, WIDTH, HEIGHT);
        add(codes);
        revalidate();
        repaint();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.vozniukAccount.setVisible(true);
                Test.mainMenu.levelMenu.roomMenu.hall.vozniukRoom.unlockTheDoor();
            }
        });

        setVisible(true);
    }
}
