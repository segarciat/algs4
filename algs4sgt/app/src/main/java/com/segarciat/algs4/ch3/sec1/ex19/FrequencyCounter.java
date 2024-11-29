package com.segarciat.algs4.ch3.sec1.ex19;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>3.1.19</strong>
 * Reads words from standard input that are no less than
 * a minimum length provided as a command-line argument.
 * Outputs all the words with the highest frequency.
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

        // Read words
        ST<String, Integer> st = new ST<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen)
                continue;

            if (!st.contains(word)) {
                st.put(word, 1);
            } else {
                st.put(word, st.get(word) + 1);
            }
        }

        // Find the most frequency words
        Queue<String> mostFrequent = new Queue<>();
        String max = "";
        st.put(max, 0);
        for (String word: st.keys()) {
            int currentFrequency = st.get(word);
            int maxFrequency = st.get(max);

            if (currentFrequency == maxFrequency) {
                mostFrequent.enqueue(word);
            } else if (currentFrequency > maxFrequency) {
                while (!mostFrequent.isEmpty())
                    mostFrequent.dequeue();
                mostFrequent.enqueue(word);
                max = word;
            }
        }

        // Display most frequent words
        for (String word: mostFrequent)
          StdOut.println(word + " " + st.get(word));
    }
}
