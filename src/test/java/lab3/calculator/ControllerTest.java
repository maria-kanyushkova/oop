package lab3.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {
    private Controller controller;
    private Exception exception;

    private void assertChangeVariable(Controller controller, String[] values, String expected) {
        controller.defineVariable(values);
        String output = controller.getValue(values[0]);
        assertEquals(output, expected);
    }

    private void assertChangeFunction(Controller controller, String[] values, String expected) {
        controller.defineFunction(values);
        String output = controller.getValue(values[0]);
        assertEquals(output, expected);
    }

    @BeforeEach
    public void init() {
        controller = new Controller();
    }

    @Nested
    @DisplayName("command - var")
    class CommandVar {
        @Test
        @DisplayName("объявление переменной")
        public void should1() {
            String[] input = new String[] {"a"};
            String expected = "NaN";
            assertChangeVariable(controller, input, expected);
        }

        @Test
        @DisplayName("объявление переменной с занятым именем")
        public void should2() {
            String[] input = new String[] {"a"};
            String expected = "NaN";
            assertChangeVariable(controller, input, expected);
            expected = "Нельзя объявить переменную повторно";
            exception = assertThrows(Exception.class, () -> controller.defineVariable(input));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - let")
    class CommandLet {
        @Test
        @DisplayName("объявление переменной со значением")
        public void should3() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
        }

        @Test
        @DisplayName("объявление переменной со занятым названием с другим значением (обновление значения)")
        public void should4() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"a", "2"};
            expected = "2";
            assertChangeVariable(controller, input, expected);
        }

        @Test
        @DisplayName("объявление переменной = значение другой переменной")
        public void should5() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"b", "a"};
            expected = "1";
            assertChangeVariable(controller, input, expected);
        }

        @Test
        @DisplayName("объявление переменной, попытаться присвоить значение несуществующей переменной")
        public void should6() {
            String[] input = new String[] {"a", "b"};
            String expected = "Попытка присвоить значение не существующей переменной";
            exception = assertThrows(Exception.class, () -> controller.defineVariable(input));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - fr")
    class CommandFr {
        @Test
        @DisplayName("объявление функции = переменной без значения")
        public void should7() {
            String[] input = new String[] {"a"};
            String expected = "NaN";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn1", "a"};
            expected = "NaN";
            assertChangeFunction(controller, input, expected);
        }

        @Test
        @DisplayName("объявление функции = переменной со значения")
        public void should08() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn1", "a"};
            expected = "1";
            assertChangeFunction(controller, input, expected);
        }

        @Test
        @DisplayName("объявление функции = имя функции")
        public void should09() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn1", "a"};
            expected = "1";
            assertChangeFunction(controller, input, expected);
            input = new String[] {"fn2", "fn1"};
            expected = "1";
            assertChangeFunction(controller, input, expected);
        }

        @Test
        @DisplayName("объявление функции = имя не существующей функции")
        public void should10() {
            String[] input = new String[] {"fn2", "fn3"};
            String expected = "Попытка присвоить значение не существующей функции";
            exception = assertThrows(Exception.class, () -> controller.defineFunction(input));
            assertEquals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("объявление функции = 2 индентификатора и существующая операция")
        public void should11() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn", "a", "+", "a"};
            expected = "2";
            assertChangeFunction(controller, input, expected);
        }

        @Test
        @DisplayName("объявление функции = 2 индентификатора и несуществующая операция")
        public void should12() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn", "a", "^", "a"};
            expected = "Попытка произвести не существующую операцию";
            String[] finalInput = input;
            exception = assertThrows(Exception.class, () -> controller.defineFunction(finalInput));
            assertEquals(expected, exception.getMessage());
        }
    }

    @Nested
    @DisplayName("command - print")
    class CommandPrint {
        @Test
        @DisplayName("печать значения существующей переменной")
        public void should13() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            String[] params = new String[] {"a"};
            String output = controller.printIdentifier(params);
            expected = "1.00";
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей переменной с неизвестным значением")
        public void should14() {
            String[] input = new String[] {"a"};
            String expected = "NaN";
            assertChangeVariable(controller, input, expected);
            String[] params = new String[] {"a"};
            String output = controller.printIdentifier(params);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей функции")
        public void should15() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn1", "a"};
            expected = "1";
            assertChangeFunction(controller, input, expected);
            expected = "1.00";
            String[] params = new String[] {"fn1"};
            String output = controller.printIdentifier(params);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения существующей функции с неизвестным значением")
        public void should16() {
            String[] input = new String[] {"a"};
            String expected = "NaN";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn1", "a"};
            assertChangeFunction(controller, input, expected);
            String[] params = new String[] {"fn1"};
            String output = controller.printIdentifier(params);
            assertEquals(output, expected);
        }

        @Test
        @DisplayName("печать значения не существующей переменной")
        public void should17() {
            String[] params = new String[] {"a"};
            String expected = "Попытка печати значения не существующей переменной";
            exception = assertThrows(Exception.class, () -> controller.printIdentifier(params));
            assertEquals(expected, exception.getMessage());
        }

        @Test
        @DisplayName("печать значения не существующей функции")
        public void should18() {
            String[] params = new String[] {"fn"};
            String expected = "Попытка печати значения не существующей функции";
            exception = assertThrows(Exception.class, () -> controller.printIdentifier(params));
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
            String output = controller.printVariables();
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("печать переменных когда они есть")
        public void should20() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            expected = "a:1.00\n";
            String output = controller.printVariables();
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
            String output = controller.printFunctions();
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("печать функций которые есть")
        public void should22() {
            String[] input = new String[] {"a", "1"};
            String expected = "1";
            assertChangeVariable(controller, input, expected);
            input = new String[] {"fn", "a"};
            assertChangeFunction(controller, input, expected);
            expected = "fn:1.00\n";
            String output = controller.printFunctions();
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
