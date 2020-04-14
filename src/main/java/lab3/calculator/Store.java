package lab3.calculator;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Double> variable = new HashMap<>();
    private Map<String, Function> function = new HashMap<>();

    public void setVariable(String name) {
        variable.put(name, null);
    }

    public void setVariable(String name, Double value) {
        variable.put(name, value);
    }

    public Double getVariableValue(String name) {
        return variable.get(name);
    }

    public Map<String, Double> getVariables() {
        return variable;
    }

    public boolean isVariable(String name) {
        return variable.containsKey(name);
    }

    public void setFunction(String name, Function value) {
        function.put(name, value);
    }

    // TODO: WTF ???
    public void setFunctionResult(String name, Double value) {
        function.get(name).setResult(value);
    }

    public Double getFunctionResult(String name) {
        return function.get(name).getResult();
    }

    public Map<String, Function> getFunctions() {
        return function;
    }

    public boolean isFunction(String name) {
        return function.containsKey(name);
    }
}
