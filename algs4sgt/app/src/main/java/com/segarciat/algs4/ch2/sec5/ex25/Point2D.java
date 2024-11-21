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
        theta = Math.atan2(y, x);
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

    public double squaredDistanceTo(Point2D that) {
        return Math.pow(this.x() - that.x(), 2) +
                Math.pow(this.y() - that.y(), 2);
    }

    /**
     * A Comparator for sorting by distance to this point.
     */
    public Comparator<Point2D> distanceToPointOrder() {
        return new DistanceToPointOrder();
    }

    public Comparator<Point2D> polarAngleToOrder() {
        return new PolarAngleToPointOrder();
    }

    @Override
    public String toString() {
        return "(%f, %f)".formatted(x, y);
    }

    /**
     * A comparator for sorting by x coordinate.
     */
    private static class XOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.x(), q.x());
        }
    }

    /**
     * A comparator for sorting by y coordinate.
     */
    private static class YOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.y(), q.y());
        }
    }

    /**
     * A comparator for sorting by distance to origin.
     */
    private static class RadiusOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(p.r(), q.r());
        }
    }

    /**
     * A comparator for sorting by distance to this point.
     */
    private class DistanceToPointOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            return Double.compare(squaredDistanceTo(p), squaredDistanceTo(q));
        }
    }

    /**
     * A comparator for sorting by polar angle to this point.
     */
    private class PolarAngleToPointOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D p, Point2D q) {
            double pX = p.x() - x();
            double pY = p.y() - y();
            double pTheta = Math.atan2(pY, pX);

            double qX = q.x() - x();
            double qY = q.y() - y();
            double qTheta = Math.atan2(qY, qX);

            return Double.compare(pTheta, qTheta);
        }
    }
}
