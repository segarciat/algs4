package com.segarciat.algs4.ch3.sec5.ex15;

import edu.princeton.cs.algs4.ST;

/**
 * <strong>3.5.15)</strong>
 * Displays all k-grams that occur in a given string.
 * @author Sergio E. Garcia Tapia
 */
public final class KGrams {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: string k");
            System.err.println("Displays the k-grams in the given string,");
            System.err.println("along with each k-gram's index in the string.");
            System.exit(1);
        }

        String s = args[0];
        int k = Integer.parseInt(args[1]);
        if (k <= 0 || k > s.length()) {
            System.err.println("Invalid integer k");
            System.exit(1);
        }
        ST<String, Integer> kGrams = new ST<>();
        for (int i = 0; i < s.length() - k; i++) {
            kGrams.put(s.substring(i, i + k), i);
        }

        for (String kGram: kGrams.keys()) {
            System.out.println(kGram + ": " + kGrams.get(kGram));
        }

    }
}
