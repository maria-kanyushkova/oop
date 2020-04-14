package lab3.calculator;

enum  Operation {
    // TODO: kick null from operations, null is default value
    NULL(" "),
    ADD("+"),
    DIV("/"),
    MUL("*"),
    SUB("-");

    private final String text;

    Operation(String operator) {
        this.text = operator;
    }

    public final String toString() {
        return text;
    }

    public final boolean equals(String value) {
        return text.equals(value);
    }
}
