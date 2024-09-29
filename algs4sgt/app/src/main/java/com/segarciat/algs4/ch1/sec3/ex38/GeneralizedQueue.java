package com.segarciat.algs4.ch1.sec3.ex38;

public interface GeneralizedQueue<Item>{
    public boolean isEmpty();
    public void insert(Item x);
    public Item delete(int k);
}
