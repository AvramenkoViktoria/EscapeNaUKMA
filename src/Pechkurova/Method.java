package Pechkurova;

import SuperSwing.ImageBackground;

import java.util.LinkedList;

public class Method extends ImageBackground {
    private int x;
    private int y;
    private int width;
    private int height;
    private int breakX;
    private int breakY;
    private boolean breakDestroyed;
    private boolean commentDestroyed;
    private LinkedList<Break> breaks = new LinkedList<Break>();
    private Comment comment;

    public Method(String imagePath, int x, int y, int width, int height) {
        super(imagePath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setLayout(null);
        setBounds(x, y, width, height);
    }

    public void setBreak(Break breake) {
        breake.setBounds(breake.getX(), breake.getY(), 100, 30);
        add(breake);
        breaks.add(breake);
        revalidate();
        repaint();
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.setBounds(comment.getX(), comment.getY(), 200, 30);
        add(comment);
        repaint();
    }
}
