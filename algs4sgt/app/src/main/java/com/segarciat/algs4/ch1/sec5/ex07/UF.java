package com.segarciat.algs4.ch1.sec5.ex07;

/**
 * API whose implementation leads to a solution of the dynamic connectivity
 * problem. Specified in Section 1.5, page 219 of Algorithms 4th edition,
 * by Sedgewick and Wayne.
 */
public interface UF {
    /**
     * Determines whether p and q belong to the same component.
     * @param p Site identifier.
     * @param q Site identifier.
     * @return <code>true</code> if sites <code>p</code> and <code>q</code>
     * belong to the same component, false otherwise.
     */
    boolean connected(int p, int q);
    /**
     * Determines the component identifier for site <code>p</code>.
     * @param p Site identifier.
     * @return An integer representing the component that site <code>p</code>
     * belongs to.
     */
    int find(int p);

    /**
     * Updates connections to ensure <code>p</code> and <code>q</code>
     * belong to the same component.
     * @param p Site identifier.
     * @param q Site identifier.
     */
    void union(int p, int q);
    /**
     * @return The number of components.
     */
    int count();
}
