package by.chmut.composite.interpreter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class BitwiseExpressionConverterTest {

    @DataProvider(name = "forConvertTest")
    public static Object[][] forConvertTest() {
        return new Object[][]{
                {"13>>2", Arrays.asList("13", "2", ">>")},
                {"5|13>>2&5", Arrays.asList("5", "13", "2", ">>", "5", "&", "|")},
                {"2&5|13>>2&5>>>5", Arrays.asList("2", "5", "&", "13", "2", ">>", "5", "5", ">>>", "&", "|")},
                {"2&(5|13)>>2&5>>>(5|2|3)", Arrays.asList("2", "5", "13", "|", "2", ">>",
                        "&", "5", "5", "2", "|", "3", "|", ">>>", "&")}
        };
    }

    @Test(dataProvider = "forConvertTest",
            description = "Convert different bitwise expression in Polish notation - " +
                    "expected correct collection according to the algorithm")
    public void convertTest(String source, List<String> expected) {
        List<String> actual = BitwiseExpressionConverter.convert(source);
        Assert.assertEquals(actual, expected);
    }
}
