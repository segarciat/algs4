package com.segarciat.algs4.ch4.sec2.ex17;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.algs4.StdOut;

public class ClientKosarajuSharirSCC {
    public static void main(String[] args) {
        Digraph G = new Digraph(new In(args[0]));
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);
        StdOut.printf("%d strong connected components%n", scc.count());
    }
}
