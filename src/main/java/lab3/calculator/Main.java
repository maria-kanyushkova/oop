package lab3.calculator;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ArgumentsDTO argumentsDTO = parseArgs(args);
            Calculator calculator = new Calculator();
            EventLoop eventLoop = new EventLoop(calculator);

            if (argumentsDTO.getInputPath().isEmpty()) {
                eventLoop.run();
            } else {
                File inputFile = FileManager.getFileByPath(argumentsDTO.getInputPath());
                eventLoop.run(inputFile);
            }

        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static ArgumentsDTO parseArgs(String[] args) throws IllegalArgumentException {
        String inputPath = "";
        if (args.length != 0) {
            inputPath = args[0];
        }
        return new ArgumentsDTO(inputPath);
    }
}
