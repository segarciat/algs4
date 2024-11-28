package com.segarciat.algs4.ch3.sec1.ex09;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>3.1.9</strong>
 * Reads words from standard input that are no less than
 * a minimum length provided as a command-line argument.
 * Outputs the most recently added word, and the words
 * encounter since one was last added.
 * Based on {@link edu.princeton.cs.algs4.FrequencyCounter}.
 * @author Sergio E. Garcia Tapia
 */
public final class FrequencyCounter {
    private FrequencyCounter() {}

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide a single argument: a length cutoff.");
            System.exit(1);
        }
        // Read cutoff length
        int minLen = Integer.parseInt(args[0]);

        // Track last word
        int countSinceLastPut = 0;

        // Read words
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen)
                continue;

            if (!st.contains(word)) {
                StdOut.printf("%s %d%n", word, countSinceLastPut);
                st.put(word, 1);
                countSinceLastPut = 0;
            } else {
                countSinceLastPut++;
                st.put(word, st.get(word) + 1);
            }
        }

        // Display most frequent word
        String max = "";
        st.put(max, 0);
        for (String word: st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
        }
        StdOut.println();
        StdOut.println(max + " " + st.get(max));
    }
}
