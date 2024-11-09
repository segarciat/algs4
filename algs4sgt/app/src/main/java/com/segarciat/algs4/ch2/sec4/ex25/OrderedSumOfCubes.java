package com.segarciat.algs4.ch2.sec4.ex25;

import edu.princeton.cs.algs4.MinPQ;

/**
 * <code>2.4.25)</code>
 * Displays the distinct integers a, b, c, d such that a^3 + b^3 = c^3 + d^3
 * for integers a, b, c, d not exceeding n = 10e6. Uses space proportional to
 * n = 10e6.
 * @author Sergio E. Garcia Tapia
 */
public class OrderedSumOfCubes {
    private OrderedSumOfCubes() {}

    public static void main(String[] args) {
        final long n = 1_000_00;

        // Initialize the queue
        MinPQ<CubedSumPair> pq = new MinPQ<>();
        for (long i = 0; i <= n; i++)
            pq.insert(new CubedSumPair(i, 0));

        CubedSumPair lastRemoved = new CubedSumPair(-1, 0);
        boolean duplicate = false;
        while (!pq.isEmpty()) {
            var min = pq.delMin();
            if (lastRemoved.cubedSum == min.cubedSum && !lastRemoved.equals(min)) {
                System.out.println(lastRemoved);
                duplicate = true;
            } else if (duplicate) {
                System.out.println(lastRemoved);
                System.out.println();
                duplicate = false;
            }
            lastRemoved = min;

            // Disallow repeats
            if (min.j < n && min.j < min.i) {
                pq.insert(new CubedSumPair(min.i, min.j + 1));
            }
        }
    }

    private static Long cube(long n) {
        return n * n * n;
    }

    private static class CubedSumPair implements Comparable<CubedSumPair> {
        private final long i;
        private final long j;
        private final long cubedSum;

        CubedSumPair(long i, long j) {
            this.i = i;
            this.j = j;
            this.cubedSum = cube(i) + cube(j);
        }

        /**
         * Compares the given pair against this one according to
         * the value of their cubed sum. If this pair
         * has a smaller sum than the given one, returns a negative
         * result; if this one has a larger sum, returns a positive;
         * otherwise, returns 0.
         * @param pair A pair to compare against this one.
         * @return -1 if this pair has a smaller sum, 1 if this
         * pair has a bigger sum, and 0 if their sum is equal.
         */
        @Override
        public int compareTo(CubedSumPair pair) {
            return Long.compare(this.cubedSum, pair.cubedSum);
        }

        /**
         * Returns a string representation of this pair and its cubed sum.
         * @return A string representation of this pair and its cubed sum.
         */
        @Override
        public String toString() {
            return "(sum of cubes=%d, i=%d, j=%d)".formatted(cubedSum, i, j);
        }

        /**
         * Compares the specified object for equality. Returns <code>true</code>
         * if and only if the objects are of the same type, if their cubedSum
         * is the same, and the same two integers are cubed (in either order).
         * @param o The object to be compared for equality with this one.
         * @return <code>true</code> if the specified object is equal to this one,
         * and <code>false</code> otherwise.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CubedSumPair triple = (CubedSumPair) o;
            if (cubedSum != triple.cubedSum)
                return false;
            return (i == triple.i && j == triple.j) || (i == triple.j || j == triple.i);
        }
    }
}
