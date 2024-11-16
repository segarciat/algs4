package com.segarciat.algs4.ch2.sec5.ex16;

import java.util.Comparator;

/**
 * <code>2.5.16)</code>
 * Compares strings according to the order defined by the letters
 * in the <code>ORDER</code> variable.
 * @author Sergio E. Garcia Tapia
 */
public class UnbiasedNameComparator implements Comparator<String> {
    public final static char[] ORDER = {
            'R', 'W', 'Q', 'O', 'J', 'M', 'V', 'A', 'H','B', 'S', 'G', 'Z',
            'X', 'N', 'T', 'C', 'I', 'E', 'K', 'U', 'P', 'D', 'Y', 'F', 'L',
    };
    private final static int[] VALUE = new int[ORDER.length];

    static {
        for (int i = 0; i < ORDER.length; i++) {
            VALUE[ORDER[i] - 'A'] = i;
        }
    }

    private int getValue(char c) {
        int i = c - 'A';
        if (i < 0 || i >= VALUE.length)
            return i;
        return VALUE[i];
    }

    @Override
    public int compare(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            int sVal = getValue(s.charAt(i));
            int tVal = getValue(t.charAt(i));
            int cmp = Integer.compare(sVal, tVal);
            if (cmp != 0)
                return cmp;
        }
        return Integer.compare(s.length(), t.length());
    }
}
