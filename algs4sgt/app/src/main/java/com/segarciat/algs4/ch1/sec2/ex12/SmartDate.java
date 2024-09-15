package com.segarciat.algs4.ch1.sec2.ex12;

/**
 * <strong>1.2.12)</strong>
 * Add a method <code>dayOfTheWeek()</code> to <code>SmartDate</code> that returns
 * a <code>String</code> value <code>Monday</code>, <code>Tuesday</code>, <code>Wednesday</code>,
 * <code>Thursday</code>, <code>Friday</code>, <code>Saturday</code>, or <code>Sunday</code>,
 * giving the appropriate day of the week for the date. You may assume that the date is in
 * the 21st century.
 *
 * @author Sergio E. Garcia Tapia
 */
public class SmartDate {
    private static final int[] MAX_DAYS_PER_MONTH = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private final int month;
    private final int day;
    private final int year;
    private final boolean isLeapYear;

    /**
     * Creates a <code>SmartDate</code> object.
     * @param m Month day, must be between 1 and 12.
     * @param d Day of the month. The valid value depends on the <code>m</code>, and <code>y</code> is a leap year.
     * @param y Must a non-negative value.
     *
     * @throws IllegalArgumentException if the year, month, day combination is invalid.
     */
    public SmartDate(int m, int d, int y) {
        if (y < 0)
            throw new IllegalArgumentException("year must be positive");

        if (m <= 0 || m > 12)
            throw new IllegalArgumentException("month must be between 1 and 12 (inclusive)");

        isLeapYear = y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
        if (d <= 0)
            throw new IllegalArgumentException("day must be a positive number");
        if ((m != 2 && d > MAX_DAYS_PER_MONTH[m])
                || (m == 2 && isLeapYear && d > 29)
                || (m == 2 && !isLeapYear && d > 28)
        )
            throw new IllegalArgumentException(String.format("day must be between 1 and %d (inclusive)"
                , (m != 2)? MAX_DAYS_PER_MONTH[m] : (isLeapYear) ? 29 : 28)
            );
        year = y;
        month = m;
        day = d;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    /**
     * If y is in the 21st century, returns a String for the day of the week.
     * Otherwise, returns an empty string.
     * @return A String for the day of the week, or empty string if the year is not in the 21st century.
     */
    public String dayOfTheWeek() {
        if (year <= 2000 || year > 2100)
            return "";

        int yearsSince21stCentury = year - 2001;
        int leapYears = yearsSince21stCentury / 4;
        int daysSince2000 = leapYears * 366 + (yearsSince21stCentury - leapYears) * 365;

        for (int m = 1; m < month; m++) {
            if (m == 2)
                daysSince2000 += (isLeapYear ? 29 : 28);
            else
                daysSince2000 += MAX_DAYS_PER_MONTH[m];
        }
        daysSince2000 += day;

        // January 1st, 2001 was a Monday.
        return switch (daysSince2000 % 7) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Sunday";
        };
    }

    public String toString() {
        return String.format("%02d/%02d/%d", month, day, year);
    }

    public boolean equals(Object x) {
        if (this == x)
            return true;
        if  (x == null)
            return false;
        if (this.getClass() != x.getClass())
            return false;
        SmartDate that = (SmartDate) x;
        if (this.day != that.day)
            return false;
        if  (this.month != that.month)
            return false;
        if (this.year != that.year)
            return false;
        return true;
    }
}
