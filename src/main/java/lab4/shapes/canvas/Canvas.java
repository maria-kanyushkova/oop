package lab4.shapes.canvas;

import lab4.shapes.common.Point;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;

public class Canvas implements ICanvas {
    private Graphics graphics = new Graphics();

    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public void drawLine(Point from, Point to, Color outlineColor) {
        graphics.setPaint(outlineColor);
        graphics.draw(new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY()));
    }

    @Override
    public void fillPolygon(List<Point> points, Color fillColor) {
        Path2D path = new Path2D.Double();
        path.moveTo(points.get(0).getX(), points.get(0).getY());
        points.stream().skip(1).forEach(point -> path.lineTo(point.getX(), point.getY()));
        path.closePath();

        graphics.setColor(fillColor);
        graphics.fill(path);
    }

    @Override
    public void drawCircle(Point center, double radius, Color outlineColor) {
        graphics.setPaint(outlineColor);
        graphics.draw(new Ellipse2D.Double(center.getX(), center.getY(), radius, radius));
    }

    @Override
    public void fillCircle(Point center, double radius, Color fillColor) {
        graphics.setPaint(fillColor);
        graphics.fill(new Ellipse2D.Double(center.getX(), center.getY(), radius, radius));
    }
}
