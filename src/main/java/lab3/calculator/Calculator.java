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
        verifyName(isExistName(nameVar), "Идентификатор " + nameVar + " уже определён");
        variable.put(nameVar, Double.NaN);
        updateFunctions(nameVar);
    }

    public void defineVariable(String nameVar, String value) throws Exception {
        double result;
        if (isNumeric(value)) {
            result = Double.parseDouble(value);
        } else {
            verifyName(!isExistName(value), "Идентификатор " + value + " не определён");
            result = getValue(value);
        }
        variable.put(nameVar, result);
        updateFunctions(nameVar);
    }

    public void defineFunction(String nameFn, String operand) throws Exception {
        verifyName(isExistName(nameFn), "Идентификатор " + nameFn + " уже определён");
        verifyName(!isExistName(operand), "Идентификатор " + operand + " не определён");
        function.put(nameFn, new Function(operand));
        calculateFunction(function.get(nameFn));
    }

    public void defineFunction(String nameFn, String leftOperand, String operation, String rightOperand) throws Exception {
        verifyName(isExistName(nameFn), "Идентификатор " + nameFn + " уже определён");
        verifyName(!isExistName(leftOperand), "Идентификатор " + leftOperand + " не определён");
        verifyName(!isExistName(rightOperand), "Идентификатор " + rightOperand + " не определён");
        Operation op = getOperationType(operation);
        if (op == null) {
            throw new Exception("Операция не определена");
        }
        function.put(nameFn, new Function(leftOperand, op, rightOperand));
        calculateFunction(function.get(nameFn));
    }

    public Double getValue(String key) throws Exception {
        verifyName(!isExistName(key), "Идентификатор " + key + " не определён");
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
            result
                .append(var.getKey())
                .append(":")
                .append(String.format("%.2f", var.getValue()))
                .append("\n");
        }
        return result.toString();
    }

    public String getFunctionsValue() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Function> fn : function.entrySet()) {
            result
                .append(fn.getKey())
                .append(":")
                .append(String.format("%.2f", fn.getValue().getResult()))
                .append("\n");
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
                value = right == 0 ? Double.POSITIVE_INFINITY : left / right;
                break;
        }
        fn.setResult(value);
    }

    private boolean isExistName(String name) {
        return variable.containsKey(name) || function.containsKey(name);
    }

    private void verifyName(boolean expected, String exceptionMessage) throws Exception {
        if (expected) {
            throw new Exception(exceptionMessage);
        }
    }

    private Operation getOperationType(String operation) {
        switch (operation) {
            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUB;
            case "*":
                return Operation.MUL;
            case "/":
                return Operation.DIV;
            default:
                return null;
        }
    }
}
