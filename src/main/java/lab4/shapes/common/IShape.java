package lab4.shapes.common;

import lab4.shapes.canvas.ICanvasDrawable;

import java.awt.*;

public interface IShape extends ICanvasDrawable {
    double getArea();

    double getPerimeter();

    Color getOutlineColor();

    String toString();
}
