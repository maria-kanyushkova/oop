package lab3.calculator;

public class Function {
    // TODO: more info for variable name
    private String left;
    private String right;
    private Operation operation = Operation.NULL;
    private double cash;
    private double result;
    // private double changed ;

    Function() {

    }

    private void setLeft(String value) {
        left = value;
    }

    private void setRight(String value) {
        right = value;
    }

    private void setOperation(Operation value) {
        operation = value;
    }

    private void setCash(Double value) {
        cash = value;
    }
    public void setResult(Double value) {
        result = value;
    }

    public double getCash() {
        return cash;
    }

    public double getResult() {
        return result;
    }

    private void calculate () {

    }
}
