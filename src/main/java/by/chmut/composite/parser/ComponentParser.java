package by.chmut.composite.parser;

import by.chmut.composite.component.CharComponent;
import by.chmut.composite.component.Component;
import by.chmut.composite.component.ComponentType;
import by.chmut.composite.component.TextComposite;
import by.chmut.composite.interpreter.BitwiseExpression;
import by.chmut.composite.interpreter.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.chmut.composite.component.ComponentType.*;

public class ComponentParser {

    private static final String REGEX_FOR_BITWISE_EXPRESSION = "((\\((?=[\\d~])|(\\d)+(?=[^ \\w])|(~)).[^ ]+)";
    private static final String FLAG_FOR_REPLACEMENT = "@";
    private static final String REGEX_FOR_CUT_STRING = "@.*";
    private static final Pattern bitwiseExpression = Pattern.compile(REGEX_FOR_BITWISE_EXPRESSION);

    private ComponentType type;

    private ComponentParser successor = DefaultComponentParser.getDefaultParser();

    public ComponentParser() {
    }

    public ComponentParser(ComponentType type) {
        this.type = type;
    }

    public void setSuccessor(ComponentParser successor) {
        this.successor = successor;
    }

    public Component chain(String source) {
        if (!isContainsComponentType(source)) {
            return successor.chain(source);
        }
        List<String> parts = parse(source);
        TextComposite composite = new TextComposite(type);
        for (String partOfSource : parts) {
            Component component = successor.chain(partOfSource);
            if (component == null) {
                continue;
            }
            composite.add(component);
        }
        return composite;
    }

    private List<String> parse(String source) {
        if (type == SENTENCE) {
            source = calculateBitwiseExpression(source);
        }
        Pattern pattern = Pattern.compile(type.getRegexForParse());
        Matcher matcher = pattern.matcher(source);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            String foundComponent = matcher.group();
            String notIncluded = "";
            if (!source.startsWith(foundComponent)) {
                notIncluded = cutNotIncludedPart(source, foundComponent);
                result.add(notIncluded);
            }
            source = source.substring(foundComponent.length() + notIncluded.length());
            result.add(foundComponent);
        }
        if (!source.isEmpty()) {
            result.add(source);
        }
        return result;
    }

    private String calculateBitwiseExpression(String source) {
        Matcher bitwiseMatcher = bitwiseExpression.matcher(source);
        while (bitwiseMatcher.find()) {
            Context context = new Context();
            List<BitwiseExpression> expressions = BitwiseExpressionParser.parse(bitwiseMatcher.group());
            for (BitwiseExpression expression : expressions) {
                expression.interpret(context);
            }
            Long number = context.pop();
            source = source.replaceFirst(REGEX_FOR_BITWISE_EXPRESSION, String.valueOf(number));
        }
        return source;
    }

    private String cutNotIncludedPart(String source, String partOfSource) {
        String frontPart = source.replace(partOfSource, FLAG_FOR_REPLACEMENT);
        frontPart = frontPart.replaceFirst(REGEX_FOR_CUT_STRING, "");
        return frontPart;
    }

    private boolean isContainsComponentType(String source) {
        Pattern pattern = Pattern.compile(type.getRegexForParse());
        Matcher matcher = pattern.matcher(source);
        return matcher.find();
    }

    private static class DefaultComponentParser extends ComponentParser {

        private static final String TABULATION = "\u0020\u0020\u0020\u0020";
        private static final String SPACE = "\u0020";
        private static final Pattern patternParseLiteral = Pattern.compile(LITERAL.getRegexForParse());
        private static final Pattern patternParseWord = Pattern.compile(WORD.getRegexForParse());

        private static DefaultComponentParser defaultParser = new DefaultComponentParser();

        private DefaultComponentParser() {
        }

        public static DefaultComponentParser getDefaultParser() {
            return defaultParser;
        }

        @Override
        public Component chain(String source) {
            if (source.equals(SPACE) || source.equals(TABULATION)) {
                return null;
            }
            Matcher word = patternParseLiteral.matcher(source);
            if (!word.find()) {
                return selectActualComponent(source);
            }
            return createWordComponent(source);
        }

        private TextComposite createWordComponent(String source) {
            TextComposite composite = new TextComposite(WORD);
            Matcher matcher = patternParseWord.matcher(source);
            while (matcher.find()) {
                CharComponent component = selectActualComponent(matcher.group());
                composite.add(component);
            }
            return composite;
        }

        private CharComponent selectActualComponent(String source) {
            if (Character.isLetter(source.charAt(0))) {
                return new CharComponent(ComponentType.LETTER, source.charAt(0));
            }
            return new CharComponent(ComponentType.MARK, source.charAt(0));
        }
    }
}
