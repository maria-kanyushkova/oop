package lab4.shapes.common.shape;

import lab4.shapes.Utils;
import lab4.shapes.canvas.ICanvas;
import lab4.shapes.common.ISolidShape;
import lab4.shapes.common.Point;
import lab4.shapes.common.Shape;

import java.awt.*;
import java.util.Arrays;

public class Rectangle extends Shape implements ISolidShape {
    private Point leftTop;
    private Point rightBottom;
    private double height;
    private double width;
    private Color outlineColor;
    private Color fillColor;

    public Rectangle(Point leftTop, double width, double height, Color outlineColor, Color fillColor) {
        this.height = height;
        this.width = width;
        this.leftTop = leftTop;
        this.rightBottom = new Point(leftTop.x + width, leftTop.y + height);
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    public Point getLeftTop() {
        return leftTop;
    }

    public Point getRightBottom() {
        return rightBottom;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
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
        return "Прямоугольник:\n" +
                super.toString() +
                "Цвет заливки: " + Utils.colorToString(fillColor) + "\n" +
                "Левая верхняя точка прямоугольника: x:" + leftTop.toString() + "\n" +
                "Правая нижняя точка прямоугольника: x:" + rightBottom.toString() + "\n" +
                "Ширина прямоугольника: " + String.format("%.2f", width) + "\n" +
                "Высота прямоугольника: " + String.format("%.2f", height) + "\n";
    }

    @Override
    public void draw(ICanvas canvas) {
        Point rightTop = new Point(rightBottom.x, leftTop.y);
        Point leftBottom = new Point(leftTop.x, rightBottom.y);
        canvas.drawLine(leftTop, rightTop, outlineColor);
        canvas.drawLine(rightTop, rightBottom, outlineColor);
        canvas.drawLine(rightBottom, leftBottom, outlineColor);
        canvas.drawLine(leftBottom, leftTop, outlineColor);
        canvas.fillPolygon(Arrays.asList(leftTop, rightTop, rightBottom, leftBottom), fillColor);
    }
}
