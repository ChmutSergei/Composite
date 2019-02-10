package by.chmut.composite.parser;

import by.chmut.composite.interpreter.BitwiseOperation;
import by.chmut.composite.interpreter.BitwiseExpression;
import by.chmut.composite.interpreter.BitwiseExpressionConverter;

import java.util.ArrayList;
import java.util.List;

public class BitwiseExpressionParser {

    public static List<BitwiseExpression> parse(String source) {
        List<BitwiseExpression> expressions = new ArrayList<>();
        List<String> polishNotation = BitwiseExpressionConverter.convert(source);
        for (String element : polishNotation) {
            if (Character.isDigit(element.charAt(0))) {
                expressions.add(c -> c.push(Long.parseLong(element)));
            } else {
                for (BitwiseOperation operator : BitwiseOperation.values()) {
                    if (element.equals(operator.getBitwiseOperator())) {
                        expressions.add(operator.getBitwiseExpressionForCalculate());
                    }
                }
            }
        }
        return expressions;
    }
}
