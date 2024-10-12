package com.segarciat.algs4.ch1.sec5.ex19;

import com.segarciat.algs4.ch1.sec5.ex18.RandomGrid;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.UF;

import java.awt.Color;

/**
 * @author Sergio E. Garcia Tapia
 */
public class RandomGridAnimation {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Provide one command-line argument: n");
            System.err.println("n    The size of the grid, which must be 2 or more.");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        RandomGrid.Connection[] connections = RandomGrid.generate(n);
        UF uf = new UF(n * n);
        StdDraw.setXscale(-1, n);
        StdDraw.setYscale(-1, n);
        for (RandomGrid.Connection conn: connections) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.01);

            int xp = conn.p % n;
            int yp = conn.p / n;
            StdDraw.point(xp, yp);

            int xq = conn.q % n;
            int yq = conn.q / n;
            StdDraw.point(xq, yp);

            if (!uf.connected(conn.p, conn.q)) {
                uf.union(conn.p, conn.q);

                StdDraw.setPenColor(Color.BLACK);
                StdDraw.setPenRadius(0.005);
                StdDraw.line(xp, yp, xq, yq);
            }
        }
    }
}
