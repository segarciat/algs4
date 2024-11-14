package com.segarciat.algs4.ch2.sec5.ex09;

import java.util.Comparator;
import java.util.Date;

/**
 * <strong>2.5.9)</strong>
 * Represents DJIA data that a client can sort.
 *
 * @author Sergio E. Garcia Tapia
 */
public record DJIAVolume(Date date, long volume) implements Comparable<DJIAVolume> {
    public DJIAVolume {
        if (date == null)
            throw new NullPointerException("date cannot be null");
        if (volume < 0)
            throw new IllegalArgumentException("volume must be non-negative");
    }

    @Override
    public int compareTo(DJIAVolume djiaVolume) {
        return Comparator.comparingLong(DJIAVolume::volume)
                .thenComparing(DJIAVolume::date)
                .compare(this, djiaVolume);
    }
}
