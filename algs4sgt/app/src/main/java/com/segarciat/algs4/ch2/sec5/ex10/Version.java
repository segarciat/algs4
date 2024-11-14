package com.segarciat.algs4.ch2.sec5.ex10;

import java.util.Comparator;

/**
 * <strong>2.5.10)</strong>
 * Represents a software version number which can be sorted.
 * @param major The major version number.
 * @param minor The minor version number.
 * @param patch The patch version number.
 * @author Sergio E. Garcia Tapia
 */
public record Version(int major, int minor, int patch) implements Comparable<Version> {
    public Version {
        if (major < 0 || minor < 0 || patch < 0)
            throw new IllegalArgumentException("version cannot have negative numbers");
    }

    @Override
    public int compareTo(Version version) {
        return Comparator.comparingInt(Version::major)
                .thenComparingInt(Version::minor)
                .thenComparingInt(Version::patch)
                .compare(this, version);
    }

    @Override
    public String toString() {
        return "%d.%d.%d".formatted(major, minor, patch);
    }
}
