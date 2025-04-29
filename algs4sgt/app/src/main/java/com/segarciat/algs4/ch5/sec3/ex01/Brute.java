package com.segarciat.algs4.ch5.sec3.ex01;

/**
 * <strong>Exercise 5.3.1</strong>
 * Implements brute-force substring search.
 * @author Sergio E. Garcia Tapia
 */
public class Brute {
    /**
     * The substring pattern a user may care for.
     */
    private String pat;

    public Brute(String pat) {
        if (pat == null) {
            throw new NullPointerException("pattern must not be null");
        }
    }

    /**
     * Determines the first position (index) where the pattern <code>pat</code> (given in
     * the constructor) occurs in the given <code>txt</code>.
     * @param txt The text to match against a pattern.
     * @return an index in the string where the pattern begins in the string if
     * the pattern is present, or the string's length otherwise.
     */
    public int search(String txt) {
        if (txt == null) {
            throw new NullPointerException("txt must not be null");
        }

        int m = pat.length();
        int n = txt.length();
        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (txt.charAt(i + j) != txt.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                return i;
            }
        }
        return n;
    }
}
