package com.segarciat.algs4.ch3.sec5.ex12;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>3.5.12)</strong>
 * Builds on {@link edu.princeton.cs.algs4.LookupCSV} by associating
 * each key with all values that appear in each key-pair, not just the
 * most recent.
 * @author Sergio E. Garcia Tapia
 */
public final class LookupCSV {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        ST<String, Queue<String>> st = new ST<>();

        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            Queue<String> vals = st.get(key);
            if (vals == null)
                vals = new Queue<>();
            vals.enqueue(val);
            st.put(key, vals);
        }

        while (!StdIn.isEmpty()) {
            String query = StdIn.readLine();
            if (st.contains(query)) {
                for (String val: st.get(query))
                    StdOut.printf("%s, ", val);
                System.out.println();
            }
        }
    }
}
