package lab2.vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessVector {
    public static List<Float> multiplyOnMaxAndMinElement(List<Float> numbers) {
        List<Float> array = new ArrayList<>();
        final float maxNumber = Collections.max(numbers);
        final float minNumber = Collections.min(numbers);
        for (Float number : numbers) {
            array.add(number * maxNumber / minNumber);
        }
        return array;
    }
}
