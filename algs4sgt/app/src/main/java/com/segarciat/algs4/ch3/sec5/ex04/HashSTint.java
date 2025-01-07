package com.segarciat.algs4.ch3.sec5.ex04;

public class HashSTint <Value>{
    private int m;
    private int n;
    private int[] keys;
    private Value[] vals;

    public HashSTint(int capacity) {
        keys = new int[capacity];
        vals = (Value[]) new Object[capacity];
        m = capacity;
    }

    public HashSTint() {
        this(16);
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hash(int k) {
        return (k & 0x7ffffff) % m;
    }

    public Value get(int key) {
        for (int i = hash(key); vals[i] != null; i = (i + 1) % m)
            if (keys[i] == key)
                return vals[i];
        return null;
    }

    public boolean contains(int key) {
        return get(key) != null;
    }

    private void resize(int capacity) {
        HashSTint<Value> temp = new HashSTint<>(capacity);
        for (int i = 0; i < m; i++) {
            if (vals[i] != null)
                temp.put(keys[i], vals[i]);
        }
        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(int key, Value val) {
        if (n >= m / 2)
            resize(2 * m);

        int i;
        for (i = hash(key); vals[i] != null; i = (i + 1) % m) {
            if (keys[i] == key) {
                vals[i] = val;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public void delete(int key) {
        if (!contains(key))
            return;

        int i = hash(key);
        vals[i] = null;
        while (key != keys[i])
            i = (i + 1) % m;
        vals[i] = null;
        i = (i + 1) % m;
        while (vals[i] != null) {
            int keyToRedo = keys[i];
            Value valToRedo = vals[i];
            vals[i] = null;
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }
        n--;
        if (n > 0 && n <= m / 8)
            resize(m / 2);
    }
}
