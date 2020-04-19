package lab3.calculator;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String, Double> variable = new HashMap<>();
    private Map<String, Function> function = new HashMap<>();

    Controller() {

    }

    public void defineVariable(String nameVar) {

    }

    public void defineVariable(String nameVar, String value) {

    }

    public void defineFunction(String nameFn, String operand) {

    }

    public void defineFunction(String nameFn, String leftOperand, String operation, String rightOperand) {

    }

    public String getValue(String key) {
        if (variable.containsKey(key)) {
            return String.format("%.2f", variable.get(key));
        }
        if (function.containsKey(key)) {
            return String.format("%.2f", function.get(key).getLastResult());
        }
        return String.valueOf(Double.NaN);
    }

    public String getVariablesValue() {
        return "";
    }

    public String getFunctionsValue() {
        return "";
    }

    public double calculate(String name) {
        Double result = null;
        return result;
    }
}
