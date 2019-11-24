package com.huawei.test;

import com.huawei.RegexWordSearch;
import com.huawei.util.InputDataUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexWordSearchTest {

    @Test
    public void testPrintPreviousString_Valid_toString() {
        RegexWordSearch wordFinder = new RegexWordSearch("to");

        assertEquals("(\"process\",\"in\",to)" , wordFinder.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_NonMatchingString() {
        RegexWordSearch wordFinder = new RegexWordSearch("and");

        assertEquals("()", wordFinder.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_EmptyString() {
        RegexWordSearch wordFinder = new RegexWordSearch("");

        assertEquals("()", wordFinder.printPreviousString());
    }

    @Test
    public void testMatchString_Valid_String() throws Exception{
        RegexWordSearch wordFinder = new RegexWordSearch("to");

        assertEquals("\"process\",\"in\",to", wordFinder.matchString(InputDataUtil.loadInputFile(),"to"));
    }

    @Test
    public void testMatchString_EmptyInput() {
        RegexWordSearch wordFinder = new RegexWordSearch("to");

        assertEquals("", wordFinder.matchString("",""));
    }

    @Test
    public void testMatchString_EmptyFile() {
        RegexWordSearch wordFinder = new RegexWordSearch("to");

        assertEquals("", wordFinder.matchString("","to"));
    }

    @Test
    public void testMatchString_SearchString() throws Exception{
        RegexWordSearch wordFinder = new RegexWordSearch("");

        assertEquals("", wordFinder.matchString(InputDataUtil.loadInputFile(),""));
    }
}