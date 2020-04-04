package lab3.car;

public class Main {
    public static void main(String[] args) {
        try {
            getMenuInfo();
            Car car = new Car();
            Controller controller = new Controller(car);
            EventLoop eventLoop = new EventLoop(controller);
            eventLoop.run();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

    private static void getMenuInfo() {
        String info = "1. info - выводится информация об автомобиле\n" +
                "2. setGear - установка передачи автомобиля\n" +
                "3. setSpeed - установка скорости автомобиля\n" +
                "4. engineOn - включить двгатель\n" +
                "5. engineOff - выключить двигатель\n" +
                "6. exit - выход с приложения";
        System.out.println(info);
    }
}
