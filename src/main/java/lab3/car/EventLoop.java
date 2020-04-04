package lab3.car;

public class EventLoop {
    private Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        while (true) {
            final String command = Controller.readFromConsole();
            switch (command) {
                case "info":
                    System.out.println(controller.getInfo());
                    break;
                case "setGear": {
                    System.out.print("Введите значение ");
                    String bufferCommand = Controller.readFromConsole();
                    System.out.println(controller.setGear(Integer.parseInt(bufferCommand)));
                    break;
                }
                case "setSpeed": {
                    System.out.print("Введите значение ");
                    String bufferCommand = Controller.readFromConsole();
                    System.out.println(controller.setSpeed(Integer.parseInt(bufferCommand)));
                    break;
                }
                case "engineOn":
                    System.out.println(controller.engineOn());
                    break;
                case "engineOff":
                    System.out.println(controller.engineOff());
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Встречена незвестная команда: " + command);
                    break;
            }
        }
    }
}
