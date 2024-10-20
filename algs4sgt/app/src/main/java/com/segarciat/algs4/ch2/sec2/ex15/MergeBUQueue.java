package com.segarciat.algs4.ch2.sec2.ex15;

import com.segarciat.algs4.ch2.sec2.ex14.MergeQueues;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Sergio E. Garcia Tapia
 */
public class MergeBUQueue {
    private MergeBUQueue() {}

    /**
     * Sorts the item in the given queue in-place.
     * @param queue A non-null queue of at least 1 item.
     * @param <T> The type of items in the queue.
     */
    public static <T extends Comparable<T>> void sort(Queue<T> queue) {
        if (queue == null)
            throw new NullPointerException("queue cannot be null");

        int n = queue.size();
        if (n < 2)
            return;

        // Create queue of n 1-item queues
        Queue<Queue<T>> queueOfQueues = new Queue<>();
        while (!queue.isEmpty()) {
            Queue<T> temp = new Queue<>();
            temp.enqueue(queue.dequeue());
            queueOfQueues.enqueue(temp);
        }

        // Repeatedly merge front queues
        while (queueOfQueues.size() != 1) {
            Queue<T> q1 = queueOfQueues.dequeue();
            Queue<T> q2 = queueOfQueues.dequeue();
            queueOfQueues.enqueue(MergeQueues.merge(q1, q2));
        }

        // Copy back to caller's queue
        for (T item: queueOfQueues.dequeue())
            queue.enqueue(item);
    }

    public static void main(String[] args) {
        int n = 10;
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(StdRandom.uniformInt(1000));
        sort(queue);
        for (int i: queue)
            System.out.print(i + " ");
    }
}
