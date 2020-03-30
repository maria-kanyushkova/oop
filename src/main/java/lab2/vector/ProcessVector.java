package lab2.vector;

import java.util.Collections;
import java.util.List;

public class ProcessVector {
    public static List<Float> multiplyOnMaxAndMinElement(List<Float> numbers) {
        if (numbers.size() == 0) {
            return numbers;
        }
        final float maxNumber = Collections.max(numbers);
        final float minNumber = Collections.min(numbers);
        for (int i = 0; i < numbers.size(); i++) {
            numbers.set(i, numbers.get(i) * maxNumber / minNumber);
        }
        return numbers;
    }
}
