package Pechkurova;

import Enums.CommentState;
import SuperSwing.ImageButtonSimple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Comment extends ImageButtonSimple {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 16;
    private final int x;
    private final int y;
    private int changeCounter;
    private CommentState currentState;
    private static final String FRAME_PATH = "Images\\Comment.png";
    private static final String COMMENT_PATH = "Images\\Contract.png";
    private static final String W_PATH = "Images\\Glybovets.jpg";
    private static final String A_PATH = "Images\\Pechkurova.jpg";
    private static final String S_PATH = "Images\\VozniukA.jpg";
    private static final String D_PATH = "Images\\Budget.png";
    private final Random random = new Random();

    public Comment(String path, int x, int y) {
        super(path);
        setBounds(x, y, WIDTH, HEIGHT);
        this.x = x;
        this.y = y;
        this.changeCounter = 0;
        this.currentState = CommentState.FRAME;


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (currentState != CommentState.FRAME && currentState != CommentState.COMMENT) {
                    checkCorrectButton(e);
                }
                revalidate();
                repaint();
                repaintParent();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentState == CommentState.FRAME) {
                    changeImage();
                    revalidate();
                    repaint();
                    repaintParent();
                }
            }
        });
        setFocusable(true);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void changeImage() {
        if (changeCounter > 1) {
            setImagePath(COMMENT_PATH);
            setCurrentState(CommentState.COMMENT);
        } else {
            int number = random.nextInt(4);
            switch (number) {
                case 0:
                    setImagePath(W_PATH);
                    setCurrentState(CommentState.W);
                    break;
                case 1:
                    setImagePath(A_PATH);
                    setCurrentState(CommentState.A);
                    break;
                case 2:
                    setImagePath(S_PATH);
                    setCurrentState(CommentState.S);
                    break;
                case 3:
                    setImagePath(D_PATH);
                    setCurrentState(CommentState.D);
                    break;
            }
            changeCounter++;
        }
    }

    private void checkCorrectButton(KeyEvent event) {
        if (buttonCorrect(event))
            changeImage();
    }

    private boolean buttonCorrect(KeyEvent event) {
        int keyCode = event.getKeyCode();
        return switch (currentState) {
            case W -> keyCode == KeyEvent.VK_W;
            case A -> keyCode == KeyEvent.VK_A;
            case S -> keyCode == KeyEvent.VK_S;
            case D -> keyCode == KeyEvent.VK_D;
            default -> false;
        };
    }

    private void repaintParent() {
        Container parent = SwingUtilities.getAncestorOfClass(Container.class, this);
        if (parent != null) {
            parent.revalidate();
            parent.repaint();
        }
    }

    private void setCurrentState(CommentState state) {
        this.currentState = state;
    }

    public CommentState getCurrentState() {
        return currentState;
    }

    public void setFrameState() {
        setImagePath(FRAME_PATH);
        setCurrentState(CommentState.FRAME);
        changeCounter = 0;
    }

    private void setImagePath(String path) {
        super.setPath(path);
    }
}
