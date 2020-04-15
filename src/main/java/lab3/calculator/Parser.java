package lab3.calculator;

import java.util.*;

public class Parser {
    private Map<String, Operation> operationMap = new HashMap<>();

    Parser() {
        operationMap.put("+", Operation.ADD);
        operationMap.put("-", Operation.SUB);
        operationMap.put("*", Operation.MUL);
        operationMap.put("/", Operation.DIV);
    }

    public String[] parseCommandLine(String commandLine) {
        List<String> result = new ArrayList<>();
        String value = "";
        for (int i = 0; i < commandLine.length(); i++) {
            String symbol = Character.toString(commandLine.charAt(i));
            if (operationMap.containsKey(symbol) || isSpace(symbol)) {
                if (!value.isEmpty()) {
                    result.add(value);
                }
                if (!isSpace(symbol)) {
                    result.add(symbol);
                }
            } else if (!isSpace(symbol)) {
                value += symbol;
            }
        }
        if (!value.isEmpty()) {
            result.add(value);
        }
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    public String[] getArgs(String[] commands) {
        List<String> result = Arrays.asList(commands);
        result.remove(0);
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    private boolean isSpace(String symbol) {
        return symbol.equals(" ");
    }
}
