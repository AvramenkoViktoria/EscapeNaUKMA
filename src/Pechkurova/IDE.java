package Pechkurova;

import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class IDE extends JFrame {
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 800;
    private LinkedList<Method> methods = new LinkedList<Method>();
    private ImageBackground background;

    public IDE() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBounds(50, 90, WIDTH, HEIGHT);
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
        /*
        Method method1 = new Method("Images\\KarelMethods\\Method1.png", 434, 70, 800, 565);
        method1.setBreak(new Break("Images\\break.png", 200, 60));
        method1.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method1);
        background.add(method1);
        background.revalidate();
        background.repaint();

        Method method2 = new Method("Images\\KarelMethods\\Method2.png", 434, 70, 800, 695);
        method2.setBreak(new Break("Images\\break.png", 200, 60));
        method2.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method2);
        background.add(method2);
        background.revalidate();
        background.repaint();

        Method method3 = new Method("Images\\KarelMethods\\Method3.png", 434, 70, 800, 782);
        method3.setBreak(new Break("Images\\break.png", 200, 60));
        method3.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method3);
        background.add(method3);
        background.revalidate();
        background.repaint();

        Method method4 = new Method("Images\\KarelMethods\\Method4.png", 433, 70, 800, 723);
        method4.setBreak(new Break("Images\\break.png", 200, 60));
        method4.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method4);
        background.add(method4);
        background.revalidate();
        background.repaint();

        Method method5 = new Method("Images\\KarelMethods\\Method5.png", 432, 70, 800, 738);
        method5.setBreak(new Break("Images\\break.png", 200, 60));
        method5.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method5);
        background.add(method5);
        background.revalidate();
        background.repaint();

        Method method6 = new Method("Images\\KarelMethods\\Method6.png", 432, 100, 800, 811);
        method6.setBreak(new Break("Images\\break.png", 200, 60));
        method6.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method6);
        background.add(method6);
        background.revalidate();
        background.repaint();

        Method method7 = new Method("Images\\KarelMethods\\Method7.png", 432, 100, 800, 815);
        method7.setBreak(new Break("Images\\break.png", 200, 60));
        method7.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method7);
        background.add(method7);
        background.revalidate();
        background.repaint();

        Method method8 = new Method("Images\\KarelMethods\\Method8.png", 433, 100, 800, 806);
        method8.setBreak(new Break("Images\\break.png", 200, 60));
        method8.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method8);
        background.add(method8);
        background.revalidate();
        background.repaint();
         */
        Method method9 = new Method("Images\\KarelMethods\\Method9.png", 432, 100, 800, 813);
        method9.setBreak(new Break("Images\\break.png", 80, 10));
        method9.setComment(new Comment("Images\\Comment.png", 30, 20));
        methods.add(method9);
        background.add(method9);
        background.revalidate();
        background.repaint();


    }
}
