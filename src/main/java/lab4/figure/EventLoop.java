package lab4.figure;

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
                final String[] commands = consoleLine.split(" ");
                String result = runCommand(commands);
                if (result.equals("exit")) {
                    System.out.println(controller.getInfoAboutFigureWithMaxArea());
                    System.out.println(controller.getInfoAboutFigureWithMinPerimeter());
                    return;
                }
                if (result.length() != 0) {
                    System.out.println(result);
                }
            } catch (Exception error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
    }

    public void run(File inputFile) {
        try (
                FileReader fileReader = new FileReader(inputFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
        ) {
            String commandLine;
            while ((commandLine = bufferedReader.readLine()) != null) {
                final String[] commands = commandLine.split(" ");
                String result = runCommand(commands);
                if (result.length() != 0) {
                    System.out.println(result);
                }
            }
            System.out.println(controller.getInfoAboutFigureWithMaxArea());
            System.out.println(controller.getInfoAboutFigureWithMinPerimeter());
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
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
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                controller.defineLine(params);
                break;
            case "triangle":
                if (params.length != 8) {
                    throw new IOException("Недостаточно аргументов");
                }
                controller.defineTriangle(params);
                break;
            case "rectangle":
                if (params.length != 6) {
                    throw new IOException("Недостаточно аргументов");
                }
                controller.defineRectangle(params);
                break;
            case "circle":
                if (params.length != 5) {
                    throw new IOException("Недостаточно аргументов");
                }
                controller.defineCircle(params);
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
}
