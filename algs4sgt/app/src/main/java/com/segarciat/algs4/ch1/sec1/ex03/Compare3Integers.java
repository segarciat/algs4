package com.segarciat.algs4.ch1.sec1.ex03;

/**
 * 1.1.3
 * Write a program that takes three command-line arguments and prints
 * 'equal'  if all three are equal and 'not equal' otherwise.
 */
public class Compare3Integers {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.printf("Usage: java %s a b c%s",
                    Compare3Integers.class.getSimpleName(),
                    System.lineSeparator());
            System.err.println("Given integers a, b, c, displays 'equal' if they are all equal.");
            System.err.println("Otherwise, displays 'not equal'.");
            System.exit(1);
        }

        try {
            long a = Long.parseLong(args[0]);
            long b = Long.parseLong(args[1]);
            long c = Long.parseLong(args[2]);

            System.out.printf("%s%s",
                    (a == b && b == c) ? "equal" : "not equal",
                    System.lineSeparator());

        } catch (NumberFormatException e) {
            System.err.println("Arguments a, b, c must all be integers.");
            System.exit(1);
        }
    }
}