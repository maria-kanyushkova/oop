package lab3.car;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

public class Car {
    private boolean isEnabled = false;
    private int currentSpeed = 0;
    private Gear currentGear = Gear.NEUTRAL;
    private Map<Gear, ValueRange> gearInfo = new HashMap<>();

    Car() {
        gearInfo.put(Gear.REVERSE, ValueRange.of(-20, 0));
        gearInfo.put(Gear.NEUTRAL, ValueRange.of(-20, 150));
        gearInfo.put(Gear.FIRST, ValueRange.of(0, 30));
        gearInfo.put(Gear.SECOND, ValueRange.of(20, 50));
        gearInfo.put(Gear.THIRD, ValueRange.of(30, 60));
        gearInfo.put(Gear.FOURTH, ValueRange.of(40, 90));
        gearInfo.put(Gear.FIFTH, ValueRange.of(50, 150));
    }

    public boolean turnOnEngine() {
        if (isEnabled) {
            return false;
        }
        isEnabled = true;
        return true;
    }

    public boolean turnOffEngine() {
        boolean isStandingStill = currentGear == Gear.NEUTRAL && currentSpeed == 0;
        if (!isEnabled || !isStandingStill) {
            return false;
        }
        isEnabled = false;
        return true;
    }

    public boolean setGear(Gear gear) {
        int newSpeed = currentSpeed * getSighOfSpeed();
        boolean inRangeOfCurrentSpeed = gearInfo.get(gear).isValidValue(newSpeed);
        if ((isEnabled && !inRangeOfCurrentSpeed) || (!isEnabled && gear != Gear.NEUTRAL)) {
            return false;
        }
        currentGear = gear;
        return true;
    }

    public boolean setSpeed(int speed) {
        int newSpeed = speed * getSighOfSpeed();
        boolean inRangeSpeedOfCurrentGear = gearInfo.get(currentGear).isValidValue(newSpeed);
        boolean canIncreaseSpeedOnNeutralGear = currentGear == Gear.NEUTRAL && getSpeed() < speed;
        if (!isEnabled || !inRangeSpeedOfCurrentGear || canIncreaseSpeedOnNeutralGear || speed < 0) {
            return false;
        }
        currentSpeed = newSpeed;
        return true;
    }

    public boolean isEngineTurnedOn() {
        return isEnabled;
    }

    public int getSpeed() {
        return Math.abs(currentSpeed);
    }

    public Gear getGear() {
        return currentGear;
    }

    public Direction getDirection() {
        if (currentSpeed == 0) {
            return Direction.STAND;
        }
        return  (currentSpeed > 0) ? Direction.FORWARD : Direction.BACK;
    }

    private Integer getSighOfSpeed() {
        return getDirection() == Direction.BACK || currentGear.toNumber() < 0 ? -1 : 1;
    }
}
