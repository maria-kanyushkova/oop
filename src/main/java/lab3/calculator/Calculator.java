package lab3.calculator;

import java.util.*;

public class Calculator {
    private Map<String, Double> variable = new HashMap<>();
    private Map<String, Function> function = new LinkedHashMap<>();

    Calculator() {
    }

    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void defineVariable(String nameVar) throws Exception {
        if (isExistName(nameVar)) {
            throw new Exception("Индентификатор " + nameVar + " уже определён");
        }
        variable.put(nameVar, Double.NaN);
        updateFunctions(nameVar);
    }

    public void defineVariable(String nameVar, String value) throws Exception {
        double result;
        if (isNumeric(value)) {
            result = Double.parseDouble(value);
        } else if (isExistName(value)) {
            result = getValue(value);
        } else {
            throw new Exception("Индентификатор " + value + " не определён");
        }
        variable.put(nameVar, result);
        updateFunctions(nameVar);
    }

    public void defineFunction(String nameFn, String operand) throws Exception {
        if (isExistName(nameFn)) {
            throw new Exception("Индентификатор " + nameFn + " уже определён");
        }
        if (!isExistName(operand)) {
            throw new Exception("Индентификатор " + operand + " не определён");
        }
        function.put(nameFn, new Function(operand));
        calculateFunction(function.get(nameFn));
    }

    public void defineFunction(String nameFn, String leftOperand, String operation, String rightOperand) throws Exception {
        if (isExistName(nameFn)) {
            throw new Exception("Индентификатор " + nameFn + " уже определён");
        }
        if (!isExistName(leftOperand)) {
            throw new Exception("Индентификатор " + leftOperand + " не определён");
        }
        if (!isExistName(rightOperand)) {
            throw new Exception("Индентификатор " + rightOperand + " не определён");
        }
        Operation op;
        switch (operation) {
            case "+":
                op = Operation.ADD;
                break;
            case "-":
                op = Operation.SUB;
                break;
            case "*":
                op = Operation.MUL;
                break;
            case "/":
                op = Operation.DIV;
                break;
            default:
                op = null;
        }
        if (op == null) {
            throw new Exception("Операция не определена");
        }
        function.put(nameFn, new Function(leftOperand, op, rightOperand));
        calculateFunction(function.get(nameFn));
    }

    public Double getValue(String key) throws Exception {
        if (!isExistName(key)) {
            throw new Exception("Индентификатор " + key + " не определён");
        }
        if (variable.containsKey(key)) {
            return variable.get(key);
        }
        if (function.containsKey(key)) {
            return function.get(key).getResult();
        }
        return Double.NaN;
    }

    public String getVariablesValue() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Double> var : variable.entrySet()) {
            result.append(var.getKey()).append(":").append(String.format("%.2f", var.getValue())).append("\n");
        }
        return result.toString();
    }

    public String getFunctionsValue() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Function> fn : function.entrySet()) {
            result.append(fn.getKey()).append(":").append(String.format("%.2f", fn.getValue().getResult())).append("\n");
        }
        return result.toString();
    }

    public void updateFunctions(String name) throws Exception {
        List<String> calculated = new ArrayList<>();
        for (Map.Entry<String, Function> fn : function.entrySet()) {
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
                if (right == 0) {
                    value = Double.POSITIVE_INFINITY;
                } else {
                    value = left / right;
                }
                break;
        }
        fn.setResult(value);
    }

    private boolean isExistName(String name) {
        return variable.containsKey(name) || function.containsKey(name);
    }
}
