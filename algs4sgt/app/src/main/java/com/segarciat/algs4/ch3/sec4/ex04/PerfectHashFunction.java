package com.segarciat.algs4.ch3.sec4.ex04;

import edu.princeton.cs.algs4.SequentialSearchST;

/**
 * Determines integer values <code>a</code> and <code>m</code> such that
 * the hash function <code>(a * k) % m</code> hashes the <code>k</code>th
 * letter of the alphabet such that there are no collisions.
 * @author Sergio E. Garcia Tapia
 */
public final class PerfectHashFunction {
    private static int hashLetter(int a, int m, char c) {
        return (a * (c - 'A')) % m;
    }

    public static void main(String[] args) {
        final String letters = "SEARCHXMPL";
        int m = letters.length() - 1;
        int a;

        boolean found = false;
        while (!found) {
            m++;
             // Not necessary to test for a >= m because the result wraps modulo m.
            for (a = 1; a < m; a++) {
                SequentialSearchST<Integer, Character> st = new SequentialSearchST<>();
                for (char c: letters.toCharArray()) {
                    st.put(hashLetter(a, m, c), c);
                }

                if (st.size() == letters.length()) {
                    found = true;
                    System.out.printf("a=%d, m=%d%n", a, m);
                    for (Integer key: st.keys()) // display what the letters hashed to
                        System.out.printf("%c: %d%n", st.get(key), key);
                    break;
                }
            }
        }

    }
}
