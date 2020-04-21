package lab3.calculator;

import java.util.*;

public class Parser {
    private Map<String, Operation> operationMap = new HashMap<>();

    Parser() {
        operationMap.put("+", Operation.ADD);
        operationMap.put("-", Operation.SUB);
        operationMap.put("*", Operation.MUL);
        operationMap.put("/", Operation.DIV);
        operationMap.put("=", Operation.EQUALS);
    }

    public String[] parseCommandLine(String commandLine) {
        List<String> result = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < commandLine.length(); i++) {
            String symbol = Character.toString(commandLine.charAt(i));
            if (operationMap.containsKey(symbol) || isSpace(symbol)) {
                if (value.length() > 0) {
                    result.add(value.toString());
                }
                if (!isSpace(symbol) && !isEqualsSymbol(symbol)) {
                    result.add(symbol);
                }
                value = new StringBuilder();
            } else if (!isSpace(symbol)) {
                value.append(symbol);
            }
        }
        if (value.length() > 0) {
            result.add(value.toString());
        }
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    public String[] getArgs(String[] commands) {
        List<String> result = new ArrayList<>();
        Collections.addAll(result, commands);
        result.remove(0);
        String[] itemsArray = new String[result.size()];
        return result.toArray(itemsArray);
    }

    private boolean isSpace(String symbol) {
        return symbol.equals(" ");
    }

    private boolean isEqualsSymbol(String symbol) {
        return symbol.equals("=");
    }
}
