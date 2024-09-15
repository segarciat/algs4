package com.segarciat.algs4.ch1.sec2.ex11;

/**
 * <strong>1.2.11)</strong>
 * Develop an implementation <code>SmartDate</code> of our <code>Date</code>
 * API that raises an exception if the date is not legal.
 *
 * @author Sergio E. Garcia Tapia
 */
public class SmartDate {
    private final int month;
    private final int day;
    private final int year;

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

        switch (m) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                if (d <= 0 || d > 31)
                    throw new IllegalArgumentException("day must be between 1 and 31 (inclusive)");
                break;
            case 4: case 6: case 9: case 11:
                if (d <= 0 || d > 30)
                    throw new IllegalArgumentException("day must be between 1 and 30 (inclusive)");
                break;
            case 2:
                boolean isLeapYear = y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
                if (d <= 0 || d > 29 || d == 29 && !isLeapYear)
                    throw new IllegalArgumentException(
                            String.format("day must be between 1 and %d for month %d and year %d",
                                    isLeapYear ? 29 : 28, m, y)
                    );
                break;
        }
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
