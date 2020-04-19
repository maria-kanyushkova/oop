package lab3.calculator;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Double> cashFunctionList = new HashMap<>();

    private Store() {}

    private static class StoreHolder {
        private final static Store instance = new Store();
    }

    public static Store getInstance() {
        return StoreHolder.instance;
    }
}
