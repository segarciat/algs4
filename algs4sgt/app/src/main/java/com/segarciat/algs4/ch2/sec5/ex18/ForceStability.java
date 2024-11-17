package com.segarciat.algs4.ch2.sec5.ex18;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * <strong>2.5.18)</strong>
 * @author Sergio E. Garcia Tapia
 */
public final class ForceStability {
    private ForceStability() {}

    /**
     * Sorts the given array in a stable manner.
     * @param a The array to be sorted.
     * @param sort The method to use for sorting the array, which should
     *             sort comparable objects.
     * @param <T> The type of elements in the array.
     */
    public static <T extends Comparable<T>> void stabilizedSort(T[] a, Consumer<Comparable[]> sort) {
        if (a == null)
            throw new NullPointerException("cannot sort null");
        if (a.length < 2)
            return;

        int n = a.length;

        // Wrap keys and sort wrapper array
        Key<T>[] wrapper = (Key<T>[]) new Key[n];
        for (int i = 0; i < n; i++)
            wrapper[i] = new Key<>(a[i], i);

        sort.accept(wrapper);

        // Copy-back
        for (int i= 0; i < n; i++)
            a[i] = wrapper[i].key;
    }

    /**
     * Wrapper to trick sorting algorithm into stable behavior.
     * @param key The original key, which is being wrapped.
     * @param index The location of the given key in the original array.
     * @param <T> The type of the key (the elements in the array being sorted).
     */
    private record Key<T extends Comparable<T>>(T key, int index) implements Comparable<Key<T>> {

        @Override
            public int compareTo(Key<T> that) {
                int cmp = this.key.compareTo(that.key);
                if (cmp != 0)
                    return cmp;
                return Integer.compare(this.index, that.index);
            }
    }

    public static void main(String[] args) {
        Integer[] a = { 4, 3, 5, 0, 1};
        stabilizedSort(a, Arrays::sort);
        System.out.println(Arrays.toString(a));
    }
}
