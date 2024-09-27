package com.segarciat.algs4.ch1.sec3.ex17;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Transaction;

/**
 * <strong>1.3.17)</strong>
 * Do Exercise 1.3.16 for <code>Transaction</code>
 */
public class ParsingTransactionsToArray {
    public static Transaction[] readAllTransactions() {
        Queue<Transaction> queue = new Queue<>();
        while (!StdIn.isEmpty()) {
            String[] transactionFields = StdIn.readString().split("\\s+");
            if (transactionFields.length != 3)
                throw new RuntimeException("Customer, date, and amount must be separated by whitespace");
            String customer = transactionFields[0];
            double amount = Double.parseDouble(transactionFields[2]);

            String[] dateFields = transactionFields[1].split("/");
            if (dateFields.length != 3)
                throw new RuntimeException("Date must be in the format mm/dd/yyyy");
            // Rely on validation from Date() object.
            int month = Integer.parseInt(dateFields[0]);
            int day   = Integer.parseInt(dateFields[1]);
            int year  = Integer.parseInt(dateFields[2]);
            Date date = new Date(month, day, year);

            queue.enqueue(new Transaction(customer, date, amount));
        }

        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++)
            transactions[i] = queue.dequeue();
        return transactions;
    }

    public static void main(String[] args) {
        Transaction[] dates = readAllTransactions();
        for (Transaction transaction: dates)
            System.out.println(transaction);
    }
}
