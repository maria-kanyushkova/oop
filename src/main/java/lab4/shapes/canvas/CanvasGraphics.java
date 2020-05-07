package lab4.shapes.canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CanvasGraphics extends JPanel {
    private static final int STROKE_WIDTH = 2;
    private List<Function<Graphics2D, Void>> painter = new ArrayList<>();

    public void setColor(Color color) {
        painter.add((Graphics2D g2d) -> {
            g2d.setColor(color);
            return null;
        });
    }

    public void draw(Shape shape) {
        painter.add((Graphics2D g2d) -> {
            g2d.draw(shape);
            return null;
        });
    }

    public void fill(Shape shape) {
        painter.add((Graphics2D g2d) -> {
            g2d.fill(shape);
            return null;
        });
    }

    public void setPaint(Color color) {
        painter.add((Graphics2D g2d) -> {
            g2d.setPaint(color);
            return null;
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        initGraphics2D(g2d);

        painter.forEach(p -> p.apply(g2d));
    }

    private void initGraphics2D(final Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
    }
}
