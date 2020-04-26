package lab4.shapes;

import lab4.shapes.canvas.ICanvas;
import lab4.shapes.common.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MockCanvas implements ICanvas {
    private List<String> out = new ArrayList<>();

    @Override
    public void drawLine(Point from, Point to, Color outlineColor) {
        out.add("<line from='" + from.toString() + "' to='" + to.toString() + "' color='" + Utils.colorToString(outlineColor) + "' />");
    }

    @Override
    public void fillPolygon(List<Point> points, Color fillColor) {
        StringBuilder result = new StringBuilder("<polygonFill points='");
        for (int i = 0; i < points.size(); i++) {
            result
                    .append("point")
                    .append(i)
                    .append(":`")
                    .append(points.get(i).toString())
                    .append("` ");
        }
        result.append("' fill='").append(Utils.colorToString(fillColor)).append("' />");
        out.add(result.toString());
    }

    @Override
    public void drawCircle(Point center, double radius, Color outlineColor) {
        out.add("<circle center='" + center.toString() + "' radius='" + Utils.doubleToString(radius) + "' color='" + Utils.colorToString(outlineColor) + "' />");
    }

    @Override
    public void fillCircle(Point center, double radius, Color fillColor) {
        out.add("<circleFill center='" + center.toString() + "' radius='" + Utils.doubleToString(radius) + "' fill='" + Utils.colorToString(fillColor) + "' />");
    }

    public List<String> getOut() {
        return out;
    }
}
