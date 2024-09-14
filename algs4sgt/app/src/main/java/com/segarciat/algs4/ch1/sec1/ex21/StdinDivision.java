package com.segarciat.algs4.ch1.sec1.ex21;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.1.20)</strong>
 * Write a program that reads in lines from standard input with each line
 * containing a name and two integers and then uses <code>pritnf()</code>
 * to print a table with a column of the names, the integers, and the result
 * of dividing the first by the second, accurate to three decimal places.
 * You could use a program like  this to tabulate batting averages for
 * baseball players or grades for students.
 *
 * @author Sergio E. Garcia Tapia
 */
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
