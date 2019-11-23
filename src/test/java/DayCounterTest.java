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
        assertEquals(1, daysCounter.countNumberOfDaysInaDate(1,1, 2000));
    }

    @Test
    public void testCountNumberOfDays_Feb() {
        assertEquals(59, daysCounter.countNumberOfDaysInaDate(2,28, 2000));
    }

    @Test
    public void testCountNumberOfDays_LeapYearDay() {
        assertEquals(265, daysCounter.countNumberOfDaysInaDate(9,21, 2004));
    }

    @Test
    public void testCountNumberOfDays_NonLeapYearDay() {
        assertEquals(264, daysCounter.countNumberOfDaysInaDate(9,21, 2003));
    }

    @Test
    public void testCountNumberOfDays_InvalidDay(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(2,29,2003));
    }

    @Test
    public void testCountNumberOfDays_InvalidYear(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(2,29,0));
    }

    @Test
    public void testCountNumberOfDays_InvalidMonth(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(14,29,0));
    }

    @Test
    public void testCountNumberOfDays_NegativeDay(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(2,-1,2003));
    }

    @Test
    public void testCountNumberOfDays_NegativeYear(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(2,29,-1));
    }

    @Test
    public void testCountNumberOfDays_NegativeMonth(){
        assertEquals(-1, daysCounter.countNumberOfDaysInaDate(-1,29,2003));
    }
}