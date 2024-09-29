package com.segarciat.algs4.ch1.sec3.ex40;

import edu.princeton.cs.algs4.StdIn;

/**
 * <strong>1.3.40</strong>
 */
public class MoveToFront {
    private static class Node {
        char c;
        Node next;
    }

    private static Node moveToFront(Node oldFirst, char c) {
        Node first = new Node();

        first.c = c;
        first.next = oldFirst;

        Node prev = first;
        Node current = oldFirst;

        // remove any duplicates beyond the first
        while (current != null) {
            if (current.c == c)
                prev.next = current.next;
            prev = current;
            current = current.next;
        }

        return first;
    }

    public static void main(String[] args) {
        Node first = null;
        while (!StdIn.isEmpty()) {
            String line = StdIn.readLine();
            for (int i = 0; i < line.length(); i++)
                first = moveToFront(first, line.charAt(i));
        }
        for (Node current = first; current != null; current = current.next)
            System.out.print(current.c + " ");
        System.out.println();
    }
}
