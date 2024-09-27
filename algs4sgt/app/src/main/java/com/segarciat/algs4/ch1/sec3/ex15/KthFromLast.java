package com.segarciat.algs4.ch1.sec3.ex15;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

/**
 * Write a <code>Stack</code> or <code>Queue</code> client that takes a command-line argument
 * <code>k</code> and prints the <code>k</code>th from the last string found on standard input
 * (assuming that standard input has <code>k</code> or more strings). Use memory proportional
 * to <code>k</code>.
 */
public class KthFromLast {
    private static void usingQueue(int k) {
        if (k <= 0)
            throw new IllegalArgumentException("Expected a positive integer, but got %d".formatted(k));
        Queue<String> queue = new Queue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (queue.size() == k)
                queue.dequeue();
            queue.enqueue(s);
        }
        System.out.println(queue.dequeue());
    }

    private static void usingStack(int k) {
        if (k <= 0)
            throw new IllegalArgumentException("Expected a positive integer, but got %d".formatted(k));

        Stack<String> olderElemStack = new Stack<>();
        Stack<String> newerElemStack = new Stack<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();

            if (olderElemStack.size() != k)
                olderElemStack.push(s);
            else if (newerElemStack.size() != k)
                newerElemStack.push(s);
            if (newerElemStack.size() == k) {
                olderElemStack = newerElemStack;
                newerElemStack = new Stack<>();
            }
        }

        int i = 0;
        while (!newerElemStack.isEmpty()) {
            newerElemStack.pop();
            i++;
        }

        while (i < k - 1 && olderElemStack.size() != 1) {
            olderElemStack.pop();
            i++;
        }
        System.out.println(olderElemStack.pop());
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Provide a single argument: a non-negative integer");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        usingQueue(k);
    }
}
