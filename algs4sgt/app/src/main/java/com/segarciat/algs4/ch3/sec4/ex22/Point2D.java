package com.segarciat.algs4.ch3.sec4.ex22;

public class Point2D {
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

    @Override
    public int hashCode() {
        int hash = 1;

        for (double val: new double[] { x, y })
            hash = 31 * hash + Double.hashCode(val);

        return hash;
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

    @Override
    public String toString() {
        return "(%f, %f)".formatted(x, y);
    }
}
