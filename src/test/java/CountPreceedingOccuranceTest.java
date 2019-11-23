import com.huawei.CountPreceedingOccurance;
import com.huawei.util.InputDataUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountPreceedingOccuranceTest {

    @Test
    public void testPrintPreviousString_Valid_toString() {
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("to");

        assertEquals("(\"process\",\"in\",to)" , countPreceedingOccurance.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_NonMatchingString() {
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("and");

        assertEquals("()", countPreceedingOccurance.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_EmptyString() {
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("");

        assertEquals("()", countPreceedingOccurance.printPreviousString());
    }

    @Test
    public void testMatchString_Valid_String() throws Exception{
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("to");

        assertEquals("\"process\",\"in\",to", countPreceedingOccurance.matchString(InputDataUtil.loadInputFile(),"to"));
    }

    @Test
    public void testMatchString_EmptyInput() {
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("to");

        assertEquals("", countPreceedingOccurance.matchString("",""));
    }

    @Test
    public void testMatchString_EmptyFile() {
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("to");

        assertEquals("", countPreceedingOccurance.matchString("","to"));
    }

    @Test
    public void testMatchString_SearchString() throws Exception{
        CountPreceedingOccurance countPreceedingOccurance = new CountPreceedingOccurance("");

        assertEquals("", countPreceedingOccurance.matchString(InputDataUtil.loadInputFile(),""));
    }
}