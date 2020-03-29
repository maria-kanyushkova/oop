package lab3.car;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

public class Car {
    private boolean isEnabled = false;
    private Directions direction = Directions.STAND;
    private int currentSpeed = 0;
    private Gear currentGear = Gear.NEUTRAL;
    private Map<Gear, ValueRange> gearInfo = new HashMap<>();

    Car() {
        gearInfo.put(Gear.REVERSE, ValueRange.of(0, 20));
        gearInfo.put(Gear.NEUTRAL, ValueRange.of(0, 150));
        gearInfo.put(Gear.FIRST, ValueRange.of(0, 30));
        gearInfo.put(Gear.SECOND, ValueRange.of(20, 50));
        gearInfo.put(Gear.THIRD, ValueRange.of(30, 60));
        gearInfo.put(Gear.FOURTH, ValueRange.of(40, 90));
        gearInfo.put(Gear.FIFTH, ValueRange.of(50, 150));
    }

    public boolean turnOnEngine() {
        if (!isEnabled) {
            isEnabled = true;
            return true;
        }
        return false;
    }

    public boolean turnOffEngine() {
        boolean isStandingStill = direction == Directions.STAND && currentGear == Gear.NEUTRAL && currentSpeed == 0;
        if (!isEnabled || !isStandingStill) {
            return false;
        }
        isEnabled = false;
        return false;
    }

    public boolean setGear(Gear gear) {
        if (!gearInfo.containsKey(gear) || gearInfo.get(gear).isValidValue(currentSpeed)) {
            return false;
        }
        currentGear = gear;
        return true;
    }

    public boolean setSpeed(int speed) {
        if (gearInfo.get(currentGear).isValidValue(speed) || (currentSpeed >= speed && currentGear == Gear.NEUTRAL)) {
            currentSpeed = speed;
            return true;
        }
        return false;
    }

    private void changeDirection(int nextGear) {
        if (currentSpeed == 0) {
            direction = Directions.STAND;
        }
        if ((currentGear.toNumber() > 0 || nextGear == 0) && currentSpeed > 0) {
            direction = Directions.FORWARD;
        }
        if ((currentGear.toNumber() < 0 || nextGear == 0) && currentSpeed > 0) {
            direction = Directions.BACK;
        }
    }
}
