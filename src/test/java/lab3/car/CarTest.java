package lab3.car;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTest {

    // 01 - машина выключена проверка начальных состояний (скорость, направление, передача, выключенность)
    @Test
    public void test01() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertEquals(car.getDirection(), Direction.STAND);
        assertEquals(car.getSpeed(), 0);
        assertEquals(car.getGear(), Gear.NEUTRAL);
    }

    // 02 - машина выключена, нельзя изменить скорость и пердачу
    @Test
    public void test02() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertFalse(car.setGear(Gear.FIRST));
        assertFalse(car.setSpeed(12));
    }

    // 03 - машину выключена, нельзя выключить повторно
    @Test
    public void test03() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertFalse(car.turnOffEngine());
    }

    // 04 - машина включена, нельзя включить повторно
    @Test
    public void test04() {
        Car car = new Car();
        car.turnOnEngine();
        assertTrue(car.getEnable());
        assertFalse(car.turnOnEngine());
    }

    // 05 - можно включить выключенную машину
    @Test
    public void test05() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertTrue(car.turnOnEngine());
    }

    // 06 - можно выключить включённую машину
    @Test
    public void test06() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.turnOffEngine());
    }

    // машина включена

    // 07 - стоим: смогли переключить на первую передачу и поехать (скорость, передача и направление движения)
    @Test
    public void test07() {
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
    public void test08() {
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
    public void test09() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertFalse(car.setGear(Gear.FIFTH));
    }

    //  10 - едем: переключение с 1ой на 5ую передачу не получается
    @Test
    public void test10() {
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
    public void test11() {
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
    public void test12() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertEquals(car.getDirection(), Direction.STAND);
        assertEquals(car.getGear(), Gear.NEUTRAL);

        assertTrue(car.setGear(Gear.REVERSE));
        assertFalse(car.setSpeed(30));
        assertFalse(car.setSpeed(-10));
        assertTrue(car.setSpeed(20));
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertFalse(car.setSpeed(30));  // нельзя увеличивать скорость на нейтралке
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
    public void test13() {
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

    // 14 - когда передача -1, не можем переключить на 1
    @Test
    public void test14() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.REVERSE));
        assertTrue(car.setSpeed(10));
        assertFalse(car.setGear(Gear.FIRST));
    }

    // 15 - нельзя в движении выключить машину
    @Test
    public void test15() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(20));
        assertFalse(car.turnOffEngine());
    }

    // 16 - проверка граничащих скоростей для -1 передачи
    @Test
    public void test16() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.REVERSE));
        assertTrue(car.setSpeed(20));
        assertFalse(car.setSpeed(21));
        assertFalse(car.setSpeed(-1));
    }

    // 17 - проверка граничащих скоростей для 0 передачи
    @Test
    public void test17() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.NEUTRAL));
        assertTrue(car.setSpeed(0));
        assertFalse(car.setSpeed(10));
    }

    // 18 - проверка граничащих скоростей для 1 передачи
    @Test
    public void test18() {
        Car car = new Car();
        assertTrue(car.turnOnEngine());
        assertTrue(car.setGear(Gear.FIRST));
        assertTrue(car.setSpeed(15));
        assertFalse(car.setSpeed(-1));
        assertFalse(car.setSpeed(31));
    }

    // 19 - проверка граничащих скоростей для 2 передачи
    @Test
    public void test19() {
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
    public void test20() {
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
    public void test21() {
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
    public void test22() {
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

    // 23 - машина выключена, можно поменять передачу только на нейтральную
    @Test
    public void test23() {
        Car car = new Car();
        assertFalse(car.getEnable());
        assertTrue(car.setGear(Gear.NEUTRAL));
    }
}
