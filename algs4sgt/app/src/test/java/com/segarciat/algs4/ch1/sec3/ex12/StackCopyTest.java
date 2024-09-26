package com.segarciat.algs4.ch1.sec3.ex12;

import edu.princeton.cs.algs4.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StackCopyTest {
    @Test
    void test_copy() {
        Stack<String> stack = new Stack<>();
        stack.push("alpha");
        stack.push("beta");
        stack.push("gamma");

        Stack<String> copy = StackCopy.copy(stack);
        Assertions.assertEquals("gamma", copy.pop());
        Assertions.assertEquals("beta", copy.pop());
        Assertions.assertEquals("alpha", copy.pop());
        Assertions.assertTrue(copy.isEmpty());

        Assertions.assertEquals("gamma", stack.pop());
        Assertions.assertEquals("beta", stack.pop());
        Assertions.assertEquals("alpha", stack.pop());
    }
}