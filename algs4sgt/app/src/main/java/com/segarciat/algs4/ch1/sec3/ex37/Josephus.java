package com.segarciat.algs4.ch1.sec3.ex37;

import edu.princeton.cs.algs4.Queue;

/**
 * 1.3.37
 */
public class Josephus {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Provide two arguments: m and n");
            System.err.println("m    How often a person gets eliminated (positive integer)");
            System.err.println("n    The number of people in the group (positive integer)");
            System.exit(1);
        }
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);

        if (m <= 0 || n <= 0)
            throw new RuntimeException("Both arguments must be positive integers");

        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        int j = 0;
        while (!queue.isEmpty()) {
            j = (j + 1) % m;
            if (j == 0)
                System.out.print(queue.dequeue() + " ");
            else
                queue.enqueue(queue.dequeue());
        }

    }
}
