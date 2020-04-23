package lab4.figure;

import lab4.figure.common.IShape;

public class Controller {
    public String getInfo(IShape figure) {
        return "Площадь фигуры: " + String.format("%.2f", figure.getArea()) + "\n" +
                "Периметр фигуры: " + String.format("%.2f", figure.getPerimeter()) + "\n" +
                "Цвет обводки: " + figure.getOutlineColor().toStringHex() + "\n" +
                figure.toString();
    }

    public String getInfoAboutFigureWithMaxArea() {
        return "";
    }

    public String getInfoAboutFigureWithMinPerimeter() {
        return "";
    }
}
