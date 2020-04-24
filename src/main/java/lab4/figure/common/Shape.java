package lab4.figure.common;

public abstract class Shape implements IShape {
    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract Color getOutlineColor();

    public String toString() {
        return "Площадь фигуры: " + String.format("%.2f", getArea()) + "\n" +
                "Периметр фигуры: " + String.format("%.2f", getPerimeter()) + "\n" +
                "Цвет обводки: " + getOutlineColor().toStringHex() + "\n";
    }
}
