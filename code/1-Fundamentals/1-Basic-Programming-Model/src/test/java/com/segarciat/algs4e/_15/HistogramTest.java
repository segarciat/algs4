package com.segarciat.algs4e._15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.segarciat.algs4e._15.Histogram.histogram;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HistogramTest {

    @Test
    @DisplayName("Check for null array")
    void test_histogram_whenGivenNull_shouldThrow() {
        int anyInteger = 0;
        assertThrows(NullPointerException.class, () -> histogram(null, anyInteger));
    }

    @Test
    @DisplayName("Check for non-positive argument")
    void test_histogram_whenGivenNegativeValue_shouldThrow() {
        int[] anyValidArray = new int[0];
        assertThrows(IllegalArgumentException.class, () -> histogram(anyValidArray, -1));
        assertThrows(IllegalArgumentException.class, () -> histogram(anyValidArray,  0));
    }

    @Test
    void test_histogram_whenMIsPositive_whenAllValuesExceedM_shouldReturnArraysOfZeroes() {
        int m = 5;
        int[] a        = { 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        int[] expected = { 0, 0, 0, 0, 0 };

        int[] actual = histogram(a, m);

        assertEquals(m, actual.length);
        assertArrayEquals(expected, actual);
    }

    @Test
    void test_histogram_whenMIsPositive_whenAllValuesAreEqual_shouldReturnArrayOfOnes() {
        int m = 5;
        int[] a = { 4, 2, 5, 1, 0, 15, 3, -1, -5};
        int[] expected = { 1, 1, 1, 1, 1 };

        int[] actual = histogram(a, m);

        assertEquals(m, actual.length);
        assertArrayEquals(expected, actual);
    }
}