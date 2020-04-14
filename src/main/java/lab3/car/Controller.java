package lab3.car;

import java.time.temporal.ValueRange;

public class Controller {
    private final ValueRange GEAR_RANGE = ValueRange.of(-1, 5);
    private final ValueRange SPEED_RANGE = ValueRange.of(0, 150);
    private final Car car;

    Controller(Car car) {
        this.car = car;
    }

    public String getInfo() {
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
        return message;
    }

    public String engineOn() throws Exception {
        String message = "";
        if (car.turnOnEngine()) {
            message = "Двигатель включился";
        } else {
            throw new Exception("Двигатель не может быть включён повторно");
        }
        return message;
    }

    public String engineOff() throws Exception {
        String message = "";
        if (car.turnOffEngine()) {
            message = "Двигатель выключился";
        } else if (!car.isEngineTurnedOn()) {
            throw new Exception("Двигатель не может быть выключен, так как он выключен");
        } else {
            throw new Exception("Двигатель не может быть выключен, так как машина находится в движении");
        }
        return message;
    }

    public String setGear(int gear) throws Exception {
        if (!GEAR_RANGE.isValidValue(gear)) {
            throw new Exception("Машина не поддерживает такую передачу. Допустимые передачи от -1 до 5");
        }
        Gear bufferGear = Gear.values()[gear + 1];
        String message = "";
        if (car.setGear(bufferGear)) {
            message = "Передача переключилась на " + bufferGear.toNumber();
        } else if (!car.isEngineTurnedOn()) {
            throw new Exception("У машины с выключенным двигателем можно поставить только нейтральную передачу");
        } else {
            throw new Exception("Текущая скорость машины не находится в диапазоне выбранной передачи");
        }
        return message;
    }

    public String setSpeed(int speed) throws Exception {
        if (!SPEED_RANGE.isValidValue(speed)) {
            throw new Exception("Машина не поддерживает такую скорость. Диапазон допустимых скоростей от 0 до 150");
        }
        String message = "";
        if (car.setSpeed(speed)) {
            message = "Скорость переключилась на " + speed;
        } else if (!car.isEngineTurnedOn()) {
            throw new Exception("У выключенной машины нельзя изменить скорость");
        } else if (car.getGear() == Gear.NEUTRAL && speed > car.getSpeed()) {
            throw new Exception("На нейтральной передаче нельзя увеличивать скорость");
        } else {
            throw new Exception("Выбранная скорость не находится в диапазоне скоростей выбранной передачи");
        }
        return message;
    }
}
