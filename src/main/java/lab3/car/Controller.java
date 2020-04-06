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
        String message = car.turnOnEngine() ? "Двигатель включился" : "Проблемка"; // TODO: описать проблему более явно
        System.out.println(message);
    }

    public void engineOff() {
        String message = car.turnOffEngine() ? "Двигатель выключился" : "Проблемка"; // TODO: описать проблему более явно
        System.out.println(message);
    }

    public void setGear() {
        System.out.print("Введите значение ");
        int gear = Integer.parseInt(readFromConsole());
        if (!GEAR_RANGE.isValidValue(gear)) {
            System.out.println("Машина не поддерживает такую передачу");
            return;
        }
        Gear bufferGear = Gear.values()[gear + 1];
        String message = car.setGear(bufferGear) ? "Передача переключилась на " + bufferGear.toNumber() : "Проблемка"; // TODO: описать проблему более явно
        System.out.println(message);
    }

    public void setSpeed() {
        System.out.print("Введите значение ");
        int speed = Integer.parseInt(readFromConsole());
        if (!SPEED_RANGE.isValidValue(speed)) {
            System.out.println("Машина не поддерживает такую скорость");
            return;
        }
        String message = car.setSpeed(speed) ? "Скорость переключилась на " + speed : "Проблемка"; // TODO: описать проблему более явно
        System.out.println(message);
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
