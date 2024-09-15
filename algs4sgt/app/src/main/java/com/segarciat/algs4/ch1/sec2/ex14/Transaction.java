package com.segarciat.algs4.ch1.sec2.ex14;

import edu.princeton.cs.algs4.Date;

/**
 * <strong>1.2.14)</strong>
 * Using our implementation of <code>equals()</code> in <code>Date</code> as a model
 * (page 103), develop an implementation of <code>equals()</code> for <code>Transaction</code>.
 *
 * @author Sergio E. Garcia Tapia
 */
public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if (who == null || when == null)
            throw new NullPointerException("who or when argument is null");
        this.who = who;
        this.when = when;
        this.amount = amount;
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

    public String toString() {
        return String.format("%s %s %s", who, when, amount);
    }

    public boolean equals(Object x) {
        if (this == x)
            return true;
        if (x == null)
            return false;
        if (this.getClass() != x.getClass())
            return false;
        Transaction that = (Transaction) x;
        if (!this.who.equals(that.who))
            return false;
        if (!this.when.equals(that.when))
            return false;
        if (this.amount != that.amount)
            return false;
        return true;
    }
}
