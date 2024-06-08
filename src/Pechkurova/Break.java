package Pechkurova;

import SuperSwing.ImageButtonSimple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Break extends ImageButtonSimple{
    private static final int WIDTH = 60;
    private static final int HEIGHT = 16;
    private final int x;
    private final int y;
    private boolean crashed;

    public Break(String imagePath, int x, int y) {
        super(imagePath);
        this.x = x;
        this.y = y;
        this.crashed = false;
        setBounds(x, y, WIDTH, HEIGHT);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                removeBreak();
            }
        });
    }

    private void removeBreak() {
        Container parent = SwingUtilities.getAncestorOfClass(Container.class, this);
        if (parent != null) {
            crashed = true;
            setVisible(false);
            parent.revalidate();
            parent.repaint();
        }
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void setCrashed(boolean crashed){
        this.crashed = crashed;
    }

    public boolean isCrashed(){
        return crashed;
    }

}
