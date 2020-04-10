package lab2.html_decode;

import common.BaseInputOutput;

import java.util.Scanner;

public class HtmlDecoder {
    public static void decode() {
        try (
                final Scanner input = new Scanner(System.in)
        ) {
            String line;
            while ((line = input.nextLine()) != null) {
                BaseInputOutput.print(decodeString(line));
            }
        }
    }

    public static String decodeString(String value) {
        return value.replace("&quot;", "\"")
                .replace("&apos;", "\'")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&");
    }
}
