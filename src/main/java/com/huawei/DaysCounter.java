package com.huawei;

import java.util.HashMap;

public class DaysCounter {

    private HashMap<Integer, Integer> monthDaysMap =  new HashMap<Integer, Integer>() {{
        put(1 ,31 ); put(2, 28); put(3, 31); put(4, 30); put(5, 31); put(6, 30);put(7, 31);put(8, 31);put(9, 30);
        put(10, 31);put(11, 30);put(12, 31);
    }};

    public int countNumberOfDaysInaDate(Integer month,Integer date,Integer year) {
        Integer dayCount= 0;

        if (isInValidDate(year, date, month)) {
            return -1;
        }

        if (!isLeapYear(year) && !validDateForNonLeapYear(year, date, month)) {
            return -1;
        }

        for (int i= 1 ; i <= 12 ; i++) {
            if  (i < month) {
                if (i == 2) {
                    if (isLeapYear(year)) {
                        dayCount += 29;
                    } else {
                        dayCount += monthDaysMap.get(i);
                    }
                } else {
                    dayCount += monthDaysMap.get(i);
                }
            }

            if (month == i) {
                dayCount += date;
            }
        }
        return dayCount;
    }

    private boolean isInValidDate(Integer year, Integer date, Integer month) {
        if ((year <= 0) || (date <=0 || (month > 0 && date > monthDaysMap.get(month))) || (month <=0 || month > 12)) {
            return true;
        }
        return false;
    }

    private boolean validDateForNonLeapYear(Integer year, Integer date, Integer month) {
        if (month == 2) {
            if (!isLeapYear(year) && date > 28) {
                return false;
            }
        }
        return true;
    }

    private static boolean isLeapYear(int year){
        return ((year % 4) == 0);
    }
}