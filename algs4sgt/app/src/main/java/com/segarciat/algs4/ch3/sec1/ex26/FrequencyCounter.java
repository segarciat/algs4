package com.segarciat.algs4.ch3.sec1.ex26;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <strong>3.1.26)</strong>
 * Modifies {@link edu.princeton.cs.algs4.FrequencyCounter} to accept a
 * second command-line argument which has words of interest. Then instead
 * of displaying the most frequent word, it displays the frequency of all
 * words in the dictionary twice: first in order of frequency, and then
 * in the order in which they appear in the dictionary.
 * @author Sergio E. Garcia Tapia
 */
public final class FrequencyCounter {
    private FrequencyCounter() {}

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Provide two command-line argument: mineLen and dictionaryFile");
            System.err.println();
            System.err.println("mineLen             The minimum length of interest for a word");
            System.err.println("dictionaryFile      The name of a file with words of interest");
            System.exit(1);
        }

        int minLen = Integer.parseInt(args[0]);
        if (minLen <= 0) {
            minLen = 1 ;
        }

        In in = new In(args[1]);

        /* Build dictionary from input file */
        // SequentialSearchT maintains keys in reversed insertion order
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            st.put(word, 0);
        }
        if (st.isEmpty()) {
            System.err.println("No words in the dictionary file");
            System.exit(1);
        }

        // Read words from standard input in the dictionary
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen)
                continue;
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            }
        }
        displayOrderedByFrequency(st);
        displayOrderedByInsertion(st);
    }

    private static void displayOrderedByFrequency(SequentialSearchST<String, Integer> st) {
        // Display ordered by frequencies
        String[] orderedByFrequency = new String[st.size()];
        int i = 0;
        for (String word: st.keys()) {
            orderedByFrequency[i++] = word;
        }
        Arrays.sort(orderedByFrequency, Comparator.comparing(st::get));
        System.out.println("-------- Ordered by frequency --------");
        for (String word: orderedByFrequency) {
            StdOut.printf("%s %s%n", word, st.get(word));
        }
    }

    private static void displayOrderedByInsertion(SequentialSearchST<String, Integer> st) {
        Stack<String> words = new Stack<>();
        for (String word: st.keys()) {
            words.push(word);
        }

        System.out.println("-------- Ordered found in dictionary --------");
        for (String word: words) {
            StdOut.printf("%s %s%n", word, st.get(word));
        }
    }
}
