package com.segarciat.algs4.ch5.sec2.ex05;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.List;

/**
 * <strong>5.2.5)</strong>
 * Implements an <code>R</code>-way trie. It implements the
 * same API as {@link edu.princeton.cs.algs4.TrieST}, but
 * in a non-recursive manner.
 * @author Sergio E. Garcia Tapia
 */
public final class TrieST <Value> {
    public final static int R = 256;
    private final Node<Value> root = new Node<>();

    public int size() {
        return root.n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(String key) {
        if (key == null)
            return null;

        Node<Value> node = get(root, key);
        if (node == null)
            return null;
        return node.val;
    }

    private Node<Value> get(Node<Value> current, String key) {
        int d = 0;
        while (current != null && d != key.length()) {
            char c = key.charAt(d++);
            current = current.next[c];
        }
        return current;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void put(String key, Value val) {
        if (key == null || val == null)
            throw new NullPointerException("cannot use null for key or value");

        // first see if key exists
        Node<Value> current = root;
        int d = 0;
        while (current != null && d != key.length()) {
            char c = key.charAt(d++);
            current = current.next[c];
        }

        // found
        if (current != null) {
            current.val = val;
            return;
        }

        // not found, so insert nodes for the key, the value, and eagerly update sub-trie sizes
        d = 0;
        current = root;
        while (d != key.length()) {
            current.n++;

            char c = key.charAt(d++);
            if (current.next[c] == null) {
                current.next[c] = new Node<>();
            }
            current = current.next[c];
        }
        // last node contains the value
        current.val = val;
        current.n = 1;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        if (prefix == null)
            throw new NullPointerException("prefix cannot be null");

        Node<Value> prefixTrie = get(root, prefix);
        Queue<String> matched = new Queue<>();
        if (prefixTrie == null)
            return matched;

        Queue<Node<Value>> nodes = new Queue<>();
        nodes.enqueue(prefixTrie);
        Queue<String> partialKeys = new Queue<>();
        partialKeys.enqueue(prefix);

        while (!nodes.isEmpty()) {
            Node<Value> current = nodes.dequeue();
            String partialKey = partialKeys.dequeue();
            if (current.val != null) {
                matched.enqueue(partialKey);
            }
            for (char c = 0; c < R; c++) {
                if (current.next[c] != null) {
                    nodes.enqueue(current.next[c]);
                    partialKeys.enqueue(partialKey + c);
                }
            }
        }

        return matched;
    }

    public Iterable<String> keysThatMatch(String pattern) {
        if (pattern == null)
            throw new NullPointerException("cannot use a null pattern");

        Queue<Node<Value>> nodes = new Queue<>();
        Queue<String> partialKeys = new Queue<>();
        Queue<String> matched = new Queue<>();

        nodes.enqueue(root);
        partialKeys.enqueue("");
        while (!nodes.isEmpty()) {
            Node<Value> current = nodes.dequeue();
            String partialKey = partialKeys.dequeue();

            int d = partialKey.length();
            if (d == pattern.length()) {
                if (current.val != null) {
                    matched.enqueue(partialKey);
                }
                // do queue node children since pattern length was reached
                continue;
            }

            // queue children matching pattern
            char next = pattern.charAt(d);
            for (char c = 0; c < R; c++) {
                if ((next == '.' || next == c) && current.next[c] != null) {
                    nodes.enqueue(current.next[c]);
                    partialKeys.enqueue(partialKey + c);
                }
            }
        }

        return matched;
    }

    public String longestPrefixOf(String s) {
        if (s == null)
            throw new NullPointerException("text cannot be null");

        int length = 0;
        int d = 0;
        Node<Value> current = root;
        while (current != null && d < s.length()) {
            if (current.val != null) {
                length = d;
            }
            char c = s.charAt(d++);
            current = current.next[c];
        }

        return s.substring(0, length);
    }

    public void delete(String key) {
        if (key == null)
            throw new NullPointerException("cannot delete a null key");

        Stack<Node<Value>> parents = new Stack<>();
        Stack<Character> characters = new Stack<>();
        Node<Value> current = root;
        int d = 0;

        // find the node to delete, if present, and keep track of parents
        while (d < key.length() && current != null) {
            char c = key.charAt(d++);
            if (current.next[c] != null) {
                parents.push(current);
                characters.push(c);
            }
            current = current.next[c];
        }

        // key is not in trie
        if (current == null || current.val == null) {
            return;
        }

        // found key; remove it
        current.val = null;
        current.n--;

        while (!parents.isEmpty()) {
            Node<Value> parent = parents.pop();
            char c = characters.pop();
            parent.n--;

            boolean hasValue = current.val != null;
            if (!hasValue) {
                for (char ch = 0; ch < R; ch++) {
                    if (current.next[ch] != null) {
                        hasValue = true;
                        break;
                    }
                }
            }

            if (!hasValue) {
                parent.next[c] = null;
            }
            current = parent;
        }
    }

    private static class Node<Value> {
        private Value val;
        private int n;
        private final Node<Value>[] next = new Node[R];

        Node(Value val) {
            this.val = val;
        }

        Node() {
            this(null);
        }
    }

    public static void main(String[] args) {
        TrieST<Integer> st = new TrieST<>();
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
        for (String key: st.keys()) {
            StdOut.printf("%s: %s%n", key, st.get(key));
        }

        // keys with prefix of cat
        StdOut.println();
        String prefix = "ca";
        StdOut.printf("Keys with prefix of %s%n", prefix);
        for (String key: st.keysWithPrefix(prefix)) {
            StdOut.println(key);
        }

        StdOut.println();
        prefix = "go";
        StdOut.printf("Keys with prefix of %s%n", prefix);
        for (String key: st.keysWithPrefix(prefix)) {
            StdOut.println(key);
        }

        StdOut.println();
        String pattern = "......";
        StdOut.printf("Keys that match pattern: %s%n", pattern);
        for (String key: st.keysThatMatch(pattern)) {
            StdOut.println(key);
        }

        StdOut.println();
        pattern = "ca.";
        StdOut.printf("Keys that match pattern: %s%n", pattern);
        for (String key: st.keysThatMatch(pattern)) {
            StdOut.println(key);
        }

        StdOut.println();
        pattern = "h.l..";
        StdOut.printf("Keys that match pattern: %s%n", pattern);
        for (String key: st.keysThatMatch(pattern)) {
            StdOut.println(key);
        }

        StdOut.println();
        String s = "caterpie";
        StdOut.printf("Longest prefix of %s: %s%n", s, st.longestPrefixOf(s));

        StdOut.println();
        s = "lovely day";
        StdOut.printf("Longest prefix of %s: %s%n", s, st.longestPrefixOf(s));

        StdOut.println();
        s = "ca";
        StdOut.printf("Longest prefix of %s: %s%n", s, st.longestPrefixOf(s));

        StdOut.println();
        s = "shellsort";
        StdOut.printf("Longest prefix of %s: %s%n", s, st.longestPrefixOf(s));

        StdOut.println();
        String toDelete = "cake";
        StdOut.printf("Deleting: %s%n", toDelete);
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));
        StdOut.printf("Original size: %s%n", st.size());
        st.delete(toDelete);
        StdOut.printf("Update size: %s%n", st.size());
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));

        StdOut.println();
        toDelete = "cat";
        StdOut.printf("Deleting: %s%n", toDelete);
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));
        StdOut.printf("Original size: %s%n", st.size());
        st.delete(toDelete);
        StdOut.printf("Update size: %s%n", st.size());
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));

        StdOut.println();
        toDelete = "curtain";
        StdOut.printf("Deleting: %s%n", toDelete);
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));
        StdOut.printf("Original size: %s%n", st.size());
        st.delete(toDelete);
        StdOut.printf("Update size: %s%n", st.size());
        StdOut.printf("Does it contain %s? %s%n", toDelete, st.contains(toDelete));
    }
}
