package com.segarciat.algs4.ch2.sec5.ex25;

import java.util.Comparator;

/**
 * <strong>2.5.25)</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class Point2D {
    private final double x;
    private final double y;
    private final double r;
    private final double theta;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        r = Math.sqrt(x * x + y * y);
        theta = Math.atan(y / x);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public double r() {
        return r;
    }

    public double theta() {
        return theta;
    }

    public double distanceTo(Point2D that) {
        return Math.sqrt(
                Math.pow(this.x() - that.x(), 2) +
                Math.pow(this.y() - that.y(), 2)
        );
    }

    public static class XOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.x(), q.x());
        }
    }

    public static class YOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.y(), q.y());
        }
    }

    public static class DistanceToOriginOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.r(), q.r());
        }
    }
}
