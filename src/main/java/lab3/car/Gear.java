package lab3.car;

enum Gear {
    REVERSE(-1),
    NEUTRAL(0),
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5);

    Gear(int gear) {
        this.gear = gear;
    }

    private final int gear;

    public final int toNumber() {
        return gear;
    }
}