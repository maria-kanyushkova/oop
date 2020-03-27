package lab3.car;

import common.BaseInputOutput;
import lab2.html_decode.HtmlDecoder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (
                final Scanner in = new Scanner(System.in)
        ) {
            final String input = BaseInputOutput.read(in);
            BaseInputOutput.print(HtmlDecoder.decode(input));
        } catch (Exception error) {
            System.out.println(error.getLocalizedMessage());
        }
    }
}
