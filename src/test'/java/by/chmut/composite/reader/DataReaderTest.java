package by.chmut.composite.reader;

import org.testng.Assert;
import org.testng.annotations.*;

public class DataReaderTest {

    private static final String FILE = "/textForTest.txt";
    private DataReader reader;
    private String pathFile;

    @BeforeMethod
    public void setUp() {
        reader = new DataReader();
        pathFile = FILE;
    }

    @AfterMethod
    public void tearDown() {
        reader = null;
        pathFile = null;
    }

    @Test(description = "Read 3 String lines from correct file: expected List with 3 String line")
    public void pathTestPositive() {
        String actual = reader.read(pathFile);
        String expected = "    It has survived - not only (five) centuries, but also the leap into " +
                "13<<2 electronic typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. " +
                "It was popularised in the 5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset " +
                "sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "PageMaker including versions of Lorem Ipsum.    " +
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking" +
                " at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 " +
                "Ipsum is that it has a more-or-less normal distribution of letters, " +
                "as opposed to using (Content here), content here', making it look like readable English.";
        Assert.assertEquals(actual,expected);
    }

    @Parameters({"WrongName"})
    @Test(expectedExceptions = RuntimeException.class,
            description = "Attempt to read data without a file: expected RuntimeException")
    public void pathTestNegative(@Optional String pathFile) {
        reader.read(pathFile);
    }

}
