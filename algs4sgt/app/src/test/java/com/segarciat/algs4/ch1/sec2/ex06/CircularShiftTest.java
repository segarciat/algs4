package com.segarciat.algs4.ch1.sec2.ex06;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularShiftTest {
    @Test
    @DisplayName("null strings are invalid")
    void test_isCircularShift_whenGivenNull_shouldThrow() {
        String anyString =  "";
        assertThrows(NullPointerException.class, () -> CircularShift.isCircularShift(null, null));
        assertThrows(NullPointerException.class, () -> CircularShift.isCircularShift(anyString, null));
        assertThrows(NullPointerException.class, () -> CircularShift.isCircularShift(null, anyString));
    }

    @Test
    @DisplayName("Strings of different lengths are not circular rotations")
    void test_isCircularShift_whenStringLengthsDiffer_shouldReturnFalse() {
        String s = "a";
        String t = "ab";
        assertFalse(CircularShift.isCircularShift(s, t));
    }

    @Test
    void test_isCircularShift_whenStringsAreCircularRotations_shouldReturnTrue() {
        String s = "ACTGACG";
        String t = "TGACGAC";
        assertTrue(CircularShift.isCircularShift(s, t));
        assertTrue(CircularShift.isCircularShift(t, s));
    }
}