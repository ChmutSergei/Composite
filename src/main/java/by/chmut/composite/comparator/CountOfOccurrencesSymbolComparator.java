package by.chmut.composite.comparator;

import by.chmut.composite.component.Component;

import java.util.Comparator;

public class CountOfOccurrencesSymbolComparator implements Comparator<Component> {

    private char symbol;

    public CountOfOccurrencesSymbolComparator(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public int compare(Component component1, Component component2) {
        int countOccurrences1 = countOccurrencesSymbol(component1);
        int countOccurrences2 = countOccurrencesSymbol(component2);
        if (countOccurrences1 - countOccurrences2 < 0) {
            return 1;
        } else if (countOccurrences1 - countOccurrences2 > 0) {
            return -1;
        }
        return component1.toString().compareTo(component2.toString());
    }

    private int countOccurrencesSymbol(Component component) {
        String literal = component.toString();
        return (int) literal.chars().filter(ch -> ch == symbol).count();
    }
}
