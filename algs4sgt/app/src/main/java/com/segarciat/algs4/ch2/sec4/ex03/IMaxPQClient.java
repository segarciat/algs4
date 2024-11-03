package com.segarciat.algs4.ch2.sec4.ex03;

import com.segarciat.algs4.ch2.SortUtil;
import com.segarciat.algs4.ch2.sec4.IMaxPQ;
import edu.princeton.cs.algs4.StdRandom;

/**
 * <strong>2.4.3)</strong>
 * Verifies the max priority queue implementations developed in
 * this exercise work as intended by using assertions.
 *
 * @author Sergio E. Garcia Tapia
 */
public class IMaxPQClient {
    /**
     * Asserts that the maximum item is removed from the priority queue each time.
     * @param pq A priority queue implementation.
     */
    public static void assertIMaxPQIsCorrect(IMaxPQ<Integer> pq) {
        final int size = 100;
        for (int i = 0; i < size; i++)
            pq.insert(StdRandom.uniformInt(0, 1_000_000));

        var prev = pq.delMax();
        while (!pq.isEmpty()) {
            var removed = pq.delMax();
            assert !SortUtil.less(prev, removed);
            prev = removed;
        }
    }

    public static void main(String[] args) {
        assertIMaxPQIsCorrect(new UnorderedArrayMaxPQ<>());
        assertIMaxPQIsCorrect(new OrderedArrayMaxPQ<>());
        assertIMaxPQIsCorrect(new UnorderedListMaxPQ<>());
        assertIMaxPQIsCorrect(new OrderedListMaxPQ<>());
    }
}
