package lab3.car;

import java.time.temporal.ValueRange;
import java.util.Scanner;

public class Controller {
    private final ValueRange GEAR_RANGE = ValueRange.of(-1, 5);
    private final ValueRange SPEED_RANGE = ValueRange.of(0, 150);
    private final Car car;

    Controller(Car car) {
        this.car = car;
    }

    public void getInfo() {
        String message = "";
        message += "Двигатель: " + (car.getEnable() ? "включен" : "выключен") + "\n";
        message += "Направление движения: ";
        switch (car.getDirection()) {
            case STAND:
                message += "стоит";
                break;
            case FORWARD:
                message += "движение вперед";
                break;
            case BACK:
                message += "движение назад";
                break;
        }
        message += "\n";
        message += "Текущая скорось машины: " + car.getSpeed() + "\n";
        message += "Текущая передача машины: " + car.getGear().toNumber();
        System.out.println(message);
    }

    public void engineOn() {
        String message = "";
        boolean result = car.turnOnEngine();
        if (result) {
            message = "Двигатель включился";
        } else {
            message = "Двигатель не может быть включён повторно";
        }
        System.out.println(message);
    }

    public void engineOff() {
        String message = "";
        boolean result = car.turnOffEngine();
        if (result) {
            message = "Двигатель выключился";
        } else if (!car.getEnable()) {
            message = "Двигатель не может быть выключен, так как он выключен";
        } else {
            message = "Двигатель не может быть выключен, так как машина находится в движении";
        }
        System.out.println(message);
    }

    public void setGear() {
        System.out.print("Введите значение ");
        int gear = Integer.parseInt(readFromConsole());
        if (!GEAR_RANGE.isValidValue(gear)) {
            System.out.println("Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5");
            return;
        }
        Gear bufferGear = Gear.values()[gear + 1];
        String message = "";
        boolean result = car.setGear(bufferGear);
        if (result) {
            message = "Передача переключилась на " + bufferGear.toNumber();
        } else if (!car.getEnable()) {
            message = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
        } else {
            message = "Текущая скорость машины не находится в диапозоне выбранной передачи";
        }
        System.out.println(message);
    }

    public void setSpeed() {
        System.out.print("Введите значение ");
        int speed = Integer.parseInt(readFromConsole());
        if (!SPEED_RANGE.isValidValue(speed)) {
            System.out.println("Машина не поддерживает такую скорость. Диапозон допустимых скоростей от 0 до 150");
            return;
        }
        String message = "";
        boolean result = car.setSpeed(speed);
        if (result) {
            message = "Скорость переключилась на " + speed;
        } else if (!car.getEnable()) {
            message = "У выключенной машины нельзя изменить скорость";
        } else if (car.getGear() == Gear.NEUTRAL && speed > car.getSpeed()) {
            message = "На нейтральной передаче нельзя увеличивать скорость";
        } else {
            message = "Выбранная скорость не лежит в диапозоне скоростей выбранной передачи";
        }
        System.out.println(message);
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
