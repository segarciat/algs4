package com.segarciat.algs4.ch2.sec5.ex16;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>2.5.16)</strong>
 * Reads names from standard input and prints them in the order
 * used during the 2003 California gubernational election.
 * Only works with uppercase letters of the english alphabet.
 * @author Sergio E. Garcia Tapia
 */
public class California {
    public static void main(String[] args) {
        MinPQ<String> candidateNames = new MinPQ<>(new UnbiasedNameComparator());
        while (!StdIn.isEmpty()) {
            candidateNames.insert(StdIn.readString().toUpperCase());
        }
        while (!candidateNames.isEmpty())
            System.out.println(candidateNames.delMin());
    }
}
