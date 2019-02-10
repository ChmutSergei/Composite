package by.chmut.composite.parser;

import by.chmut.composite.component.CharComponent;
import by.chmut.composite.component.Component;
import by.chmut.composite.component.ComponentType;
import by.chmut.composite.component.TextComposite;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static by.chmut.composite.component.ComponentType.*;


public class ComponentParserTest {

    private ComponentParser textParser;

    @BeforeMethod
    public void setUp() {
        ComponentParser literalParser = new ComponentParser(ComponentType.LITERAL);
        ComponentParser sentenceParser = new ComponentParser(ComponentType.SENTENCE);
        sentenceParser.setSuccessor(literalParser);
        ComponentParser paragraphParser = new ComponentParser(ComponentType.PARAGRAPH);
        paragraphParser.setSuccessor(sentenceParser);
        textParser = new ComponentParser(ComponentType.TEXT);
        textParser.setSuccessor(paragraphParser);
    }

    @AfterMethod
    public void tearDown() {
        textParser = null;
    }

    @Test(description = "Expected return correct component - TextComposite")
    public void chainTest() {
        Component expected = makeTextComposite();
        Component actual = textParser.chain(" I love Java!");
        Assert.assertEquals(actual,expected);
    }

    private static Component makeTextComposite() {
        CharComponent letter1 = new CharComponent(LETTER,'I');
        CharComponent letter2 = new CharComponent(LETTER,'l');
        CharComponent letter3 = new CharComponent(LETTER,'o');
        CharComponent letter4 = new CharComponent(LETTER,'v');
        CharComponent letter5 = new CharComponent(LETTER,'e');
        CharComponent letter6 = new CharComponent(LETTER,'J');
        CharComponent letter7 = new CharComponent(LETTER,'a');
        CharComponent letter8 = new CharComponent(LETTER,'v');
        CharComponent letter9 = new CharComponent(LETTER,'a');
        CharComponent mark = new CharComponent(MARK,'!');
        TextComposite word1 = new TextComposite(WORD);
        word1.add(letter1);
        TextComposite word2 = new TextComposite(WORD);
        word2.add(letter2);
        word2.add(letter3);
        word2.add(letter4);
        word2.add(letter5);
        TextComposite word3 = new TextComposite(WORD);
        word3.add(letter6);
        word3.add(letter7);
        word3.add(letter8);
        word3.add(letter9);
        TextComposite literal1 = new TextComposite(LITERAL);
        literal1.add(word1);
        TextComposite literal2 = new TextComposite(LITERAL);
        literal2.add(word2);
        TextComposite literal3 = new TextComposite(LITERAL);
        literal3.add(word3);
        literal3.add(mark);
        TextComposite sentence = new TextComposite(SENTENCE);
        sentence.add(literal1);
        sentence.add(literal2);
        sentence.add(literal3);
        TextComposite paragraph = new TextComposite(PARAGRAPH);
        paragraph.add(sentence);
        TextComposite text = new TextComposite(TEXT);
        text.add(paragraph);
        return text;
    }
}
