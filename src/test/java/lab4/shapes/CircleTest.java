package lab4.shapes;

import lab4.shapes.MockCanvas;
import lab4.shapes.common.Point;
import lab4.shapes.common.shape.Circle;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CircleTest {
    public final double DELTA = 5e-5;
    private Point center = new Point(100, 100);
    private double radius = 25.25;
    private Color color = new Color(0, 0, 0);
    private Color fill = new Color(29, 141, 215);
    private Circle circle = new Circle(center, radius, color, fill);

    @Test
    public void getArea() {
        double expected = 2001.94625;
        assertEquals(circle.getArea(), expected, DELTA);
    }

    @Test
    public void getPerimeter() {
        double expected = 158.57;
        assertEquals(circle.getPerimeter(), expected, DELTA);
    }

    @Test
    public void toStringTest() {
        String expected = "Круг:\n" +
                "Площадь фигуры: 2001,95\n" +
                "Периметр фигуры: 158,57\n" +
                "Цвет обводки: #000000\n" +
                "Цвет заливки: #1d8dd7\n" +
                "Точка центра окружности: " + center.toString() + "\n" +
                "Радиус окружности: 25,25\n";
        assertEquals(circle.toString(), expected);
    }

    @Test
    public void draw() {
        MockCanvas canvas = new MockCanvas();
        circle.draw(canvas);
        List<String> expected = new ArrayList<>();
        expected.add("<circle center='x:100,00 y:100,00' radius='25,25' color='#000000' />");
        expected.add("<circleFill center='x:100,00 y:100,00' radius='25,25' fill='#1d8dd7' />");
        assertEquals(canvas.getOut(), expected);
    }
}
