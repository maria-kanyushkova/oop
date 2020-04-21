package lab3.calculator;

public class Main {
    public static void main(String[] args) {
        try {
            Calculator calculator = new Calculator();
            EventLoop eventLoop = new EventLoop(calculator);
            eventLoop.run();

//            File inputFile = FileManager.getFileByPath("tasks/lab3/calculator/fib.txt");
//            eventLoop.run(inputFile);
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
