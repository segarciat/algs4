package com.segarciat.algs4.ch3.sec1.ex01;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>3.1.1</strong>
 * Given a list of grades in standard input, computes the GPA.
 * @author Sergio E. Garcia Tapia
 */
public final class GPA {
    private GPA() {}

    public static void main(String[] args) {
        // Build the ST
        ST<String, Double> grades = new ST<>();
        grades.put("A+", 4.33);
        grades.put("A", 4.00);
        grades.put("A-", 3.67);
        grades.put("B+", 3.33);
        grades.put("B", 3.00);
        grades.put("B-", 2.67);
        grades.put("C+", 2.33);
        grades.put("C", 2.00);
        grades.put("C-", 1.67);
        grades.put("D", 1.00);
        grades.put("F", 0.00);

        // Read grades from stdin
        double sum = 0;
        int n = 0;
        while (!StdIn.isEmpty()) {
            String grade = StdIn.readString();
            if (!grades.contains(grade)) {
                StdOut.printf("Invalid grade: %s%n", grade);
                System.exit(1);
            }
            sum += grades.get(grade);
            n++;
        }

        // Display GPA
        StdOut.printf("%.2f%n", sum / n);
    }
}
