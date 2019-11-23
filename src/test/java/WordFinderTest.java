import com.huawei.WordFinder;
import com.huawei.util.InputDataUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordFinderTest {

    @Test
    public void testPrintPreviousString_Valid_toString() {
        WordFinder wordFinder = new WordFinder("to");

        assertEquals("(\"process\",\"in\",to)" , wordFinder.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_NonMatchingString() {
        WordFinder wordFinder = new WordFinder("and");

        assertEquals("()", wordFinder.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_EmptyString() {
        WordFinder wordFinder = new WordFinder("");

        assertEquals("()", wordFinder.printPreviousString());
    }

    @Test
    public void testMatchString_Valid_String() throws Exception{
        WordFinder wordFinder = new WordFinder("to");

        assertEquals("\"process\",\"in\",to", wordFinder.matchString(InputDataUtil.loadInputFile(),"to"));
    }

    @Test
    public void testMatchString_EmptyInput() {
        WordFinder wordFinder = new WordFinder("to");

        assertEquals("", wordFinder.matchString("",""));
    }

    @Test
    public void testMatchString_EmptyFile() {
        WordFinder wordFinder = new WordFinder("to");

        assertEquals("", wordFinder.matchString("","to"));
    }

    @Test
    public void testMatchString_SearchString() throws Exception{
        WordFinder wordFinder = new WordFinder("");

        assertEquals("", wordFinder.matchString(InputDataUtil.loadInputFile(),""));
    }
}