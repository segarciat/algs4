package com.segarciat.algs4.ch1.sec5.ex21;

import com.segarciat.algs4.ch1.sec5.ex17.ErdosRenyi;

/**
 * @author Sergio E. Garcia Tapia
 */
public class SingleUFComponent {
    public static void main(String[] args) {
        for (int n = 256; true; n *= 2) {
            long pairs = ErdosRenyi.count(n);
            double predicted = 0.5 * n * Math.log(n);
            System.out.printf("n=%d, predicted=%.1f, actual=%d, ratio=%.1f%n",
                    n, predicted, pairs, predicted / pairs);
        }
    }
}
