package com.segarciat.algs4.ch3.sec4.ex34;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.time.Instant;

/**
 * @author Sergio E. Garcia Tapia
 */
public final class HashCost {
    private static final int TRIALS = 1_000_000;
    public static <T extends Comparable<T>> double hashCompareRatio(T x, T y) {
        double hashTime = 0.0;
        double compareTime = 0.0;

        Stopwatch watch;
        for (int t = 0; t < TRIALS; t++) {
            watch = new Stopwatch();
            x.hashCode();
            hashTime += watch.elapsedTime();
        }

        for (int t = 0; t < TRIALS; t++) {
            watch = new Stopwatch();
            x.compareTo(y);
            compareTime += watch.elapsedTime();
        }

        return hashTime / compareTime;
    }

    public static double intCost() {
        int m = StdRandom.uniformInt(Integer.MAX_VALUE);
        int n = StdRandom.uniformInt(Integer.MAX_VALUE);

        return hashCompareRatio(m, n);
    }

    public static double doubleCost() {
        double x = StdRandom.uniformDouble();
        double y = StdRandom.uniformDouble();

        return hashCompareRatio(x, y);
    }

    public static double stringCost() {
        String s = "onomatopeia";
        String t = "onomato";
        String r = "peia";
        return hashCompareRatio(s, t + r);
    }

    public static double instantCost() {
        Instant x = Instant.now();
        Instant y = Instant.ofEpochMilli(StdRandom.uniformLong(Long.MAX_VALUE));

        return hashCompareRatio(x, y);
    }

    public static void main(String[] args) {
        System.out.printf("Integer: %f%n", intCost());
        System.out.printf("Double: %f%n", doubleCost());
        System.out.printf("String: %f%n", stringCost());
        System.out.printf("Instant: %f%n", instantCost());
    }
}
