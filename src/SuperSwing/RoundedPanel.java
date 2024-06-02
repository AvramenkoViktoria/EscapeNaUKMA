package SuperSwing;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private Color backgroundColor;
    private int cornerRadius = 15;

    public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
        super(layout);
        cornerRadius = radius;
        backgroundColor = bgColor;
        setOpaque(false);
    }

    public RoundedPanel(int radius, Color bgColor) {
        this(new FlowLayout(), radius, bgColor);
    }

    public RoundedPanel(LayoutManager layout, int radius) {
        this(layout, radius, null);
    }

    public RoundedPanel(int radius) {
        this(radius, null);
    }

    public RoundedPanel(LayoutManager layout) {
        this(layout, 15);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(cornerRadius, cornerRadius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set background color if provided
        if (backgroundColor != null) {
            graphics.setColor(backgroundColor);
        } else {
            graphics.setColor(getBackground());
        }

        // Draws the rounded panel without borders
        graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);
    }
}
