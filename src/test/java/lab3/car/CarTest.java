package lab3.car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarTest {
    public Car car;

    // todo: эти ли функции необходимы для применения и последующей проверки?
    private void trySetGear(Car car, Gear gear) {
        assertTrue(car.setGear(gear));
        assertEquals(car.getGear(), gear);
    }

    private void trySetSpeed(Car car, int speed) {
        assertTrue(car.setSpeed(speed));
        assertEquals(car.getSpeed(), speed);
    }

    @BeforeEach
    public void init() {
        car = new Car();
    }

    @Test
    @DisplayName("car initial state is correct")
    public void shouldCorrectlyInitialState() {
        assertFalse(car.isEngineTurnedOn());
        assertEquals(car.getDirection(), Direction.STAND);
        assertEquals(car.getSpeed(), 0);
        assertEquals(car.getGear(), Gear.NEUTRAL);
    }

    @Nested
    @DisplayName("when car is turn off")
    class CarIsTurnOff {
        @Test
        @DisplayName("shouldn't turn off car")
        public void shouldNotTurnOffRepeatedly() {
            assertFalse(car.turnOffEngine());
        }

        @Test
        @DisplayName("should switch on neutral gear")
        public void shouldSwitchOnNeutralGear() {
            assertTrue(car.setGear(Gear.NEUTRAL));
        }

        @Test
        @DisplayName("shouldn't change speed and gear")
        public void shouldNotChangeSpeedAndGear() {
            assertFalse(car.setGear(Gear.REVERSE));
            assertFalse(car.setGear(Gear.FIRST));
            assertFalse(car.setSpeed(12));
        }
    }

    @Test
    @DisplayName("should turn on car")
    public void shouldTurnOn() {
        assertTrue(car.turnOnEngine());
        assertTrue(car.isEngineTurnedOn());
    }

    @Nested
    @DisplayName("when car is turn on")
    class WhenCarIsTurnOn {
        @BeforeEach
        public void init() {
            car.turnOnEngine();
        }

        @Test
        @DisplayName("shouldn't turn on when car is already on")
        public void shouldNotTurnOnRepeatedly() {
            assertFalse(car.turnOnEngine());
        }

        @Test
        @DisplayName("should turn off car")
        public void shouldTurnOff() {
            assertTrue(car.turnOffEngine());
        }

        @Nested
        @DisplayName("when car is standing")
        class WhenCarIsStanding {
            @Test
            @DisplayName("should switch on first gear")
            public void shouldSwitchOnFirstGear() {
                assertEquals(car.getDirection(), Direction.STAND);
                assertTrue(car.setGear(Gear.FIRST));
                assertTrue(car.setSpeed(30));

                assertEquals(car.getGear(), Gear.FIRST);
                assertEquals(car.getSpeed(), 30);
                assertEquals(car.getDirection(), Direction.FORWARD);
            }

            @Test
            @DisplayName("should switch on reverse gear")
            public void shouldSwitchOnReverseGear() {
                assertEquals(car.getDirection(), Direction.STAND);
                assertTrue(car.setGear(Gear.REVERSE));
                assertTrue(car.setSpeed(12));

                assertEquals(car.getGear(), Gear.REVERSE);
                assertEquals(car.getSpeed(), 12);
                assertEquals(car.getDirection(), Direction.BACK);
            }

            @Test
            @DisplayName("shouldn't switch with neutral gear on last gear")
            public void shouldNotSwitchOnLastGear() {
                assertEquals(car.getDirection(), Direction.STAND);
                assertFalse(car.setGear(Gear.FIFTH));
            }
        }

        @Nested
        @DisplayName("when car is moving")
        class WhenCarIsMoving {
            @BeforeEach
            public void setMoving() {
                car.setGear(Gear.FIRST);
                car.setSpeed(30);
            }

            @Test
            @DisplayName("shouldn't switch with first gear on last gear")
            public void shouldNotSwitchOnLastGear() {
                assertEquals(car.getDirection(), Direction.FORWARD);
                assertEquals(car.getGear(), Gear.FIRST);
                assertEquals(car.getSpeed(), 30);

                assertFalse(car.setGear(Gear.FIFTH));
            }

            @Test
            @DisplayName("shouldn't switch with first gear on reverse gear")
            public void shouldNotSwitchOnReverseGear() {
                assertEquals(car.getDirection(), Direction.FORWARD);
                assertFalse(car.setGear(Gear.REVERSE));
            }

            @Test
            @DisplayName("shouldn't turn off car")
            public void shouldNotTurnOff() {
                assertFalse(car.turnOffEngine());
            }
        }

        @Test
        @DisplayName("should moving to back before car stops")
        public void shouldMovingToBackBeforeCarStops() {
            assertTrue(car.setGear(Gear.REVERSE));

            assertTrue(car.setSpeed(20));
            assertEquals(car.getDirection(), Direction.BACK);

            assertTrue(car.setGear(Gear.NEUTRAL));
            assertEquals(car.getDirection(), Direction.BACK);

            assertTrue(car.setSpeed(0));
            assertEquals(car.getDirection(), Direction.STAND);
        }

        @Test
        @DisplayName("should moving to forward before car stops")
        public void shouldMovingToForwardBeforeCarStops() {
            assertTrue(car.setGear(Gear.FIRST));

            assertTrue(car.setSpeed(20));
            assertEquals(car.getDirection(), Direction.FORWARD);

            assertTrue(car.setGear(Gear.NEUTRAL));
            assertEquals(car.getDirection(), Direction.FORWARD);

            assertTrue(car.setSpeed(0));
            assertEquals(car.getDirection(), Direction.STAND);
        }

        @Test
        @DisplayName("should switch gear to reverse with moving car")
        public void shouldSwitchGearToReverseWithMovingCar() {
            assertTrue(car.setGear(Gear.REVERSE));
            assertTrue(car.setSpeed(19));
            assertTrue(car.setGear(Gear.NEUTRAL));
            assertFalse(car.setGear(Gear.REVERSE));
        }

        @Test
        @DisplayName("should correct boundary speed for reverse gear")
        public void checkBoundarySpeedForReverseGear() {
            assertTrue(car.setGear(Gear.REVERSE));
            assertTrue(car.setSpeed(20));
            assertFalse(car.setSpeed(21));
            assertFalse(car.setSpeed(-1));
        }

        @Test
        @DisplayName("should correct boundary speed for neutral gear")
        public void checkBoundarySpeedForNeutralGear() {
            assertTrue(car.setGear(Gear.NEUTRAL));
            assertTrue(car.setSpeed(0));
            assertFalse(car.setSpeed(10));
        }

        @Test
        @DisplayName("should correct boundary speed for first gear")
        public void checkBoundarySpeedForFirstGear() { ;
            assertTrue(car.setGear(Gear.FIRST));
            assertTrue(car.setSpeed(15));
            assertFalse(car.setSpeed(-1));
            assertFalse(car.setSpeed(31));
        }

        @Test
        @DisplayName("should correct boundary speed for second gear")
        public void checkBoundarySpeedForSecondGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(20);

            assertTrue(car.setGear(Gear.SECOND));
            assertTrue(car.setSpeed(30));
            assertFalse(car.setSpeed(19));
            assertFalse(car.setSpeed(51));
        }

        @Test
        @DisplayName("should correct boundary speed for third gear")
        public void checkBoundarySpeedForThirdGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);

            assertTrue(car.setGear(Gear.THIRD));
            assertTrue(car.setSpeed(40));
            assertFalse(car.setSpeed(29));
            assertFalse(car.setSpeed(61));
        }

        @Test
        @DisplayName("should correct boundary speed for fourth gear")
        public void checkBoundarySpeedForFourthGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);
            car.setGear(Gear.THIRD);
            car.setSpeed(40);

            assertTrue(car.setGear(Gear.FOURTH));
            assertTrue(car.setSpeed(50));
            assertFalse(car.setSpeed(39));
            assertFalse(car.setSpeed(91));
        }

        @Test
        @DisplayName("should correct boundary speed for fifth gear")
        public void checkBoundarySpeedForFifthGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);
            car.setGear(Gear.THIRD);
            car.setSpeed(50);

            assertTrue(car.setGear(Gear.FIFTH));
            assertTrue(car.setSpeed(100));
            assertFalse(car.setSpeed(49));
            assertFalse(car.setSpeed(151));
        }
    }
}
