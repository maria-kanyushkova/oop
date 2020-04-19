package lab3.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void should1() {
        String input = "";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[] {};
        assertEquals(output, expected);
    }

    @Test
    public void should2() {
        String input = "var x";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[]{"var", "x"};
        assertEquals(output, expected);
    }

    @Test
    public void should3() {
        String input = "let x=a";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[]{"let", "x", "a"};
        assertEquals(output, expected);
    }

    @Test
    public void should4() {
        String input = "fn fn1=x+x";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[]{"fn", "fn1", "x", "+", "x"};
        assertEquals(output, expected);
    }

    @Test
    public void should5() {
        String input = "print a";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[]{"print", "a"};
        assertEquals(output, expected);
    }

    @Test
    public void should6() {
        String input = "printvars";
        String[] output = parser.parseCommandLine(input);
        String[] expected = new String[]{"printvars"};
        assertEquals(output, expected);
    }
}
