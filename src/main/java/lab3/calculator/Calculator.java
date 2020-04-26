package lab3.calculator;

import java.util.*;

public class Calculator {
    private Map<String, Double> variables = new HashMap<>();
    private Map<String, Function> functions = new LinkedHashMap<>();

    Calculator() {
    }
    
    private static double getInfinitySign(double value) {
        return value > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public void defineVariable(String varName) throws Exception {
        String variableName = verifyNewName(varName);
        variables.put(variableName, Double.NaN);
        updateFunctions(variableName);
    }

    public void defineVariable(String varName, Double value) throws Exception {
        variables.put(varName, value);
        updateFunctions(varName);
    }

    public void defineVariable(String varName, String value) throws Exception {
        String varValue = verifyExistName(value);
        double result = getValue(varValue);
        variables.put(varName, result);
        updateFunctions(varName);
    }

    public void defineFunction(String fnName, String operand) throws Exception {
        String functionName = verifyNewName(fnName);
        String functionOperand = verifyExistName(operand);
        functions.put(functionName, new Function(functionOperand));
        calculateFunction(functions.get(functionName));
    }

    public void defineFunction(String fnName, String leftOperand, Operation operation, String rightOperand) throws Exception {
        String functionName = verifyNewName(fnName);
        String functionLeft = verifyExistName(leftOperand);
        String functionRight = verifyExistName(rightOperand);
        functions.put(functionName, new Function(functionLeft, operation, functionRight));
        calculateFunction(functions.get(functionName));
    }

    public Double getValue(String key) throws Exception {
        String nameIdent = verifyExistName(key);
        if (variables.containsKey(nameIdent)) {
            return variables.get(nameIdent);
        }
        if (functions.containsKey(nameIdent)) {
            return functions.get(nameIdent).getResult();
        }
        return Double.NaN;
    }

    public Map<String, Double> getVariables() {
        return variables;
    }

    public Map<String, Function> getFunctions() {
        return functions;
    }

    public void updateFunctions(String name) throws Exception {
        List<String> calculated = new ArrayList<>();
        for (Map.Entry<String, Function> fn : functions.entrySet()) {
            if (fn.getValue().getLeftOperand().equals(name)
                    || fn.getValue().getRightOperand().equals(name)
                    || calculated.contains(fn.getValue().getLeftOperand())
                    || calculated.contains(fn.getValue().getRightOperand())
            ) {
                calculateFunction(fn.getValue());
                calculated.add(fn.getKey());
            }
        }
    }

    public void calculateFunction(Function fn) throws Exception {
        if (fn.getOperation() == null) {
            fn.setResult(getValue(fn.getLeftOperand()));
            return;
        }
        double left = getValue(fn.getLeftOperand());
        double right = getValue(fn.getRightOperand());
        double value = Double.NaN;
        switch (fn.getOperation()) {
            case ADD:
                value = left + right;
                break;
            case SUB:
                value = left - right;
                break;
            case MUL:
                value = left * right;
                break;
            case DIV:
                value = right == 0 ? getInfinitySign(left) : left / right;
                break;
        }
        fn.setResult(value);
    }

    private boolean isContainsName(String name) {
        return variables.containsKey(name) || functions.containsKey(name);
    }

    private String verifyNewName(String name) throws Exception {
        if (isContainsName(name)) {
            throw new Exception("Идентификатор " + name + " уже определён");
        }
        return name;
    }

    private String verifyExistName(String name) throws Exception {
        if (!isContainsName(name)) {
            throw new Exception("Идентификатор " + name + " не определён");
        }
        return name;
    }
}
