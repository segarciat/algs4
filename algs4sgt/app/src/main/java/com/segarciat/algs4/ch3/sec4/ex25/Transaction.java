package com.segarciat.algs4.ch3.sec4.ex25;

import edu.princeton.cs.algs4.Date;

/**
 * <strong>Exercise 3.4.25</strong>
 * Implements the {@link edu.princeton.cs.algs4.Transaction} but
 * computes the hash code only once, since the type is immutable.
 * @author Sergio E. Garcia Tapia
 */
public final class Transaction {
    private final String who;
    private final Date when;
    private final double amount;
    private final int hash;

    public Transaction(String who, Date when, double amount) {
        if (who == null || when == null)
            throw new NullPointerException("cannot have null fields");
        this.who= who;
        this.when = when;
        this.amount = amount;

        int h = 1;
        h = 31 * h + who.hashCode();
        h = 31 * h + when.hashCode();
        h = 31 * h + Double.hashCode(amount);
        this.hash = h;
    }

    public String who() {
        return who;
    }

    public Date when() {
        return when;
    }

    public double amount() {
        return amount;
    }

    public int hashCode() {
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || (o.getClass() != this.getClass()))
            return false;
        Transaction other = (Transaction) o;
        if (!other.who.equals(who))
            return false;
        if (!other.when.equals(when))
            return false;
        if (Double.compare(amount, other.amount) != 0)
            return false;
        return true;
    }
}
