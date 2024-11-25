package com.segarciat.algs4.ch2.sec5.ex27;

import edu.princeton.cs.algs4.Interval1D;

import java.util.Comparator;

/**
 * <strong>2.5.27)</strong>
 * Comparator that compares {@link Interval1D} objects by their minimum endpoint.
 * @author Sergio E. Garcia Tapia
 */
public final class MinEndpointComparator implements Comparator<Interval1D> {
    @Override
    public int compare(Interval1D I, Interval1D J) {
        return Double.compare(I.min(), J.min());
    }
}
