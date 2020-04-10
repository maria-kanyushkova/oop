package lab3.car;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;

public class Car {
    private boolean isEnabled = false;
    private Direction direction = Direction.STAND;
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
        int tempSpeed = direction == Direction.BACK || currentGear.toNumber() < 0 ? -currentSpeed : currentSpeed;
        boolean inRangeOfCurrentSpeed = gearInfo.get(gear).isValidValue(tempSpeed);
        boolean isCarOff = !isEnabled && gear == Gear.NEUTRAL;
        if (!isCarOff && !(isEnabled && inRangeOfCurrentSpeed)) {
            return false;
        }
        currentGear = gear;
        changeDirection();
        return true;
    }

    public boolean setSpeed(int speed) {
        int tempSpeed = direction == Direction.BACK || currentGear.toNumber() < 0 ? -speed : speed;
        boolean inRangeSpeedOfCurrentGear = gearInfo.get(currentGear).isValidValue(tempSpeed);
        boolean canIncreaseSpeed = currentSpeed < tempSpeed && currentGear == Gear.NEUTRAL;
        if (!isEnabled || !inRangeSpeedOfCurrentGear || canIncreaseSpeed || speed < 0) {
            return false;
        }
        currentSpeed = speed;
        changeDirection();
        return true;
    }

    public boolean getEnable() {
        return isEnabled;
    }

    public int getSpeed() {
        return currentSpeed;
    }

    public Gear getGear() {
        return currentGear;
    }

    public Direction getDirection() {
        return direction;
    }

    private void changeDirection() {
        if (currentSpeed == 0) {
            direction = Direction.STAND;
        }
        if (currentGear.toNumber() > 0 && currentSpeed > 0) {
            direction = Direction.FORWARD;
        }
        if (currentGear.toNumber() < 0 && currentSpeed > 0) {
            direction = Direction.BACK;
        }
    }
}
