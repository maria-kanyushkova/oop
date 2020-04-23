package lab4.figure.shape;

import lab4.figure.Color;
import lab4.figure.ISolidShape;
import lab4.figure.Point;

public class Rectangle implements ISolidShape {
    private Point leftTop;
    private Point rightBottom;
    private double height;
    private double width;
    private Color outlineColor;
    private Color fillColor;

    Rectangle(double left, double top, double width, double height, Color outlineColor, Color fillColor) {
        this.height = height;
        this.width = width;
        this.leftTop = new Point(left, top);
        this.rightBottom = new Point(left + width, top + height);
        this.outlineColor = outlineColor;
        this.fillColor = fillColor;
    }

    Point getLeftTop() {
        return leftTop;
    }

    Point getRightBottom() {
        return rightBottom;
    }

    double getHeight() {
        return height;
    }

    double getWidth() {
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
        return "Цвет заливки: " + fillColor.toStringHex() + "\n" +
                "Левая верхняя точка прямоугольника: x:" + leftTop.toString() + "\n" +
                "Правая нижняя точка прямоугольника: x:" + rightBottom.toString() + "\n" +
                "Ширина прямоугольника: " + String.format("%.2f", width) + "\n"+
                "Высота прямоугольника: " + String.format("%.2f", height) + "\n";
    }
}
