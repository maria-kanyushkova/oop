package lab3.car;

import lab2.mini_dictionary.Dictionary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    final InputStream original = System.in;
    private ByteArrayOutputStream mock = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        mock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mock));
    }

    // info
    // 01 - проверка корректности вывода информации об автомобиле
    @Test
    public void test01() {
        String expected = "";

    }

    // engineOn
    // 02 - проверка корректности включения автомобиля из выключенного состояния
    @Test
    public void test02() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        String expected = "Двигатель включился";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
    }

    // 03 - проверка корректности включения автомобиля из включённого состояния
    @Test
    public void test03() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        controller.engineOn();
        String expected = "Двигатель не может быть включён повторно";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
    }

    // engineOff
    // 04 - проверка корректности выключения автомобиля из включённого состояния
    @Test
    public void test04() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        controller.engineOff();
        String expected = "Двигатель выключился";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
    }

    // 05 - проверка корректности выключения автомобиля из выключенного состояния
    @Test
    public void test05() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOff();
        String expected = "Двигатель не может быть выключен, так как он выключен";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
    }

    // 06 - проверка корректности выключения автомобиля из движущегося состояния
    @Test
    public void test06() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        setUp();
        controller.engineOff();
        String expected = "Двигатель не может быть выключен, так как машина находится в движении";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("\n", "").replace("\r", "")
        );
        System.setIn(original);
    }

    // setGear
    // 07 - проверка корректности переключения передачи автомобиля с 0 на 1 при 0 скорости
    @Test
    public void test07() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Передача переключилась на 1";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);

    }

    // 08 - проверка корректности переключения передачи автомобиля на 0 при выключенном двигателе
    @Test
    public void test08() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Передача переключилась на 0";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 09 - попытка переключить на не 0вую передачу при выключенном двигателе
    @Test
    public void test09() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 10 - попытка переключить на 2ую передачу при скорости 0
    @Test
    public void test10() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Текущая скорость машины не находится в диапозоне выбранной передачи";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 11 - попытка переключить на не существующую передачу -2 или 6
    @Test
    public void test11() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("6".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // setSpeed
    // 12 - проверка корректности переключения скорости на 1ой передаче
    @Test
    public void test12() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        setUp();
        in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "Скорость переключилась на 15";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 13 - попытка переключить скорость у выключенной машины
    @Test
    public void test13() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "У выключенной машины нельзя изменить скорость";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 14 - попытка увеличить скорость на нейтральной передаче
    @Test
    public void test14() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "На нейтральной передаче нельзя увеличивать скорость";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 15 - попытка поставить скорость не из диапозона текущей передачи (для первой 31)
    @Test
    public void test15() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        setUp();
        in = new ByteArrayInputStream("31".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "Выбранная скорость не лежит в диапозоне скоростей выбранной передачи";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }

    // 16 - попытка поставить неподдерживаему скорость (170)
    @Test
    public void test16() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        setUp();
        in = new ByteArrayInputStream("151".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "Машина не поддерживает такую скорость. Диапозон допустимых скоростей от 0 до 150";
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                mock.toString().replace("Введите значение ", "").replace("\r\n", "")
        );
        System.setIn(original);
    }
}
