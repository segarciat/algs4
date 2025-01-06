package com.segarciat.algs4.ch3.sec4.ex22;

public final class Date {
    int month;
    int day;
    int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
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

    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + Integer.hashCode(month);
        hash = 31 * hash + Integer.hashCode(day);
        hash = 31 * hash + Integer.hashCode(year);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || (obj.getClass() != this.getClass()))
            return false;
        Date other = (Date) obj;
        if (this.day != other.day)
            return false;
        if (this.month != other.month)
            return false;
        if (this.year != other.year)
            return false;
        return true;
    }
}
