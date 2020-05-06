package lab4.shapes;

import lab4.shapes.MockCanvas;
import lab4.shapes.common.Point;
import lab4.shapes.common.shape.Rectangle;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RectangleTest {
    public final double DELTA = 5e-5;
    private Point leftTop = new Point(200, 200);
    private double width = 150;
    private double height = 100;
    private Color color = new Color(0, 0, 0);
    private Color fill = new Color(29, 141, 215);
    private Rectangle rectangle = new Rectangle(leftTop, width, height, color, fill);

    @Test
    public void getArea() {
        double expected = 15000.00;
        assertEquals(rectangle.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        double expected = 500.00;
        assertEquals(rectangle.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Прямоугольник:\n" +
                "Площадь фигуры: 15000,00\n" +
                "Периметр фигуры: 500,00\n" +
                "Цвет обводки: #000000\n" +
                "Цвет заливки: #1d8dd7\n" +
                "Левая верхняя точка прямоугольника: x:x:200,00 y:200,00\n" +
                "Правая нижняя точка прямоугольника: x:x:350,00 y:300,00\n" +
                "Ширина прямоугольника: 150,00\n" +
                "Высота прямоугольника: 100,00\n";
        assertEquals(rectangle.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        rectangle.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<polygonFill points='point0:`x:200,00 y:200,00` point1:`x:350,00 y:200,00` point2:`x:350,00 y:300,00` point3:`x:200,00 y:300,00` ' fill='#1d8dd7' />");
        expected.add("<line from='x:200,00 y:200,00' to='x:350,00 y:200,00' color='#000000' />");
        expected.add("<line from='x:350,00 y:200,00' to='x:350,00 y:300,00' color='#000000' />");
        expected.add("<line from='x:350,00 y:300,00' to='x:200,00 y:300,00' color='#000000' />");
        expected.add("<line from='x:200,00 y:300,00' to='x:200,00 y:200,00' color='#000000' />");
        assertEquals(canvas.getOut(), expected);
    }
}
