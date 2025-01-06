package com.segarciat.algs4.ch3.sec4.ex22;

public final class Interval1D {
    private final double min;
    private final double max;

    public Interval1D(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double min() {
        return min;
    }

    public double max() {
        return max;
    }

    public double length() {
        return max - min;
    }

    public boolean contains(double x) {
        return min <= x && x <= max;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        for (double val: new double[] { min, max })
            hash = 31 * hash + Double.hashCode(val);

        return hash;
    }
}
