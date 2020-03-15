package lab2.vector;

import common.BaseInputOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputOutput extends BaseInputOutput {
    public static List<Float> parse(final String[] args) throws IOException {
        List<Float> parsed = new ArrayList<>();
        for (String arg : args) {
            if (!isNumeric(arg)) {
                throw new IOException("Cannot parse \"" + arg + "\"!");
            }
            parsed.add(Float.parseFloat(arg));
        }
        Collections.sort(parsed);
        return parsed;
    }

    public static boolean isNumeric(String strNum) {
        try {
            double doubleValue = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
