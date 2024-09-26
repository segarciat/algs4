package com.segarciat.algs4.ch1.sec3.ex12;

import edu.princeton.cs.algs4.Stack;

/**
 * <strong>1.3.12)</strong>
 * Write an iterable <code>Stack</code> <em>client</em> that has a static
 * method <code>copy()</code> that takes a stack of strings as argument and
 * returns a copy of the stack. <code>Note</code>: This ability is a prime
 * example of the value of having an iterator, because it allows development
 * of such functionality without changing the basic API.
 */
public class StackCopy {
    public static Stack<String> copy(Stack<String> stack) {
        Stack<String> reversed = new Stack<>();
        for (String s: stack)
            reversed.push(s);

        Stack<String> copyStack = new Stack<>();
        for (String s: reversed)
            copyStack.push(s);
        return copyStack;
    }
}
