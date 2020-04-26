package lab3.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator;
    private Exception exception;

    private String formattingDigit(Double value) {
        return String.format("%.2f", value);
    }

    private void assertEqualsValue(Calculator calculator, String name, String expected) throws Exception {
        String output = formattingDigit(calculator.getValue(name));
        assertEquals(output, expected);
    }

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("command - var")
    class CommandVar {
        @Test
        @DisplayName("should be define variable with default value")
        public void shouldBeDefineVariableWithDefaultValue() throws Exception {
            calculator.defineVariable("a");
            assertEqualsValue(calculator, "a", "NaN");
        }

        @Test
        @DisplayName("should not define variable with existing name")
        public void shouldNotDefineVariableWithExistingName() throws Exception {
            calculator.defineVariable("a");
            String expected = "Идентификатор a уже определён";
            exception = assertThrows(Exception.class, () -> calculator.defineVariable("a"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - let")
    class CommandLet {
        @Test
        @DisplayName("should be define variable with value")
        public void shouldBeDefineVariableWithValue() throws Exception {
            calculator.defineVariable("a", Double.parseDouble("1"));
            assertEqualsValue(calculator, "a", "1,00");
        }

        @BeforeEach
        public void setVariables() throws Exception {
            calculator.defineVariable("a", Double.parseDouble("1"));
        }

        @Test
        @DisplayName("should be update variable value")
        public void shouldBeUpdateVariableValue() throws Exception {
            calculator.defineVariable("a", Double.parseDouble("2"));
            assertEqualsValue(calculator, "a", "2,00");
        }

        @Test
        @DisplayName("should be define variable with value another variable")
        public void shouldBeDefineVariableWithValueAnotherVariable() throws Exception {
            calculator.defineVariable("b", "a");
            assertEqualsValue(calculator, "b", "1,00");
        }

        @Test
        @DisplayName("should be define variable with value not existing variable")
        public void shouldBeDefineVariableWithValueNotExistingVariable() {
            String expected = "Идентификатор b не определён";
            exception = assertThrows(Exception.class, () -> calculator.defineVariable("a", "b"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - fn")
    class CommandFr {
        @BeforeEach
        public void setVariables() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", Double.parseDouble("1"));
        }

        @Test
        @DisplayName("should be define function with value not defined variable")
        public void shouldBeDefineFunctionWithValueNotDefinedVariable() throws Exception {
            calculator.defineFunction("fn1", "a");
            assertEqualsValue(calculator, "fn1", "NaN");
        }

        @Test
        @DisplayName("should be define function with value defined variable")
        public void shouldBeDefineFunctionWithValueDefinedVariable() throws Exception {
            calculator.defineFunction("fn1", "b");
            assertEqualsValue(calculator, "fn1", "1,00");
        }

        @Test
        @DisplayName("should be define function with value defined function")
        public void shouldBeDefineFunctionWithValueDefinedFunction() throws Exception {
            calculator.defineFunction("fn1", "b");
            calculator.defineFunction("fn2", "fn1");
            assertEqualsValue(calculator, "fn2", "1,00");
        }

        @Test
        @DisplayName("should not define function with value not existing function")
        public void shouldNotDefineFunctionWithValueNotExistingFunction() {
            String expected = "Идентификатор fn3 не определён";
            exception = assertThrows(Exception.class, () -> calculator.defineFunction("fn2", "fn3"));
            assertEquals(exception.getMessage(), expected);
        }

        @Test
        @DisplayName("should define function with two operands and existing operation")
        public void shouldDefineFunctionWithTwoOperandsAndExistingOperation() throws Exception {
            calculator.defineFunction("fn", "b", Operation.ADD, "b");
            assertEqualsValue(calculator, "fn", "2,00");
        }
    }

    @Nested
    @DisplayName("command - print")
    class CommandPrint {
        @BeforeEach
        public void setVariablesAndFunctions() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", Double.parseDouble("1"));
            calculator.defineFunction("fn1", "a");
            calculator.defineFunction("fn2", "b");
        }

        @Test
        @DisplayName("should print not defined value of variable")
        public void shouldPrintNotDefinedValueOfVariable() throws Exception {
            String output = formattingDigit(calculator.getValue("a"));
            assertEquals(output, "NaN");
        }

        @Test
        @DisplayName("should print defined value of variable")
        public void shouldPrintDefinedValueOfVariable() throws Exception {
            String output = formattingDigit(calculator.getValue("b"));
            assertEquals(output, "1,00");
        }

        @Test
        @DisplayName("should print not defined value of function")
        public void shouldPrintNotDefinedValueOfFunction() throws Exception {
            String output = formattingDigit(calculator.getValue("fn1"));
            assertEquals(output, "NaN");
        }

        @Test
        @DisplayName("should print defined value of function")
        public void shouldPrintDefinedValueOfFunction() throws Exception {
            String output = formattingDigit(calculator.getValue("fn2"));
            assertEquals(output, "1,00");
        }

        @Test
        @DisplayName("should print not defined variable")
        public void shouldPrintNotDefinedVariable() {
            String expected = "Идентификатор c не определён";
            exception = assertThrows(Exception.class, () -> calculator.getValue("c"));
            assertEquals(exception.getMessage(), expected);
        }

        @Test
        @DisplayName("should print not defined variable")
        public void shouldPrintNotDefinedFunction() {
            String expected = "Идентификатор fn3 не определён";
            exception = assertThrows(Exception.class, () -> calculator.getValue("fn3"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - printvars")
    class CommandPrintVars {
        @Test
        @DisplayName("should print values of not existing variables")
        public void shouldPrintValuesOfNotExistingVariables() {
            Map<String, Double> output = calculator.getVariables();
            Map<String, Double> expected = new HashMap<>();
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("should print values of existing variables")
        public void shouldPrintValuesOfExistingVariables() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", Double.parseDouble("1"));
            Map<String, Double> expected = new HashMap<>();
            expected.put("a", Double.NaN);
            expected.put("b", Double.parseDouble("1"));
            assertEquals(calculator.getVariables(), expected);
        }
    }

    @Nested
    @DisplayName("command - printfns")
    class CommandPrintFns {
        @Test
        @DisplayName("should print values of not existing functions")
        public void shouldPrintValuesOfNotExistingFunctions() {
            Map<String, Function> output = calculator.getFunctions();
            Map<String, Function> expected = new LinkedHashMap<>();
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("should print values of existing variables")
        public void shouldPrintValuesOfExistingFunctions() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", Double.parseDouble("1"));
            calculator.defineFunction("fn1", "a");
            calculator.defineFunction("fn2", "b");
            Map<String, Function> output = calculator.getFunctions();
            assertEquals(output.get("fn1").getResult(), Double.NaN, 5e-5);
            assertEquals(output.get("fn2").getResult(), Double.parseDouble("1"), 5e-5);
        }
    }
}
