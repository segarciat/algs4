package com.segarciat.algs4.ch2.sec5.ex08;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

import java.util.Comparator;

/**
 * <strong>2.5.8)</strong>
 * Reads strings from standard input and displays them
 * in descending order of frequency.
 * @author Sergio E. Garcia Tapia
 */
public class Frequency {
    private Frequency() {}
    public static void main(String[] args) {
        // Read all strings from standard input, ordered lexicographically
        if (StdIn.isEmpty())
            System.exit(0);
        MinPQ<String> strings = new MinPQ<>(StdIn.readAllStrings());

        // Tally frequencies, ordered by frequencies
        MaxPQ<StringCountNode> counts = new MaxPQ<>(Comparator.comparing(StringCountNode::getFrequency));
        while (!strings.isEmpty()) {
            StringCountNode sNode = new StringCountNode(strings.delMin());
            while (!strings.isEmpty() && strings.min().equals(sNode.getString())) {
                sNode.tally();
                strings.delMin();
            }
            counts.insert(sNode);
        }

        // Show in decreasing order of frequency
        while (!counts.isEmpty())
            System.out.println(counts.delMax());
    }

    private static class StringCountNode {
        private final String s;
        private int frequency = 1;

        public StringCountNode(String s) {
            if (s == null)
                throw new NullPointerException("cannot have null string");
            this.s = s;
        }

        public String getString() {
            return s;
        }

        public void tally() {
            frequency++;
        }

        public int getFrequency() {
            return frequency;
        }

        @Override
        public String toString() {
            return "%d: %s".formatted(frequency, s);
        }
    }
}
