package by.chmut.composite.parser;

import by.chmut.composite.interpreter.BitwiseExpression;
import by.chmut.composite.interpreter.Context;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class BitwiseExpressionParserTest {

    private Context context;
    @BeforeMethod
    public void setUp() {
        context = new Context();
    }

    @DataProvider(name = "forParseTest")
    public static Object[][] forParseTest() {
        return new Object[][]{
                {"13>>2", (long)(13>>2)},
                {"5|13>>2&5", (long)(5|13>>2&5)},
                {"2&5|13>>2&5>>>5", (long)(2&5|13>>2&5>>>5)},
                {"2&(5|13)>>2&5>>>(5|2|3)", (long)(2&(5|13)>>2&5>>>(5|2|3))}
        };
    }

    @Test(dataProvider = "forParseTest", description = "Test for calculating the correct value on bitwise expression")
    public void parseTest(String source, Long expected) {
        List<BitwiseExpression> expressions = BitwiseExpressionParser.parse(source);
        for (BitwiseExpression expression : expressions) {
            expression.interpret(context);
        }
        Long actual = context.pop();
        Assert.assertEquals(actual,expected);
    }
}
