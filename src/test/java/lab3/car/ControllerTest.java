package lab3.car;

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

    public void equals(String expected, String output) {
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                output.replace("Введите значение ", "").replace("\n", "").replace("\r", "")
        );
    }

    // info
    // 01 - проверка корректности вывода информации об автомобиле
    @Test
    public void shouldCorrectlyOutputCarInfo() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.getInfo();
        String expected = "Двигатель: выключенНаправление движения: стоитТекущая скорость машины: 0Текущая передача машины: 0";
        equals(expected, mock.toString());
    }

    // engineOn
    // 02 - проверка корректности включения автомобиля из выключенного состояния
    @Test
    public void shouldTurnOn() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        String expected = "Двигатель включился";
        equals(expected, mock.toString());
    }

    // 03 - проверка корректности включения автомобиля из включённого состояния
    @Test
    public void shouldNotTurnOnIfEnabled() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        controller.engineOn();
        String expected = "Двигатель не может быть включён повторно";
        equals(expected, mock.toString());
    }

    // engineOff
    // 04 - проверка корректности выключения автомобиля из включённого состояния
    @Test
    public void shouldTurnOff() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        controller.engineOff();
        String expected = "Двигатель выключился";
        equals(expected, mock.toString());
    }

    // 05 - проверка корректности выключения автомобиля из выключенного состояния
    @Test
    public void shouldNotTurnOnInVehicleOff() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOff();
        String expected = "Двигатель не может быть выключен, так как он выключен";
        equals(expected, mock.toString());
    }

    // 06 - проверка корректности выключения автомобиля из движущегося состояния
    @Test
    public void shouldNotTurnOffWhileVehicleIsMoving() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        setUp();
        controller.engineOff();
        String expected = "Двигатель не может быть выключен, так как машина находится в движении";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // setGear
    // 07 - проверка корректности переключения передачи автомобиля с 0 на 1 при 0 скорости
    @Test
    public void shouldShiftToFirstFromNeutralGear() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Передача переключилась на 1";
        equals(expected, mock.toString());
        System.setIn(original);

    }

    // 08 - проверка корректности переключения передачи автомобиля на 0 при выключенном двигателе
    @Test
    public void shouldShiftToNeutralGearInVehicleOff() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("0".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Передача переключилась на 0";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 09 - попытка переключить на не 0вую передачу при выключенном двигателе
    @Test
    public void shouldNotShiftToNotNeutralGearInVehicleOff() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 10 - попытка переключить на 2ую передачу при скорости 0
    @Test
    public void shouldNotShiftToSecondGearInZeroSpeed() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Текущая скорость машины не находится в диапазоне выбранной передачи";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 11 - попытка переключить на не существующую передачу -2 или 6
    @Test
    public void shouldNotShiftToNotExistingGear() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("6".getBytes());
        System.setIn(in);
        controller.setGear();
        String expected = "Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // setSpeed
    // 12 - проверка корректности переключения скорости на 1ой передаче
    @Test
    public void shouldShiftSpeedInFirstGear() {
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
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 13 - попытка переключить скорость у выключенной машины
    @Test
    public void shouldNotShiftSpeedInVehicleOff() {
        Car car = new Car();
        Controller controller = new Controller(car);
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "У выключенной машины нельзя изменить скорость";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 14 - попытка увеличить скорость на нейтральной передаче
    @Test
    public void shouldNotIncreaseSpeedInNeutralGear() {
        Car car = new Car();
        Controller controller = new Controller(car);
        controller.engineOn();
        setUp();
        ByteArrayInputStream in = new ByteArrayInputStream("15".getBytes());
        System.setIn(in);
        controller.setSpeed();
        String expected = "На нейтральной передаче нельзя увеличивать скорость";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 15 - попытка поставить скорость не из диапазона текущей передачи (для первой 31)
    @Test
    public void shouldNotSpeedInNotRangeSpeedThisGear() {
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
        String expected = "Выбранная скорость не находится в диапазоне скоростей выбранной передачи";
        equals(expected, mock.toString());
        System.setIn(original);
    }

    // 16 - попытка поставить неподдерживаему скорость (170)
    @Test
    public void shouldNotSetUnsupportedSpeed() {
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
        String expected = "Машина не поддерживает такую скорость. Диапазон допустимых скоростей от 0 до 150";
        equals(expected, mock.toString());
        System.setIn(original);
    }
}
