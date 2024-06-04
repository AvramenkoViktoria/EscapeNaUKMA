package Pechkurova;

import SuperSwing.ImageBackground;

import javax.swing.*;
import java.util.LinkedList;

public class IDE extends JFrame {
    private static final int WIDTH = 1350;
    private static final int HEIGHT = 840;
    private LinkedList<Method> methods = new LinkedList<Method>();
    private ImageBackground background;

    public IDE() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBounds(300, 150, WIDTH, HEIGHT);
        setLayout(null);
        background = new ImageBackground("Images\\IDELower.png");
        background.setLayout(null);
        background.setBounds(0, 0, WIDTH - 20, HEIGHT - 10);
        add(background);
        revalidate();
        repaint();
        setVisible(true);

        addUpperFrame();
        initializeMethodsList();
    }

    private void addUpperFrame() {
        int height = 110;
        JFrame upperIDE = new JFrame();
        upperIDE.setAlwaysOnTop(true);
        upperIDE.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        upperIDE.setResizable(false);
        upperIDE.setBounds(getX(), getY() - 60, WIDTH, height);
        upperIDE.setLayout(null);
        ImageBackground background1 = new ImageBackground("Images\\IDEUpper.png");
        background1.setLayout(null);
        background1.setBounds(0, 0, WIDTH - 20, upperIDE.getHeight() - 38);
        upperIDE.add(background1);
        upperIDE.revalidate();
        upperIDE.repaint();
        upperIDE.setVisible(true);
    }

    private void initializeMethodsList() {
        Method method1 = new Method("Images\\KarelMethods\\Method1.png", 300, 70, 700, 600);
        method1.setBreak(new Break("Images\\KvitWarning.png", 60, 60));
        method1.setComment(new Comment("Images\\KvitWarning.png", 30, 20));
        methods.add(method1);
        background.add(method1);
        background.revalidate();
        background.repaint();
    }
}
