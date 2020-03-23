package lab2.url_parser;

import common.BaseInputOutput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (
                Scanner scanner = new Scanner(System.in)
        ) {
            final String input = BaseInputOutput.read(scanner);
            final Parser parser = new Parser(input);
            System.out.println(parser.parse().print());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
