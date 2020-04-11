package lab3.car;

public class Main {
    public static void main(String[] args) {
        try {
            Car car = new Car();
            Controller controller = new Controller(car);
            EventLoop eventLoop = new EventLoop(controller);
            eventLoop.run();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
