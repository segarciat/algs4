package com.segarciat.algs4.ch1.sec2.ex06;

/**
 *
 * 1.1.9) A string <code>s</code> is a circular shift (or <em>circular rotation</em>)
 * of a string <code>t</code> if it matches when the characters are circularly shifted
 * by any number of positions; e.g., <code>ACTGACG</code> is a circular shift of
 * <code>TGACGAC</code>, and vice-versa. Detecting this condition is important in the
 * study of genomic sequences. Write a program that checks whether two given strings
 * <code>s</code> and <code>t</code> are circular shifts of one another.
 * <em>Hint</em>: The solution is a one-liner with <code>indexOf()</code>, <code>length()</code>,
 * and string concatenation.
 */
public class CircularShift {
    /**
     * Detects whether s and t are circular rotations (shifts) of one another.
     * The order of the arguments does not matter.
     *
     * @param s Any String
     * @param t Any String
     *
     * @return true if s and t are circular rotations (shifts) of one another, false otherwise.
     *
     * @throws NullPointerException if <code>s</code> or <code>t</code> is <code>null</code>.
     */
    public static boolean isCircularShift(String s, String t) {
        if (s == null || t == null)
            throw new NullPointerException("The strings should not be null.");
        return s .length() == t.length() && (s + s).contains(t);
    }
}
