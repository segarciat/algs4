package com.segarciat.algs4.ch3.sec5.ex14;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;

/**
 * <strong>3.5.14)</strong>
 * Implements a method {@link #invert(ST)} that inverts a symbol table.
 * @author Sergio E. Garcia Tapia
 */
public final class InvertST {
    public static ST<String, Bag<String>> invert(ST<String, Bag<String>> st) {
        if (st == null)
            throw new NullPointerException("cannot invert a null table");

        ST<String, Bag<String>> ts = new ST<>();

        for (String key: st.keys()) {
            for (String val: st.get(key)) {
                Bag<String> bag = ts.get(val);
                if (bag == null)
                    bag = new Bag<>();
                bag.add(key);
                ts.put(val, bag);
            }
        }

        return ts;
    }

    public static void main(String[] args) {
        ST<String, Bag<String>> st = new ST<>();
        st.put("1", new Bag<>());
        st.put("-1.5", new Bag<>());
        st.put("pi", new Bag<>());

        Bag<String> bag = st.get("1");
        bag.add("positive");
        bag.add("integer");
        bag.add("rational");
        bag.add("real");

        bag = st.get("-1.5");
        bag.add("negative");
        bag.add("rational");
        bag.add("real");

        bag = st.get("pi");
        bag.add("positive");
        bag.add("irrational");
        bag.add("real");

        System.out.println("Original symbol table:");
        for (String key: st.keys())  {
            System.out.print(key + ":");
            for (String val: st.get(key))
                System.out.print(" " + val);
            System.out.println();
        }

        ST<String, Bag<String>> ts = invert(st);
        System.out.println();
        System.out.println("Inverted Table");
        for (String key: ts.keys())  {
            System.out.print(key + ":");
            for (String val: ts.get(key))
                System.out.print(" " + val);
            System.out.println();
        }
    }
}
