package lab4.shapes.canvas;

import lab4.shapes.common.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Canvas extends JPanel implements ICanvas {
    private static final int STROKE_WIDTH = 2;
    private List<Function<Graphics2D, Void>> painter = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        initGraphics2D(g2d);

        painter.forEach(p -> p.apply(g2d));
    }

    @Override
    public void drawLine(Point from, Point to, Color outlineColor) {
        painter.add((Graphics2D g2d) -> {
            g2d.setPaint(outlineColor);
            g2d.draw(new Line2D.Double(from.x, from.y, to.x, to.y));
            return null;
        });
    }

    @Override
    public void fillPolygon(List<Point> points, Color fillColor) {
        painter.add((Graphics2D g2d) -> {
            Path2D path = new Path2D.Double();
            path.moveTo(points.get(0).x, points.get(0).y);
            points.stream().skip(1).forEach(point -> path.lineTo(point.x, point.y));
            path.closePath();
            g2d.fill(path);
            return null;
        });
    }

    @Override
    public void drawCircle(Point center, double radius, Color outlineColor) {
        painter.add((Graphics2D g2d) -> {
            g2d.setColor(outlineColor);
            g2d.draw(new Ellipse2D.Double(center.x, center.y, radius, radius));
            return null;
        });
    }

    @Override
    public void fillCircle(Point center, double radius, Color fillColor) {
        painter.add((Graphics2D g2d) -> {
            g2d.setColor(fillColor);
            g2d.fill(new Ellipse2D.Double(center.x, center.y, radius, radius));
            return null;
        });
    }

    private void initGraphics2D(final Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
    }
}
