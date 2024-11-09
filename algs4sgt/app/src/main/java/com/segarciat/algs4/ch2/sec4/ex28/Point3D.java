package com.segarciat.algs4.ch2.sec4.ex28;

/**
 * Represents a point in 3-dimensional Euclidean space.
 * @param x The x-coordinate.
 * @param y The y-coordinate.
 * @param z The z-coordinate.
 * @author Sergio E. Garcia Tapia
 */
public record Point3D(double x, double y, double z) {

    /**
     * Computes the distance of <code>this</code> point to <code>p</code>.
     * @param p The point whose distance to the origin we are interested in.
     * @return The distance from <code>this</code> point to <code>p</code>.
     */
    public double distanceTo(Point3D p) {
        if (this == p)
            return 0.0;

        return Math.sqrt(
                Math.pow((p.x() - x()), 2)
                + Math.pow(p.y() - y(), 2)
                + Math.pow(p.z() - z(), 2)
        );
    }

    /**
     * @return A string representation of this point.
     */
    @Override
    public String toString() {
        return "Point3D(%f, %f, %f)".formatted(x(), y(), z());
    }
}
