package lab4.shapes.common;

import lab4.shapes.Utils;

import java.awt.*;

public abstract class Shape implements IShape {
    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Color getOutlineColor();

    public String toString() {
        return "Площадь фигуры: " + Utils.doubleToString(getArea()) + "\n" +
                "Периметр фигуры: " + Utils.doubleToString(getPerimeter()) + "\n" +
                "Цвет обводки: " + Utils.colorToString(getOutlineColor()) + "\n";
    }
}
