package com.segarciat.algs4.ch1.sec1.ex33;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {
    @Test
    void test_dot_whenGivenInvalidArguments_throwsExceptions() {
        double[] x = { 1.0, 2.0 };
        double[] y = { 3.0, 4.0, 5.0 };

        assertThrows(NullPointerException.class, () -> Matrix.dot(null, null));
        assertThrows(NullPointerException.class, () -> Matrix.dot(null, y));
        assertThrows(NullPointerException.class, () -> Matrix.dot(x, null));
        assertThrows(IllegalArgumentException.class, () -> Matrix.dot(x, y));
    }

    @Test
    void test_dot_whenGivenValidArguments_Ok() {
        double[] x = { 1.0, 2.0 };
        double[] y = { 3.0, 4.0 };

        assertEquals(11.0, Matrix.dot(x, y), 0.001);
    }

    @Test
    void test_mult_whenGivenTwoInvalidMatrices_throws() {
        double[][] a = {
                {1.0, 2.0},
                {3.0, 4.0}
        };
        double[][] b = {
                { 5.0, 6.0 }
        };

        assertThrows(NullPointerException.class, () -> Matrix.mult((double[][]) null, (double[][]) null));
        assertThrows(NullPointerException.class, () -> Matrix.mult(a, (double[][]) null));
        assertThrows(NullPointerException.class, () -> Matrix.mult((double[][]) null, b));
        assertThrows(IllegalArgumentException.class, () -> Matrix.mult(a, b));
    }

    @Test
    void test_mult_whenGivenCompatibleMatrices_Ok() {
        double[][] a = {
                {1.0, 0.0},
                {0.0, 1.0}
        };
        double[][] b = {
                { 5.0, 6.0, 7.0  },
                { 8.0, 9.0, 10.0 }
        };
        assertArrayEquals(b, Matrix.mult(a, b));
    }

    @Test
    void test_transpose() {
        double[][] a = {
                { 5.0, 6.0, 7.0  },
                { 8.0, 9.0, 10.0 }
        };

        double[][] expected = {
                { 5.0, 8.0},
                { 6.0, 9.0},
                { 7.0, 10.0}
        };

        assertArrayEquals(expected, Matrix.transpose(a));
    }
}