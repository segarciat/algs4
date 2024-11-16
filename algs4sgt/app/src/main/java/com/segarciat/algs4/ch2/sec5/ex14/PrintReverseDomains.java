package com.segarciat.algs4.ch2.sec5.ex14;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

/**
 * <code>2.5.14)</code>
 * Reads domains from standard input and displays them in standard
 * output in sorted fashion according to their reverse subdomain
 * @author Sergio E. Garcia Tapia
 */
public class PrintReverseDomains {
    private PrintReverseDomains() {}
    public static void main(String[] args) {
        MinPQ<Domain> domains = new MinPQ<>();
        while (!StdIn.isEmpty()) {
            String domain = StdIn.readString();
            domains.insert(new Domain(domain));
        }

        while (!domains.isEmpty()) {
            System.out.println(domains.delMin().reverseDomain());
        }
    }
}
