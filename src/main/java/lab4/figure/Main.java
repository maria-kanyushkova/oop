package lab4.figure;

import common.FileManager;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        try {
            FigureDTO figureDTO = parseArgs(args);
            Controller controller = new Controller();
            EventLoop eventLoop = new EventLoop(controller);
            if (figureDTO.getInputPath().isEmpty()) {
                eventLoop.run();
            } else {
                File inputFile = FileManager.getFileByPath(figureDTO.getInputPath());
                eventLoop.run(inputFile);
            }
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static FigureDTO parseArgs(String[] args) throws IllegalArgumentException {
        String inputPath = "";
        if (args.length != 0) {
            inputPath = args[0];
        }
        return new FigureDTO(inputPath);
    }
}
