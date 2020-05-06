package lab4.shapes;

import lab4.shapes.MockCanvas;
import lab4.shapes.common.Point;
import lab4.shapes.common.shape.Triangle;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    public final double DELTA = 5e-5;
    private Point vertex1 = new Point(200, 200);
    private Point vertex2 = new Point(400, 300);
    private Point vertex3 = new Point(300, 400);
    private Color color = new Color(0, 0, 0);
    private Color fill = new Color(29, 141, 215);
    private Triangle circle = new Triangle(vertex1, vertex2, vertex3, color, fill);

    @Test
    public void getArea() {
        double expected = 14999.99999;
        assertEquals(circle.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        double expected = 588.63495;
        assertEquals(circle.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Треугольник:\n" +
                "Площадь фигуры: 15000,00\n" +
                "Периметр фигуры: 588,63\n" +
                "Цвет обводки: #000000\n" +
                "Цвет заливки: #1d8dd7\n" +
                "Первая точка треугольника: " + vertex1.toString() + "\n" +
                "Вторая точка треугольника: " + vertex2.toString() + "\n" +
                "Третья точка треугольника: " + vertex3.toString() + "\n";
        assertEquals(circle.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        circle.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<polygonFill points='point0:`x:200,00 y:200,00` point1:`x:400,00 y:300,00` point2:`x:300,00 y:400,00` ' fill='#1d8dd7' />");
        expected.add("<line from='x:200,00 y:200,00' to='x:400,00 y:300,00' color='#000000' />");
        expected.add("<line from='x:400,00 y:300,00' to='x:300,00 y:400,00' color='#000000' />");
        expected.add("<line from='x:300,00 y:400,00' to='x:200,00 y:200,00' color='#000000' />");
        assertEquals(canvas.getOut(), expected);
    }
}
