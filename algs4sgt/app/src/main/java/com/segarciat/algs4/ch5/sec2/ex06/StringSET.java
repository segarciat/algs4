package com.segarciat.algs4.ch5.sec2.ex06;

import edu.princeton.cs.algs4.TrieST;

/**
 * <strong>5.2.6)</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class StringSET {
    private final TrieST<Integer> tst = new TrieST<>();

    public void add(String key) {
        tst.put(key, 0);
    }

    public void delete(String key) {
        tst.delete(key);
    }

    public boolean contains(String key) {
        return tst.contains(key);
    }

    public int size() {
        return tst.size();
    }

    @Override
    public String toString() {
        return tst.keys().toString();
    }
}
