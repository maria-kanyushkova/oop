package lab3.calculator;

import java.util.Scanner;

public class Controller {
    private final Store store;

    Controller(Store store){
        this.store = store;
    }

    public void defineVariable(String[] values) {

    }

    public void defineVariableValue(String[] values) {

    }

    public void defineFunction(String[] values) {

    }

    public void printIdentifier(String[] values) {

    }

    public void printVariables() {

    }

    public void printFunctions() {

    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
