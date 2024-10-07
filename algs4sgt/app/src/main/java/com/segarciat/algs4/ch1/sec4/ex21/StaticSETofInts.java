package com.segarciat.algs4.ch1.sec4.ex21;

import java.util.Arrays;

public class StaticSETofInts {
    private final int[] s;

    public StaticSETofInts(int[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            throw new IllegalArgumentException("array cannot be empty");

        // defensive copy of array
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; i++)
            temp[i] = a[i];
        Arrays.sort(temp);

        // Push unique entries to the front
        int d = 1;
        for (int i = 1; i < temp.length; i++)
            if (temp[i] != temp[i-1])
                temp[d++] = temp[i];

        // Retain only unique entries
        s = new int[d];
        for (int i = 0; i < d; i++)
            s[i] = temp[i];
    }

    /**
     * Determines whether the given key is in the set.
     * @param key The key to search for.
     * @return <code>true</code> if the key is in the set, <code>false</code> otherwise.
     */
    public boolean contains(int key) {
        int lo = 0;
        int hi = s.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > s[mid])
                lo = mid + 1;
            else if (key < s[mid])
                hi = mid - 1;
            else
                return true;
        }
        return false;
    }
}
