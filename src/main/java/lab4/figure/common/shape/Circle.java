package lab4.figure.common.shape;

import lab4.figure.common.Color;
import lab4.figure.common.ISolidShape;
import lab4.figure.common.Point;

public class Circle implements ISolidShape {
    private final double pi = 3.14;
    private Point center;
    private double radius;
    private Color outlineColor;
    private Color fillColor;

    public Circle(Point center, double radius, Color outlineColor, Color fillColor) {
        this.center = center;
        this.radius = radius;
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    Point getCenter() {
        return center;
    }

    double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return pi * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * pi * radius;
    }

    @Override
    public Color getOutlineColor() {
        return outlineColor;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public String toString() {
        return "Цвет заливки: " + fillColor.toStringHex() + "\n" +
                "Точка центра окружности: " + center.toString() + "\n" +
                "Радиус окружности: " + String.format("%.2f", radius) + "\n";
    }
}
