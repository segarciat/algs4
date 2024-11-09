package com.segarciat.algs4.ch2.sec4.ex28;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

import java.util.Comparator;

/**
 * <strong>2.4.28)</strong>
 * Displays the m points in Euclidean space in the given input
 * stream that are closest to the origin.
 *
 * @author Sergio E. Garcia Tapia
 */
public class ClosestMPoints {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Reads points in (x, y, z) in Euclidean space from standard input,");
            System.err.println("each on a separate line. Then, displays the m closest points to the");
            System.err.println("origin on standard output. Points must be given in comma-separated form ");
            System.err.println("(no parentheses).");
            System.err.println();
            System.err.println("Usage: Provide the following arguments: m");
            System.err.println();
            System.err.println("m    The number of pairs desired");

            System.exit(1);
        }

        int m = -1;
        try {
            m = Integer.parseInt(args[0]);
        } catch(NumberFormatException e) {
            System.err.println("Invalid integer");
            System.exit(1);
        }

        if (m <= 0) {
            System.err.println("Expected a positive integer.");
            System.exit(1);
        }

        MaxPQ<Point3D> pq = new MaxPQ<>(m + 1,
                Comparator.comparing(p -> p.distanceTo(new Point3D(0, 0, 0)))
        );

        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            String[] tokens = line.split(",");
            if (tokens.length != 3)
                continue;

            double x = Double.parseDouble(tokens[0]);
            double y = Double.parseDouble(tokens[1]);
            double z = Double.parseDouble(tokens[2]);

            pq.insert(new Point3D(x, y, z));

            if (pq.size() > m)
                pq.delMax();
        }

        // The remaining m are smallest; display in distance-ascending order.
        Stack<Point3D> stack = new Stack<>();
        while (!pq.isEmpty())
            stack.push(pq.delMax());
        for (Point3D p: stack)
            System.out.println(p);
    }
}
