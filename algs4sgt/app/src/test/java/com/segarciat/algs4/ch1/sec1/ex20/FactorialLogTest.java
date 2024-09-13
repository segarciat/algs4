package com.segarciat.algs4.ch1.sec1.ex20;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.segarciat.algs4.ch1.sec1.ex20.FactorialLog.factorialLog;
import static org.junit.jupiter.api.Assertions.*;

class FactorialLogTest {

    @Test
    @DisplayName("Invalid inputs: non-positive values")
    void test_factorialLog_whenGivenZeroOrNegative_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> factorialLog(0));
        assertThrows(IllegalArgumentException.class, () -> factorialLog(-1));
    }

    @Test
    @DisplayName("Base case of 1 results in 0")
    void test_factorialLog_whenGivenBaseCase_shouldReturn0() {
        assertEquals(0, factorialLog(1));
    }

    @Test
    @DisplayName("Positive input greater than 1")
    void test_factorialLog_whenGivenValueGreaterThan1_shouldReturnValidResult() {
        double tolerance = 0.0000001;
        long twoFactorial = 2;
        long threeFactorial = 3 * twoFactorial;
        long fourFactorial = 4 * threeFactorial;
        long fiveFactorial  = 5 * fourFactorial;

        assertEquals(Math.log(twoFactorial), factorialLog(2), tolerance);
        assertEquals(Math.log(threeFactorial), factorialLog(3), tolerance);
        assertEquals(Math.log(fourFactorial), factorialLog(4), tolerance);
        assertEquals(Math.log(fiveFactorial), factorialLog(5), tolerance);
    }
}