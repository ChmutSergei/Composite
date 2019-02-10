package by.chmut.composite.component;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;

public class ComponentTest {

    private TextComposite text;
    private Comparator comparator;

    @BeforeMethod
    public void setUp() {
        Component character1 = new CharComponent(ComponentType.LETTER, '1');
        Component character2 = new CharComponent(ComponentType.LETTER, '2');
        Component character3 = new CharComponent(ComponentType.LETTER, '3');
        TextComposite literal = new TextComposite(ComponentType.LITERAL);
        TextComposite literalReverse = new TextComposite(ComponentType.LITERAL);
        literal.add(character1);literal.add(character2);literal.add(character3);
        literalReverse.add(character3);literalReverse.add(character2);literalReverse.add(character1);
        TextComposite sentence1 = new TextComposite(ComponentType.SENTENCE);
        TextComposite sentence2 = new TextComposite(ComponentType.SENTENCE);
        sentence1.add(literal);sentence1.add(literalReverse);
        sentence2.add(literalReverse);sentence2.add(literal);
        TextComposite paragraph1 = new TextComposite(ComponentType.PARAGRAPH);
        TextComposite paragraph2 = new TextComposite(ComponentType.PARAGRAPH);
        paragraph1.add(sentence1);paragraph1.add(sentence2);
        paragraph2.add(sentence2);paragraph2.add(sentence1);
        text = new TextComposite(ComponentType.TEXT);
        text.add(paragraph1);
        text.add(paragraph2);
        comparator = Comparator.comparing(component -> component.toString()).reversed();
    }

    @AfterMethod
    public void tearDown() {
        text = null;
        comparator = null;
    }

    @Test(description = "Sort component of TEXT - expected PARAGRAPH reverse queue : 321 123 and 123 321")
    public void sortTestText() {
        text.sort(ComponentType.TEXT, comparator);
        String actual = text.getComponents().get(0).toString();
        Assert.assertEquals(actual, "321 123 123 321 ");
    }

    @Test(description = "Sort component of PARAGRAPH - expected SENTENCE(first PARAGRAPH) reverse queue : 321 and 123 ")
    public void sortTestParagraph() {
        text.sort(ComponentType.PARAGRAPH, comparator);
        String actual = ((TextComposite)text.getChild(0)).getChild(0).toString();
        Assert.assertEquals(actual, "321 123 ");
    }

    @Test(description = "Sort component of SENTENCE - expected LITERAL(first SENTENCE) reverse queue : 3,2,1 ")
    public void sortTestSentence() {
        text.sort(ComponentType.SENTENCE, comparator);
        Component temp = ((TextComposite)text.getChild(0)).getChild(0);
        String actual = ((TextComposite)temp).getChild(0).toString();
        Assert.assertEquals(actual, "321");
    }

    @Test(description = "Sort component of LITERAL - expected CHAR(first LITERAL) reverse queue : 3")
    public void sortTestLiteral() {
        text.sort(ComponentType.LITERAL, comparator);
        Component temp = ((TextComposite)text.getChild(0)).getChild(0);
        Component temp2 = ((TextComposite)temp).getChild(0);
        String actual = ((TextComposite)temp2).getChild(0).toString();
        Assert.assertEquals(actual, "3");
    }

}
