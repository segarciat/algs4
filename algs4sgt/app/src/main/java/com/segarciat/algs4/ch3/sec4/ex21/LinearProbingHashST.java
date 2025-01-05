package com.segarciat.algs4.ch3.sec4.ex21;

public class LinearProbingHashST<Key, Value> {
    private int n;
    private int m = 16;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> temp;
        temp = new LinearProbingHashST<>();

        for (int i = 0; i < m; i++)
            if (keys[i] != null)
                temp.put(keys[i], vals[i]);

        keys = temp.keys;
        vals = temp.vals;
        m = temp.m;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add null key");

        if (n >= m / 2)
            resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key))
                return vals[i];
        }

        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void delete(Key key) {
        if (!contains(key))
            return;

        // find the key
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % m;

        // remove the key-value pair
        keys[i] = null;
        vals[i] = null;

        // re-hash all keys in the same cluster that follow it
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            // put will increment n again
            n--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % m;
        }

        n--;

        // shrink while maintaining a reasonable load factor
        if (n > 0 && n <= m / 8)
            resize(m / 2);
    }

    public double averageMissCost() {
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i; keys[j] != null; j = (j + 1) % m)
                count++;
        }

        return (count + 0.0) / m;
    }
}
