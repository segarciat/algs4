package com.segarciat.algs4.ch3.sec2.ex14;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * <strong>3.2.14)</strong>
 * Implements non-recursive versions of methods in {@link edu.princeton.cs.algs4.BST},
 * continuing from {@link com.segarciat.algs4.ch3.sec2.ex13.BSTNonRec}.
 * @author Sergio E. Garcia Tapia
 */
public final class BSTNonRec<Key extends Comparable<Key>, Value>{
    private Node<Key, Value> root = null;

    private static class Node<Key, Value> {
        private final Key key;
        private Value val;
        private Node<Key, Value> left;
        private Node<Key, Value> right;
        private int n;

        Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Key, Value> node) {
        if (node == null)
            return 0;
        else
            return node.n;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("no null keys allowed");

        Node<Key, Value> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("cannot add a null key");

        Node<Key, Value> current = root;

        // update value for existing key, if any
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }

        // search for immediate parent for new node and update counts
        Node<Key, Value> parent = null;
        int cmp = 0;
        current = root;
        while (current != null) {
            current.n++;

            cmp = key.compareTo(current.key);
            parent = current;
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        current = new Node<>(key, val, 1);
        if (root == null) {
            root = current;
        } else if (cmp < 0) {
            parent.left = current;
        } else {
            parent.right = current;
        }
    }

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("no minimum in empty tree");

        Node<Key, Value> current = root;
        while (current.left != null)
            current = current.left;

        return current.key;
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("no minimum in empty tree");

        Node<Key, Value> current = root;
        while (current.right != null)
            current = current.right;

        return current.key;
    }

    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("no floor for null key");

        Node<Key, Value> floorNode = null;
        Node<Key, Value> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if  (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                floorNode = current;
                current = current.right;
            } else {
                return key;
            }
        }
        if (floorNode != null)
            return floorNode.key;
        throw new NoSuchElementException();
    }

    public Key ceiling(Key key) {
        if (key == null)
            throw new NullPointerException("no ceiling for null key");

        Node<Key, Value> ceilingNode = null;
        Node<Key, Value> current = root;

        while (current != null) {
            int cmp = key.compareTo(current.key);

            if (cmp < 0) {
                ceilingNode = current;
                current = current.left;
            } else if  (cmp > 0)
                current = current.right;
            else {
                return key;
            }
        }
        if (ceilingNode != null)
            return ceilingNode.key;
        throw new NoSuchElementException();
    }

    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("null key does not have a rank");

        Node<Key, Value> current = root;
        int k = 0;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                k += size(current.left) + 1;
                current = current.right;
            } else {
                k += size(current.left);
                break;
            }
        }
        return k;
    }

    public Key select(int k) {
        if (k < 0 || k >= size(root))
            throw new IllegalArgumentException("invalid rank");

        Node<Key, Value> current = root;
        int t;
        while (current != null) {
            t = size(current.left);
            if (t > k) {
                current = current.left;
            } else if (t < k) {
                k = k - t - 1;
                current = current.right;
            } else {
                return current.key;
            }
        }
        return null;
    }

    /**
     * Based on the no-argument implementation of <code>keys()</code>
     * in https://algs4.cs.princeton.edu/32bst/NonrecursiveBST.java.html
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null)
            throw new NullPointerException("lo and hi must not be null");

        Queue<Key> queue = new Queue<>();
        if (root == null || hi.compareTo(lo) < 0)
            return queue;

        Stack<Node<Key, Value>> stack = new Stack<>();
        Node<Key, Value> current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                // Always push without processing, but descend conditionally
                stack.push(current);
                if (lo.compareTo(current.key) < 0) {
                    current = current.left;
                } else {
                    current = null;
                }
            } else {
                // pop, process, and descend conditionally
                current = stack.pop();

                int cmpLo = lo.compareTo(current.key);
                int cmpHi = hi.compareTo(current.key);
                if (cmpLo <= 0 && cmpHi >= 0) {
                    queue.enqueue(current.key);
                }

                if (cmpHi > 0) {
                    current = current.right;
                } else {
                    current = null;
                }
            }
        }

        return queue;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        BSTNonRec<Integer, Integer> bst = new BSTNonRec<>();

        Integer[] vals = new Integer[10];
        for (int i = 0; i < 10; i++) {
            vals[i] = StdRandom.uniformInt(0, 1000);
            bst.put(vals[i], 0);
        }

        Quick.sort(vals);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.printf("Array values: %s%n", Arrays.toString(vals));
            System.out.println("Enter q to quit, or two numbers representing a range separated by a space: ");

            if (!scanner.hasNext()) {
                System.exit(0);
            }

            String input = scanner.nextLine();
            if ("q".equals(input)) {
                System.exit(0);
            }

            String[] tokens = input.split("\\s+");

            if (tokens.length != 2) {
                continue;
            }

            try {
                int lo = Integer.parseInt(tokens[0]);
                int hi = Integer.parseInt(tokens[1]);

                System.out.println();
                System.out.print("Keys in range: ");
                for (Integer val : bst.keys(lo, hi)) {
                    System.out.print(val + " ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide two numbers.");
            }
            System.out.println();
        }
    }
}
