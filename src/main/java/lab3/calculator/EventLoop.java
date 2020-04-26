package lab3.calculator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class EventLoop {
    private final Parser parser = new Parser();
    private final Calculator calculator;

    EventLoop(Calculator calculator) {
        this.calculator = calculator;
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

    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static Operation getOperationType(String operation) {
        switch (operation) {
            case "+":
                return Operation.ADD;
            case "-":
                return Operation.SUB;
            case "*":
                return Operation.MUL;
            case "/":
                return Operation.DIV;
            default:
                return null;
        }
    }

    public static String getVariablesString(Map<String, Double> variables) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Double> var : variables.entrySet()) {
            result
                    .append(var.getKey())
                    .append(":")
                    .append(String.format("%.2f", var.getValue()))
                    .append("\n");
        }
        return result.toString();
    }

    public static String getFunctionsString(Map<String, Function> functions) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Function> fn : functions.entrySet()) {
            result
                    .append(fn.getKey())
                    .append(":")
                    .append(String.format("%.2f", fn.getValue().getResult()))
                    .append("\n");
        }
        return result.toString();
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
                BufferedReader bufferedReader = new BufferedReader(fileReader)
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

    private String process(String commandsLine) throws Exception {
        final String[] commands = parser.parseCommandLine(commandsLine);
        if (commands.length == 0) {
            throw new IOException("Недостаточно аргументов");
        }
        String command = commands[0];
        String[] value = parser.getArgs(commands);
        return runCommand(command, value);
    }

    private String runCommand(String command, String[] args) throws Exception {
        String name = "";
        String value = "";
        if (args.length != 0) {
            name = args[0];
        }
        if (args.length >= 2) {
            value = args[1];
        }
        switch (command) {
            case "help":
                return getMenuInfo();
            case "var":
                if (args.length != 1) {
                    throw new IOException("Недостаточно аргументов: var <имя>");
                }
                calculator.defineVariable(name);
                break;
            case "let":
                if (args.length != 2) {
                    throw new IOException("Недостаточно аргументов: let <имя> = <значение>");
                }
                if (isNumeric(value)) {
                    calculator.defineVariable(name, Double.parseDouble(value));
                } else {
                    calculator.defineVariable(name, value);
                }
                break;
            case "fn":
                if (args.length == 2) {
                    calculator.defineFunction(name, value);
                } else if (args.length == 4) {
                    Operation operation = getOperationType(args[2]);
                    if (operation == null) {
                        throw new Exception("Операция не определена");
                    }
                    String rightOperator = args[3];
                    calculator.defineFunction(name, value, operation, rightOperator);
                } else {
                    throw new IOException("Недостаточно аргументов");
                }
                break;
            case "print":
                if (args.length != 1) {
                    throw new IOException("Недостаточно аргументов");
                }
                return String.format("%.2f", calculator.getValue(name));
            case "printvars":
                return getVariablesString(calculator.getVariables());
            case "printfns":
                return getFunctionsString(calculator.getFunctions());
            case "exit":
                return "exit";
            default:
                throw new IOException("Встречена незвестная команда");
        }
        return "";
    }
}
