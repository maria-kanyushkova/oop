package lab3.car;

import java.util.Scanner;

public class EventLoop {
    private Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        getMenuInfo();
        while (true) {
            if (!this.runCommand()) {
                return;
            }
        }
    }

    private boolean runCommand() {
        final String commandLine = readFromConsole();
        final String[] commandArray = parseCommandLine(commandLine);
        // todo: дублирование с default в switch
        if (commandArray.length > 2 ) {
            System.out.println("Встречена незвестная команда: " + commandLine);
            return true;
        }
        final String command = commandArray[0];
        final String value = commandArray.length == 2 ? commandArray[1] : "";
        switch (command) {
            case "help":
                getMenuInfo();
                break;
            case "info":
                controller.getInfo();
                break;
            case "setGear":
                controller.setGear(value);
                break;
            case "setSpeed":
                controller.setSpeed(value);
                break;
            case "engineOn":
                controller.engineOn();
                break;
            case "engineOff":
                controller.engineOff();
                break;
            case "exit":
                return false;
            default:
                System.out.println("Встречена незвестная команда: " + commandLine);
                break;
        }
        return true;
    }

    private static void getMenuInfo() {
        System.out.println(
            "0. help - выводится информация о командах\n" +
            "1. info - выводится информация об автомобиле\n" +
            "2. setGear <значение> - установка передачи автомобиля\n" +
            "3. setSpeed <значение> - установка скорости автомобиля\n" +
            "4. engineOn - включить двгатель\n" +
            "5. engineOff - выключить двигатель\n" +
            "6. exit - выход с приложения"
        );
    }

    private static String[] parseCommandLine(String line) {
        return line.split(" ");
    }

    public static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
