package lab3.car;

import java.time.temporal.ValueRange;

public class Controller {
    private final ValueRange GEAR_RANGE = ValueRange.of(-1, 5);
    private final ValueRange SPEED_RANGE = ValueRange.of(0, 150);
    private final Car car;

    Controller(Car car) {
        this.car = car;
    }

    public void getInfo() {
        String message = "";
        message += "Двигатель: " + (car.isEngineTurnedOn() ? "включен" : "выключен") + "\n";
        message += "Направление движения: ";
        switch (car.getDirection()) {
            case STAND:
                message += "стоит";
                break;
            case FORWARD:
                message += "вперед";
                break;
            case BACK:
                message += "назад";
                break;
        }
        message += "\n";
        message += "Текущая скорость машины: " + car.getSpeed() + "\n";
        message += "Текущая передача машины: " + car.getGear().toNumber();
        System.out.println(message);
    }

    public void engineOn() {
        String message = car.turnOnEngine() ? "Двигатель включился" : "Двигатель не может быть включён повторно";
        System.out.println(message);
    }

    public void engineOff() {
        String message = "";
        if (car.turnOffEngine()) {
            message = "Двигатель выключился";
        } else if (!car.isEngineTurnedOn()) {
            message = "Двигатель не может быть выключен, так как он выключен";
        } else {
            message = "Двигатель не может быть выключен, так как машина находится в движении";
        }
        System.out.println(message);
    }

    public void setGear(String value) {
        // todo: возможно дублирование части кода с setSpeed
        if (!isNumeric(value)) {
            System.out.println("Значение не является числом");
            return;
        }
        int gear = Integer.parseInt(value);
        if (!GEAR_RANGE.isValidValue(gear)) {
            System.out.println("Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5");
            return;
        }
        Gear bufferGear = Gear.values()[gear + 1];
        String message = "";
        if (car.setGear(bufferGear)) {
            message = "Передача переключилась на " + bufferGear.toNumber();
        } else if (!car.isEngineTurnedOn()) {
            message = "У машины с выключенным двигателем можно поставить только нейтральную передачу";
        } else {
            message = "Текущая скорость машины не находится в диапазоне выбранной передачи";
        }
        System.out.println(message);
    }

    public void setSpeed(String value) {
        if (!isNumeric(value)) {
            System.out.println("Значение не является числом");
            return;
        }
        int speed = Integer.parseInt(value);
        if (!SPEED_RANGE.isValidValue(speed)) {
            System.out.println("Машина не поддерживает такую скорость. Диапазон допустимых скоростей от 0 до 150");
            return;
        }
        String message = "";
        if (car.setSpeed(speed)) {
            message = "Скорость переключилась на " + speed;
        } else if (!car.isEngineTurnedOn()) {
            message = "У выключенной машины нельзя изменить скорость";
        } else if (car.getGear() == Gear.NEUTRAL && speed > car.getSpeed()) {
            message = "На нейтральной передаче нельзя увеличивать скорость";
        } else {
            message = "Выбранная скорость не находится в диапазоне скоростей выбранной передачи";
        }
        System.out.println(message);
    }

    public static boolean isNumeric(String strNum) {
        try {
            double doubleValue = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
