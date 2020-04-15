package lab3.car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {
    private Controller controller;
    private Exception exception;

    private static void equals(String expected, String output) {
        assertEquals(
                expected.replace("\n", "").replace("\r", ""),
                output.replace("\n", "").replace("\r", "")
        );
    }

    @BeforeEach
    public void init() {
        Car car = new Car();
        controller = new Controller(car);
    }

    @Nested
    @DisplayName("command - info")
    class CommandInfo {
        @Test
        @DisplayName("should correctly output car info")
        public void shouldCorrectlyOutputCarInfo() {
            String output = controller.getInfo();
            String expected = "Двигатель: выключен\nНаправление движения: стоит\nТекущая скорость машины: 0\nТекущая передача машины: 0";
            ControllerTest.equals(expected, output);
        }
    }

    @Nested
    @DisplayName("command - engineOn")
    class CommandEngineOn {
        @Test
        @DisplayName("should get message on turn on car")
        public void shouldTurnOn() throws Exception {
            String output = controller.engineOn();
            String expected = "Двигатель включился";
            ControllerTest.equals(expected, output);
        }

        @Test
        @DisplayName("should get message on turn on car repeatedly")
        public void shouldNotTurnOnIfCarIsEnabled() throws Exception {
            controller.engineOn();
            String expected = "Двигатель не может быть включён повторно";
            exception = assertThrows(Exception.class, () -> controller.engineOn());
            ControllerTest.equals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - engineOff")
    class CommandEngineOff {
        @Test
        @DisplayName("should get message on turn off car")
        public void shouldTurnOff() throws Exception {
            controller.engineOn();
            String expected = "Двигатель выключился";
            String output = controller.engineOff();
            ControllerTest.equals(expected, output);
        }

        @Test
        @DisplayName("should get message on turn off car repeatedly")
        public void shouldNotTurnOnInCarOff() {
            String expected = "Двигатель не может быть выключен, так как он выключен";
            exception = assertThrows(Exception.class, () -> controller.engineOff());
            ControllerTest.equals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("should get message on turn off moving car")
        public void shouldNotTurnOffWhileCarIsMoving() throws Exception {
            controller.engineOn();
            controller.setGear(1);
            String expected = "Двигатель не может быть выключен, так как машина находится в движении";
            exception = assertThrows(Exception.class, () -> controller.engineOff());
            ControllerTest.equals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - setGear")
    class CommandSetGear {
        @Test
        @DisplayName("should get message on correct set gear")
        public void shouldSwitchOnFirstGear() throws Exception {
            controller.engineOn();
            String output = controller.setGear(1);
            String expected = "Передача переключилась на 1";
            ControllerTest.equals(expected, output);
        }

        @Test
        @DisplayName("should get message on set not neutral gear when car is off")
        public void shouldNotSwitchToNotNeutralGearInCarOff() {
            String expected = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
            exception = assertThrows(Exception.class, () -> controller.setGear(1));
            ControllerTest.equals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("should get message on try set second gear")
        public void shouldNotSwitchToSecondGearInZeroSpeed() throws Exception {
            controller.engineOn();
            String expected = "Текущая скорость машины не находится в диапазоне выбранной передачи";
            exception = assertThrows(Exception.class, () -> controller.setGear(2));
            ControllerTest.equals(expected, exception.getMessage());

        }

        @Test
        @DisplayName("should get message on try set not existing gear")
        public void shouldNotSwitchToNotExistingGear() throws Exception {
            controller.engineOn();
            String expected = "Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5";
            exception = assertThrows(Exception.class, () -> controller.setGear(6));
            ControllerTest.equals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - setSpeed")
    class CommandSetSpeed {
        @Test
        @DisplayName("should get message on change speed for first gear")
        public void shouldSwitchSpeedInFirstGear() throws Exception {
            controller.engineOn();
            controller.setGear(1);
            String output = controller.setSpeed(15);
            String expected = "Скорость переключилась на 15";
            ControllerTest.equals(expected, output);
        }

        @Test
        @DisplayName("should get message on try change speed if car is off")
        public void shouldNotSwitchSpeedInCarOff() {
            String expected = "У выключенной машины нельзя изменить скорость";
            exception = assertThrows(Exception.class, () -> controller.setSpeed(15));
            ControllerTest.equals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("should get message on try increase speed for neutral gear")
        public void shouldNotIncreaseSpeedInNeutralGear() throws Exception {
            controller.engineOn();
            String expected = "На нейтральной передаче нельзя увеличивать скорость";
            exception = assertThrows(Exception.class, () -> controller.setSpeed(15));
            ControllerTest.equals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("should get message on try change speed if speed is not range speed for current gear")
        public void shouldNotSpeedInNotRangeSpeedThisGear() throws Exception {
            controller.engineOn();
            controller.setGear(1);
            String expected = "Выбранная скорость не находится в диапазоне скоростей выбранной передачи";
            exception = assertThrows(Exception.class, () -> controller.setSpeed(31));
            ControllerTest.equals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("should get message on try set unsupported speed")
        public void shouldNotSetUnsupportedSpeed() throws Exception {
            controller.engineOn();
            controller.setGear(1);
            String expected = "Машина не поддерживает такую скорость. Диапазон допустимых скоростей от 0 до 150";
            exception = assertThrows(Exception.class, () -> controller.setSpeed(151));
            ControllerTest.equals(expected, exception.getMessage());
        }
    }
}
