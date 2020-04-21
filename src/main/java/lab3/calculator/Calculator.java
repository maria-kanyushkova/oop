package lab3.calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private Map<String, Double> variable = new HashMap<>();
    private Map<String, Function> function = new HashMap<>();
    private Map<String, Double> cashFunctionList = new HashMap<>();

    Calculator() {

    }

    public void defineVariable(String nameVar) {

    }

    public void defineVariable(String nameVar, String value) {

        // call update calculated function values
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

    public double updateFunctions(String name) {
        Double result = null;
        return result;
    }

    public void calculateFunctionValue(Function fn) {

    }
}
