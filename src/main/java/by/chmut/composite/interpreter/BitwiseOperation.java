package by.chmut.composite.interpreter;

public enum BitwiseOperation {

    NOT("~", c -> c.push(~c.pop()), 5),
    LEFT_SHIFT("<<", c -> {long temp = c.pop(); c.push(c.pop() << temp);}, 4),
    SIGNED_RIGHT_SHIFT(">>", c -> {long temp = c.pop(); c.push(c.pop() >> temp);}, 4),
    UNSIGNED_RIGHT_SHIFT(">>>", c -> {long temp = c.pop(); c.push(c.pop() >>> temp);}, 4),
    AND("&", c -> c.push(c.pop() & c.pop()), 3),
    XOR("^", c -> c.push(c.pop() ^ c.pop()), 2),
    OR("|", c -> c.push(c.pop() | c.pop()), 1);

    private String bitwiseOperator;
    private BitwiseExpression bitwiseExpressionForCalculate;
    private int bitwisePriority;

    BitwiseOperation(String bitwiseOperator, BitwiseExpression bitwiseExpressionForCalculate, int priority) {
        this.bitwiseOperator = bitwiseOperator;
        this.bitwiseExpressionForCalculate = bitwiseExpressionForCalculate;
        this.bitwisePriority = priority;
    }

    public String getBitwiseOperator() {
        return bitwiseOperator;
    }

    public BitwiseExpression getBitwiseExpressionForCalculate() {
        return bitwiseExpressionForCalculate;
    }

    public int getBitwisePriority() {
        return bitwisePriority;
    }

    public static int getPriority(String operation) {
        for (BitwiseOperation bitwiseOperation : BitwiseOperation.values()) {
            if (operation.equals(bitwiseOperation.getBitwiseOperator())) {
                return bitwiseOperation.getBitwisePriority();
            }
        }
        return 0;
    }
}
