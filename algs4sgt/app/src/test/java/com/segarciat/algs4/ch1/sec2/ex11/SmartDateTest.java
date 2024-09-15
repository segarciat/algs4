package com.segarciat.algs4.ch1.sec2.ex11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDateTest {
    @Test
    void test_SmartDate_constructor_whenLeapYear_andFebruary_andDayExceeds29_shouldThrow() {
        int leapYear = 400;
        int feb = 2;

        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 32, leapYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 31, leapYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 30, leapYear));
    }

    @Test
    void test_SmartDate_constructor_whenLeapYear_andFebruary_andDayIs29_shouldSucceed() {
        int leapYear = 400;
        int feb = 2;

        assertDoesNotThrow(() -> new SmartDate(feb, 29, leapYear));
    }

    @Test
    void test_SmartDate_constructor_whenNotLeapYear_andFebruary_andDayExceeds28_shouldThrow() {
        int notLeapYear = 600;
        int feb = 2;

        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 32, notLeapYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 31, notLeapYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 30, notLeapYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(feb, 29, notLeapYear));
    }

    @Test
    void test_SmartDate_constructor_whenNotLeapYear_andFebruary_andDayIs28_shouldSucceed() {
        int notLeapYear = 600;
        int feb = 2;

        assertDoesNotThrow(() -> new SmartDate(feb, 28, notLeapYear));
    }

    @Test
    void test_SmartDate_constructor_whenMonthHas30days_andDayExceeds30_shouldThrow() {
        int anyYear = 2345;
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(4, 31, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(6, 31, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(9, 31, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(11, 31, anyYear));
    }

    @Test
    void test_SmartDate_constructor_whenMonthHas31days_andDayExceeds31_shouldThrow() {
        int anyYear = 2345;
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(1, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(3, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(5, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(7, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(8, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(10, 32, anyYear));
        assertThrows(IllegalArgumentException.class, () -> new SmartDate(12, 32, anyYear));
    }

    @Test
    void test_SmartDate_constructor_whenMonthHas31days_andDayIs31_shouldSucceed() {
        int anyYear = 2345;
        assertDoesNotThrow(() -> new SmartDate(1, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(3, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(5, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(7, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(8, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(10, 31, anyYear));
        assertDoesNotThrow(() -> new SmartDate(12, 31, anyYear));
    }
}