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

    public void defineVariableValue(String[] values) {

    }

    public void defineFunction(String[] values) {

    }

    public String printIdentifier(String[] values) {
        return "";
    }

    public String printVariables() {
        return "";
    }

    public String printFunctions() {
        return "";
    }

    public double calculate(String name) {
        Double result = null;
        return result;
    }
}
