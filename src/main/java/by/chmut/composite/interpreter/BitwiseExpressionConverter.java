package by.chmut.composite.interpreter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitwiseExpressionConverter {

    private static final String REGEX_FOR_SPLIT_ON_OPERANDS_AND_OPERATORS =
                                        "(<<)|(>>>)|(>>)|(\\D(?=[\\d()|~&]|))|(\\d+(?=[\\D]|))";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";

    public static List<String> convert(String source) {
        List<String> members = splitOnOperandsAndOperators(source);
        List<String> polishNotation = new ArrayList<>();
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String member : members) {
            if (Character.isDigit(member.charAt(0))) {
                polishNotation.add(member);
            } else if (member.equals(LEFT_BRACKET) || member.equals(BitwiseOperation.NOT.getBitwiseOperator())) {
                stack.push(member);
            } else if (member.equals(RIGHT_BRACKET)) {
                while (!stack.getFirst().equals(LEFT_BRACKET)) {
                    polishNotation.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && (stack.getFirst().equals(BitwiseOperation.NOT.getBitwiseOperator()) ||
                        BitwiseOperation.getPriority(stack.getFirst()) >= BitwiseOperation.getPriority(member))) {
                    polishNotation.add(stack.pop());
                }
                stack.push(member);
            }
        }
        polishNotation.addAll(stack);
        return polishNotation;
    }

    private static List<String> splitOnOperandsAndOperators(String expression) {
        Pattern pattern = Pattern.compile(REGEX_FOR_SPLIT_ON_OPERANDS_AND_OPERATORS);
        Matcher matcher = pattern.matcher(expression);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group());
        }
        return result;
    }
}
