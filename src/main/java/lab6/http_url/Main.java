package lab6.http_url;

public class Main {

    public static void main(String[] args) {
        try {
            EventLoop eventLoop = new EventLoop();
            eventLoop.run();
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }

}
