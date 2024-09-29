package com.segarciat.algs4.ch1.sec3.ex38;

public class GeneralizedQueueClient {
    public static void testGeneralizedQueue(GeneralizedQueue<Integer> queue) {
        for (int i = 0; i < 10; i++)
            queue.insert(i);
        while (!queue.isEmpty())
            System.out.print(queue.delete(0) + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        testGeneralizedQueue(new ArrayGeneralizedQueue<>());
        testGeneralizedQueue(new ListGeneralizedQueue<>());
    }
}
