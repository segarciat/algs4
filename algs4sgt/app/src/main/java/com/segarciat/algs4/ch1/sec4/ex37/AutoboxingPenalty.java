package com.segarciat.algs4.ch1.sec4.ex37;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class AutoboxingPenalty {
    public static double timeTrial(int n) {
        FixedCapacityStack<Integer> autoBoxIntStack = new FixedCapacityStack<>(n);
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++)
            autoBoxIntStack.push(i);
        double autoBoxElapsed = timer.elapsedTime();


        FixedCapacityStackOfInts primitiveIntStack = new FixedCapacityStackOfInts(n);
        timer = new Stopwatch();
        for (int i = 0; i < n; i++)
            primitiveIntStack.push(i);
        double primitiveIntElapsed = timer.elapsedTime();

        return autoBoxElapsed / primitiveIntElapsed;
    }

    public static void main(String[] args) {
        for (int n = 250; true; n *= 2) {
            // How much slower the generic version is.
            double ratio = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, ratio);
        }
    }
}
