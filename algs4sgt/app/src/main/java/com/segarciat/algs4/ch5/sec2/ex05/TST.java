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
        while (current != null) {
            char c = key.charAt(d);
            if (c < current.c) {
                current = current.left;
            } else if (c > current.c) {
                current = current.right;
            } else {
                d++;
                if (d == key.length())
                    break;
                current = current.mid;
            }
        }
        if (current == null)
            return null;
        return current.val;
    }

    public void put(String key, Value val) {
        if (key == null || val == null) {
            throw new NullPointerException("cannot use null for key or value");
        }
        if (key.isEmpty()) {
            throw new IllegalArgumentException("cannot use an empty string as a key");
        }

        if (root == null) {
            root = new Node<>(key.charAt(0));
        }

        // check if key exists
        Node<Value> current = root;
        int d = 0;
        while (d < key.length()) {
            char c = key.charAt(d);
            if (c < current.c) {
                if (current.left == null)
                    break;
                current = current.left;
            } else if (c > current.c) {
                if (current.right == null)
                    break;
                current = current.right;
            } else {
                if (current.mid == null)
                    break;
                current = current.mid;
                d++;
            }
        }

        if (d == key.length() && current.val != null) {
            // key exists, so replace value
            current.val = val;
            return;
        } else if (d < key.length()) {
            // key missing, and node for next character would be null
            char c = key.charAt(d++);
            if (c < current.c) {
                current.left = new Node<>(c);
                current = current.left;
            } else if (c > current.c) {
                current.right = new Node<>(c);
                current = current.right;
            }
        }

        // create all middle nodes on the way down to leaf of key
        while (d < key.length()) {
            current.mid = new Node<>(key.charAt(d++));
            current = current.mid;
        }
        current.val = val;

        // finally, update sizes
        d = 0;
        current = root;
        while (d < key.length() && current != null) {
            current.n++;
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
    }

    private static class Node<Value> {
        private Node<Value> left;
        private Node<Value> mid;
        private Node<Value> right;
        private final char c;
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
        StdOut.printf("Adding %d keys: %s%n", keys.size(), keys);
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
