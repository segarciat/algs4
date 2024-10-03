package com.segarciat.algs4.ch1.sec4.ex15;

import java.util.Arrays;

public class TwoSumFaster {
    /**
     * Determines the number of pairs that sum to 0.
     *
     * @param a Array of integers
     * @return How many pairs sum to 0.
     * @throws NullPointerException if <code>a</code> is <code>null</code>.
     */
    public static int count(int[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        int count = 0;
        Arrays.sort(a);

        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            int sum = a[lo] + a[hi];
            if (sum < 0)
                lo++;
            else if (sum > 0)
                hi--;
            else {
                // count how many such pairs
                int x = a[lo];
                int y = a[hi];
                int xCount = 0;
                int yCount = 0;
                while (lo < a.length && a[lo] == x) {
                    lo++;
                    xCount++;
                }
                while (hi >= 0 && a[hi] == y) {
                    hi--;
                    yCount++;
                }
                count += xCount * yCount;
            }
        }

        return count;
    }
}
