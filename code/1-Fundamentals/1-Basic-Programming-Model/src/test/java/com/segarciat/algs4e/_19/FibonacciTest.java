package com.segarciat.algs4e._19;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.segarciat.algs4e._19.Fibonacci.fibonacciFaster;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    @Test
    @DisplayName("Invalid sequence index")
    void test_fibonacciFaster_whenGivenInvalidIndex_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> fibonacciFaster(-1));
    }

    @Test
    @DisplayName("Fibonacci base cases")
    void test_fibonacciFaster_whenGivenBaseCases_shouldReturnValidValues() {
        assertEquals(0, fibonacciFaster(0));
        assertEquals(1, fibonacciFaster(1));
    }

    @Test
    @DisplayName("Fibonacci sequence values beyond base case")
    void test_fibonacciFaster_whenGivenIndexBeyond1_shouldReturnValidValues() {
        assertEquals(1, fibonacciFaster(2));
        assertEquals(2, fibonacciFaster(3));
        assertEquals(3, fibonacciFaster(4));
        assertEquals(5, fibonacciFaster(5));
        assertEquals(8, fibonacciFaster(6));
        assertEquals(13, fibonacciFaster(7));
        assertEquals(21, fibonacciFaster(8));
    }
}