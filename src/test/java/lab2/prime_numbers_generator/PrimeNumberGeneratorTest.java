package lab2.prime_numbers_generator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PrimeNumberGeneratorTest {
    @Test
    public void shouldGeneratePositivePrimeNumbers() {
        final int input = 10;
        final List<Boolean> expected = Arrays.asList(true, true, true, true, false, true, false, true, false, false, false);
        final List<Boolean> output = Sieve.sieving(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldNotGenerateNegativePrimeNumbers() {
        final int input = -1;
        final List<Boolean> expected = null;
        final List<Boolean> output = Sieve.sieving(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldNotGeneratePrimeNumberForZero() {
        final int input = 0;
        final List<Boolean> expected = null;
        final List<Boolean> output = Sieve.sieving(input);
        assertEquals(expected, output);
    }
}
