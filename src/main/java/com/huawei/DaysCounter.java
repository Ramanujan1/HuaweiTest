package com.huawei;

import java.util.HashMap;

public class DaysCounter {

    //Map to maintain number of days in each month
    private HashMap<Integer, Integer> monthDaysMap =  new HashMap<Integer, Integer>() {{
        put(1 ,31 ); put(2, 28); put(3, 31); put(4, 30); put(5, 31); put(6, 30);put(7, 31);put(8, 31);put(9, 30);
        put(10, 31); put(11, 30); put(12, 31);
    }};

    public int getDatNum(int day, int currentMonth, int year) {
        Integer dayCount= 0;

        if (isInValidDate(year, day, currentMonth)) {
            return -1;
        }

        if (!isLeapYear(year) && !validDateForNonLeapYear(year, day, currentMonth)) {
            return -1;
        }

        for (int month = 1 ; month <= 12 ; month++) {
            if  (month < currentMonth) {
                // logic for Feb currentMonth
                if (month == 2) {
                    // handle leap year Feb month
                    if (isLeapYear(year)) {
                        dayCount += 29;
                    } else {
                        dayCount += monthDaysMap.get(month);
                    }
                } else {
                    dayCount += monthDaysMap.get(month);
                }
            }

            if (currentMonth == month) {
                dayCount += day;
            }
        }
        return dayCount;
    }

    private boolean isInValidDate(int year, int date, int month) {
        if ((year <= 0) || (date <=0 ||
                (month > 0 && date > monthDaysMap.get(month))) ||
                (month <=0 || month > 12)) {
            return true;
        }
        return false;
    }

    private boolean validDateForNonLeapYear(int year, int date, int month) {
        if (month == 2) {
            //condition for a non leap year having more than 28 days in Feb
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