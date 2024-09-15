    package com.segarciat.algs4.ch1.sec2.ex17;

import com.segarciat.algs4.ch1.sec1.ex24.Euclid;

    /**
     * <strong>1.2.17)</strong>
     * <em>Robust implementation of rational numbers</em>. Use assertions to develop
     * an implementation of <code>Rational</code> (see Exercise 1.2.16) that is immune
     * to overflow.
     *
     * @author Sergio E. Garcia Tapia
     */
    public class Rational {
        private final long num;
        private final long den;

        public Rational(long p, long q) {
            if (q == 0)
                throw new IllegalArgumentException("denominator cannot be 0");
            // Math.abs(Long.MIN_VALUE) == Long.MIN_VALUE, violating the invariant that
            // the denominator should be positive, and precluding usage of Euclid's algorithm.
            if (p == Long.MIN_VALUE  || q == Long.MIN_VALUE)
                throw new IllegalArgumentException(String.format("The value %d is not allowed", Long.MIN_VALUE));
            long absP = Math.abs(p);
            long absQ = Math.abs(q);

            long gcd = Euclid.gcd(absP, absQ);

            // Denominator is always positive, and numerator is non-negative.
            den = absQ / gcd;
            if ((p >= 0) == (q >= 0))
                num =  absP / gcd;
            else
                num = -(absP / gcd);
        }

        /**
         * Computes the product of a and b and detects overflow.
         * Detection based on Exercise 2.35 on CS:APP (3rd edition) by Bryant and O'Hallaron
         *
         * @param a First operand
         * @param b Second operand
         *
         * @return The product of a and b
         * @throws AssertionError if the product overflows
         */
        private static long prod(long a, long b) {
            long p = a * b;
            assert a == 0 || (p/a == b) : "product overflow";
            return p;
        }

        /**
         * Computes the sum of a and b and detects overflow.
         * Detection based on page 92 of CS:APP (3rd Edition) by Bryant and O'Hallaron
         * @param a First operand
         * @param b Second operand
         * @return The sum of a and b
         * @throws AssertionError if the sum overflows
         */
        private static long sum(long a, long b) {
            long s  = a + b;
            // Signs are distinct or the result's sign is the same sign as the operands.
            assert (a >= 0 != b >= 0) || (a >= 0 == s >= 0);
            return s;
        }

        public Rational plus(Rational that) {
            // Reduce the likelihood of overflow when getting common denominator
            long denGcd = Euclid.gcd(this.den, that.den);

            long missingFactorFromFirst = this.den / denGcd;
            long missingFactorFromSecond = that.den / denGcd;

            long commonDenominator = this.den * missingFactorFromSecond;

            long resultNum = Rational.sum(
                    Rational.prod(this.num, missingFactorFromSecond),
                    Rational.prod(that.num, missingFactorFromFirst)
            );

            return new Rational(resultNum, commonDenominator);
        }

        public Rational minus(Rational that) {
            return plus(new Rational(-that.num, that.den));
        }

        public Rational times(Rational that) {
            // Cross-reduce to minimize the likelihood of overflow
            long gcdA = Euclid.gcd(this.num, that.den);
            long gcdB = Euclid.gcd(that.num, this.den);

            long resultNum = Rational.prod(this.num / gcdA, that.num / gcdB);

            long resultDen = Rational.prod(this.den / gcdB, that.den / gcdA);

            return new Rational(resultNum, resultDen);
        }

        public Rational dividedBy(Rational that) {
            if (that.num == 0)
                throw new IllegalArgumentException("cannot divided by 0");
            // Multiply by the reciprocal
            return times(new Rational(that.den, that.num));
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
