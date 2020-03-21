package lab2.vector;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.lang.Float.NaN;
import static org.junit.Assert.assertEquals;

public class VectorTest {
    @Test
    public void shouldMultiplyArrayOfFloat() {
        final List<Float> input = Arrays.asList(-1.0f, 2.0f, 3.0f, 8.0f);
        final List<Float> expected = Arrays.asList(8.000f, -16.000f, -24.000f, -64.000f);
        final List<Float> output = ProcessVector.multiplyOnMaxAndMinElement(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldMultiplyArrayOfZero() {
        final List<Float> input = Arrays.asList(0.0f, 0.0f, 0.0f, 0.0f);
        final List<Float> expected = Arrays.asList(NaN, NaN, NaN, NaN);
        final List<Float> output = ProcessVector.multiplyOnMaxAndMinElement(input);
        assertEquals(expected, output);
    }

    @Test
    public void shouldMultiplyOnlyOneNumber() {
        final List<Float> input = Arrays.asList(35.0f);
        final List<Float> expected = Arrays.asList(35.0f);
        final List<Float> output = ProcessVector.multiplyOnMaxAndMinElement(input);
        assertEquals(expected, output);
    }
}
