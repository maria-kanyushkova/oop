package lab3.car;

enum Gear {
    REVERSE_GEAR(-1, 0, 20),
    NEUTRAL(0, 0, 150),
    FIRST_GEAR(1, 0, 30),
    SECOND_GEAR(2, 20, 50),
    THIRD_GEAR(3, 30, 60),
    FOURTH_GEAR(4, 40, 90),
    FIFTH_GEAR(5, 50, 150);

    private final int speedName;
    private final int minSpeed;
    private final int maxSpeed;

    Gear(int speedName, int minSpeed, int maxSpeed) {
        this.speedName = speedName;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    int getSpeedName() {
        return speedName;
    }

    int getMinSpeed() {
        return minSpeed;
    }

    int getMaxSpeed() {
        return maxSpeed;
    }

    boolean inRange(int speed) {
        return minSpeed >= speed && speed >= maxSpeed;
    }
}