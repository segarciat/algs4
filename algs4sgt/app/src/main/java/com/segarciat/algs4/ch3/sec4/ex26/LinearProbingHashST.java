package com.segarciat.algs4.ch3.sec4.ex26;

public class LinearProbingHashST <Key, Value> {
    private int n;
    private int m = 16;
    private int tombstoned = 0;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    public Value get(Key key) {
        if (key == null)
            return null;

        /*
         * Note: because we set the value of lazy-deleted key-value
         * pairs to null, the search for keys that follow in the
         * cluster won't end, so those items can still be found.
         */
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (key.equals(keys[i]))
                return vals[i];
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>();
        for (int i = 0; i < m; i++) {
            if (keys[i] != null && vals[i] != null)
                temp.put(keys[i], vals[i]);
        }
        tombstoned = 0;
        keys = temp.keys;
        vals = temp.vals;
        n = temp.n;
        m = temp.m;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("cannot delete null key");
        if (!contains(key))
            return;

        int i = hash(key);
        vals[i] = null;
        tombstoned++;
        n--;
        int total = n + tombstoned;
        if (total > 0 && total <= m / 8)
            resize(m / 2);
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot insert a null key");
        if ((n + tombstoned) >= m / 2)
            resize(2 * m);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (vals[i] == null) { // tombstoned item
                tombstoned--;
                break;
            } else if (keys[i].equals(key)) { // existing non-tombstoned item
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        n++;
    }
}
