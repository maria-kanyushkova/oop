package lab3.calculator;

import java.io.*;
import java.util.Scanner;

public class EventLoop {
    private final Parser parser = new Parser();
    private final Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    private static String getMenuInfo() {
        return "0. help - выводится информация о командах\n" +
                "1. var <индентификатор> - объявление преременной\n" +
                "2. let <индентификатор> = <индентификатор> или <число> - объявление переменной со значением или изменение значения переменной\n" +
                "3. fr <индентификатор> = <индентификатор> - объявление фунции\n" +
                "4. fr <индентификатор> = <индентификатор> <операция> <индентификатор> - объявление фунции\n" +
                "5. print <индентификатор> - печать значения функции или переменной\n" +
                "6. printvars - печать всех переменных в порядке возрастания имён\n" +
                "7. printfns - печать всех функций в порядке возрастание имён\n" +
                "8. exit - выход с приложения";
    }

    private static String readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void run() {
        System.out.println(getMenuInfo());
        while (true) {
            try {
                final String consoleLine = readFromConsole();
                String result = process(consoleLine);

                if (result.equals("exit")) {
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
                String result = process(commandLine);
                if (result.length() != 0) {
                    System.out.println(result);
                }
            }
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private String process(String commandsLine) throws IOException {
        final String[] commands = parser.parseCommandLine(commandsLine);
        if (commands.length == 0) {
            throw new IOException("Недостаточно аргументов");
        }
        String command = commands[0];
        String[] value = parser.getArgs(commands);
        return runCommand(command, value);
    }

    private String runCommand(String command, String[] args) throws IOException {
        switch (command) {
            case "help":
                return getMenuInfo();
            case "var":
            case "let":
                controller.defineVariable(args);
                break;
            case "fr":
                controller.defineFunction(args);
                break;
            case "print":
                return controller.printIdentifier(args);
            case "printvars":
                return controller.printVariables();
            case "printfns":
                return controller.printFunctions();
            case "exit":
                return "exit";
            default:
                throw new IOException("Встречена незвестная команда");
        }
        return "";
    }
}
