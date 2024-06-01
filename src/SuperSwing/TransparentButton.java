package SuperSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class TransparentButton extends JButton {

    private Color defaultColor;
    private Color hoverColor;

    public TransparentButton(String text, Color defaultColor) {
        super(text);
        this.defaultColor=defaultColor;
        setForeground(defaultColor);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        hoverColor = Color.WHITE;

        // Add mouse listeners to handle hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(defaultColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Рисуем текст кнопки
        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20);
        return shape.contains(x, y);
    }
}


