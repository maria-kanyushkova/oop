package lab3.calculator;

import java.util.Map;

public class Function {
    private String name;
    private String leftOperand;
    private String rightOperand;
    private Operation operation;
    private boolean isComplex = false;
    private double result;

    Function(String name, String leftOperand) {
        this.name = name;
        this.leftOperand = leftOperand;
        this.isComplex = false;
    }

    Function(String name, String leftOperand, Operation operation, String rightOperand) {
        this.name = name;
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
        this.isComplex = true;
    }

    public double getResult(Controller controller, Map<String, Double> cashFunctionList) {
        if (cashFunctionList.containsKey(name) && !cashFunctionList.get(name).isNaN()) {
            return cashFunctionList.get(name);
        }
        if (rightOperand.length() == 0) {
            return controller.calculate(leftOperand);
        }
        double result = 0;
        double leftValue = controller.calculate(leftOperand);
        double rightValue = controller.calculate(rightOperand);
        switch (operation) {
            case ADD:
                result = leftValue + rightValue;
                break;
            case SUB:
                result = leftValue - rightValue;
                break;
            case MUL:
                result = leftValue * rightValue;
                break;
            case DIV:
                result = leftValue / rightValue;
                break;
        }
        cashFunctionList.put(name, result);
        this.result = result;
        return result;
    }

    public double getLastResult() {
        return this.result;
    }
}
