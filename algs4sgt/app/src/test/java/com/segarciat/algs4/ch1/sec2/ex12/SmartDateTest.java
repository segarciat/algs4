package com.segarciat.algs4.ch1.sec2.ex12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartDateTest {
    @Test
    void test_dayOfTheWeek_whenDateIsFirstDay21stCentury_returnsMonday() {
        assertEquals("Monday", new SmartDate(1, 1, 2001).dayOfTheWeek());
    }

    @Test
    void test_dayOfTheWeek_whenDateIsLastDayOf21stCentury_returnsFriday() {
        assertEquals("Friday", new SmartDate(12, 31, 2100).dayOfTheWeek());
    }

    @Test
    void test_dayOfTheWeek_whenDateIsNotIn21stCentury_returnsEmptyString() {
        assertEquals("", new SmartDate(12, 31, 2000).dayOfTheWeek());
        assertEquals("", new SmartDate(1, 1, 2101).dayOfTheWeek());
    }
}