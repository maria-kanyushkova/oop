package lab4.shapes.canvas;

import lab4.shapes.common.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.List;

public class Canvas extends JPanel implements ICanvas {
    private static final int STROKE_WIDTH = 5;
    private Graphics2D g2d;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
    }

    @Override
    public void drawLine(Point from, Point to, Color outlineColor) {
        g2d.setPaint(outlineColor);
        g2d.draw(new Line2D.Double(from.x, from.y, to.x, to.y));
    }

    @Override
    public void fillPolygon(List<Point> points, Color fillColor) {
        g2d.setColor(fillColor);
        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).x, points.get(0).y);
        points.stream().skip(1).forEach(point -> path.lineTo(point.x, point.y));
        path.closePath();
        g2d.fill(path);
    }

    @Override
    public void drawCircle(Point center, double radius, Color outlineColor) {
        g2d.setColor(outlineColor);
        g2d.draw(new Ellipse2D.Double(center.x, center.y, radius, radius));
    }

    @Override
    public void fillCircle(Point center, double radius, Color fillColor) {
        g2d.setColor(fillColor);
        g2d.fill(new Ellipse2D.Double(center.x, center.y, radius, radius));
    }
}
