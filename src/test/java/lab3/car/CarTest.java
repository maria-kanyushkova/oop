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

    private void assertChangeGear(Car car, Gear gear, boolean expectedResult) {
        assertEquals(car.setGear(gear), expectedResult);
        if (expectedResult) {
            assertEquals(car.getGear(), gear);
        }
    }

    private void assertChangeSpeed(Car car, int speed, boolean expectedResult) {
        assertEquals(car.setSpeed(speed), expectedResult);
        if (expectedResult) {
            assertEquals(car.getSpeed(), speed);
        }
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
            assertChangeGear(car, Gear.NEUTRAL, true);
        }

        @Test
        @DisplayName("shouldn't change speed and gear")
        public void shouldNotChangeSpeedAndGear() {
            assertChangeGear(car, Gear.REVERSE, false);
            assertChangeGear(car, Gear.FIRST, false);
            assertChangeSpeed(car, 12, false);
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
                assertChangeGear(car, Gear.FIRST, true);
                assertChangeSpeed(car, 30, true);
                assertEquals(car.getDirection(), Direction.FORWARD);
            }

            @Test
            @DisplayName("should switch on reverse gear")
            public void shouldSwitchOnReverseGear() {
                assertChangeGear(car, Gear.REVERSE, true);
                assertChangeSpeed(car, 12, true);
                assertEquals(car.getDirection(), Direction.BACK);
            }

            @Test
            @DisplayName("shouldn't switch with neutral gear on last gear")
            public void shouldNotSwitchOnLastGear() {
                assertChangeGear(car, Gear.FIFTH, false);
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
                assertChangeGear(car, Gear.FIFTH, false);
            }

            @Test
            @DisplayName("shouldn't switch with first gear on reverse gear")
            public void shouldNotSwitchOnReverseGear() {
                assertChangeGear(car, Gear.REVERSE, false);
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
            assertChangeGear(car, Gear.REVERSE, true);
            assertChangeSpeed(car, 20, true);
            assertEquals(car.getDirection(), Direction.BACK);
            assertChangeGear(car, Gear.NEUTRAL, true);
            assertEquals(car.getDirection(), Direction.BACK);
            assertChangeSpeed(car, 0, true);
            assertEquals(car.getDirection(), Direction.STAND);
        }

        @Test
        @DisplayName("should moving to forward before car stops")
        public void shouldMovingToForwardBeforeCarStops() {
            assertChangeGear(car, Gear.FIRST, true);
            assertChangeSpeed(car, 20, true);
            assertEquals(car.getDirection(), Direction.FORWARD);
            assertChangeGear(car, Gear.NEUTRAL, true);
            assertEquals(car.getDirection(), Direction.FORWARD);
            assertChangeSpeed(car, 0, true);
            assertEquals(car.getDirection(), Direction.STAND);
        }

        @Test
        @DisplayName("should switch gear to reverse with moving car")
        public void shouldSwitchGearToReverseWithMovingCar() {
            assertChangeGear(car, Gear.REVERSE, true);
            assertChangeSpeed(car, 19, true);
            assertChangeGear(car, Gear.NEUTRAL, true);
            assertChangeGear(car, Gear.REVERSE, false);
        }

        @Test
        @DisplayName("should correct boundary speed for reverse gear")
        public void checkBoundarySpeedForReverseGear() {
            assertChangeGear(car, Gear.REVERSE, true);
            assertChangeSpeed(car, 20, true);
            assertChangeSpeed(car, 21, false);
            assertChangeSpeed(car, -1, false);
        }

        @Test
        @DisplayName("should correct boundary speed for neutral gear")
        public void checkBoundarySpeedForNeutralGear() {
            assertChangeGear(car, Gear.NEUTRAL, true);
            assertChangeSpeed(car, 0, true);
            assertChangeSpeed(car, 10, false);
        }

        @Test
        @DisplayName("should correct boundary speed for first gear")
        public void checkBoundarySpeedForFirstGear() {
            assertChangeGear(car, Gear.FIRST, true);
            assertChangeSpeed(car, 15, true);
            assertChangeSpeed(car, -1, false);
            assertChangeSpeed(car, 31, false);
        }

        @Test
        @DisplayName("should correct boundary speed for second gear")
        public void checkBoundarySpeedForSecondGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(20);

            assertChangeGear(car, Gear.SECOND, true);
            assertChangeSpeed(car, 30, true);
            assertChangeSpeed(car, 19, false);
            assertChangeSpeed(car, 51, false);
        }

        @Test
        @DisplayName("should correct boundary speed for third gear")
        public void checkBoundarySpeedForThirdGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);

            assertChangeGear(car, Gear.THIRD, true);
            assertChangeSpeed(car, 40, true);
            assertChangeSpeed(car, 29, false);
            assertChangeSpeed(car, 61, false);
        }

        @Test
        @DisplayName("should correct boundary speed for fourth gear")
        public void checkBoundarySpeedForFourthGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);
            car.setGear(Gear.THIRD);
            car.setSpeed(40);

            assertChangeGear(car, Gear.FOURTH, true);
            assertChangeSpeed(car, 50, true);
            assertChangeSpeed(car, 39, false);
            assertChangeSpeed(car, 91, false);
        }

        @Test
        @DisplayName("should correct boundary speed for fifth gear")
        public void checkBoundarySpeedForFifthGear() {
            car.setGear(Gear.FIRST);
            car.setSpeed(30);
            car.setGear(Gear.THIRD);
            car.setSpeed(50);

            assertChangeGear(car, Gear.FIFTH, true);
            assertChangeSpeed(car, 100, true);
            assertChangeSpeed(car, 49, false);
            assertChangeSpeed(car, 151, false);
        }
    }
}
