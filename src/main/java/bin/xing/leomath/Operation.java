package bin.xing.leomath;

public enum Operation {
    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('x'),
    DIVISION('÷');

    private char operator;

    Operation(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return operator;
    }
}
