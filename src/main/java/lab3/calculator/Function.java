package lab3.calculator;

public class Function {
    private String leftOperand = "";
    private String rightOperand = "";
    private Operation operation = null;
    private double result = Double.NaN;

    Function(String leftOperand) {
        this.leftOperand = leftOperand;
    }

    Function(String leftOperand, Operation operation, String rightOperand) {
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public String getLeftOperand() {
        return leftOperand;
    }

    public String getRightOperand() {
        return rightOperand;
    }

    public Operation getOperation() {
        return operation;
    }
}
