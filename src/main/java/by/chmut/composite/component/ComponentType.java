package by.chmut.composite.component;

public enum ComponentType {

    TEXT(".+?(?=\\s{4}|$)"),
    PARAGRAPH("[A-Z].*?[.!?](?=\\s|$)"),
    SENTENCE("([^ \\t\\r\\n\\f])+(?=[A-Z])*"),
    LITERAL("([\\w])+([^ \\W]*)"),
    WORD(".{1}"),
    LETTER,
    MARK;

    private String regexForParse = "";

    ComponentType() {
    }

    ComponentType(String regexForParse) {
        this.regexForParse = regexForParse;
    }

    public String getRegexForParse() {
        return regexForParse;
    }
}
