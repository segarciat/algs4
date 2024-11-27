package com.segarciat.algs4.ch3.sec1.ex04;

public final class Time implements Comparable<Time> {
    private final byte hours;
    private final byte minutes;
    private final byte seconds;

    public Time(int h, int m, int s) {
        if (h < 0 || m < 0 || s < 0 || h > 23 || m > 59 || s > 59)
            throw new IllegalArgumentException("invalid time");
        hours = (byte) h;
        minutes = (byte) m;
        seconds = (byte) s;
    }

    public byte hours() {
        return hours;
    }

    public byte minutes() {
        return minutes;
    }

    public byte seconds() {
        return seconds;
    }

    @Override
    public int compareTo(Time t) {
        if (hours < t.hours)
            return -1;
        if (hours > t.hours)
            return 1;
        if (minutes < t.minutes)
            return -1;
        if (minutes > t.minutes)
            return 1;
        if (seconds < t.seconds)
            return -1;
        if (seconds > t.seconds)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "%02d:%02d:%02d".formatted(hours, minutes, seconds);
    }

    public static void main(String[] args) {
        System.out.println(new Time(9, 31, 3));
    }
}
