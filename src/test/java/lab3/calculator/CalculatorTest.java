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
        @DisplayName("объявление переменной")
        public void should1() throws Exception {
            calculator.defineVariable("a");
            assertEqualsValue(calculator, "a", "NaN");
        }

        @Test
        @DisplayName("объявление переменной с занятым именем")
        public void should2() throws Exception {
            calculator.defineVariable("a");
            String expected = "Идентификатор b уже определён";
            exception = assertThrows(Exception.class, () -> calculator.defineVariable("a"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - let")
    class CommandLet {
        @Test
        @DisplayName("объявление переменной со значением")
        public void should3() throws Exception {
            calculator.defineVariable("a", "1");
            assertEqualsValue(calculator, "a", "1,00");
        }

        @BeforeEach
        public void setVariables() throws Exception {
            calculator.defineVariable("a", "1");
        }

        @Test
        @DisplayName("объявление переменной с занятым названием и другим значением (обновление значения)")
        public void should4() throws Exception {
            calculator.defineVariable("a", "2");
            assertEqualsValue(calculator, "a", "2,00");
        }

        @Test
        @DisplayName("объявление переменной = значение другой переменной")
        public void should5() throws Exception {
            calculator.defineVariable("b", "a");
            assertEqualsValue(calculator, "b", "1,00");
        }

        @Test
        @DisplayName("объявление переменной, попытаться присвоить значение несуществующей переменной")
        public void should6() {
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
            calculator.defineVariable("b", "1");
        }

        @Test
        @DisplayName("объявление функции = переменной без значения")
        public void should7() throws Exception {
            calculator.defineFunction("fn1", "a");
            assertEqualsValue(calculator, "fn1", "NaN");
        }

        @Test
        @DisplayName("объявление функции = переменной со значением")
        public void should08() throws Exception {
            calculator.defineFunction("fn1", "b");
            assertEqualsValue(calculator, "fn1", "1,00");
        }

        @Test
        @DisplayName("объявление функции = имя функции")
        public void should09() throws Exception {
            calculator.defineFunction("fn1", "b");
            calculator.defineFunction("fn2", "fn1");
            assertEqualsValue(calculator, "fn2", "1,00");
        }

        @Test
        @DisplayName("объявление функции = имя не существующей функции")
        public void should10() {
            String expected = "Идентификатор fn3 не определён";
            exception = assertThrows(Exception.class, () -> calculator.defineFunction("fn2", "fn3"));
            assertEquals(exception.getMessage(), expected);
        }

        @Test
        @DisplayName("объявление функции = 2 Идентификатора и существующая операция")
        public void should11() throws Exception {
            calculator.defineFunction("fn", "b", "+", "b");
            assertEqualsValue(calculator, "fn", "2,00");
        }

        @Test
        @DisplayName("объявление функции = 2 Идентификатора и несуществующая операция")
        public void should12() {
            String expected = "Операция не определена";
            exception = assertThrows(Exception.class, () -> calculator.defineFunction("fn", "b", "^", "b"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - print")
    class CommandPrint {
        @BeforeEach
        public void setVariablesAndFunctions() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", "1");
            calculator.defineFunction("fn1", "a");
            calculator.defineFunction("fn2", "b");
        }

        @Test
        @DisplayName("печать значения существующей переменной с неизвестным значением")
        public void should14() throws Exception {
            String output = formattingDigit(calculator.getValue("a"));
            assertEquals(output, "NaN");
        }

        @Test
        @DisplayName("печать значения существующей переменной")
        public void should13() throws Exception {
            String output = formattingDigit(calculator.getValue("b"));
            assertEquals(output, "1,00");
        }

        @Test
        @DisplayName("печать значения существующей функции")
        public void should15() throws Exception {
            String output = formattingDigit(calculator.getValue("fn1"));
            assertEquals(output, "NaN");
        }

        @Test
        @DisplayName("печать значения существующей функции с неизвестным значением")
        public void should16() throws Exception {
            String output = formattingDigit(calculator.getValue("fn2"));
            assertEquals(output, "1,00");
        }

        @Test
        @DisplayName("печать значения не существующей переменной")
        public void should17() {
            String expected = "Идентификатор c не определён";
            exception = assertThrows(Exception.class, () -> calculator.getValue("c"));
            assertEquals(exception.getMessage(), expected);
        }

        @Test
        @DisplayName("печать значения не существующей функции")
        public void should18() {
            String expected = "Идентификатор fn3 не определён";
            exception = assertThrows(Exception.class, () -> calculator.getValue("fn3"));
            assertEquals(exception.getMessage(), expected);
        }
    }

    @Nested
    @DisplayName("command - printvars")
    class CommandPrintVars {
        @Test
        @DisplayName("печать переменных когда их нет")
        public void should19() {
            String output = calculator.getVariablesValue();
            assertEquals(output, "");
        }

        @Test
        @DisplayName("печать переменных когда они есть")
        public void should20() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", "1");
            String expected = "a:NaN\n" + "b:1,00\n";
            assertEquals(calculator.getVariablesValue(), expected);
        }
    }

    @Nested
    @DisplayName("command - printfns")
    class CommandPrintFns {
        @Test
        @DisplayName("печать функций которых нет")
        public void should21() {
            String output = calculator.getFunctionsValue();
            assertEquals(output, "");
        }

        @Test
        @DisplayName("печать функций которые есть")
        public void should22() throws Exception {
            calculator.defineVariable("a");
            calculator.defineVariable("b", "1");
            calculator.defineFunction("fn1", "a");
            calculator.defineFunction("fn2", "b");
            String expected = "fn1:NaN\n" + "fn2:1,00\n";
            assertEquals(calculator.getFunctionsValue(), expected);
        }
    }

    @Nested
    @DisplayName("complex test")
    class ComplexCommand {
        @Test
        @DisplayName("Док: Объявление, присваивание и вывод значений переменных")
        public void should23() throws Exception {
            calculator.defineVariable("x");

            String expected = "NaN";
            String output = formattingDigit(calculator.getValue("x"));
            assertEquals(expected, output);

            calculator.defineVariable("x", "42");

            expected = "42,00";
            output = formattingDigit(calculator.getValue("x"));
            assertEquals(expected, output);

            calculator.defineVariable("x", "1.234");

            expected = "1,23";
            output = formattingDigit(calculator.getValue("x"));
            assertEquals(expected, output);

            calculator.defineVariable("y", "x");
            calculator.defineVariable("x", "99");

            output = calculator.getVariablesValue();
            expected = "x:99,00\n" +
                    "y:1,23\n";
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("Док: Объявление функций")
        public void should24() throws Exception {
            calculator.defineVariable("x");
            calculator.defineVariable("y");
            calculator.defineFunction("XPlusY", "x", "+", "y");

            String expected = "NaN";
            String output = formattingDigit(calculator.getValue("XPlusY"));
            assertEquals(expected, output);

            calculator.defineVariable("x", "3");
            calculator.defineVariable("y", "4");

            expected = "7,00";
            output = formattingDigit(calculator.getValue("XPlusY"));
            assertEquals(expected, output);

            calculator.defineVariable("x", "10");

            expected = "14,00";
            output = formattingDigit(calculator.getValue("XPlusY"));
            assertEquals(expected, output);

            calculator.defineVariable("z", "3.5");
            calculator.defineFunction("XPlusYDivZ", "XPlusY", "/", "z");

            output = calculator.getFunctionsValue();
            expected = "XPlusY:14,00\n" +
                    "XPlusYDivZ:4,00\n";
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("Док: Еще раз про различие между fn и let")
        public void should25() throws Exception {
            calculator.defineVariable("v", "42");
            calculator.defineVariable("variable", "v");
            calculator.defineFunction("function", "v");
            calculator.defineVariable("v", "43");

            String expected = "42,00";
            String output = formattingDigit(calculator.getValue("variable"));
            assertEquals(expected, output);

            expected = "43,00";
            output = formattingDigit(calculator.getValue("function"));
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("Док: Вычисление площади круга")
        public void should26() throws Exception {
            calculator.defineVariable("radius");
            calculator.defineVariable("pi", "3.14159265");
            calculator.defineFunction("radiusSquared", "radius", "*", "radius");
            calculator.defineFunction("circleArea", "pi", "*", "radiusSquared");
            calculator.defineVariable("radius", "10");

            String expected = "314,16";
            String output = formattingDigit(calculator.getValue("circleArea"));
            assertEquals(expected, output);

            calculator.defineVariable("circle10Area", "circleArea");
            calculator.defineVariable("radius", "20");
            calculator.defineVariable("circle20Area", "circleArea");

            expected = "radiusSquared:400,00\n" +
                    "circleArea:1256,64\n";
            output = calculator.getFunctionsValue();
            assertEquals(expected, output);

            expected = "pi:3,14\n" +
                    "radius:20,00\n" +
                    "circle10Area:314,16\n" +
                    "circle20Area:1256,64\n";
            output = calculator.getVariablesValue();
            assertEquals(expected, output);
        }

        @Test
        @DisplayName("Док: Вычисление последовательности Фибоначчи")
        public void should27() throws Exception {
            calculator.defineVariable("v0", "0");
            calculator.defineVariable("v1", "1");
            calculator.defineFunction("fib0", "v0");
            calculator.defineFunction("fib1", "v1");
            calculator.defineFunction("fib2", "fib1", "+", "fib0");
            calculator.defineFunction("fib3", "fib2", "+", "fib1");
            calculator.defineFunction("fib4", "fib3", "+", "fib2");
            calculator.defineFunction("fib5", "fib4", "+", "fib3");
            calculator.defineFunction("fib6", "fib5", "+", "fib4");

            String expected = "fib0:0,00\n" +
                    "fib1:1,00\n" +
                    "fib2:1,00\n" +
                    "fib3:2,00\n" +
                    "fib4:3,00\n" +
                    "fib5:5,00\n" +
                    "fib6:8,00\n";
            String output = calculator.getFunctionsValue();
            assertEquals(expected, output);

            calculator.defineVariable("v0", "1");
            calculator.defineVariable("v1", "1");

            expected = "fib0:1,00\n" +
                    "fib1:1,00\n" +
                    "fib2:2,00\n" +
                    "fib3:3,00\n" +
                    "fib4:5,00\n" +
                    "fib5:8,00\n" +
                    "fib6:13,00\n";
            output = calculator.getFunctionsValue();
            assertEquals(expected, output);
        }
    }
}
