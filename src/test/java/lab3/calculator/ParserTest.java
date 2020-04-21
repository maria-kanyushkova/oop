package lab3.calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertArrayEquals;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    @DisplayName("should parse empty string")
    public void shouldParseEmptyString() {
        String input = "";
        String[] output = parser.parseCommandLine(input);
        String[] expected = {};
        assertArrayEquals(output, expected);
    }

    @Test
    @DisplayName("should parse string with command")
    public void shouldParseStringWithCommand() {
        String input = "printvars";
        String[] output = parser.parseCommandLine(input);
        String[] expected = {"printvars"};
        assertArrayEquals(output, expected);
    }

    @Test
    @DisplayName("should parse string with command and param")
    public void shouldParseStringWithCommandAndParam() {
        String input = "var x";
        String[] output = parser.parseCommandLine(input);
        String[] expected = {"var", "x"};
        assertArrayEquals(output, expected);
    }

    @Test
    @DisplayName("should parse string with defining variable")
    public void shouldParseStringWithDefiningVariable() {
        String input = "let x=a";
        String[] output = parser.parseCommandLine(input);
        String[] expected = {"let", "x", "a"};
        assertArrayEquals(output, expected);
    }

    @Test
    @DisplayName("should parse string with defining function")
    public void shouldParseStringWithDefiningFunction() {
        String input = "fn fn1=x+x";
        String[] output = parser.parseCommandLine(input);
        String[] expected = {"fn", "fn1", "x", "+", "x"};
        assertArrayEquals(output, expected);
    }
}
