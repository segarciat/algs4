package com.segarciat.algs4.ch3.sec5.ex16;

import edu.princeton.cs.algs4.LinearProbingHashST;

/**
 * <strong>3.5.16)</strong>
 * Augments {@link edu.princeton.cs.algs4.SparseVector} with a method
 * {@link #sum(SparseVector, double)}.
 * @author Sergio E. Garcia Tapia
 */
public final class SparseVector {
    private final LinearProbingHashST<Integer, Double> st;

    public SparseVector() {
        st = new LinearProbingHashST<>();
    }

    public int size() {
        return st.size();
    }

    public void put(int i, double x) {
        st.put(i, x);
    }

    public double get(int i) {
        if (!st.contains(i))
            return 0.0;
        else
            return st.get(i);
    }

    public double dot(double[] that) {
        double sum = 0.0;
        for (int i: st.keys())
            sum += that[i] * this.get(i);
        return sum;
    }

    /**
     * Computes the sum of two sparse vectors. The given epsilon
     * is used as a threshold value; any sum in an epsilon-neighborhood
     * of zero is treated as zero.
     * @param that The vector we add this vector too
     * @param epsilon The threshold value for sums.
     * @return A new sparse vector that is the sum of this one and that one.
     */
    public SparseVector sum(SparseVector that, double epsilon) {
        if (that == null)
            throw new NullPointerException("cannot be null");

        SparseVector result = new SparseVector();
        // Initialize to values in this
        for (Integer i: this.st.keys()) {
            result.put(i, this.st.get(i));
        }
        // Add to values in that if any
        for (Integer i: that.st.keys()) {
            double x = this.get(i) + that.get(i);
            if (x < -epsilon || x > epsilon) {
                if (result.st.contains(i))
                    result.st.delete(i);
            } else {
                result.st.put(i, x);
            }
        }
        return result;
    }
}
