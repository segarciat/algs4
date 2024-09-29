package com.segarciat.algs4.ch1.sec3.ex38;

public class ArrayGeneralizedQueue<Item> implements GeneralizedQueue<Item> {
    private int n;
    private Item[]a;

    public ArrayGeneralizedQueue() {
        a = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void insert(Item x) {
        if (x == null)
            throw new NullPointerException("cannot add null");
        if (n == a.length)
            resize(a.length * 2);
        a[n++] = x;
    }

    public Item delete(int k) {
        if (k < 0 || k >= n)
            throw new IndexOutOfBoundsException("must be non-negative less than queue size");
        int m = n - 1 - k;
        Item item = a[m];
        // Shift elements past removed element left
        for (int i = m; i + 1 < n; i++)
            a[i] = a[i + 1];
        a[--n] = null; // avoid loitering
        if (n > 0 && n == a.length / 4)
            resize(a.length / 2);
        return item;
    }
}
