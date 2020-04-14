package lab3.car;

import java.io.IOException;
import java.util.Scanner;

public class EventLoop {
    private Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    private static String getMenuInfo() {
        return "0. help - выводится информация о командах\n" +
                "1. info - выводится информация об автомобиле\n" +
                "2. setGear <значение> - установка передачи автомобиля\n" +
                "3. setSpeed <значение> - установка скорости автомобиля\n" +
                "4. engineOn - включить двгатель\n" +
                "5. engineOff - выключить двигатель\n" +
                "6. exit - выход с приложения";
    }

    private static String[] parseCommandLine(String line) {
        return line.split(" ");
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private static int parseValue(String args) {
        if (!isNumeric(args)) {
            throw new ArithmeticException("Значение не является числом");
        }
        return Integer.parseInt(args);
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                final String consoleLine = readFromConsole();
                final String[] commands = parseCommandLine(consoleLine);
                if (commands.length > 2) {
                    throw new IOException("Превышено количество аргументов");
                }
                String command = commands[0];
                String value = commands.length == 2 ? commands[1] : null;
                String result = this.runCommand(command, value);
                if (result.isEmpty()) {
                    return;
                }
                System.out.println(result);
            } catch (Exception error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
    }

    private String runCommand(String command, String value) throws Exception {
        switch (command) {
            case "help":
                return getMenuInfo();
            case "info":
                return controller.getInfo();
            case "setGear":
                return controller.setGear(parseValue(value));
            case "setSpeed":
                return controller.setSpeed(parseValue(value));
            case "engineOn":
                return controller.engineOn();
            case "engineOff":
                return controller.engineOff();
            case "exit":
                return "";
            default:
                throw new IOException("Встречена незвестная команда");
        }
    }
}
