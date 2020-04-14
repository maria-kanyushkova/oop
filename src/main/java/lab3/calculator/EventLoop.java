package lab3.calculator;

public class EventLoop {
    private final Controller controller;

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
        final String line = Controller.readFromConsole();
        final String command = Parser.getCommandName(line);
        final String[] values = Parser.parseValues(line);
        switch (command) {
            case "help":
                getMenuInfo();
                break;
            case "var":
                controller.defineVariable(values);
                break;
            case "let":
                controller.defineVariableValue(values);
                break;
            case "fr":
                controller.defineFunction(values);
                break;
            case "print":
                controller.printIdentifier(values);
                break;
            case "printvars":
                controller.printVariables();
                break;
            case "printfns":
                controller.printFunctions();
                break;
            case "exit":
                return false;
            default:
                System.out.println("Встречена незвестная команда: " + command);
                break;
        }
        return true;
    }

    private static void getMenuInfo() {
        System.out.println(
                "0. help - выводится информация о командах\n" +
                "1. var <индентификатор> - объявление преременной\n" +
                "2. let <индентификатор> = <индентификатор> или <число> - объявление переменной со значением или изменение значения переменной\n" +
                "3. fr <индентификатор> = <индентификатор> - объявление фунции\n" +
                "4. fr <индентификатор> = <индентификатор> <операция> <индентификатор> - объявление фунции\n" +
                "5. print <индентификатор> - печать значения функции или переменной\n" +
                "6. printvars - печать всех переменных в порядке возрастания имён\n" +
                "7. printfns - печать всех функций в порядке возрастание имён\n" +
                "8. exit - выход с приложения"
        );
    }
}
