package com.segarciat.algs4.ch2.sec5.ex26;

import com.segarciat.algs4.ch2.sec5.ex25.Point2D;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * <strong>2.5.26)</strong>
 * Reads xy-coordinate pairs from standard input, one pair in each
 * line, with its components separated by a comma. Then draws a polygon
 * by connecting those points.
 * @author Sergio E. Garcia Tapia
 */
public final class DrawPolygon {
    private DrawPolygon() {}

    public static void main(String[] args) {

        // Read points fro standard input
        List<Point2D> points = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            String[] tokens = StdIn.readLine().split(",");
            double x = Double.parseDouble(tokens[0]);
            double y = Double.parseDouble(tokens[1]);
            points.add(new Point2D(x, y));
        }

        // Identify the lowest point and the scale values
        Point2D p0 = points.getFirst();
        double xMin = p0.x();
        double xMax = xMin;
        double yMin = p0.y();
        double yMax = yMin;
        for (Point2D p: points) {
            if (p.y() < p0.y() || (p.y() == p0.y() && p.x() < p0.x())) {
                p0 = p;
            }

            if (p.x() < xMin)
                xMin = p.x();
            if (p.x() > xMax)
                xMax = p.x();
            if (p.y() < yMin)
                yMin = p.y();
            if (p.y() > yMax)
                yMax = p.y();
        }
        StdDraw.setXscale(xMin, xMax);
        StdDraw.setYscale(yMin, yMax);

        // Connect in order of increasing polar angle to p0
        MinPQ<Point2D> angleOrderedPoints = new MinPQ<>(p0.polarAngleToOrder());
        for (Point2D p: points)
            if (p != p0)
                angleOrderedPoints.insert(p);


        // Draw the reference point
        StdDraw.setPenRadius(0.025);
        StdDraw.setPenColor(Color.RED);
        StdDraw.point(p0.x(), p0.y());

        // Draw and connect points
        Point2D last = p0;
        while (!angleOrderedPoints.isEmpty()) {
            Point2D p = angleOrderedPoints.delMin();

            StdDraw.setPenRadius(0.025);
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(p.x(), p.y());

            StdDraw.setPenRadius(0.005);
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.line(last.x(), last.y(), p.x(), p.y());

            last = p;
        }
        StdDraw.line(last.x(), last.y(), p0.x(), p0.y());
    }
}
