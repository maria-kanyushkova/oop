package lab4.figure;

import lab4.figure.common.Color;
import lab4.figure.common.FigureType;
import lab4.figure.common.IShape;
import lab4.figure.common.Point;
import lab4.figure.common.shape.Circle;
import lab4.figure.common.shape.Line;
import lab4.figure.common.shape.Rectangle;
import lab4.figure.common.shape.Triangle;

import java.util.LinkedHashMap;
import java.util.Map;

public class Controller {
    private Map<FigureType, IShape> figures = new LinkedHashMap<>();

    private static boolean isNumericString(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static boolean isHexNumber(String strNum) {
        try {
            Integer.parseInt(strNum, 16);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static Point isPoint(String pointX, String pointY) throws Exception {
        if (!isNumericString(pointX) || !isNumericString(pointY)) {
            throw new Exception("Значение не является числом");
        }
        return new Point(Double.parseDouble(pointX), Double.parseDouble(pointY));
    }

    private static Color isColor(String color) throws Exception {
        if (color.length() != 7 || !isHexNumber(color.substring(1, 7))) {
            throw new Exception("Значение не является hex цветом");
        }
        return new Color(color);
    }

    private static double isNumber(String number) throws Exception {
        if (!isNumericString(number)) {
            throw new Exception("Значение не является числом");
        }
        return Double.parseDouble(number);
    }

    public String getInfo(IShape figure) {
        if (figure == null) {
            return "";
        }
        return "Площадь фигуры: " + String.format("%.2f", figure.getArea()) + "\n" +
                "Периметр фигуры: " + String.format("%.2f", figure.getPerimeter()) + "\n" +
                "Цвет обводки: " + figure.getOutlineColor().toStringHex() + "\n" +
                figure.toString();
    }

    public String getInfoAboutFigureWithMaxArea() {
        IShape figure = null;
        for (Map.Entry<FigureType, IShape> shape : figures.entrySet()) {
            if (figure != null) {
                if (figure.getArea() < shape.getValue().getArea()) {
                    figure = shape.getValue();
                }
            }
        }
        return getInfo(figure);
    }

    public String getInfoAboutFigureWithMinPerimeter() {
        IShape figure = null;
        for (Map.Entry<FigureType, IShape> shape : figures.entrySet()) {
            if (figure != null) {
                if (figure.getPerimeter() > shape.getValue().getPerimeter()) {
                    figure = shape.getValue();
                }
            }
        }
        return getInfo(figure);
    }

    public void defineLine(String[] params) throws Exception {
        Point start = isPoint(params[0], params[1]);
        Point end = isPoint(params[2], params[3]);
        Color outlineColor = isColor(params[4]);
        figures.put(FigureType.LINE, new Line(start, end, outlineColor));
    }

    public void defineTriangle(String[] params) throws Exception {
        Point vertex1 = isPoint(params[0], params[1]);
        Point vertex2 = isPoint(params[2], params[3]);
        Point vertex3 = isPoint(params[4], params[5]);
        Color outlineColor = isColor(params[6]);
        Color fillColor = isColor(params[7]);
        figures.put(FigureType.TRIANGLE, new Triangle(vertex1, vertex2, vertex3, outlineColor, fillColor));
    }

    public void defineRectangle(String[] params) throws Exception {
        Point leftTop = isPoint(params[0], params[1]);
        double width = isNumber(params[2]);
        double height = isNumber(params[3]);
        Color outlineColor = isColor(params[4]);
        Color fillColor = isColor(params[5]);
        figures.put(FigureType.RECTANGLE, new Rectangle(leftTop, width, height, outlineColor, fillColor));
    }

    public void defineCircle(String[] params) throws Exception {
        Point center = isPoint(params[0], params[1]);
        double radius = isNumber(params[2]);
        Color outlineColor = isColor(params[3]);
        Color fillColor = isColor(params[4]);
        figures.put(FigureType.CIRCLE, new Circle(center, radius, outlineColor, fillColor));
    }
}
