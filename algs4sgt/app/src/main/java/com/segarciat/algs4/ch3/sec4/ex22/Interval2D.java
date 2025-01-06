package com.segarciat.algs4.ch3.sec4.ex22;

import edu.princeton.cs.algs4.Interval1D;

public final class Interval2D {
    private Interval1D x;
    private Interval1D y;

    public Interval2D(Interval1D x, Interval1D y) {
        this.x = x;
        this.y = y;
    }

    public double area() {
        return x.length() * y.length();
    }

    @Override
    public int hashCode() {
        int hash = 1;

        for (Interval1D val: new Interval1D[] { x, y })
            hash = 31 * hash + val.hashCode();

        return hash;
    }
}
