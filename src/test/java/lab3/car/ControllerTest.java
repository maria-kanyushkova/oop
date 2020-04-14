package lab3.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
    private ByteArrayOutputStream mock = new ByteArrayOutputStream();
    private Controller controller;

    private static void equals(String expected, String output) {
        assertEquals(
            expected.replace("\n", "").replace("\r", ""),
            output.replace("\n", "").replace("\r", "")
        );
    }

    private void clearConsole() {
        mock = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mock));
    }

    @BeforeEach
    public void init() {
        Car car = new Car();
        controller = new Controller(car);
        clearConsole();
    }

    @Nested
    @DisplayName("command - info")
    class CommandInfo {
        @Test
        @DisplayName("should correctly output car info")
        public void shouldCorrectlyOutputCarInfo() {
            controller.getInfo();
            String expected = "Двигатель: выключен\nНаправление движения: стоит\nТекущая скорость машины: 0\nТекущая передача машины: 0";
            ControllerTest.equals(expected, mock.toString());
        }
    }

    @Nested
    @DisplayName("command - engineOn")
    class CommandEngineOn {
        @Test
        @DisplayName("should get message on turn on car")
        public void shouldTurnOn() {
            controller.engineOn();
            String expected = "Двигатель включился";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on turn on car repeatedly")
        public void shouldNotTurnOnIfCarIsEnabled() {
            controller.engineOn();
            clearConsole();
            controller.engineOn();
            String expected = "Двигатель не может быть включён повторно";
            ControllerTest.equals(expected, mock.toString());
        }
    }

    @Nested
    @DisplayName("command - engineOff")
    class CommandEngineOff {
        @Test
        @DisplayName("should get message on turn off car")
        public void shouldTurnOff() {
            controller.engineOn();
            clearConsole();
            controller.engineOff();
            String expected = "Двигатель выключился";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on turn off car repeatedly")
        public void shouldNotTurnOnInCarOff() {
            controller.engineOff();
            String expected = "Двигатель не может быть выключен, так как он выключен";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on turn off moving car")
        public void shouldNotTurnOffWhileCarIsMoving() {
            controller.engineOn();
            controller.setGear("1");
            clearConsole();
            controller.engineOff();
            String expected = "Двигатель не может быть выключен, так как машина находится в движении";
            ControllerTest.equals(expected, mock.toString());
        }
    }

    @Nested
    @DisplayName("command - setGear")
    class CommandSetGear {
        @Test
        @DisplayName("should get message on correct set gear")
        public void shouldSwitchOnFirstGear() {
            controller.engineOn();
            clearConsole();
            controller.setGear("1");
            String expected = "Передача переключилась на 1";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on set not neutral gear when car is off")
        public void shouldNotSwitchToNotNeutralGearInCarOff() {
            controller.setGear("1");
            String expected = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on try set second gear")
        public void shouldNotSwitchToSecondGearInZeroSpeed() {
            controller.engineOn();
            clearConsole();
            controller.setGear("2");
            String expected = "Текущая скорость машины не находится в диапазоне выбранной передачи";
            ControllerTest.equals(expected, mock.toString());
        }
        
        @Test
        @DisplayName("should get message on try set not existing gear")
        public void shouldNotSwitchToNotExistingGear() {
            controller.engineOn();
            clearConsole();
            controller.setGear("6");
            String expected = "Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5";
            ControllerTest.equals(expected, mock.toString());
        }
    }

    @Nested
    @DisplayName("command - setSpeed")
    class CommandSetSpeed {
        @Test
        @DisplayName("should get message on change speed for first gear")
        public void shouldSwitchSpeedInFirstGear() {
            controller.engineOn();
            controller.setGear("1");
            clearConsole();
            controller.setSpeed("15");
            String expected = "Скорость переключилась на 15";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on try change speed if car is off")
        public void shouldNotSwitchSpeedInCarOff() {
            controller.setSpeed("15");
            String expected = "У выключенной машины нельзя изменить скорость";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on try increase speed for neutral gear")
        public void shouldNotIncreaseSpeedInNeutralGear() {
            controller.engineOn();
            clearConsole();
            controller.setSpeed("15");
            String expected = "На нейтральной передаче нельзя увеличивать скорость";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on try change speed if speed is not range speed for current gear")
        public void shouldNotSpeedInNotRangeSpeedThisGear() {
            controller.engineOn();
            controller.setGear("1");
            clearConsole();
            controller.setSpeed("31");
            String expected = "Выбранная скорость не находится в диапазоне скоростей выбранной передачи";
            ControllerTest.equals(expected, mock.toString());
        }

        @Test
        @DisplayName("should get message on try set unsupported speed")
        public void shouldNotSetUnsupportedSpeed() {
            controller.engineOn();
            controller.setGear("1");
            clearConsole();
            controller.setSpeed("151");
            String expected = "Машина не поддерживает такую скорость. Диапазон допустимых скоростей от 0 до 150";
            ControllerTest.equals(expected, mock.toString());
        }
    }
}
