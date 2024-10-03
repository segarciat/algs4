package com.segarciat.algs4.ch1.sec4.ex15;

import java.util.Arrays;

public class ThreeSumFaster {
    /**
     * Determines the number of triples that sum to 0.
     *
     * @param a Array of integers
     * @return How many triples sum to 0.
     * @throws NullPointerException if <code>a</code> is <code>null</code>.
     */
    public static int count(int[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");

        Arrays.sort(a);
        int count = 0;

        for (int i = 0; i < a.length; i++) {
            int j = i + 1;
            int k = a.length - 1;

            while (j < k) {
                int sum =  a[i] + a[j] + a[k];
                if (sum < 0)
                    j++;
                else if (sum > 0)
                    k--;
                else {
                    int x = a[j];
                    int y = a[k];
                    int xCount = 0;
                    int yCount = 0;
                    while (j < a.length && a[j] == x) {
                        xCount++;
                        j++;
                    }
                    while (k >= 0 && a[k] == y) {
                        yCount++;
                        k--;
                    }
                    count += xCount * yCount;
                }
            }
        }

        return count;
    }
}
