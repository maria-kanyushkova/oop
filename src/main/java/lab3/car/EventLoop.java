package lab3.car;

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
        final String command = Controller.readFromConsole();
        switch (command) {
            case "help":
                getMenuInfo();
                break;
            case "info":
                controller.getInfo();
                break;
            case "setGear":
                controller.setGear();
                break;
            case "setSpeed":
                controller.setSpeed();
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
                System.out.println("Встречена незвестная команда: " + command);
                break;
        }
        return true;
    }

    private static void getMenuInfo() {
        System.out.println(
            "0. help - выводится информация о командах\n" +
            "1. info - выводится информация об автомобиле\n" +
            "2. setGear - установка передачи автомобиля\n" +
            "3. setSpeed - установка скорости автомобиля\n" +
            "4. engineOn - включить двгатель\n" +
            "5. engineOff - выключить двигатель\n" +
            "6. exit - выход с приложения"
        );
    }
}
