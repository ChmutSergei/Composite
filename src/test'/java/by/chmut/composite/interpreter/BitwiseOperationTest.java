package by.chmut.composite.interpreter;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BitwiseOperationTest {

    @DataProvider(name = "forGetPriority")
    public static Object[][] forGetPriority() {
        return new Object[][]{
                {"~",5},
                {"<<",4},
                {">>",4},
                {">>",4},
                {"&",3},
                {"^",2},
                {"|",1},
                {"(",0},
                {")",0}
        };
    }

    @Test(dataProvider = "forGetPriority", description = "Test for returning the correct priority value")
    public void getPriorityTest(String operation, int expected) {
        int actual = BitwiseOperation.getPriority(operation);
        Assert.assertEquals(actual,expected);
    }
}
