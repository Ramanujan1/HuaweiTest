package com.huawei.test;

import com.huawei.RegexWordSearch;
import com.huawei.util.InputDataUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RegexWordSearchTest {
    ArrayList<String> arrayList = null;
    ArrayList<String> arrayListFinal = null;

    @Before
    public void init() {
        arrayList =  new ArrayList<>();
        arrayListFinal =  new ArrayList<>();
        arrayListFinal.add("\"process\"");
        arrayListFinal.add("\"in\"");
        arrayListFinal.add("2");
    }

    @Test
    public void testPrintPreviousString_Valid_toString() {
        RegexWordSearch regexWordSearch = new RegexWordSearch("to");

        assertEquals("(\"process\",\"in\",2)" , regexWordSearch.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_NonMatchingString() {
        RegexWordSearch regexWordSearch = new RegexWordSearch("and");

        assertEquals("()", regexWordSearch.printPreviousString());
    }

    @Test
    public void testPrintPreviousString_EmptyString() {
        RegexWordSearch regexWordSearch = new RegexWordSearch("");

        assertEquals("()", regexWordSearch.printPreviousString());
    }

    @Test
    public void testMatchString_Valid_String() throws Exception{
        RegexWordSearch regexWordSearch = new RegexWordSearch("to");

        assertEquals(arrayListFinal, regexWordSearch.matchString(InputDataUtil.loadInputFile(),"to"));
    }

    @Test
    public void testMatchString_EmptyInput() {
        RegexWordSearch regexWordSearch = new RegexWordSearch("to");

        assertEquals(arrayList, regexWordSearch.matchString("",""));
    }

    @Test
    public void testMatchString_EmptyFile() {
        RegexWordSearch regexWordSearch = new RegexWordSearch("to");

        assertEquals(arrayList, regexWordSearch.matchString("","to"));
    }

    @Test
    public void testMatchString_SearchString() throws Exception{
        RegexWordSearch regexWordSearch = new RegexWordSearch("");

        assertEquals(arrayList, regexWordSearch.matchString(InputDataUtil.loadInputFile(),""));
    }
}