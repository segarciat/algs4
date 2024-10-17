package com.segarciat.algs4.ch2;

import com.segarciat.algs4.ch2.sec1.ex24.InsertionSentinel;
import com.segarciat.algs4.ch2.sec1.ex25.InsertionHalfExchanges;
import edu.princeton.cs.algs4.Heap;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Implementation from Algorithms by Sedgewick and Wayne (Section 2.1, page 256)
 * Runs two sorts named from the first two command line arguments on arrays of
 * n random doubles between 0 and 1, repeating the experiment T times.
 *
 * @author Sergio E. Garcia Tapia
 */
public class SortCompare {
    private static final String INSERTION_SORT = "Insertion";
    private static final String INSERTION_SORT_SENTINEL = "InsertionSentinel";
    private static final String INSERTION_SORT_HALF = "InsertionHalf";
    private static final String SELECTION_SORT = "SELECTION";
    private static final String SHELL_SORT = "Shell";
    private static final String MERGE_SORT = "Merge";
    private static final String QUICK_SORT = "Quick";
    private static final String HEAP_SORT = "Heap";

    /**
     * Returns the time the chosen algorithm takes to execute on
     * the given array.
     * Implementation from Algorithms by Sedgewick and Wayne (Section 2.1, page 255).
     * @param alg The algorithm of interest.
     * @param a The array to be sorted.
     * @return How long the algorithm took to sort the array.
     */
    public static double time(String alg, Double[] a) {
        if (alg == null || a == null)
            throw new NullPointerException("arguments cannot be null");
        Stopwatch timer = new Stopwatch();
        if (alg.equalsIgnoreCase(SELECTION_SORT))
            Selection.sort(a);
        else if (alg.equalsIgnoreCase(INSERTION_SORT))
            Insertion.sort(a);
        else if (alg.equalsIgnoreCase(INSERTION_SORT_SENTINEL))
            InsertionSentinel.sort(a);
        else if (alg.equalsIgnoreCase(INSERTION_SORT_HALF))
            InsertionHalfExchanges.sort(a);
        else if (alg.equalsIgnoreCase(SHELL_SORT))
            Shell.sort(a);
        else if (alg.equalsIgnoreCase(MERGE_SORT))
            Merge.sort(a);
        else if (alg.equalsIgnoreCase(QUICK_SORT))
            Quick.sort(a);
        else if (alg.equalsIgnoreCase(HEAP_SORT))
            Heap.sort(a);
        else {
            throw new IllegalArgumentException("Invalid sorting algorithm name");
        }
        return timer.elapsedTime();
    }

    /**
     * Implementation from Algorithms by Sedgewick and Wayne (Section 2.1, page 256).
     * Times the given sorting algorithm on an array of size n as many times as the
     * give trials request.
     *
     * @param alg The algorithm to be timed.
     * @param n The size of the array.
     * @param trials The number of trials for the experiment.
     * @return The average sorting during among the trials for the given algorithm.
     */
    public static double timeRandomInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniformDouble(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String[] validAlgorithms = {
                SELECTION_SORT,
                INSERTION_SORT,
                INSERTION_SORT_SENTINEL,
                INSERTION_SORT_HALF,
                SHELL_SORT,
                MERGE_SORT,
                QUICK_SORT,
                HEAP_SORT,
        };
        if (args.length != 4) {
            System.err.println("Runs the requested sorted algorithms and displays");
            System.err.println("a summary of their comparative performance.");
            System.err.println();
            System.err.println("Please provide four command-line arguments:");
            System.err.println("alg1      The first algorithm to test");
            System.err.println("alg2      The second algorithm to test");
            System.err.println("n         The size of the array to be sorted during the test");
            System.err.println("T         The number of trials for each algorithm");
            System.err.println();
            System.err.printf("Valid algorithms: %s%n",
                    String.join(",", validAlgorithms)
            );
        }

        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int trials = Integer.parseInt(args[3]);
        if (n < 2) {
            System.err.println("Array must have 2 or more elements.");
            System.exit(1);
        }
        if (trials <= 0) {
            System.err.println("Must have at least 1 trial run");
            System.exit(1);
        }

        double time1 = timeRandomInput(alg1, n, trials);
        double time2 = timeRandomInput(alg2, n, trials);
        double ratio = time2 / time1;

        StdOut.printf("For %d random Doubles%n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s%n", ratio, alg2);
    }
}
