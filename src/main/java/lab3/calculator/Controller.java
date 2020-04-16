package lab3.calculator;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String, Double> variable = new HashMap<>();
    private Map<String, Function> function = new HashMap<>();
    private Map<String, Double> cashFunctionList = new HashMap<>();

    Controller() {

    }

    public void defineVariable(String[] values) {

    }

    public void defineFunction(String[] values) {

    }

    // todo: наверное переназвать, так как функция не печатает а возвращает строку
    public String printIdentifier(String[] values) {
        return "";
    }

    // todo: наверное переназвать, так как функция не печатает а возвращает строку
    public String printVariables() {
        return "";
    }

    // todo: наверное переназвать, так как функция не печатает а возвращает строку
    public String printFunctions() {
        return "";
    }

    public double calculate(String name) {
        Double result = null;
        return result;
    }

    public String getValue(String key) {
        if (variable.containsKey(key)) {
            return String.valueOf(variable.get(key));
        }
        if (function.containsKey(key)) {
            return String.valueOf(function.get(key).getLastResult());
        }
        return String.valueOf(Double.NaN);
    }
}
