package Pechkurova;

import Enums.CommentState;
import SuperSwing.ImageBackground;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class IDE extends JFrame implements ActionListener {
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 800;
    private LinkedList<Method> methods = new LinkedList<Method>();
    private LinkedList<Method> methodsOnScreen = new LinkedList<Method>();
    private ImageBackground background;
    private Timer timer;
    private Random random;
    private int lastMethodNum;
    private ArrayList<Integer> numbersOfMethodsOnScreen = new ArrayList<>();

    public IDE() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setBounds((int) ((width - WIDTH) / 2), (int) ((height - HEIGHT + 45) / 2), WIDTH, HEIGHT);
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
        timer = new Timer(15, this);
        random = new Random();
        int newMethodNum = random.nextInt(9);
        lastMethodNum = newMethodNum;
        numbersOfMethodsOnScreen.add(newMethodNum);
        Method newMethod = methods.get(newMethodNum);
        newMethod.setBounds(newMethod.getX(), HEIGHT, newMethod.getWidth(), newMethod.getHeight());
        newMethod.setY(HEIGHT - 60);
        background.add(newMethod);
        background.revalidate();
        background.repaint();
        methodsOnScreen.add(newMethod);
        timer.start();
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
        Method method1 = new Method("Images\\KarelMethods\\Method1.png", 433, 70, 800, 565);
        method1.setBreak(new Break("Images\\break.png", 155, 111));
        method1.setBreak(new Break("Images\\break.png", 197, 257));
        method1.setBreak(new Break("Images\\break.png", 117, 433));
        method1.setBreak(new Break("Images\\break.png", 117, 490));
        method1.setComment(new Comment("Images\\Comment.png", 35, 25));
        methods.add(method1);

        Method method2 = new Method("Images\\KarelMethods\\Method2.png", 434, -100, 800, 695);
        method2.setBreak(new Break("Images\\break.png", 156, 145));
        method2.setBreak(new Break("Images\\break.png", 195, 235));
        method2.setBreak(new Break("Images\\break.png", 118, 408));
        method2.setBreak(new Break("Images\\break.png", 155, 585));
        method2.setComment(new Comment("Images\\Comment.png", 35, 29));
        methods.add(method2);

        Method method3 = new Method("Images\\KarelMethods\\Method3.png", 433, -100, 800, 782);
        method3.setBreak(new Break("Images\\break.png", 156, 198));
        method3.setBreak(new Break("Images\\break.png", 238, 315));
        method3.setBreak(new Break("Images\\break.png", 238, 460));
        method3.setComment(new Comment("Images\\Comment.png", 35, 22));
        methods.add(method3);

        Method method4 = new Method("Images\\KarelMethods\\Method4.png", 433, -100, 800, 723);
        method4.setBreak(new Break("Images\\break.png", 116, 238));
        method4.setBreak(new Break("Images\\break.png", 157, 355));
        method4.setBreak(new Break("Images\\break.png", 125, 530));
        method4.setBreak(new Break("Images\\break.png", 85, 650));
        method4.setComment(new Comment("Images\\Comment.png", 35, 30));
        methods.add(method4);

        Method method5 = new Method("Images\\KarelMethods\\Method5.png", 432, -100, 800, 738);
        method5.setBreak(new Break("Images\\break.png", 87, 195));
        method5.setBreak(new Break("Images\\break.png", 87, 340));
        method5.setBreak(new Break("Images\\break.png", 87, 517));
        method5.setComment(new Comment("Images\\Comment.png", 35, 50));
        methods.add(method5);

        Method method6 = new Method("Images\\KarelMethods\\Method6.png", 433, -100, 800, 811);
        method6.setBreak(new Break("Images\\break.png", 88, 150));
        method6.setBreak(new Break("Images\\break.png", 160, 355));
        method6.setBreak(new Break("Images\\break.png", 88, 595));
        method6.setComment(new Comment("Images\\Comment.png", 35, 33));
        methods.add(method6);

        Method method7 = new Method("Images\\KarelMethods\\Method7.png", 433, -100, 800, 815);
        method7.setBreak(new Break("Images\\break.png", 90, 130));
        method7.setBreak(new Break("Images\\break.png", 127, 305));
        method7.setBreak(new Break("Images\\break.png", 127, 545));
        method7.setBreak(new Break("Images\\break.png", 90, 753));
        method7.setComment(new Comment("Images\\Comment.png", 35, 13));
        methods.add(method7);

        Method method8 = new Method("Images\\KarelMethods\\Method8.png", 433, -100, 800, 806);
        method8.setBreak(new Break("Images\\break.png", 127, 207));
        method8.setBreak(new Break("Images\\break.png", 167, 355));
        method8.setBreak(new Break("Images\\break.png", 88, 533));
        method8.setBreak(new Break("Images\\break.png", 127, 710));
        method8.setComment(new Comment("Images\\Comment.png", 35, 30));
        methods.add(method8);

        Method method9 = new Method("Images\\KarelMethods\\Method9.png", 405, -100, 800, 813);
        method9.setBreak(new Break("Images\\break.png", 167, 250));
        method9.setBreak(new Break("Images\\break.png", 125, 455));
        method9.setBreak(new Break("Images\\break.png", 85, 605));
        method9.setBreak(new Break("Images\\break.png", 85, 722));
        method9.setComment(new Comment("Images\\Comment.png", 35, 13));
        methods.add(method9);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int step = 2;
        Method methodToRepair = null;
        boolean repairMethod = false;

        if (methodsOnScreen.getLast().getY() + methodsOnScreen.getLast().getHeight() < HEIGHT + 5) {
            boolean acceptable = false;
            int newMethodNum = 0;

            while (!acceptable) {
                newMethodNum = random.nextInt(9);
                acceptable = !numbersOfMethodsOnScreen.contains(newMethodNum);
            }

            numbersOfMethodsOnScreen.add(newMethodNum);
            lastMethodNum = newMethodNum;
            Method newMethod = methods.get(newMethodNum);
            newMethod.setBounds(newMethod.getX(), methodsOnScreen.getLast().getY() + methodsOnScreen.getLast().getHeight(), newMethod.getWidth(), newMethod.getHeight());
            newMethod.setY(methodsOnScreen.getLast().getY() + methodsOnScreen.getLast().getHeight());
            background.add(newMethod);
            methodsOnScreen.add(newMethod);
        }


        Iterator<Method> iterator = methodsOnScreen.iterator();
        while (iterator.hasNext()) {
            Method method = iterator.next();
            if (method.getY() + method.getHeight() <= background.getY() + 40) {
                iterator.remove();
                numbersOfMethodsOnScreen.removeFirst();
                background.remove(method);

                if (methods.contains(method)) {
                    methodToRepair = method;
                    repairMethod = true;
                }
            }
        }

        for (Method method : methodsOnScreen) {
            method.move(step);
            if (checkForLoss(method))
                timer.stop();
        }

        background.revalidate();
        background.repaint();


        if (repairMethod) {
            repairMethod(methodToRepair);
        }
    }

    private void repairMethod(Method method) {
        for (Break breake : method.getBreaks()) {
            breake.setVisible(true);
            breake.setCrashed(false);
        }
        method.getComment().setFrameState();
    }

    private boolean checkForLoss(Method method) {
        for (Break breake : method.getBreaks()) {
            if (method.getY() + breake.getY() <= background.getY() && !breake.isCrashed())
                return true;
        }
        return method.getY() + method.getComment().getY() <= background.getY() &&
                !method.getComment().getCurrentState().equals(CommentState.COMMENT);
    }
}
