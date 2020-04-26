package lab4.shapes;

import lab4.shapes.common.Point;
import lab4.shapes.common.shape.Circle;
import lab4.shapes.common.shape.Line;
import lab4.shapes.common.shape.Rectangle;
import lab4.shapes.common.shape.Triangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void init() {
        controller = new Controller();
    }

    @Nested
    @DisplayName("empty list of shape")
    class EmptyShapeList {
        @BeforeEach
        public void init() {
            assertTrue(controller.getShapes().isEmpty());
        }

        @Test
        @DisplayName("should not give info about shape with max area")
        public void getInfoShapeWithMaxArea() {
            String expected = "";
            assertEquals(controller.getInfoAboutFigureWithMaxArea(), expected);
        }

        @Test
        @DisplayName("should not give info about shape with min perimeter")
        public void getInfoShapeWithMinPerimeter() {
            String expected = "";
            assertEquals(controller.getInfoAboutFigureWithMinPerimeter(), expected);
        }
    }

    @Nested
    @DisplayName("have shapes")
    class NotEmptyShapeList {
        @BeforeEach
        public void init() {
            controller.appendShape(new Line(new Point(10, 10), new Point(100, 100), new Color(0, 0, 0)));
            controller.appendShape(new Circle(new Point(100, 100), 25.25, new Color(0, 0, 0), new Color(29, 141, 215)));
            controller.appendShape(new Triangle(new Point(200, 200), new Point(400, 300), new Point(300, 400), new Color(0, 0, 0), new Color(29, 141, 215)));
            controller.appendShape(new Rectangle(new Point(200, 200), 150, 100, new Color(0, 0, 0), new Color(29, 141, 215)));
        }

        @Test
        @DisplayName("should give info about shape with max area")
        public void getInfoShapeWithMaxArea() {
            String expected = "Прямоугольник:\n" +
                    "Площадь фигуры: 15000,00\n" +
                    "Периметр фигуры: 500,00\n" +
                    "Цвет обводки: #000000\n" +
                    "Цвет заливки: #1d8dd7\n" +
                    "Левая верхняя точка прямоугольника: x:x:200,00 y:200,00\n" +
                    "Правая нижняя точка прямоугольника: x:x:350,00 y:300,00\n" +
                    "Ширина прямоугольника: 150,00\n" +
                    "Высота прямоугольника: 100,00\n";
            assertEquals(controller.getInfoAboutFigureWithMaxArea(), expected);
        }

        @Test
        @DisplayName("should give info about shape with min perimeter")
        public void getInfoShapeWithMinPerimeter() {
            String expected = "Линия:\n" +
                    "Площадь фигуры: 0,00\n" +
                    "Периметр фигуры: 127,28\n" +
                    "Цвет обводки: #000000\n" +
                    "Точка начала отрезка: x:10,00 y:10,00\n" +
                    "Точка конца отрезка: x:100,00 y:100,00\n";
            assertEquals(controller.getInfoAboutFigureWithMinPerimeter(), expected);
        }
    }
}
