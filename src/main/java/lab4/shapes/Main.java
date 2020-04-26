package lab4.shapes;

import common.FileManager;
import lab4.shapes.canvas.Canvas;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes 2D";

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

            System.out.println("Максимальная площадь у фигуры:\n" + controller.getInfoAboutFigureWithMaxArea());
            System.out.println("Минимальный периметр у фигуры:\n" + controller.getInfoAboutFigureWithMinPerimeter());

            JFrame frame = createUIFrame();

            Canvas canvas = new Canvas();
            frame.add(canvas);
            controller.draw(canvas);

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

    private static JFrame createUIFrame() {
        JFrame frame = new JFrame();
        frame.setTitle(FRAME_TITLE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }
}
