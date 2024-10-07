package com.segarciat.algs4.ch1.sec4.ex19;

public class LocalMatrixMinimum {
    /**
     * Determines the index of the minimum entry.
     * @param a An array with at least 1 value.
     * @return The index of the minimum entry.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if the array is empty.
     */
    private static int minIndex(int[] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        if (a.length == 0)
            throw new IllegalArgumentException("array must have at least one value");
        int minIndex = 0;
        for (int i = 1; i < a.length; i++)
            if (a[i] < a[minIndex])
                minIndex = i;
        return minIndex;
    }

    /**
     * Determines a local minimum in the matrix. The is, an entry that
     * is less than all of its neighbors, where a neighbor can be
     * an entry above, below, left, or right, if they exist.
     * @param a A square matrix array with dimension at least 2 by 2.
     * @return A local minimum in the matrix.
     * @throws NullPointerException if the array is <code>null</code>.
     * @throws IllegalArgumentException if the matrix is not square and at least 2 by 2.
     */
    public static int localMinimum(int[][] a) {
        if (a == null)
            throw new NullPointerException("array cannot be null");
        // Could check all rows.
        if (a.length < 2)
            throw new IllegalArgumentException("matrix must have at least 2 rows");
        for (int[] row : a)
            if (row.length != a.length)
                throw new IllegalArgumentException("the matrix must be square");
        int n = a.length;


        // -------------------------- Corner entries (2 neighbors) -----------------------------
        // Top-left corner
        if (a[0][0] < a[0][1] && a[0][0] < a[1][0])
            return a[0][0];
        // Top-right corner
        if (a[0][n - 1] < a[0][n - 2] && a[0][n - 1] < a[1][n - 1])
            return a[0][n - 1];
        // Bottom-left corner
        if (a[n - 1][0] < a[n - 2][0] && a[n - 1][0] < a[n - 1][1])
            return a[n - 1][0];
        // Bottom-right corner
        if (a[n - 1][n - 1] < a[n - 2][n - 1] && a[n - 1][n - 1] < a[n - 1][n - 2])
            return a[n - 1][n - 1];

        // ------------------------- Non-corner edge entries (3 neighbors) ---------------------
        for (int j = 1; j < n - 1; j++) {
            // First row
            if (a[0][j] < a[0][j - 1] && a[0][j] < a[0][j + 1] && a[0][j] < a[1][j])
                return a[0][j];
            // Last row
            if (a[n - 1][j] < a[n - 1][j - 1] && a[n - 1][j] < a[n - 1][j + 1] && a[n - 1][j] < a[n - 2][j])
                return a[0][j];
        }

        for (int i = 1; i < n - 1; i++) {
            // First column
            if (a[i][0] < a[i - 1][0] && a[i][0] < a[i + 1][0] && a[i][0] < a[i][1])
                return a[i][0];
            // Last column
            if (a[i][n - 1] < a[i - 1][n-1] && a[i][n - 1] < a[i + 1][n - 1] && a[i][n - 1] < a[i][n - 2])
                return a[i][n - 1];
        }

        // -------------------------- Inner entries (4 neighbors) -------------------------
        int rowLo = 1;
        int rowHi = n - 2;

        while (rowLo <= rowHi) {
            int mid = rowLo + (rowHi - rowLo) / 2;
            int minRowIndex = minIndex(a[mid]);
            if (a[mid][minRowIndex] > a[mid + 1][minRowIndex])
                rowLo = mid + 1;
            else if (a[mid][minRowIndex] > a[mid - 1][minRowIndex])
                rowHi = mid - 1;
            else
                return a[mid][minRowIndex];
        }

        return -1;
    }
}
