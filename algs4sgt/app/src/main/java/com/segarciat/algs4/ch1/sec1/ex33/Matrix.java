package com.segarciat.algs4.ch1.sec1.ex33;

/**
 * <strong>1.1.33</strong>
 * <em>Matrix library</em>. Write a library <code>Matrix</code> that implements
 * the API on page 60.
 */
public class Matrix {
    /**
     * Interprets arrays <code>x</code> and <code>y</code> as two equal length vectors
     * and computes their dot product.
     * @param x A non-null array of numbers.
     * @param y A non-null array of numbers.
     * @return The dot product of <code>x</code> and <code>y</code>
     * @throws NumberFormatException if either of <code>x</code> or <code>y</code> is <code>null</code>
     * @throws IllegalArgumentException if <code>x</code>  and <code>y</code> are not of equal length.
     */
    public static double dot(double[] x, double[] y) {
        if (x == null || y == null)
            throw new NullPointerException("vectors cannot be null");
        if (x.length != y.length || x.length == 0)
            throw new IllegalArgumentException("vectors must be of equal (positive) length");
        double sum = 0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    /**
     * Interprets two-dimensional arrays <code>a</code> and <code>b</code> as
     * matrices given in row-major format and computes their product.
     * @param a A two-dimensional array.
     * @param b A two-dimensional array.
     * @return The matrix product of <code>a</code> and <code>b</code>.
     * @throws NullPointerException if <code>a</code> or <code>b</code> is <code>null</code>.
     * @throws IllegalArgumentException if either <code>a</code> or <code>b</code> have less than 1
     * row or column, or if the number of columns in <code>a</code> is not equal to the number
     * of columns in <code>b</code>.
     */
    public static double[][] mult(double[][] a, double[][] b) {
        if (a == null || b == null)
            throw new NullPointerException("matrices cannot be null");

        if (a.length == 0 || a[0].length == 0)
            throw new IllegalArgumentException("matrix a must have at least 1 row and at least 1 column");
        if (b.length == 0 || b[0].length == 0)
            throw new IllegalArgumentException("matrix b must have at least 1 row and at least 1 column");
        if (a[0].length != b.length)
            throw new IllegalArgumentException("the number of columns in a does not match the number of rows in b");
        int m = a.length;
        int n = b[0].length;
        int q = b.length; // or a[0].length

        double[][] c = new double[m][n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < q; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    /**
     * Interprets <code>a</code> as a matrix and computes its transpose.
     * @param a A two-dimensional array representing a matrix.
     * @return The transpose of <code>a</code>.
     * @throws NullPointerException if <code>a</code> is <code>null</code>
     * @throws IllegalArgumentException if <code>a</code> does not have at least 1 row and 1 column.
     */
    public static double[][] transpose(double[][] a) {
        if (a == null)
            throw new NullPointerException("matrix cannot be null");
        if (a.length == 0 || a[0].length == 0)
            throw new IllegalArgumentException("matrix must have at least 1 row and at least 1 column");

        int m = a.length;
        int n = a[0].length;
        double[][] aTranspose = new double[n][m];

        // Note to self: not cache-friendly.
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                aTranspose[j][i] = a[i][j];

        return aTranspose;
    }

    /**
     * Interprets <code>a</code> as a matrix (in row-major format) and <code>x</code>
     * as a column vector, and computes the matrix-vector product of <code>a</code>
     * and <code>x</code>.
     * @param a A matrix in row-major format.
     * @param x A column vector.
     * @return The matrix-vector product of <code>a</code> and <code>x</code>, with
     * <code>a.length</code> entries
     * @throws NullPointerException if <code>a</code> or <code>x</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>a</code> does not have at least 1 row and 1 column,
     * or if <code>a</code> and <code>x</code> are not of matching dimensions for the product.
     */
    public static double[] mult(double[][]a, double[]x) {
        if (a == null || x == null)
            throw new NullPointerException("matrix and vector cannot be null");
        if (a.length == 0 || a[0].length == 0)
            throw new IllegalArgumentException("matrix must have at least 1 row and at least 1 column");
        if (a[0].length != x.length)
            throw new IllegalArgumentException("matrix and column vector are not of matching dimensions");
        double[] b = new double[a.length];

        for (int i = 0; i < a.length; i++)
            for (int k = 0; k < x.length; k++)
                b[i] += a[i][k] * x[k];

        return b;
    }

    /**
     * Interprets <code>a</code> as a two-dimensional matrix in row-major format
     * and <code>y</code> as a row vector, and computes the product of
     * <code>y</code> and <code>a</code> (in that order).
     * @param y A row vector.
     * @param a A matrix.
     * @return A row-vector that results from multiplying <code>y</code> and <code>a</code>
     * (in that order)
     * @throws NullPointerException if <code>a</code> or <code>y</code> is <code>null</code>.
     * @throws IllegalArgumentException if <code>a</code> does not have at least 1 row and 1 column,
     * or if <code>y</code> and <code>a</code> are not of matching dimensions for the product.
     */
    public static double[] mult(double[] y, double[][] a) {
        if (a == null || y == null)
            throw new NullPointerException("matrix and vector cannot be null");
        if (a.length == 0 || a[0].length == 0)
            throw new IllegalArgumentException("matrix must have at least 1 row and at least 1 column");
        if (a.length != y.length)
            throw new IllegalArgumentException("matrix and row vector are not of matching dimensions");
        double[] b = new double[a[0].length];

        for (int j = 0; j < b.length; j++)
            for (int i = 0; i < a.length; i++)
                b[j] += y[i] * a[i][j];

        return b;
    }
}
