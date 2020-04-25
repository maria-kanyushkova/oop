package lab4.shapes.common;

import lab4.shapes.Utils;

import java.awt.*;

public abstract class Shape implements IShape {
    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Color getOutlineColor();

    public String toString() {
        return "Площадь фигуры: " + String.format("%.2f", getArea()) + "\n" +
                "Периметр фигуры: " + String.format("%.2f", getPerimeter()) + "\n" +
                "Цвет обводки: " + Utils.colorToString(getOutlineColor()) + "\n";
    }
}
