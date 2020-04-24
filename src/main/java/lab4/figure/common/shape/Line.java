package lab4.figure.common.shape;

import lab4.figure.common.Color;
import lab4.figure.common.Point;
import lab4.figure.common.Shape;

public class Line extends Shape {
    private Point startPoint;
    private Point endPoint;
    private Color outlineColor;

    public Line(Point startPoint, Point endPoint, Color outlineColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.outlineColor = outlineColor;
    }

    Point getStartPoint() {
        return startPoint;
    }

    Point getEndPoint() {
        return endPoint;
    }

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        return Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2) + Math.pow(endPoint.y - startPoint.y, 2));
    }

    @Override
    public Color getOutlineColor() {
        return outlineColor;
    }

    @Override
    public String toString() {
        return "Линия:" +
                super.toString() +
                "Точка начала отрезка: " + startPoint.toString() + "\n" +
                "Точка конца отрезка: " + endPoint.toString() + "\n";
    }
}
