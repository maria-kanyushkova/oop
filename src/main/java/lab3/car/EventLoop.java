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
            double doubleValue = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                String result = this.runCommand();
                if (result.isEmpty()) {
                    return;
                }
                System.out.println(result);
            } catch (Exception error) {
                System.out.println(error.getLocalizedMessage());
            }
        }
    }

    private String runCommand() throws Exception {
        final String commandLine = readFromConsole();
        final String[] commandArray = parseCommandLine(commandLine);
        final String command = commandArray[0];
        int value = 0;
        // todo: дублирование с default в switch
        if (commandArray.length > 2) {
            throw new IOException("Встречена незвестная команда: " + commandLine);
        }
        if (commandArray.length == 2) {
            if (!isNumeric(commandArray[1])) {
                throw new ArithmeticException("Значение не является числом");
            }
            value = Integer.parseInt(commandArray[1]);
        }
        String message = "";
        switch (command) {
            case "help":
                message = getMenuInfo();
                break;
            case "info":
                message = controller.getInfo();
                break;
            case "setGear":
                message = controller.setGear(value);
                break;
            case "setSpeed":
                message = controller.setSpeed(value);
                break;
            case "engineOn":
                message = controller.engineOn();
                break;
            case "engineOff":
                message = controller.engineOff();
                break;
            case "exit":
                return "";
            default:
                throw new IOException("Встречена незвестная команда: " + commandLine);
        }
        return message;
    }
}
