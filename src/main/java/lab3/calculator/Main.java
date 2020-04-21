package lab3.calculator;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            CalculatorDTO calculatorDTO = parseArgs(args);
            Calculator calculator = new Calculator();
            EventLoop eventLoop = new EventLoop(calculator);

            if (calculatorDTO.getInputPath().isEmpty()) {
                eventLoop.run();
            } else {
                File inputFile = FileManager.getFileByPath(calculatorDTO.getInputPath());
                eventLoop.run(inputFile);
            }

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static CalculatorDTO parseArgs(String[] args) throws IllegalArgumentException {
        String inputPath = "";
        if (args.length != 0) {
            inputPath = args[0];
        }
        return new CalculatorDTO(inputPath);
    }
}
