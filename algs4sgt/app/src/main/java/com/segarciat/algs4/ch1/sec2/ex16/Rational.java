    package com.segarciat.algs4.ch1.sec2.ex16;

import com.segarciat.algs4.ch1.sec1.ex24.Euclid;

/**
 * <strong>1.2.16)</strong>
 * <em>Rational Numbers</em>. Implement an immutable data type <em>Rational</em>
 * for rational numbers that supports addition, subtraction, multiplication, and division.
 *
 * <pre>
 * {@code
 *  public class Rational
 *               Rational(int numerator, int denominator)
 *      Rational plus(Rational that)         // sum of this number and that
 *      Rational minus(Rational that)        // difference of this number and that
 *      Rational times(Rational that)        // product of this number and that
 *      Rational dividedBy(Rational that)    // quotient of this number and that
 *      boolean  equals(Object that)         // is this number equal to that?
 *      String   toString()                  // string representation
 * }
 * </pre>
 *
 * You do not have to worry about testing for overflow (see Exercise 1.2.17), but use
 * as instance variables two <code>long</code> values that represent the numerator and
 * denominator to limit the possibility of overflow. Use Euclid's algorithm (see page 4)
 * to ensure that the numerator and denominator never have any common factors. Include
 * a test client that exercises all of your methods.
 *
 * @author Sergio E. Garcia Tapia
 */
public class Rational {
    private final long num;
    private final long den;

    public Rational(long p, long q) {
        if (q == 0)
            throw new IllegalArgumentException("denominator cannot be 0");

        long absP = Math.abs(p);
        long absQ = Math.abs(q);

        long gcd = Euclid.gcd(absP, absQ);

        // Denominator is always positive, and numerator is non-negative.
        den = absQ / gcd;
        if ((p >= 0) == (q >= 0))
            num =  absP / gcd;
        else
            num = -absP / gcd;
    }

    public Rational plus(Rational that) {
        return new Rational(this.num * that.den + that.num * this.den, this.den * that.den);
    }

    public Rational minus(Rational that) {
        return new Rational(this.num * that.den - that.num * this.den, this.den * that.den);
    }

    public Rational times(Rational that) {
        return new Rational(this.num * that.num, this.den * that.den);
    }

    public Rational dividedBy(Rational that) {
        if (that.num == 0)
            throw new IllegalArgumentException("cannot divided by 0");
        return new Rational(this.num * that.den, this.den * that.num);
    }

    public boolean equals(Object x) {
        if (x == this)
            return true;
        if (x == null)
            return false;
        if (this.getClass() != x.getClass())
            return false;
        Rational that = (Rational) x;
        if (this.num != that.num)
            return false;
        if (this.den != that.den)
            return false;
        return true;
    }

    public String toString() {
        return String.format("%d/%d", num, den);
    }
}
