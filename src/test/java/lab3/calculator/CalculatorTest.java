package lab3.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    private Calculator calculator;
    private Exception exception;

    // todo: добавить тесты на то, что аргументов не достаточно пришло для осуществления операции?

    private void assertEqualsValue(Calculator calculator, String name, String expected) {
        String output = calculator.getValue(name);
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
        @DisplayName("объявление переменной")
        public void should1() {
            String input = "a";
            calculator.defineVariable(input);
            String expected = "NaN";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление переменной с занятым именем")
        public void should2() {
            String input = "a";
            calculator.defineVariable(input);
            String expected = "NaN";
            assertEqualsValue(calculator, input, expected);
            expected = "Нельзя объявить переменную повторно";
            exception = assertThrows(Exception.class, () -> calculator.defineVariable(input));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - let")
    class CommandLet {
        @Test
        @DisplayName("объявление переменной со значением")
        public void should3() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление переменной со занятым названием с другим значением (обновление значения)")
        public void should4() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "a";
            value = "2";
            calculator.defineVariable(input, value);
            expected = "2.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление переменной = значение другой переменной")
        public void should5() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "b";
            value = "a";
            calculator.defineVariable(input, value);
            expected = "1.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление переменной, попытаться присвоить значение несуществующей переменной")
        public void should6() {
            String input = "a";
            String value = "b";
            String expected = "Попытка присвоить значение не существующей переменной";
            exception = assertThrows(Exception.class, () -> calculator.defineVariable(input, value));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - fr")
    class CommandFr {
        @Test
        @DisplayName("объявление функции = переменной без значения")
        public void should7() {
            String input = "a";
            calculator.defineVariable(input);
            String expected = "NaN";
            assertEqualsValue(calculator, input, expected);
            input = "fn1";
            String value = "a";
            calculator.defineFunction(input, value);
            expected = "NaN";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление функции = переменной со значения")
        public void should08() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "fn1";
            value = "a";
            calculator.defineFunction(input, value);
            expected = "1.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление функции = имя функции")
        public void should09() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "fn1";
            value = "a";
            calculator.defineFunction(input, value);
            expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "fn2";
            value = "fn1";
            calculator.defineFunction(input, value);
            expected = "1.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление функции = имя не существующей функции")
        public void should10() {
            String input = "fn2";
            String value = "fn3";
            String expected = "Попытка присвоить значение не существующей функции";
            exception = assertThrows(Exception.class, () -> calculator.defineFunction(input, value));
            assertEquals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("объявление функции = 2 индентификатора и существующая операция")
        public void should11() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            String nameFn = "fn";
            String leftOperand = "a";
            String operation = "+";
            String rightOperand = "a";
            calculator.defineFunction(nameFn, leftOperand, operation, rightOperand);
            expected = "2.00";
            assertEqualsValue(calculator, input, expected);
        }

        @Test
        @DisplayName("объявление функции = 2 индентификатора и несуществующая операция")
        public void should12() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            String nameFn = "fn";
            String leftOperand = "a";
            String operation = "^";
            String rightOperand = "a";
            expected = "Попытка произвести не существующую операцию";
            exception = assertThrows(Exception.class, () -> calculator.defineFunction(nameFn, leftOperand, operation, rightOperand));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - print")
    class CommandPrint {
        @Test
        @DisplayName("печать значения существующей переменной")
        public void should13() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            String output = calculator.getValue(input);
            expected = "1.00";
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей переменной с неизвестным значением")
        public void should14() {
            String input = "a";
            calculator.defineVariable(input);
            String expected = "NaN";
            assertEqualsValue(calculator, input, expected);
            String output = calculator.getValue(input);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей функции")
        public void should15() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "fn1";
            value = "a";
            calculator.defineFunction(input, value);
            assertEqualsValue(calculator, input, expected);
            String output = calculator.getValue(input);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей функции с неизвестным значением")
        public void should16() {
            String input = "a";
            calculator.defineVariable(input);
            String expected = "NaN";
            assertEqualsValue(calculator, input, expected);
            input = "fn1";
            String value = "a";
            calculator.defineFunction(input, value);
            assertEqualsValue(calculator, input, expected);
            String output = calculator.getValue(input);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения не существующей переменной")
        public void should17() {
            String params = "a";
            String expected = "Попытка печати значения не существующей переменной";
            exception = assertThrows(Exception.class, () -> calculator.getValue(params));
            assertEquals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("печать значения не существующей функции")
        public void should18() {
            String params = "fn";
            String expected = "Попытка печати значения не существующей функции";
            exception = assertThrows(Exception.class, () -> calculator.getValue(params));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - printvars")
    class CommandPrintVars {
        @Test
        @DisplayName("печать переменных когда их нет")
        public void should19() {
            String expected = "";
            String output = calculator.getVariablesValue();
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("печать переменных когда они есть")
        public void should20() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            expected = "a:1.00\n";
            String output = calculator.getVariablesValue();
            assertEquals(expected, output);
        }
    }

    @Nested
    @DisplayName("command - printfns")
    class CommandPrintFns {
        @Test
        @DisplayName("печать функций которых нет")
        public void should21() {
            String expected = "";
            String output = calculator.getFunctionsValue();
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("печать функций которые есть")
        public void should22() {
            String input = "a";
            String value = "1";
            calculator.defineVariable(input, value);
            String expected = "1.00";
            assertEqualsValue(calculator, input, expected);
            input = "fn";
            value = "a";
            calculator.defineFunction(input, value);
            assertEqualsValue(calculator, input, expected);
            expected = "fn:1.00\n";
            String output = calculator.getFunctionsValue();
            assertEquals(expected, output);
        }
    }

    @Nested
    @DisplayName("command - complex")
    class ComplexCommand {
        @Test
        @DisplayName("проверка фибоначи")
        public void should23() {
        }
    }
}
