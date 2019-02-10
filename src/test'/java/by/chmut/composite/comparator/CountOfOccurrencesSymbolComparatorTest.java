package by.chmut.composite.comparator;

import by.chmut.composite.component.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CountOfOccurrencesSymbolComparatorTest {

    private TextComposite componentWithSymbol;
    private TextComposite componentWithoutSymbol;
    private TextComposite componentWithSymbol2;
    private CountOfOccurrencesSymbolComparator comparator;

    @BeforeMethod
    public void setUp() {
        char symbol = 's';
        Component letter = new CharComponent(ComponentType.LETTER, 's');
        Component letter2 = new CharComponent(ComponentType.LETTER, 'a');
        Component mark = new CharComponent(ComponentType.MARK, '?');
        componentWithSymbol = new TextComposite(ComponentType.LITERAL);
        componentWithSymbol.add(letter);
        componentWithSymbol2 = new TextComposite(ComponentType.LITERAL);
        componentWithSymbol2.add(letter2);
        componentWithoutSymbol = new TextComposite(ComponentType.LITERAL);
        componentWithoutSymbol.add(mark);
        comparator = new CountOfOccurrencesSymbolComparator(symbol);
    }

    @AfterMethod
    public void tearDown() {
        componentWithSymbol = null;
        componentWithoutSymbol = null;
        comparator = null;
    }

    @Test(description = "In this test first component include symbol(2nd - without symbol) and expected comparator return -1")
    public void compareTest1() {
        int actual = comparator.compare(componentWithSymbol,componentWithoutSymbol);
        int expected = -1;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "In this test first component without symbol(2nd - with symbol) and expected comparator return 1")
    public void compareTest2() {
        int actual = comparator.compare(componentWithoutSymbol, componentWithSymbol);
        int expected = 1;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "In this test 2 component without symbol so they are compared alphabetically" +
            "and expected comparator return number < 0, because component2 included letter \"а\" ")
    public void compareTest3() {
        int actual = comparator.compare(componentWithoutSymbol, componentWithSymbol2);
        boolean expected = actual < 0;
        Assert.assertTrue(expected);
    }

    @Test(description = "In this test 2 component without symbol so they are compared alphabetically" +
            "and expected comparator return number > 0, because component1 included letter \"а\" ")
    public void compareTest4() {
        int actual = comparator.compare(componentWithSymbol2, componentWithoutSymbol);
        boolean expected = actual > 0;
        Assert.assertTrue(expected);
    }

    @Test(description = "In this test first component equals second component (contains symbol) - expected 0")
    public void compareTest5() {
        int actual = comparator.compare(componentWithSymbol, componentWithSymbol);
        int expected = 0;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "In this test first component equals second component (without symbol) - expected 0")
    public void compareTest6() {
        int actual = comparator.compare(componentWithoutSymbol, componentWithoutSymbol);
        int expected = 0;
        Assert.assertEquals(actual, expected);
    }

}
