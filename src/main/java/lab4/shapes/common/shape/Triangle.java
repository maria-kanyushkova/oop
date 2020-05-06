package lab4.shapes.common.shape;

import lab4.shapes.Utils;
import lab4.shapes.canvas.ICanvas;
import lab4.shapes.common.ISolidShape;
import lab4.shapes.common.Point;
import lab4.shapes.common.Shape;

import java.awt.*;
import java.util.Arrays;

public class Triangle extends Shape implements ISolidShape {
    private Point vertex1;
    private Point vertex2;
    private Point vertex3;
    private Color outlineColor;
    private Color fillColor;

    public Triangle(Point vertex1, Point vertex2, Point vertex3, Color outlineColor, Color fillColor) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    public Point getVertex1() {
        return vertex1;
    }

    public Point getVertex2() {
        return vertex2;
    }

    public Point getVertex3() {
        return vertex3;
    }

    @Override
    public double getArea() {
        double width12 = getLineWidth(vertex1, vertex2);
        double width23 = getLineWidth(vertex2, vertex3);
        double width31 = getLineWidth(vertex3, vertex1);
        double p = (width12 + width23 + width31) / 2;
        return Math.sqrt(p * (p - width12) * (p - width23) * (p - width31));
    }

    @Override
    public double getPerimeter() {
        return getLineWidth(vertex1, vertex2) + getLineWidth(vertex2, vertex3) + getLineWidth(vertex3, vertex1);
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
        return "Треугольник:\n" +
                super.toString() +
                "Цвет заливки: " + Utils.colorToString(fillColor) + "\n" +
                "Первая точка треугольника: " + vertex1.toString() + "\n" +
                "Вторая точка треугольника: " + vertex2.toString() + "\n" +
                "Третья точка треугольника: " + vertex3.toString() + "\n";
    }

    private double getLineWidth(Point point1, Point point2) {
        return Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
    }

    @Override
    public void draw(ICanvas canvas) {
        canvas.fillPolygon(Arrays.asList(vertex1, vertex2, vertex3), fillColor);
        canvas.drawLine(vertex1, vertex2, outlineColor);
        canvas.drawLine(vertex2, vertex3, outlineColor);
        canvas.drawLine(vertex3, vertex1, outlineColor);
    }
}
