package com.segarciat.algs4.ch2.sec2.ex14;

import com.segarciat.algs4.ch2.SortUtil;
import edu.princeton.cs.algs4.Queue;

/**
 * @author Sergio E. Garcia Tapia
 */
public class MergeQueues {
    /**
     * Merges two sorted queues into a single merged queue. The given queues
     * remain unchanged.
     * @param q1 A non-null queue
     * @param q2 A non-null queue
     * @return A queue combining <code>q1</code> and <code>q2</code> in sorted order.
     * @param <T> The type for items in the queue.
     */
    public static <T extends Comparable<T>> Queue<T> merge(Queue<T> q1, Queue<T> q2) {
        if (q1 == null || q2 == null)
            throw new NullPointerException("Queues cannot be null");

        Queue<T> sortedResult = new Queue<>();
        int n1 = 0;
        int n2 = 0;
        while (n1 < q1.size() && n2 < q2.size()){
            T val;
            if (SortUtil.less(q1.peek(), q2.peek())) {
                val = q1.dequeue();
                q1.enqueue(val);
                n1++;
            } else {
                val = q2.dequeue();
                q2.enqueue(val);
                n2++;
            }
            sortedResult.enqueue(val);
        }

        while (n1 < q1.size()) {
            T val = q1.dequeue();
            sortedResult.enqueue(val);
            n1++;
        }

        while (n2 < q2.size()) {
            T val = q2.dequeue();
            sortedResult.enqueue(val);
            n2++;
        }
        return sortedResult;
    }

    public static void main(String[] args) {
        Queue<Integer> q1 = new Queue<>();
        for (int i = 0; i < 20; i += 2)
            q1.enqueue(i);

        Queue<Integer> q2 = new Queue<>();
        for (int i = 1; i < 20; i += 3)
            q2.enqueue(i);

        Queue<Integer> result = merge(q1, q2);
        for (Integer val: result)
            System.out.println(val);
    }
}
