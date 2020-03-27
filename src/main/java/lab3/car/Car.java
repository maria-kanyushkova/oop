package lab3.car;

public class Car {
    private boolean statusOfCar = false;
    private Directions direction = Directions.STAND;
    private int currentSpeed = 0;
    private int currentGear = 0;

    public boolean turnOnEngine() {
        if (!statusOfCar) {
            statusOfCar = true;
        }
        return statusOfCar;
    }

    public boolean turnOffEngine() {
        if (!statusOfCar) {
            return statusOfCar;
        }
        if (statusOfCar && direction == Directions.STAND && currentGear == 0) {
            statusOfCar = false;
        }
        return statusOfCar;
    }

    public boolean setGear(int gear) {
        Gear gearBuffer = getGearInfo(gear);
        if (gearBuffer.inRange(currentSpeed)) {
            currentGear = gear;
            return true;
        }
        return false;
    }

    public boolean setSpeed(int speed) {
        Gear gearBuffer = getGearInfo(currentGear);
        if (gearBuffer.inRange(speed) || (currentSpeed >= speed && gearBuffer == Gear.NEUTRAL)) {
            currentSpeed = speed;
            return true;
        }
        return false;
    }

    private void changeDirection(int nextTransmission) {
        if (currentSpeed == 0) {
            direction = Directions.STAND;
        }
        if ((currentGear > 0 || nextTransmission == 0) && currentSpeed > 0) {
            direction = Directions.FORWARD;
        }
        if ((currentGear < 0 || nextTransmission == 0) && currentSpeed > 0) {
            direction = Directions.BACK;
        }
    }

    private Gear getGearInfo(int gear) {
        switch(gear) {
            case -1:
                return Gear.REVERSE_GEAR;
            case 0:
                return Gear.NEUTRAL;
            case 1:
                return Gear.FIRST_GEAR;
            case 2:
                return Gear.SECOND_GEAR;
            case 3:
                return Gear.THIRD_GEAR;
            case 4:
                return Gear.FOURTH_GEAR;
            case 5:
                return Gear.FIFTH_GEAR;
            default:
                return null;
        }
    }
}
