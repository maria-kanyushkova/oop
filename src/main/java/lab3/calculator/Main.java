package lab3.calculator;

import common.FileManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(String.valueOf(Double.NaN));

//            Controller controller = new Controller();
//            EventLoop eventLoop = new EventLoop(controller);
//            eventLoop.run();

//            File inputFile = FileManager.getFileByPath("tasks/lab3/calculator/fib.txt");
//            eventLoop.run(inputFile);
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
