package lab2.html_decode;

import common.BaseInputOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HtmlDecoder {
    private final Map<String, String> dictionary = new HashMap<>();

    HtmlDecoder() {
        dictionary.put(HtmlEntities.QUOT.toString(), "\"");
        dictionary.put(HtmlEntities.APOS.toString(), "\'");
        dictionary.put(HtmlEntities.LT.toString(), "<");
        dictionary.put(HtmlEntities.GT.toString(), ">");
        dictionary.put(HtmlEntities.AMP.toString(), "&");
    }

    public void decode() {
        try (
                final Scanner input = new Scanner(System.in)
        ) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {
                    break;
                }
                BaseInputOutput.print(decodeString(line));
            }
        }
    }

    public String decodeString(String value) {
        StringBuilder result = new StringBuilder();
        boolean replaced = false;
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == '&') {
                replaced = false;
                for (Map.Entry<String, String> dictionaryEntity : dictionary.entrySet()) {
                    if (i + dictionaryEntity.getKey().length() > value.length()) {
                        break;
                    }
                    if (value.substring(i, i + dictionaryEntity.getKey().length()).equals(dictionaryEntity.getKey())) {
                        result.append(dictionaryEntity.getValue());
                        i += dictionaryEntity.getKey().length() - 1;
                        replaced = true;
                        break;
                    }
                }
                if (!replaced) {
                    result.append(value.charAt(i));
                }
            } else {
                result.append(value.charAt(i));
            }
        }
        return result.toString();
    }
}
