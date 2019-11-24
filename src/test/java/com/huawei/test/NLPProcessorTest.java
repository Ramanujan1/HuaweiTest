package com.huawei.test;

import com.huawei.NLPProcessor;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NLPProcessorTest {
    NLPProcessor nlpProcessor;
    List<List> allTriples;

    @Before
    public void init() throws Exception{
         nlpProcessor = new NLPProcessor();
         allTriples = NLPProcessor.generateVerbTriples("verbtriples_example.txt", false);
    }

    @Test
    public void testGetVerbTriples_Valid_FileName_Zeroth_Element()  {
        assertEquals(5, allTriples.size());
        assertEquals(Arrays.asList("all", "featured", "content"),allTriples.get(0));
    }

    @Test
    public void testGetVerbTriples_Valid_FileName_Middle_Element()  {
        assertEquals(5, allTriples.size());
        assertEquals(Arrays.asList("that it", "meets", "the highest standards in to"),allTriples.get(3));
    }

    @Test
    public void testGetVerbTriples_Valid_FileName_Last_Element()  {
        assertEquals(5, allTriples.size());
        assertEquals(Arrays.asList("the highest standards in to", "serve", "as the best example of our end goals"),allTriples.get(4));
    }

}