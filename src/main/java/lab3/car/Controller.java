package lab3.car;

import java.util.Scanner;

public class Controller {
    private final Car car;

    Controller(Car car) {
        this.car = car;
    }

    public String getInfo() {
        String info = "";
        info += "Двигатель: " + (car.getEnable() ? "включен" : "выключен") + "\n";
        info += "Направление движения: ";
        switch (car.getDirection()) {
            case STAND:
                info += "стоит";
                break;
            case FORWARD:
                info += "движение вперед";
                break;
            case BACK:
                info += "движение назад";
                break;
        }
        info += "\n";
        info += "Текущая скорось машины: " + car.getSpeed() + "\n";
        info += "Текущая передача машины: " + car.getGear().toNumber() + "\n";
        return info;
    }

    public String engineOn() {
        return (car.turnOnEngine() ? "Включился" : "Проблемка") + "\n";
    }

    public String engineOff() {
        return (car.turnOffEngine() ? "Выключился" : "Проблемка") + "\n";
    }

    public String setGear(int gear) {
        Gear bufferGear = Gear.values()[gear];
        return (car.setGear(bufferGear) ? "Передача переключилась на " + bufferGear.toNumber() : "Проблемка") + "\n";
    }

    public String setSpeed(int speed) {
        return (car.setSpeed(speed) ? "Скорость переключилась на " + speed : "Проблемка") + "\n";
    }

    public static String readFromConsole() {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            return scanner.nextLine();
        }
    }
}
