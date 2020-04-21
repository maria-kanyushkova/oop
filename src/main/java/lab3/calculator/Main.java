package lab3.calculator;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            EventLoop eventLoop = new EventLoop(calculator);
            eventLoop.run();

//            File inputFile = FileManager.getFileByPath("tasks/lab3/calculator/large_sequence.txt");
//            eventLoop.run(inputFile);
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
