package lab3.car;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTest {

    // 01 - машина выключена проверка начальных состояний (скорость, направление, передача, выключенность)
    @Test
    public void shouldCorrectlyCarState() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertEquals(car.getDirection(), Direction.STAND);
        assertEquals(car.getSpeed(), 0);
        assertEquals(car.getGear(), Gear.NEUTRAL);
    }

    // 02 - машина выключена, нельзя изменить скорость и пердачу
    @Test
    public void shouldNotShiftValueForSpeedOrGearForOffVehicle() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertFalse(car.setGear(Gear.FIRST));
        assertFalse(car.setSpeed(12));
    }

    // 03 - машину выключена, нельзя выключить повторно
    @Test
    public void shouldNotTurnOnInVehicleOff() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertFalse(car.turnOffEngine());
    }

    // 04 - машина включена, нельзя включить повторно
    @Test
    public void shouldNotTurnOnIfEnabled() {
        Car car = new Car();
        car.turnOnEngine();
        assertTrue(car.getEnable());
        assertFalse(car.turnOnEngine());
    }

    // 05 - можно включить выключенную машину
    @Test
    public void shouldTurnOn() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertTrue(car.turnOnEngine());
    }

    // 06 - можно выключить включённую машину
    @Test
    public void shouldTurnOff() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.turnOffEngine());
    }

    // 23 - машина выключена, можно поменять передачу только на нейтральную
    @Test
    public void shouldShiftToNeutralGearInVehicleOff() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertTrue(car.setGear(Gear.NEUTRAL));
    }

    // машина включена

    // 07 - стоим: смогли переключить на первую передачу и поехать (скорость, передача и направление движения)
    @Test
    public void shouldShiftToFirstGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(12));

        assertEquals(car.getGear(), Gear.FIRST);
        assertEquals(car.getSpeed(), 12);
        assertEquals(car.getDirection(), Direction.FORWARD);
    }

    // 08 - стоим: смогли переключиться на задний ход (скорость, передача и направление движения)
    @Test
    public void shouldShiftToReverseGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertTrue(car.setGear(Gear.REVERSE));
        assertTrue(car.setSpeed(12));

        assertEquals(car.getGear(), Gear.REVERSE);
        assertEquals(car.getSpeed(), 12);
        assertEquals(car.getDirection(), Direction.BACK);
    }

    //  09 - стоим: не можем переключиться на 5ую предачу
    @Test
    public void shouldNotShiftToFiveGearInStandDirection() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertFalse(car.setGear(Gear.FIFTH));
    }

    //  10 - едем: переключение с 1ой на 5ую передачу не получается
    @Test
    public void shouldNotShiftGearToFifthFromFirstGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));

        assertEquals(car.getDirection(), Direction.FORWARD);
        assertEquals(car.getGear(), Gear.FIRST);
        assertEquals(car.getSpeed(), 30);

        assertFalse(car.setGear(Gear.FIFTH));
    }

    //  11 - едем: переключение с 1ой на -1ую передачу нельзя
    @Test
    public void shouldNotShiftFromFirstToReverseGearInMove() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));

        assertEquals(car.getDirection(), Direction.FORWARD);

        assertFalse(car.setGear(Gear.REVERSE));
    }

    //  12 - переключение скоростей с 0 на -1 на 0 и на 5 (граничная скорость или не в диапазоне)
    @Test
    public void checkShiftGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertEquals(car.getGear(), Gear.NEUTRAL);

        assertTrue(car.setGear(Gear.REVERSE));
        assertFalse(car.setSpeed(30));
        assertFalse(car.setSpeed(-10));
        assertTrue(car.setSpeed(20));
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertFalse(car.setSpeed(30));
        assertFalse(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(0));
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));
        assertTrue(car.setGear(Gear.SECOND));
        assertFalse(car.setSpeed(10));
        assertTrue(car.setSpeed(50));
        assertTrue(car.setGear(Gear.THIRD));
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertTrue(car.setGear(Gear.THIRD));
    }

    //  13 - когда движемся и передача нейтральная, то движение остается пока не станет "стоим" (вперёд\назад)
    @Test
    public void shouldDirectionUnchangedUntilSpeedNotEqualToZero() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertTrue(car.setGear(Gear.REVERSE));
        assertTrue(car.setSpeed(20));
        assertEquals(car.getDirection(), Direction.BACK);
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertEquals(car.getDirection(), Direction.BACK);
        assertTrue(car.setSpeed(1));
        assertEquals(car.getDirection(), Direction.BACK);
        assertTrue(car.setSpeed(0));
        assertEquals(car.getDirection(), Direction.STAND);

        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(20));
        assertEquals(car.getDirection(), Direction.FORWARD);
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertEquals(car.getDirection(), Direction.FORWARD);
        assertTrue(car.setSpeed(1));
        assertEquals(car.getDirection(), Direction.FORWARD);
        assertTrue(car.setSpeed(0));
        assertEquals(car.getDirection(), Direction.STAND);
    }

    // 15 - нельзя в движении выключить машину
    @Test
    public void shouldNotTurnOffWhileVehicleIsMoving() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(20));
        assertFalse(car.turnOffEngine());
    }

    // 16 - проверка граничащих скоростей для -1 передачи
    @Test
    public void checkBoundarySpeedForReverseGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.REVERSE));
        assertTrue(car.setSpeed(20));
        assertFalse(car.setSpeed(21));
        assertFalse(car.setSpeed(-1));
    }

    // 17 - проверка граничащих скоростей для 0 передачи
    @Test
    public void checkBoundarySpeedForNeutralGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertTrue(car.setSpeed(0));
        assertFalse(car.setSpeed(10));
    }

    // 18 - проверка граничащих скоростей для 1 передачи
    @Test
    public void checkBoundarySpeedForFirstGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(15));
        assertFalse(car.setSpeed(-1));
        assertFalse(car.setSpeed(31));
    }

    // 19 - проверка граничащих скоростей для 2 передачи
    @Test
    public void checkBoundarySpeedForSecondGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(20));
        assertTrue(car.setGear(Gear.SECOND));
        assertTrue(car.setSpeed(30));
        assertFalse(car.setSpeed(19));
        assertFalse(car.setSpeed(51));
    }

    // 20 - проверка граничащих скоростей для 3 передачи
    @Test
    public void checkBoundarySpeedForThirdGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));
        assertTrue(car.setGear(Gear.THIRD));
        assertTrue(car.setSpeed(40));
        assertFalse(car.setSpeed(29));
        assertFalse(car.setSpeed(61));
    }

    // 21 - проверка граничащих скоростей для 4 передачи
    @Test
    public void checkBoundarySpeedForFourthGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));
        assertTrue(car.setGear(Gear.THIRD));
        assertTrue(car.setSpeed(40));
        assertTrue(car.setGear(Gear.FOURTH));
        assertTrue(car.setSpeed(50));
        assertFalse(car.setSpeed(39));
        assertFalse(car.setSpeed(91));
    }

    // 22 - проверка граничащих скоростей для 5 передачи
    @Test
    public void checkBoundarySpeedForFifthGear() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(30));
        assertTrue(car.setGear(Gear.THIRD));
        assertTrue(car.setSpeed(50));
        assertTrue(car.setGear(Gear.FIFTH));
        assertTrue(car.setSpeed(100));
        assertFalse(car.setSpeed(49));
        assertFalse(car.setSpeed(151));
    }
}
