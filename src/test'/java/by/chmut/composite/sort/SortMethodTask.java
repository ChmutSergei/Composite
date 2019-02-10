package by.chmut.composite.sort;

import by.chmut.composite.comparator.CountOfOccurrencesSymbolComparator;
import by.chmut.composite.component.Component;
import by.chmut.composite.component.ComponentType;
import by.chmut.composite.component.TextComposite;
import by.chmut.composite.parser.ComponentParser;
import by.chmut.composite.reader.DataReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

public class SortMethodTask {

    private static final char EXAMPLE_CHAR = 's';
    private static final String FILE = "/text.txt";
    private ComponentParser textParser;
    private TextComposite text;
    private String source;
    private Comparator<Component> comparator;

    @BeforeMethod
    public void setUp() {
        ComponentParser literalParser = new ComponentParser(ComponentType.LITERAL);
        ComponentParser sentenceParser = new ComponentParser(ComponentType.SENTENCE);
        sentenceParser.setSuccessor(literalParser);
        ComponentParser paragraphParser = new ComponentParser(ComponentType.PARAGRAPH);
        paragraphParser.setSuccessor(sentenceParser);
        textParser = new ComponentParser(ComponentType.TEXT);
        textParser.setSuccessor(paragraphParser);
        DataReader reader = new DataReader();
        source = reader.read(FILE);
        text = (TextComposite) textParser.chain(source);
    }

    @AfterMethod
    public void tearDown() {
        textParser = null;
        source = null;
        text = null;
        comparator = null;
    }

    @Test(description = "Sort paragraph in the text on count of sentence")
    public void sortParagraphOnSize() {
        comparator = Comparator.comparingInt(o -> ((TextComposite)o).getComponents().size());
        text.sort(ComponentType.TEXT,comparator);
        System.out.println(text);
    }

    @Test(description = "Sort literal in the sentence on it length")
    public void sortLiteralOnLength() {
        comparator = Comparator.comparingInt(o -> o.toString().length());
        text.sort(ComponentType.SENTENCE,comparator);
        System.out.println(text);
    }

    @Test(description = "Sort sentence in the paragraph on count of literal(word)")
    public void sortSentenceOnSize() {
        comparator = Comparator.comparingInt(o -> {
            if (o instanceof TextComposite) ((TextComposite)o).getComponents().size(); return 0; });
        text.sort(ComponentType.PARAGRAPH,comparator);
        System.out.println(text);
    }

    @Test(description = "Sort literal(word) in the sentence on count of occurrences symbol (for example 's')" +
            "if literal(word) doesn't have a symbol - on alphabet")
    public void sortLiteralInSentenceOnCountOfOccurrencesSymbol() {
        comparator = new CountOfOccurrencesSymbolComparator(EXAMPLE_CHAR);
        text.sort(ComponentType.SENTENCE,comparator);
        System.out.println(text);
    }

}
