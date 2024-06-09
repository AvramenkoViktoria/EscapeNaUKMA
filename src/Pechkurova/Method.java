package Pechkurova;

import Enums.CommentState;
import SuperSwing.ImageBackground;

import java.util.ArrayList;
import java.util.LinkedList;

public class Method extends ImageBackground{
    private int x;
    private int y;
    private int width;
    private int height;
    private LinkedList<Break> breaks = new LinkedList<>();
    private Comment comment;
    private String imagePath;

    public Method(String imagePath, int x, int y, int width, int height) {
        super(imagePath);
        this.imagePath = imagePath;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setLayout(null);
        setBounds(x, y, width, height);
    }

    public void setBreak(Break breake) {
        breake.setBounds(breake.getX(), breake.getY(), breake.getWidth(), breake.getHeight());
        this.add(breake);
        breaks.add(breake);
        repaint();
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.setBounds(comment.getX(), comment.getY(), comment.getWidth(), comment.getHeight());
        this.add(comment);
        repaint();
    }

    public void move(int step) {
        y -= step;
        setBounds(x, y, width, height);
        revalidate();
        repaint();
    }

    public void setY(int y) {
        this.y = y;
        setBounds(x, y, width, height);
    }

    public void setX(int x) {
        this.x = x;
        setBounds(x, y, width, height);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public LinkedList<Break> getBreaks() {
        return breaks;
    }

    @Override
    public String toString() {
        return imagePath;
    }

    public Comment getComment() {
        return comment;
    }
}
