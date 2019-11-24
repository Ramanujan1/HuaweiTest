package com.huawei.test;

import com.huawei.DaysCounter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DayCounterTest {
    DaysCounter daysCounter;

    @Before
    public void init() {
         daysCounter = new DaysCounter();
    }

    @Test
    public void testCountNumberOfDays_Jan() {
        assertEquals(1, daysCounter.getDatNum(1,1, 2000));
    }

    @Test
    public void testCountNumberOfDays_Feb() {
        assertEquals(59, daysCounter.getDatNum(28, 2, 2000));
    }

    @Test
    public void testCountNumberOfDays_LeapYearDay() {
        assertEquals(265, daysCounter.getDatNum(21,9, 2004));
    }

    @Test
    public void testCountNumberOfDays_NonLeapYearDay() {
        assertEquals(264, daysCounter.getDatNum(21,9, 2003));
    }

    @Test
    public void testCountNumberOfDays_InvalidDay(){
        assertEquals(-1, daysCounter.getDatNum(29,2,2003));
    }

    @Test
    public void testCountNumberOfDays_InvalidYear(){
        assertEquals(-1, daysCounter.getDatNum(29,2,0));
    }

    @Test
    public void testCountNumberOfDays_InvalidMonth(){
        assertEquals(-1, daysCounter.getDatNum(29,14,0));
    }

    @Test
    public void testCountNumberOfDays_NegativeDay(){
        assertEquals(-1, daysCounter.getDatNum(-1,2,2003));
    }

    @Test
    public void testCountNumberOfDays_NegativeYear(){
        assertEquals(-1, daysCounter.getDatNum(29,2,-1));
    }

    @Test
    public void testCountNumberOfDays_NegativeMonth(){
        assertEquals(-1, daysCounter.getDatNum(29,-1,2003));
    }

    @Test
    public void testCountNumberOfDays_31dayMonth(){
        assertEquals(213, daysCounter.getDatNum(31,7,2004));
    }

    @Test
    public void testCountNumberOfDays_WrongLeapYearDate(){
        assertEquals(-1, daysCounter.getDatNum(29,2,1999));
    }
}