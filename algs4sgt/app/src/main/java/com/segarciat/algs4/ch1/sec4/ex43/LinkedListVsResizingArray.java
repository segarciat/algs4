package com.segarciat.algs4.ch1.sec4.ex43;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class LinkedListVsResizingArray {
    public static double timeTrial(int n) {
        // Time pushing into LinkedList version of stack.
        Stack<Integer> stackList = new Stack<>();
        Stopwatch timer = new Stopwatch();
        for (int i = 0; i < n; i++)
            stackList.push(i);
        double linkedListElapsed = timer.elapsedTime();

        // Time pushing into ResizingArray version of Stack.
        ResizingArrayStack<Integer> stackArray = new ResizingArrayStack<>();
        timer = new Stopwatch();
        for (int i = 0; i < n; i++)
            stackArray.push(i);
        double resizingArrayElapsed = timer.elapsedTime();

        return linkedListElapsed / resizingArrayElapsed;
    }

    public static void main(String[] args) {
        for (int n = 250; true; n *= 2) {
            double ratio = timeTrial(n);
            StdOut.printf("%7d %7.1f\n", n, ratio);
        }
    }
}
