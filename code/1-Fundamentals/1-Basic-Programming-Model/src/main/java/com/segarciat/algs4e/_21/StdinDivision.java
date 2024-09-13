package com.segarciat.algs4e._21;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StdinDivision {
    public static void main(String[] args) {
        StdOut.printf("%10s %10s %10s %10s %16s%n",
                "Line", "Name", "First", "Second", "Division");
        long lineCount = 0;
        while (!StdIn.isEmpty()) {
            String name = StdIn.readString();
            long m = StdIn.readLong();
            long n = StdIn.readLong();

            // Read the rest of the line.
            StdIn.readLine();
            lineCount++;
            if (n == 0) {
                System.err.printf("Error: Second integer is 0 on line %d: cannot divide by 0%n", lineCount);
                System.exit(1);
            }
            double quotient = (double) m / (double) n;
            StdOut.printf("%10d %10s %10d %10d %16f%n", lineCount, name, m, n, quotient);
        }
    }
}
