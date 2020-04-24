package lab4.figure;

import lab4.figure.common.Color;
import lab4.figure.common.IShape;
import lab4.figure.common.Point;
import lab4.figure.common.ShapeFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class EventLoop {
    private final Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    private static String getMenuInfo() {
        return "0. help - выводится информация о командах\n" +
                "1. line <startX> <startY> <endX> <endy> <outlineColor: #000000>\n" +
                "2. triangle <vertex1X> <vertex1Y> <vertex2X> <vertex2Y> <vertex3X> <vertex3Y> <outlineColor: #000000> <fillColor: #000000>\n" +
                "3. rectangle <leftTopX> <leftTopY> <width> <height> <outlineColor: #000000> <fillColor: #000000>\n" +
                "4. circle <centerX> <centerY> <radius> <outlineColor: #000000> <fillColor: #000000>\n" +
                "5. draw - рисование введённых фигур\n" +
                "6. exit - выход с приложения";
    }

    private static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


    private static String[] getParams(String[] commands) {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, commands);
        result.remove(0);
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                final String consoleLine = readFromConsole();
                if (runImpl(consoleLine)) {
                    break;
                }
            } catch (Exception error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
        System.out.println(controller.getInfoAboutFigureWithMaxArea());
        System.out.println(controller.getInfoAboutFigureWithMinPerimeter());
    }

    public void run(File inputFile) {
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String commandLine;
            while ((commandLine = bufferedReader.readLine()) != null) {
                runImpl(commandLine);
            }
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
        System.out.println(controller.getInfoAboutFigureWithMaxArea());
        System.out.println(controller.getInfoAboutFigureWithMinPerimeter());
    }

    private boolean runImpl(String commandsLine) throws Exception {
        final String[] commands = commandsLine.split(" ");
        String result = runCommand(commands);
        if (result.equals("exit")) {
            return true;
        }
        if (result.length() != 0) {
            System.out.println(result);
        }
        return false;
    }

    private String runCommand(String[] args) throws Exception {
        String[] params = getParams(args);
        String command = "";
        if (args.length != 0) {
            command = args[0];
        }
        switch (command) {
            case "help":
                return getMenuInfo();
            case "line":
            case "triangle":
            case "rectangle":
            case "circle":
                controller.appendShape(createShape(command, params));
                break;
            case "draw":
                break;
            case "exit":
                return "exit";
            default:
                throw new IOException("Встречена незвестная команда");
        }
        return "";
    }

    private IShape createShape(String type, String[] params) throws Exception {
        Color outlineColor;
        Color fillColor;
        switch (type) {
            case "line":
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point start = Utils.convertToPoint(params[0], params[1]);
                Point end = Utils.convertToPoint(params[2], params[3]);
                outlineColor = Utils.convertToColor(params[4]);
                return ShapeFactory.createLine(start, end, outlineColor);
            case "triangle":
                if (params.length != 8) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point vertex1 = Utils.convertToPoint(params[0], params[1]);
                Point vertex2 = Utils.convertToPoint(params[2], params[3]);
                Point vertex3 = Utils.convertToPoint(params[4], params[5]);
                outlineColor = Utils.convertToColor(params[6]);
                fillColor = Utils.convertToColor(params[7]);
                return ShapeFactory.createTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
            case "rectangle":
                if (params.length != 6) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point leftTop = Utils.convertToPoint(params[0], params[1]);
                double width = Utils.convertToNumber(params[2]);
                double height = Utils.convertToNumber(params[3]);
                outlineColor = Utils.convertToColor(params[4]);
                fillColor = Utils.convertToColor(params[5]);
                return ShapeFactory.createRectangle(leftTop, width, height, outlineColor, fillColor);
            case "circle":
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                Point center = Utils.convertToPoint(params[0], params[1]);
                double radius = Utils.convertToNumber(params[2]);
                outlineColor = Utils.convertToColor(params[3]);
                fillColor = Utils.convertToColor(params[4]);
                return ShapeFactory.createCircle(center, radius, outlineColor, fillColor);
            default:
                throw new IOException("Unknown shape type");
        }
    }
}
