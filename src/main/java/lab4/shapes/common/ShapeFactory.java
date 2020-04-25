package lab4.shapes.common;

import lab4.shapes.common.shape.Circle;
import lab4.shapes.common.shape.Line;
import lab4.shapes.common.shape.Rectangle;
import lab4.shapes.common.shape.Triangle;

import java.awt.*;

public class ShapeFactory {
    public static IShape createLine(Point start, Point end, Color outlineColor) {
        return new Line(start, end, outlineColor);
    }

    public static IShape createTriangle(Point vertex1, Point vertex2, Point vertex3, Color outlineColor, Color fillColor) {
        return new Triangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
    }

    public static IShape createRectangle(Point leftTop, double width, double height, Color outlineColor, Color fillColor) {
        return new Rectangle(leftTop, width, height, outlineColor, fillColor);
    }

    public static IShape createCircle(Point center, double radius, Color outlineColor, Color fillColor) {
        return new Circle(center, radius, outlineColor, fillColor);
    }
}