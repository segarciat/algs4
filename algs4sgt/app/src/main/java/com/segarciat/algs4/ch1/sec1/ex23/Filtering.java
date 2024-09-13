package com.segarciat.algs4.ch1.sec1.ex23;


import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 1.1.23)
 * Add to the <code>BinarySearch</code> test client the ability to respond to
 * a second argument: <code>+</code> to print numbers from standard input that
 * <em>are not</em> in the whitelist, <code>-</code> to print numbers that <em>are</em>
 * 	in the whitelist.
 */
public class Filtering {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Filtering int_reference_file <+|->");
            System.err.println("Reads integers from standard input and possibly displays them ");
            System.err.println("depending on the given reference file and whether the second ");
            System.err.println("command-line argument is '+' or '-'");
            System.out.println();
            System.err.println("\t+\tDisplay integers in stdin that are not in the reference file.");
            System.err.println("\t-\tDisplay integers in stdin that are in the reference file.");
            System.exit(1);
        }
        if (!"+".equals(args[1]) && !"-".equals(args[1])) {
            System.err.printf("Expected + or - in second argument, but got: %s%n", args[1]);
            System.exit(1);
        }
        In in = new In(args[0]);
        int[] referenceList = in.readAllInts();

        if ("+".equals(args[1]))
            displayNotInList(referenceList);
        else
            displayInList(referenceList);
    }

    private static void displayNotInList(int[] referenceList) {
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(referenceList, key) == -1)
                StdOut.println();
        }
    }

    private static void displayInList(int[] referenceList) {
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearch.indexOf(referenceList, key) != -1)
                StdOut.println();
        }
    }
}
