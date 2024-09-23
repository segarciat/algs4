package com.segarciat.algs4.ch1.sec3.ex04;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * <strong>1.3.4)</strong>
 * Write a stack client <code>Parentheses</code> that reads in a text stream from standard
 * input and uses a stack to determine whether its parentheses are properly balanced.
 * For example, your program should print <code>true</code> for [()]{}{[()()]()} and
 * <code>false</code> for <code>[(])</code>.
 */
public class Parentheses {
    public static void main(String[] args) {
        Stack<Character> parens = new Stack<>();
        while (!StdIn.isEmpty()) {
            String input = StdIn.readLine();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                switch (c) {
                    case '[': case '(': case '{':
                        parens.push(c);
                        break;
                    case ']': case ')': case '}':
                        char popped = parens.pop();
                        if (popped == '(' && c != ')' || popped == '[' && c != ']' || popped == '{' && c != '}') {
                            StdOut.println(false);
                            System.exit(0);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        StdOut.println(parens.isEmpty());
    }
}
