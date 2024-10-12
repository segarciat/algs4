package com.segarciat.algs4.ch1.sec5.ex18;

import com.segarciat.algs4.ch1.sec3.ex34.RandomBag;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Sergio E. Garcia Tapia
 */
public class RandomGrid {
    public static Connection[] generate(int n) {
        if (n <= 1)
            throw new IllegalArgumentException("n must be at least 2");
        RandomBag<Connection> bag = new RandomBag<>();

        // Process first n-1 rows and n-1 columns
        for (int r = 0; r < n; r++) {
            int p = r *  n;
            for (int c = 0; c < n; c++) {
                // Connect to right
                if (c < n - 1) {
                    int q = p + 1;
                    if (StdRandom.uniformDouble() < 0.5)
                        bag.add(new Connection(p, q));
                    else
                        bag.add(new Connection(q, p));
                }

                // Connect to bottom
                if (r < n - 1) {
                    int q = p + n;
                    if (StdRandom.uniformDouble() < 0.5)
                        bag.add(new Connection(p, q));
                    else
                        bag.add(new Connection(q, p));
                    p++;
                }
            }
        }

        Connection[] connections = new Connection[bag.size()];
        int i = 0;
        for (Connection conn: bag)
            connections[i++] = conn;
        return connections;
    }

    public static void main(String[] args){
        if(args.length != 1) {
            System.err.println("Provide one command-line argument: n");
            System.err.println("n    The size of the grid, which must be 2 or more.");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        Connection[] connections = generate(n);
        for (Connection conn: connections)
            System.out.printf("%d %d%n", conn.p, conn.q);
    }

    public static class Connection {
        public final int p;
        public final int q;

        Connection(int p, int q) {
            this.p = p;
            this.q = q;
        }
    }
}
