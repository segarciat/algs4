package com.segarciat.algs4.ch1.sec2.ex03;

import edu.princeton.cs.algs4.Interval1D;
import edu.princeton.cs.algs4.Interval2D;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;

/**
 *  1.2.3) Write an <code>Interval2D</code> client that takes command-line arguments <code>n</code>,
 * 	<code>min</code>, and <code>max</code> and generates <code>n</code> random 2D intervals whose
 * 	width and height are uniformly distributed between <code>min</code> and <code>max</code>
 * 	in the unit square. Draw them on <code>StdDraw</code> and print the number of pairs of intervals
 * 	that intersect and the number of intervals that are contained in one another.
 */
public class IntersectingRectangles {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Provide three arguments: n, min, max");
            System.err.println();
            System.err.println("n      The number of 2D intervals to create in the unit square.");
            System.err.println("       Must be a positive integer.");
            System.err.println("min    Minimum value for an interval width or height");
            System.err.println("       Must be a positive real number less than 1.");
            System.err.println("max    Maximum value for an interval width or height");
            System.err.println("       Must be a positive real number less than 1.");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);
        if (n <= 0) {
            System.err.printf("Expected a positive integer, but got: %d%n", n);
            System.exit(1);
        }

        double min = Double.parseDouble(args[1]);
        double max = Double.parseDouble(args[2]);

        if (min <= 0 || max <= 0 || min >= 1 || max >= 1) {
            System.err.printf("Either min or max is in the open interval (0, 1): min=%f, max=%f%n", min, max);
            System.exit(1);
        }

        if (max < min) {
            System.err.printf("Invalid input: max=%f, but min=%f%n", max, min);
            System.exit(1);
        }

        // Create n 2D rectangles in the unit square with widths and heights between min and max
        Point2D[] bottomLeftVertices = new Point2D[n];
        Point2D[] topRightVertices = new Point2D[n];
        Interval2D[] rectangles = new Interval2D[n];
        for (int i = 0; i < n; i++) {
            double width = StdRandom.uniformDouble(min, max);
            double height = StdRandom.uniformDouble(min, max);

            double xLeft = StdRandom.uniformDouble(0, 1 - width);
            double xRight = xLeft+ width;

            double yBottom = StdRandom.uniformDouble(0, 1 - height);
            double yTop = yBottom + height;

            //  Track bottom left and top left vertices to check containments later.
            bottomLeftVertices[i] = new Point2D(xLeft, yBottom);
            topRightVertices[i] = new Point2D(xRight, yTop);

            Interval1D xInterval = new Interval1D(xLeft,xRight);
            Interval1D yInterval = new Interval1D(yBottom, yTop);

            rectangles[i] = new Interval2D(xInterval, yInterval);
            rectangles[i].draw();
        }

        int intersections = 0;
        int containments = 0;
        for (int i = 0; i < n; i++) {
            Interval2D rectA = rectangles[i];
            Point2D bottomLeftVertexA = bottomLeftVertices[i];
            Point2D topRightVertexA  = topRightVertices[i];

            for (int j = i + 1; j < n; j++) {
                Interval2D rectB = rectangles[j];

                if (rectA.intersects(rectB))
                    intersections++;
                else {
                    Point2D bottomLeftVertexB = bottomLeftVertices[j];
                    Point2D topRightVertexB = topRightVertices[j];

                    if ((rectA.contains(bottomLeftVertexB) && rectA.contains(topRightVertexB))
                            || (rectB.contains(bottomLeftVertexA) && rectB.contains(topRightVertexA)))
                        containments++;
                }
            }
        }
        System.out.printf("Intersections: %d%n", intersections);
        System.out.printf("Containments: %d%n", containments);
    }
}
