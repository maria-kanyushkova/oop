package lab3.calculator;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            CalculatorDto calculatorDto = parseArgs(args);
            File inputFile = FileManager.getFileByPath(calculatorDto.getInputPath());

            Store store = new Store();
            Controller controller = new Controller(store);
            EventLoop eventLoop = new EventLoop(controller);
            eventLoop.run();

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static CalculatorDto parseArgs(String[] args) throws IllegalArgumentException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Few arguments.\nExpected format: <inputFile>");
        }
        return new CalculatorDto(args[0]);
    }
}
