package com.segarciat.algs4.ch3.sec5.ex01;

import edu.princeton.cs.algs4.ST;

/**
 * <strong>3.5.1</strong>
 * Implements a set by using an ordered symbol table as a wrapper.
 * @author Sergio E. Garcia Tapia
 */
public class SET <Key extends Comparable<Key>> {
    private ST<Key, Integer> st;

    public void add(Key key) {
        st.put(key, 0);
    }

    public void delete(Key key) {
        st.delete(key);
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (Key key: st.keys())
            sb.append(key.toString()).append(", ");
        sb.append(" }");
        return sb.toString();
    }
}
