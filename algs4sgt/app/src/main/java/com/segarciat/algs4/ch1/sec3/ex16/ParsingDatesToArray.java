package com.segarciat.algs4.ch1.sec3.ex16;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

/**
 * <code>1.3.16)</code>
 * Using <code>readAllInts()</code> on page 126 as a model, write a static
 * method <code>readAllDates()</code> that reads dates fro standard input
 * in the format specified in the table on page 119 and returns an array
 * containing them.
 */
public class ParsingDatesToArray {
    public static Date[] readAllDates() {
        Queue<Date> queue = new Queue<>();
        while (!StdIn.isEmpty()) {
            String[] dateFields = StdIn.readString().split("/");
            if (dateFields.length != 3)
                throw new RuntimeException("Date must be in mm/dd/yyyy format");

            // Rely on validation from Date() object.
            int month = Integer.parseInt(dateFields[0]);
            int day   = Integer.parseInt(dateFields[1]);
            int year  = Integer.parseInt(dateFields[2]);
            queue.enqueue(new Date(month, day, year));
        }

        int n = queue.size();
        Date[] dates = new Date[n];
        for (int i = 0; i < n; i++)
            dates[i] = queue.dequeue();
        return dates;
    }

    public static void main(String[] args) {
        Date[] dates = readAllDates();
        for (Date date: dates)
            System.out.println(date);
    }
}
