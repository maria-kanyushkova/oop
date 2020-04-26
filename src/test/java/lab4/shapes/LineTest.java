package lab4.shapes;

import lab4.shapes.MockCanvas;
import lab4.shapes.common.Point;
import lab4.shapes.common.shape.Line;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LineTest {
    public final double DELTA = 5e-5;
    private Point start = new Point(10, 10);
    private Point end = new Point(100, 100);
    private Color color = new Color(0, 0, 0);
    private Line line = new Line(start, end, color);

    @Test
    public void getArea() {
        double expected = 0;
        assertEquals(line.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        double expected = 127.27922;
        assertEquals(line.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Линия:\n" +
                "Площадь фигуры: 0,00\n" +
                "Периметр фигуры: 127,28\n" +
                "Цвет обводки: #000000\n" +
                "Точка начала отрезка: " + start.toString() + "\n" +
                "Точка конца отрезка: " + end.toString() + "\n";
        assertEquals(line.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        line.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<line from='x:10,00 y:10,00' to='x:100,00 y:100,00' color='#000000' />");
        assertEquals(canvas.getOut(), expected);
    }
}