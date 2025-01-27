package com.segarciat.algs4.ch5.sec2.ex05;

import edu.princeton.cs.algs4.StdOut;

import java.util.List;

public class TST <Value> {
    private Node<Value> root;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<Value> node) {
        if (node == null)
            return 0;
        return node.n;
    }

    public Value get(String key) {
        if (key == null)
            return null;

        Node<Value> current = root;
        int d = 0;
        while (d < key.length() && current != null) {
            char c = key.charAt(d);
            if (c < current.c) {
                current = current.left;
            } else if (c > current.c) {
                current = current.right;
            } else {
                current = current.mid;
            }
        }

        if (current == null || current.val == null) {
            return null;
        }
        return current.val;
    }

    public void put(String key, Value val) {
        if (key == null || val == null) {
            throw new NullPointerException("cannot use null for key or value");
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("cannot use an empty string as a key");
        }

        // first search for node
        Node<Value> parent = null;
        Node<Value> current = root;
        int d = 0;
        while (d < key.length() && current != null) {
            parent = current;
            char c = key.charAt(d);
            if (c < current.c) {
                current = current.left;
            } else if (c > current.c) {
                current = current.right;
            } else {
                d++;
                current = current.mid;
            }
        }

        // found
        if (current != null) {
            current.val = val;
            return;
        }

        // did not find
        d = 0;
        if (root == null) {
            root = new Node<>(key.charAt(d));
        }

        current = root;
        while (d < key.length() && current != null) {
            current.n++;
            char c = key.charAt(d);
            parent = current;
            if (c < current.c) {
                current = current.left;
            } else if (c > current.c) {
                current = current.right;
            } else {
                d++;
                current = current.mid;
            }
        }

    }

    private Node<Value> getOrCreate(Node<Value> node, char c) {
        if (node != null)
            return node;
        return new Node<>(c);
    }



    private static class Node<Value> {
        private Node<Value> left;
        private Node<Value> mid;
        private Node<Value> right;
        private char c;
        private Value val;
        private int n;

        Node(char c) {
            this.c = c;
        }
    }

    public static void main(String[] args) {
        TST<Integer> st = new TST<>();
        // expect 0
        StdOut.printf("Trie size: %d%n", st.size());
        List<String> keys = List.of("hello", "goodbye", "cake", "onomatopoeia", "cat", "garden", "lovely", "halo!",
                "shell", "shells");

        StdOut.println();
        StdOut.printf("Adding keys: %s%n", keys);
        for (String key: keys) {
            st.put(key, key.length());
        }

        // expect list.size
        StdOut.println();
        StdOut.printf("Trie size: %d%n", st.size());
        for (String key: keys) {
            StdOut.printf("%s: %s%n", key, st.get(key));
        }
    }
}
