package lab2.mini_dictionary;

import java.io.IOException;
import java.util.*;

public class EventLoop {
    private static final String TERMINAL_STRING = "...";

    private Controller controller;

    EventLoop(Controller controller) {
        this.controller = controller;
    }

    public void run() throws IOException {
        while (true) {
            final String word = Controller.readFromConsole();
            if (word.isEmpty()) {
                controller.onEmptyInput();
            } else if (Objects.equals(word, TERMINAL_STRING)) {
                boolean saved = controller.onFinishWord();
                if (saved) {
                    controller.onExit();
                    return;
                }
            } else {
                controller.onInputWord(word.toLowerCase().trim());
            }
        }
    }
}
