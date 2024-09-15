package com.segarciat.algs4.ch1.sec2.ex16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RationalTest {
    @Test
    void test_Rational_constructor_whenGivenZeroDenominator_shouldThrow() {
        long anyNumerator = 1;
        assertThrows(IllegalArgumentException.class, () -> new Rational(anyNumerator, 0));
    }

    @Test
    void test_toString_whenPositiveFractionInLowestTerms_shouldRetainNumeratorAndDenominator() {
        Rational r = new Rational(1, 2);
        assertEquals("1/2", r.toString());
    }

    @Test
    void test_toString_whenPositiveFractionNotInLowestTerms_shouldReduceToLowerTerms() {
        Rational r = new Rational(4, 18);
        assertEquals("2/9", r.toString());
    }

    @Test
    void test_toString_whenFractionIsNegative_shouldHaveNegativeNumerator_andShouldHavePositiveDenominator() {
        assertEquals("-2/9", new Rational(4, -18).toString());
        assertEquals("-2/9", new Rational(-4, 18).toString());
    }

    @Test
    void test_toString_whenNumAndDenAreNegative_shouldHavePositiveNumAndDen() {
        assertEquals("2/9", new Rational(-4, -18).toString());
    }

    @Test
    void test_plus_whenAddFractions_shouldResultInLowestTerms() {
        Rational x = new Rational(2, 5);
        Rational y = new Rational(1, 10);

        Rational xPlusY = x.plus(y);
        Rational yPlusX = y.plus(x);

        assertEquals("1/2", xPlusY.toString());
        assertEquals("1/2", yPlusX.toString());
        assertEquals("2/5", x.toString());
        assertEquals("1/10", y.toString());
    }

    @Test
    void test_minus_whenSubtractFraction_shouldResultInLowestTerms() {
        Rational x = new Rational(3, 5);
        Rational y = new Rational(1, 10);

        Rational xMinusY = x.minus(y);
        Rational yMinusX = y.minus(x);

        assertEquals("1/2", xMinusY.toString());
        assertEquals("-1/2", yMinusX.toString());
        assertEquals("3/5", x.toString());
        assertEquals("1/10", y.toString());
    }

    @Test
    void test_times_whenMultiplyByZero_shouldBeZero() {
        Rational zero = new Rational(0, 1);
        Rational x = new Rational(23, 97);

        assertEquals(zero, x.times(zero));
        assertEquals("23/97",  x.toString());
    }

    @Test
    void test_times_whenMultiplyByOne_shouldRemainUnchanged() {
        Rational one = new Rational(1, 1);
        Rational x = new Rational(23, 97);

        assertEquals(x, x.times(one));
        assertEquals("23/97", x.toString());
    }

    @Test
    void test_dividedBy_whenDivideByZero_shouldThrow() {
        Rational zero = new Rational(0, 1);
        Rational x = new Rational(23, 97);

        assertThrows(IllegalArgumentException.class, () -> x.dividedBy(zero));
    }

    @Test
    void test_dividedBy_whenDivideByOne_shouldRemainUnchanged() {
        Rational one = new Rational(1, 1);
        Rational x = new Rational(23, 97);

        assertEquals("23/97", x.dividedBy(one).toString());
    }

    @Test
    void test_divideBy_whenNonZeroIsDividedByItself_shouldBeOne() {
        Rational x = new Rational(23, 97);

        assertEquals("1/1", x.dividedBy(x).toString());
    }
}