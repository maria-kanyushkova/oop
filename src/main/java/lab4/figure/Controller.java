package lab4.figure;

import lab4.figure.common.IShape;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<IShape> figures = new ArrayList<>();

    public String getInfoAboutFigureWithMaxArea() {
        double area = Double.MIN_VALUE;
        IShape figure = null;
        for (IShape shape : figures) {
            if (area < shape.getArea()) {
                area = shape.getArea();
                figure = shape;
            }
        }
        return figure != null ? figure.toString() : "";
    }

    public String getInfoAboutFigureWithMinPerimeter() {
        double perimeter = Double.MIN_VALUE;
        IShape figure = null;
        for (IShape shape : figures) {
            if (perimeter > shape.getPerimeter()) {
                perimeter = shape.getPerimeter();
                figure = shape;
            }
        }
        return figure != null ? figure.toString() : "";
    }

    public void appendShape(IShape shape) {
        figures.add(shape);
    }
}
