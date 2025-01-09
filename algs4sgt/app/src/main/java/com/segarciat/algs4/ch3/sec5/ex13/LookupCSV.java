package com.segarciat.algs4.ch3.sec5.ex13;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>3.5.13)</strong>
 * Builds on {@link edu.princeton.cs.algs4.LookupCSV}, modifying
 * its behavior to require two inputs defining a range for
 * keys, and displaying all key-value pairs for keys in that range.
 * @author Sergio E. Garcia Tapia
 */
public final class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        RedBlackBST<String, String> st = new RedBlackBST<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }

        while (!StdIn.isEmpty()) {
            String[] query = StdIn.readLine().split("\\s+");
            if (query.length != 2) {
                System.out.println("Enter exactly two keys to define a range.");
                continue;
            }
            String lo = query[0];
            String hi = query[1];
            for (String key: st.keys(lo, hi)) {
                System.out.printf("%s, %s%n", key, st.get(key));
            }
        }
    }
}
